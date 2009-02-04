// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.Source;
import FlowDesigner.Target;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

class EventLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> eventMap
              ) {
        if (!(eObject instanceof Source)) {
            return;
        }

        Flow flow = (Flow) eObject.eContainer();
        Target nextState = null;
        if (eventMap.get("nextState") != null) {
            nextState = (Target) flow.findStateByName((String) eventMap.get("nextState"));
        } else {
            nextState = flow.getFinalState();
        }
        if (nextState == null) {
            return;
        }

        Event event = FlowDesignerFactoryImpl.eINSTANCE.createEvent();
        event.setEvent((String) eventMap.get("event"));
        event.setNextState(nextState);

        ActionLoad load = new ActionLoad();
        load.load(event, eventMap);

        ((Source) eObject).getEvents().add(event);
    }

}
