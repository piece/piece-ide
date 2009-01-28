// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

public class FinalStateSave extends AbstractSave {
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

        for (NamedState state: getStatesToFinalState(flow)) {
            Map<String, Object> lastStateMap = new LinkedHashMap<String, Object>();
            lastStateMap.put("name", state.getName());
            if (state instanceof ViewState) {
                lastStateMap.put("view", ((ViewState) state).getView());
            }
            ActionSave save = new ActionSave();
            save.save(lastStateMap, state);

            flowMap.put("lastState", lastStateMap);
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
