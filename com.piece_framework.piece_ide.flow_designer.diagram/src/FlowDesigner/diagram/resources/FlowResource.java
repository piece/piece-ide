// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.ho.yaml.Yaml;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerFactory;
import FlowDesigner.InitialState;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

public class FlowResource extends ResourceImpl {
    public FlowResource() {
        super();
    }

    public FlowResource(URI uri) {
        super(uri);
    }

    @Override
    protected void doLoad(InputStream inputStream,
                          Map<?, ?> options
                          ) throws IOException {
        FlowDesignerFactory factory = FlowDesignerFactoryImpl.eINSTANCE;
        Flow eFlow = factory.createFlow();

        HashMap<?, ?> flow = Yaml.loadType(inputStream, HashMap.class);
        for (Object key: flow.keySet()) {
            if (key.equals("firstState")) {
                InitialState initialState = factory.createInitialState();
                eFlow.setInitialState(initialState);
            } else if (key.equals("lastState")) {
                FinalState finalState = factory.createFinalState();
                eFlow.setFinalState(finalState);

                HashMap<?, ?> stateElements = (HashMap<?, ?>) flow.get(key);
                if (stateElements.containsKey("view")) {
                    ViewState viewState = factory.createViewState();
                    viewState.setName((String) stateElements.get("name"));
                    viewState.setView((String) stateElements.get("view"));
                    eFlow.getStates().add(viewState);
                } else {
                    ActionState actionState = factory.createActionState();
                    actionState.setName((String) stateElements.get("name"));
                    eFlow.getStates().add(actionState);
                }
            }
        }

        for (Object key: flow.keySet()) {
            if (key.equals("firstState")) {
                for (NamedState state: eFlow.getStates()) {
                    if (state.getName().equals(flow.get(key))) {
                        Event event = factory.createEvent();
                        event.setName("(FirstState)");
                        event.setNextState(state);
                        eFlow.getInitialState().getEvents().add(event);
                    }
                }
            }
        }

        getContents().add(eFlow);
    }

    @Override
    protected void doSave(OutputStream outputStream,
                          Map<?, ?> options
                          ) throws IOException {
        // TODO Auto-generated method stub
        super.doSave(outputStream, options);
    }
}
