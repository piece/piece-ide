// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

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
        ActionSave actionSave = new ActionSave();
        actionSave.save(stateMap, state);

        TransitionEventSave transitionEventSave = new TransitionEventSave();
        transitionEventSave.save(stateMap, state);
    }
}
