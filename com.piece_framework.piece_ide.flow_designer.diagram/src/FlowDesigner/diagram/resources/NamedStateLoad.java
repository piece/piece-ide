// $Id$

package FlowDesigner.diagram.resources;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

abstract class NamedStateLoad extends AbstractLoad {
    @Override
    @SuppressWarnings("unchecked")
    void load(EObject object,
              Map<?, ?> map
              ) {
        if (!(object instanceof Flow)) {
            return;
        }

        if (map.get(getStateType()) != null) {
            for (Map<?, ?> stateAttributes: (List<Map<?, ?>>) map.get(getStateType())) {
                NamedState state = createNamedState();
                setStateAttributes(state,
                                   stateAttributes
                                   );
                ((Flow) object).getStates().add(state);
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
