// $Id$

package FlowDesigner.resource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;

abstract class NamedStateListSave extends AbstractSave {
    @Override
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        List<Map<String, Object>> stateList = new ArrayList<Map<String, Object>>();
        for (NamedState state: getStates(flow)) {
            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            NamedStateSave namedStateSave = new NamedStateSave();
            namedStateSave.save(stateMap, state);

            stateList.add(stateMap);
        }
        if (stateList.size() > 0) {
            flowMap.put(getKey(), stateList);
        }
    }

    abstract String getKey();

    abstract boolean equalStateType(NamedState state);

    private List<NamedState> getStates(Flow flow) {
        List<NamedState> stateList = new ArrayList<NamedState>();
        for (NamedState state: flow.getStates()) {
            if (equalStateType(state)
                && !hasTransitionEventToFinalState(state)
                ) {
                stateList.add(state);
            }
        }
        return stateList;
    }

    private boolean hasTransitionEventToFinalState(NamedState state) {
        FinalState finalState = ((Flow) state.eContainer()).getFinalState();
        for (Event event: state.getEvents()) {
            if (event.getNextState() == finalState) {
                return true;
            }
        }
        return false;
    }
}
