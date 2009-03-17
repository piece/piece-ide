// $Id$

package FlowDesigner.resource;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.impl.FlowDesignerPackageImpl;

class ActionSave extends AbstractSave {
    @Override
    void save(Map<String, Object> map,
              EObject eObject
              ) {
        Map<String, Object> actionMap = new LinkedHashMap<String, Object>();
        for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                if (eObject.eGet(eAttribute) == null
                    || ((String) eObject.eGet(eAttribute)).equals("")) {
                    continue;
                }

                Map<String, String> methodMap = new LinkedHashMap<String, String>();
                methodMap.put("method", (String) eObject.eGet(eAttribute));
                actionMap.put(getActionName(eAttribute.getName()),
                              methodMap
                              );
            }
        }

        String[] keys = {"entry",
                         "activity",
                         "exit",
                         "action",
                         "guard",
                         "initial",
                         "final"
                         };
        for (String key: keys) {
            if (actionMap.get(key) != null) {
                ((Map<String, Object>) map).put(key, actionMap.get(key));
            }
        }
    }

    private String getActionName(String attributeName) {
        Map<String, String> actionMap = new HashMap<String, String>();
        actionMap.put("initialize", "initial");
        actionMap.put("finalize", "final");

        if (actionMap.containsKey(attributeName)) {
            return actionMap.get(attributeName);
        }
        return attributeName;
    }
}
