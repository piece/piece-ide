// $Id$

package FlowDesigner.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;

class TransitionEventLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> flowMap
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }

        Flow flow = (Flow) eObject;
        createEventFromFirstState(flow, flowMap);
        createTransitionEvent(flow, flowMap);
    }

    private void createEventFromFirstState(Flow flow,
                                           Map<?, ?> flowMap
                                           ) {
        if (flowMap.get("firstState") == null) {
            return;
        }

        Map<String, String> eventAttributes = new HashMap<String, String>();
        eventAttributes.put("event", Event.FIRSTSTATE_EVENT);
        eventAttributes.put("nextState", (String) flowMap.get("firstState"));

        EventLoad load = new EventLoad();
        load.load(flow.getInitialState(), eventAttributes);
    }

    private void createTransitionEvent(Flow flow, Map<?, ?> map) {
        for (Map<?, ?> stateAttributes : createStateMap(map)) {
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

    @SuppressWarnings("unchecked")
    private List<Map<?, ?>> createStateMap(Map<?, ?> flowMap) {
        List<Map<?, ?>> viewStateMap = (List<Map<?, ?>>) flowMap.get("viewState");
        List<Map<?, ?>> actionStateMap = (List<Map<?, ?>>) flowMap.get("actionState");
        List<Map<?, ?>> stateList = new ArrayList<Map<?, ?>>();
        if (viewStateMap != null) {
            stateList.addAll(viewStateMap);
        }
        if (actionStateMap != null) {
            stateList.addAll(actionStateMap);
        }

        return stateList;
    }

}
