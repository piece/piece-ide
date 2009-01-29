// $Id$

package FlowDesigner.resource;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

abstract class NamedStateLoad extends AbstractLoad {
    @Override
    @SuppressWarnings("unchecked")
    void load(EObject eObject,
              Map<?, ?> flowMap
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }

        if (flowMap.get(getStateType()) != null) {
            for (Map<?, ?> stateAttributes: (List<Map<?, ?>>) flowMap.get(getStateType())) {
                NamedState state = createNamedState();
                setStateAttributes(state,
                                   stateAttributes
                                   );
                ((Flow) eObject).getStates().add(state);
            }
        }
    }

    abstract NamedState createNamedState();

    abstract String getStateType();

    private void setStateAttributes(NamedState state,
                                    Map<?, ?> stateAttributes
                                    ) {
        for (EAttribute eAttribute : state.eClass().getEAllAttributes()) {
            if (stateAttributes.get(eAttribute.getName()) instanceof String) {
                state.eSet(eAttribute, (String) stateAttributes.get(eAttribute.getName()));
            }
        }

        ActionLoad load = new ActionLoad();
        load.load(state, stateAttributes);
    }
}
