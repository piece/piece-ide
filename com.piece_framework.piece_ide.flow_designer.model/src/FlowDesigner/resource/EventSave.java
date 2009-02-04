// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.NamedState;

class EventSave extends AbstractSave {
    @Override
    void save(Map<String, Object> eventMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Event)) {
            return;
        }
        Event event = (Event) eObject;

        eventMap.put("event", event.getEvent());
        eventMap.put("nextState", ((NamedState) event.getNextState()).getName());
        ActionSave actionSave = new ActionSave();
        actionSave.save(eventMap, event);
    }
}
