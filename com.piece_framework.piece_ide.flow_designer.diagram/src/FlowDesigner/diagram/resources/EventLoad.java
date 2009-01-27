package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.Source;
import FlowDesigner.Target;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

public class EventLoad extends AbstractLoad {
    @Override
    void load(EObject object,
              Map<?, ?> map
              ) {
        if (!(object instanceof Source)) {
            return;
        }

        Flow flow = (Flow) object.eContainer();
        Target nextState = null;
        if (map.get("nextState") != null) {
            nextState = (Target) flow.findStateByName((String) map.get("nextState"));
        } else {
            nextState = flow.getFinalState();
        }
        if (nextState == null) {
            return;
        }

        Event event = FlowDesignerFactoryImpl.eINSTANCE.createEvent();
        event.setName((String) map.get("event"));
        event.setNextState(nextState);

        ActionLoad load = new ActionLoad();
        load.load(event, map);

        ((Source) object).getEvents().add(event);
    }

}
