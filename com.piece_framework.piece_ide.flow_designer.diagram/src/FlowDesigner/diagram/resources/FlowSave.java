// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

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
                        Map<String, Object> lastState = new LinkedHashMap<String, Object>();
                        lastState.put("name", state.getName());
                        if (state instanceof ViewState) {
                            lastState.put("view", ((ViewState) state).getView());
                        }
                        if (state.getEntry() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getEntry());
                            lastState.put("entry", action);
                        }
                        if (state.getActivity() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getActivity());
                            lastState.put("activity", action);
                        }
                        if (state.getExit() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getExit());
                            lastState.put("exit", action);
                        }

                        flowMap.put("lastState", lastState);
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
            if (state.getEntry() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getEntry());
                stateMap.put("entry", action);
            }
            if (state.getActivity() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getActivity());
                stateMap.put("activity", action);
            }
            if (state.getExit() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getExit());
                stateMap.put("exit", action);
            }
            List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
            for (Event event: state.getEvents()) {
                Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
                eventMap.put("event", event.getName());
                eventMap.put("nextState", ((NamedState) event.getNextState()).getName());
                if (event.getAction() != null) {
                    Map<String, String> action = new LinkedHashMap<String, String>();
                    action.put("method", event.getAction());
                    eventMap.put("action", action);
                }
                if (event.getGuard() != null) {
                    Map<String, String> action = new LinkedHashMap<String, String>();
                    action.put("method", event.getGuard());
                    eventMap.put("guard", action);
                }

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
}
