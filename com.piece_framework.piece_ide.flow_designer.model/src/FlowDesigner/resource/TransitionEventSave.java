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

class TransitionEventSave extends AbstractSave {
    @Override
    void save(Map<String, Object> stateMap,
              EObject eObject
              ) {
        if (!(eObject instanceof NamedState)) {
            return;
        }
        NamedState state = (NamedState) eObject;

        List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
        FinalState finalState = ((Flow) state.eContainer()).getFinalState();
        for (Event event: state.getEvents()) {
            if (event.getNextState() == finalState) {
                continue;
            }

            Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
            EventSave eventSave = new EventSave();
            eventSave.save(eventMap, event);

            transitionList.add(eventMap);
        }

        if (transitionList.size() > 0) {
            stateMap.put("transition", transitionList);
        }
    }

}
