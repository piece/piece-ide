// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

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
        InitialStateLoad initialStateLoad = new InitialStateLoad();
        initialStateLoad.load(flow, flowMap);

        FinalStateLoad finalStateLoad = new FinalStateLoad();
        finalStateLoad.load(flow, flowMap);
        
        ViewStateLoad viewStateLoad = new ViewStateLoad();
        viewStateLoad.load(flow, flowMap);

        ActionStateLoad actionStateLoad = new ActionStateLoad();
        actionStateLoad.load(flow, flowMap);
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
            ArrayList<?> events = (ArrayList<?>) stateAttributes.get("transition");
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
}
