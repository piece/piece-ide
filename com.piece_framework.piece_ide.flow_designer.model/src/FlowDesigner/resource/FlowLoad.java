// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;

public class FlowLoad extends AbstractLoad {
    @Override
    public void load(EObject eObject,
              Map<?, ?> flowMap
              ) {
        if (!(eObject instanceof Flow)) {
            return;
        }

        Flow flow = (Flow) eObject;

        InitialStateLoad initialStateLoad = new InitialStateLoad();
        initialStateLoad.load(flow, flowMap);

        FinalStateLoad finalStateLoad = new FinalStateLoad();
        finalStateLoad.load(flow, flowMap);
        
        ViewStateLoad viewStateLoad = new ViewStateLoad();
        viewStateLoad.load(flow, flowMap);

        ActionStateLoad actionStateLoad = new ActionStateLoad();
        actionStateLoad.load(flow, flowMap);

        TransitionEventLoad transitionEventLoad = new TransitionEventLoad();
        transitionEventLoad.load(flow, flowMap);
    }
}
