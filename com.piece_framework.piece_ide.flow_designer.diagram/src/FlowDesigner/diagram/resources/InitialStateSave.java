// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

public class InitialStateSave extends AbstractSave {
    @Override
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        NamedState firstState = (NamedState) flow.getInitialState().getEvents().get(0).getNextState();
        flowMap.put("firstState",
                    firstState.getName()
                    );
    }
}
