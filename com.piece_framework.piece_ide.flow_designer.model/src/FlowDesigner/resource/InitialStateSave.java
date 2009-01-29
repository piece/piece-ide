// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

class InitialStateSave extends AbstractSave {
    @Override
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        if (flow.getInitialState().getEvents().size() == 0) {
            return;
        }

        if (flow.getInitialState().getInitialize() != null) {
            ActionSave actionSave = new ActionSave();
            actionSave.save(flowMap, flow.getInitialState());
        }

        NamedState firstState = (NamedState) flow.getInitialState().getEvents().get(0).getNextState();
        flowMap.put("firstState",
                    firstState.getName()
                    );
    }
}
