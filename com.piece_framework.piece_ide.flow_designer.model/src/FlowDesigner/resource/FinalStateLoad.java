// $Id$

package FlowDesigner.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

class FinalStateLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> flowMap
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        if (flowMap.get("lastState") == null) {
            return;
        }

        Flow flow = (Flow) eObject;

        FinalState finalState = FlowDesignerFactoryImpl.eINSTANCE.createFinalState();
        flow.setFinalState(finalState);

        Map<String, List<Map<?, ?>>> lastStateMap = createStateTypeMap(flowMap);

        ViewStateLoad viewStateLoad = new ViewStateLoad();
        viewStateLoad.load(flow, lastStateMap);

        ActionStateLoad actionStateLoad = new ActionStateLoad();
        actionStateLoad.load(flow, lastStateMap);

        createEventToFinalState(flow, lastStateMap);

        if (flowMap.get("final") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("finalize", (HashMap<?, ?>) flowMap.get("final"));

            ActionLoad load = new ActionLoad();
            load.load(flow.getFinalState(), action);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, List<Map<?, ?>>> createStateTypeMap(Map<?, ?> flowMap) {
        List<Map<?, ?>> stateList = null;
        if (flowMap.get("lastState") instanceof Map) {
            stateList = new ArrayList<Map<?, ?>>();
            stateList.add((Map<?, ?>) flowMap.get("lastState"));
        } else if (flowMap.get("lastState") instanceof List) {
            stateList = (List<Map<?, ?>>) flowMap.get("lastState");
        }

        Map<String, List<Map<?, ?>>> stateTypeMap = new HashMap<String, List<Map<?, ?>>>();
        List<Map<?, ?>> viewStateList = new ArrayList<Map<?, ?>>();
        List<Map<?, ?>> actionStateList = new ArrayList<Map<?, ?>>();
        for (Map<?, ?> stateAttributes: stateList) {
            if (stateAttributes.containsKey("view")) {
                viewStateList.add(stateAttributes);
            } else {
                actionStateList.add(stateAttributes);
            }
        }

        if (viewStateList.size() > 0) {
            stateTypeMap.put("viewState", viewStateList);
        }
        if (actionStateList.size() > 0) {
            stateTypeMap.put("actionState", actionStateList);
        }

        return stateTypeMap;
    }

    private void createEventToFinalState(Flow flow,
                                         Map<String, List<Map<?, ?>>> lastStateMap
                                         ) {
        for (String stateType: lastStateMap.keySet()) {
            for (Map<?, ?> stateAttributes: lastStateMap.get(stateType)) {
                NamedState state = flow.findStateByName((String) stateAttributes.get("name"));
                Map<String, String> eventAttributes = new HashMap<String, String>();
                eventAttributes.put("event", Event.LASTSTATE_EVENT);
                EventLoad load = new EventLoad();
                load.load(state, eventAttributes);
            }
        }
    }
}
