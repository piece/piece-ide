// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;
import FlowDesigner.impl.FlowDesignerPackageImpl;

public class FlowSave extends AbstractSave {
    @Override
    void save(Map<?, ?> map,
              EObject object
              ) {
        if (!(object instanceof Flow)) {
            return;
        }
        Flow eFlow = (Flow) object;
        Map<String, Object> flowMap = (Map<String, Object>) map;

        NamedState firstState = (NamedState) eFlow.getInitialState().getEvents().get(0).getNextState();
        flowMap.put("firstState", firstState.getName());

        if (eFlow.getFinalState() != null) {
            for (NamedState state: eFlow.getStates()) {
                for (Event event: state.getEvents()) {
                    if (event.getNextState() == eFlow.getFinalState()) {
                        Map<String, Object> lastStateMap = new LinkedHashMap<String, Object>();
                        lastStateMap.put("name", state.getName());
                        if (state instanceof ViewState) {
                            lastStateMap.put("view", ((ViewState) state).getView());
                        }
                        setAction(lastStateMap, state);

                        flowMap.put("lastState", lastStateMap);
                    }
                }
            }
        }

        List<Map<String, Object>> viewStateList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> actionStateList = new ArrayList<Map<String, Object>>();
        for (NamedState state: eFlow.getStates()) {
            boolean hasFinalState = false;
            for (Event event: state.getEvents()) {
                if (event.getNextState() == eFlow.getFinalState()) {
                    hasFinalState = true;
                    break;
                }
            }
            if (hasFinalState) {
                continue;
            }

            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            stateMap.put("name", state.getName());
            if (state instanceof ViewState) {
                stateMap.put("view", ((ViewState) state).getView());
            }
            setAction(stateMap, state);

            List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
            for (Event event: state.getEvents()) {
                Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
                eventMap.put("event", event.getName());
                eventMap.put("nextState", ((NamedState) event.getNextState()).getName());
                setAction(eventMap, event);

                transitionList.add(eventMap);
            }
            if (transitionList.size() > 0) {
                stateMap.put("transition", transitionList);
            }

            if (state instanceof ViewState) {
                viewStateList.add(stateMap);
            } else if (state instanceof ActionState) {
                actionStateList.add(stateMap);
            }
        }
        if (viewStateList.size() > 0) {
            flowMap.put("viewState", viewStateList);
        }
        if (actionStateList.size() > 0) {
            flowMap.put("actionState", actionStateList);
        }
    }

    private void setAction(Map<String, Object> map,
                           EObject eObject
                           ) {
        Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
        for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                if (eObject.eGet(eAttribute) == null) {
                    continue;
                }

                Map<String, String> methodMap = new LinkedHashMap<String, String>();
                methodMap.put("method", (String) eObject.eGet(eAttribute));
                actionMap.put(eAttribute.getName(),
                              methodMap
                              );
            }
        }

        for (String key: new String[]{"entry", "activity", "exit", "action", "guard"}) {
            if (actionMap.get(key) != null) {
                map.put(key, actionMap.get(key));
            }
        }
    }
}
