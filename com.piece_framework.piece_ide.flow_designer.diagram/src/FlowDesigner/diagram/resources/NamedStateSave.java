// $Id$

package FlowDesigner.diagram.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

class NamedStateSave extends AbstractSave {
    @Override
    void save(Map<String, Object> stateMap,
              EObject eObject
              ) {
        if (!(eObject instanceof NamedState)) {
            return;
        }
        NamedState state = (NamedState) eObject;

        stateMap.put("name", state.getName());
        if (state instanceof ViewState) {
            stateMap.put("view", ((ViewState) state).getView());
        }
        ActionSave save = new ActionSave();
        save.save(stateMap, state);

        List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
        FinalState finalState = ((Flow) state.eContainer()).getFinalState();
        for (Event event: state.getEvents()) {
            if (event.getNextState() == finalState) {
                continue;
            }

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
    }
}
