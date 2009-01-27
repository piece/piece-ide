// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.impl.FlowDesignerPackageImpl;

class ActionLoad extends AbstractLoad {
    @Override
    void load(EObject object,
              Map<?, ?> map
              ) {
        for (EAttribute eAttribute : object.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                Map<?, ?> action = (Map<?, ?>) map.get(eAttribute.getName());
                if (action != null) {
                    object.eSet(eAttribute, (String) action.get("method"));
                }
            }
        }
    }
}
