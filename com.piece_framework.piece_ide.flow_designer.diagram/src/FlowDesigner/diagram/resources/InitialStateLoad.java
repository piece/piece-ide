// $Id$

package FlowDesigner.diagram.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;

public class InitialStateLoad extends AbstractLoad {
    @Override
    void load(EObject object,
              Map<?, ?> map
              ) {
        if (!(object instanceof Flow)) {
            return;
        }
        if (map.get("initial") != null) {
            Map<String, Map<?, ?>> action = new HashMap<String, Map<?, ?>>();
            action.put("initialize", (Map<?, ?>) map.get("initial"));

            ActionLoad load = new ActionLoad();
            load.load(((Flow) object).getInitialState(), action);
        }
    }
}
