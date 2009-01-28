// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;

class FlowSave extends AbstractSave {
    @Override
    void save(Map<String, Object> flowMap,
              EObject eObject
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }
        Flow flow = (Flow) eObject;

        InitialStateSave initialStateSave = new InitialStateSave();
        initialStateSave.save(flowMap, flow);

        FinalStateSave finalStateSave = new FinalStateSave();
        finalStateSave.save(flowMap, flow);

        ViewStateListSave viewStateListSave = new ViewStateListSave();
        viewStateListSave.save(flowMap, flow);

        ActionStateListSave actionStateListSave = new ActionStateListSave();
        actionStateListSave.save(flowMap, flow);
    }
}
