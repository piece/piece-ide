// $Id$

package FlowDesigner.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;

class InitialStateLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> flowMap
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        if (flowMap.get("initial") != null) {
            Map<String, Map<?, ?>> action = new HashMap<String, Map<?, ?>>();
            action.put("initialize", (Map<?, ?>) flowMap.get("initial"));

            ActionLoad load = new ActionLoad();
            load.load(((Flow) eObject).getInitialState(), action);
        }
    }
}
