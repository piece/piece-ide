// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;
import FlowDesigner.impl.FlowDesignerPackageImpl;

class FlowLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> map
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }

        createStates((Flow) eObject, map);
        createEvents((Flow) eObject, map);
    }

    private void createStates(Flow flow,
                              Map<?, ?> flowMap
                              ) {
        Map<String, EClass> stateMap = new HashMap<String, EClass>();
        stateMap.put("viewState", FlowDesignerPackageImpl.eINSTANCE.getViewState());
        stateMap.put("actionState", FlowDesignerPackageImpl.eINSTANCE.getActionState());

        if (flowMap.get("lastState") != null) {
            FinalState finalState = FlowDesignerFactoryImpl.eINSTANCE.createFinalState();
            flow.setFinalState(finalState);

            for (Map<?, ?> stateAttributes : getStateList(flowMap, "lastState")) {
                String stateType = stateAttributes.containsKey("view") ?
                                   "viewState" : "actionState";

                NamedState state = (NamedState) FlowDesignerFactoryImpl.eINSTANCE.create(stateMap.get(stateType));
                setStateAttributes(state,
                                   stateAttributes
                                   );
                flow.getStates().add(state);

                Map<String, String> eventAttributes = new HashMap<String, String>();
                eventAttributes.put("event", "FinalStateFrom" + state.getName());
                EventLoad load = new EventLoad();
                load.load(state, eventAttributes);
            }
        }

        for (String stateType: stateMap.keySet()) {
            if (flowMap.get(stateType) == null) {
                continue;
            }

            for (Map<?, ?> stateAttributes : getStateList(flowMap, stateType)) {
                NamedState state = (NamedState) FlowDesignerFactoryImpl.eINSTANCE.create(stateMap.get(stateType));
                setStateAttributes(state,
                                   stateAttributes
                                   );
                flow.getStates().add(state);
            }
        }

        if (flowMap.get("initial") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("initialize", (HashMap<?, ?>) flowMap.get("initial"));
            
            ActionLoad load = new ActionLoad();
            load.load(flow.getInitialState(), action);
        }
        if (flowMap.get("final") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("finalize", (HashMap<?, ?>) flowMap.get("final"));

            ActionLoad load = new ActionLoad();
            load.load(flow.getFinalState(), action);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<?, ?>> getStateList(Map<?, ?> flow,
                                         Object key
                                         ) {
        if (flow.get(key) == null) {
            return null;
        }

        ArrayList<Map<?, ?>> states = null;
        if (flow.get(key) instanceof HashMap) {
            ArrayList<Map<?, ?>> list = new ArrayList<Map<?, ?>>();
            HashMap<?, ?> stateAttributes = (HashMap<?, ?>) flow.get(key);
            list.add(stateAttributes);
            states = list;
        } else {
            ArrayList<Map<?, ?>> list = new ArrayList<Map<?, ?>>();
            list.addAll((ArrayList<HashMap<?, ?>>) flow.get(key));
            states = list;
        }
        return states;
    }

    private void createEvents(Flow flow,
                              Map<?, ?> flowMap
                              ) {
        if (flowMap.get("firstState") != null) {
            HashMap<String, String> eventAttributes = new HashMap<String, String>();
            eventAttributes.put("event", "(FirstState)");
            eventAttributes.put("nextState", (String) flowMap.get("firstState"));

            EventLoad load = new EventLoad();
            load.load(flow.getInitialState(), eventAttributes);
        }

        List<Map<?, ?>> viewStates = getStateList(flowMap, "viewState");
        List<Map<?, ?>> actionStates = getStateList(flowMap, "actionState");
        List<Map<?, ?>> states = new ArrayList<Map<?, ?>>();
        if (viewStates != null) {
            states.addAll(viewStates);
        }
        if (actionStates != null) {
            states.addAll(actionStates);
        }
        for (Map<?, ?> stateAttributes : states) {
            ArrayList<?> events = (ArrayList<?>) stateAttributes
                    .get("transition");
            if (events == null) {
                continue;
            }

            NamedState state = flow.findStateByName((String) stateAttributes.get("name"));
            for (HashMap<?, ?> eventAttributes : events.toArray(new HashMap<?, ?>[0])) {
                EventLoad load = new EventLoad();
                load.load(state, eventAttributes);
            }
        }
    }

    private void setStateAttributes(NamedState state,
                                    Map<?, ?> stateAttributes
                                    ) {
        for (EAttribute eAttribute : state.eClass().getEAllAttributes()) {
            if (stateAttributes.get(eAttribute.getName()) instanceof String) {
                state.eSet(eAttribute, (String) stateAttributes.get(eAttribute.getName()));
            }
        }

        ActionLoad load = new ActionLoad();
        load.load(state, stateAttributes);
    }
}
