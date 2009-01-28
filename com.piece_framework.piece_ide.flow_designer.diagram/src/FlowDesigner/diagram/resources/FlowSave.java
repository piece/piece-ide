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
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        InitialStateSave initialStateSave = new InitialStateSave();
        initialStateSave.save(flowMap, flow);

        FinalStateSave finalStateSave = new FinalStateSave();
        finalStateSave.save(flowMap, flow);

        List<Map<String, Object>> viewStateList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> actionStateList = new ArrayList<Map<String, Object>>();
        for (NamedState state: flow.getStates()) {
            boolean hasFinalState = false;
            for (Event event: state.getEvents()) {
                if (event.getNextState() == flow.getFinalState()) {
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
            ActionSave save = new ActionSave();
            save.save(stateMap, state);

            List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
            for (Event event: state.getEvents()) {
                Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
                eventMap.put("event", event.getName());
                eventMap.put("nextState", ((NamedState) event.getNextState()).getName());
                ActionSave actionSave = new ActionSave();
                actionSave.save(eventMap, event);

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
