// $Id$

package FlowDesigner.diagram.resources;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.impl.FlowDesignerPackageImpl;

public class ActionSave extends AbstractSave {
    @Override
    @SuppressWarnings("unchecked")
    void save(Map<?, ?> map,
              EObject eObject
              ) {
        Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
        for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                if (eObject.eGet(eAttribute) == null) {
                    continue;
                }

                Map<String, String> methodMap = new LinkedHashMap<String, String>();
                methodMap.put("method", (String) eObject.eGet(eAttribute));
                actionMap.put(eAttribute.getName(),
                              methodMap
                              );
            }
        }

        for (String key: new String[]{"entry", "activity", "exit", "action", "guard"}) {
            if (actionMap.get(key) != null) {
                ((Map<String, Object>) map).put(key, actionMap.get(key));
            }
        }
    }

}
