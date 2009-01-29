// $Id$

package FlowDesigner.resource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;

class FinalStateSave extends AbstractSave {
    @Override
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        if (flow.getFinalState() == null) {
            return;
        }

        if (flow.getFinalState().getFinalize() != null) {
            ActionSave actionSave = new ActionSave();
            actionSave.save(flowMap, flow.getFinalState());
        }

        List<Map<String, Object>> stateList = new ArrayList<Map<String, Object>>();
        for (NamedState state: getStatesToFinalState(flow)) {
            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            NamedStateSave save = new NamedStateSave();
            save.save(stateMap, state);
            stateList.add(stateMap);
        }
        if (stateList.size() == 1) {
            flowMap.put("lastState", stateList.get(0));
        } else if (stateList.size() > 1) {
            flowMap.put("lastState", stateList);
        }
    }

    private List<NamedState> getStatesToFinalState(Flow flow) {
        List<NamedState> stateList = new ArrayList<NamedState>();
        for (NamedState state: flow.getStates()) {
            for (Event event: state.getEvents()) {
                if (event.getNextState() == flow.getFinalState()) {
                    stateList.add(state);
                    break;
                }
            }
        }

        return stateList;
    }
}
