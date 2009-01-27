// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;

class FlowLoad extends AbstractLoad {
    @Override
    void load(EObject eObject,
              Map<?, ?> map
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }

        Flow flow = (Flow) eObject;

        InitialStateLoad initialStateLoad = new InitialStateLoad();
        initialStateLoad.load(flow, map);

        FinalStateLoad finalStateLoad = new FinalStateLoad();
        finalStateLoad.load(flow, map);
        
        ViewStateLoad viewStateLoad = new ViewStateLoad();
        viewStateLoad.load(flow, map);

        ActionStateLoad actionStateLoad = new ActionStateLoad();
        actionStateLoad.load(flow, map);

        TransitionEventLoad transitionEventLoad = new TransitionEventLoad();
        transitionEventLoad.load(flow, map);
    }
}
