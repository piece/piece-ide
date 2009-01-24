// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
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
import FlowDesigner.impl.FlowDesignerPackageImpl;

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

                HashMap<?, ?> stateAttributes = (HashMap<?, ?>) flow.get(key);
                if (stateAttributes.containsKey("view")) {
                    eFlow.getStates().add(createViewState(stateAttributes));
                } else {
                    eFlow.getStates().add(createActionState(stateAttributes));
                }
            } else {
                ArrayList<?> states = (ArrayList<?>) flow.get(key);
                for (HashMap<?, ?> stateAttributes: states.toArray(new HashMap<?, ?>[0])) {
                    if (key.equals("viewState")) {
                        eFlow.getStates().add(createViewState(stateAttributes));
                    } else if (key.equals("actionState")) {
                        eFlow.getStates().add(createActionState(stateAttributes));
                    }
                }
            }
        }

        for (Object key: flow.keySet()) {
            if (key.equals("firstState")) {
                HashMap<String, String> eventAttributes = new HashMap<String, String>();
                eventAttributes.put("event", "(FirstState)");
                eventAttributes.put("nextState", (String) flow.get(key));
                eFlow.getInitialState().getEvents().add(createEvent(eventAttributes, eFlow));
            } else if (!key.equals("lastState")){
                ArrayList<?> states = (ArrayList<?>) flow.get(key);
                for (HashMap<?, ?> stateAttributes: states.toArray(new HashMap<?, ?>[0])) {
                    ArrayList<?> events = (ArrayList<?>) stateAttributes.get("transition");
                    if (events == null) {
                        continue;
                    }

                    NamedState state = eFlow.findStateByName((String) stateAttributes.get("name"));
                    for (HashMap<?, ?> eventAttributes: events.toArray(new HashMap<?, ?>[0])) {
                        state.getEvents().add(createEvent(eventAttributes, eFlow));
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

    private ViewState createViewState(HashMap<?, ?> stateAttributes) {
        ViewState viewState = FlowDesignerFactoryImpl.eINSTANCE.createViewState();
        setStateAttributes(viewState, stateAttributes);
        return viewState;
    }

    private ActionState createActionState(HashMap<?, ?> stateAttributes) {
        ActionState actionState = FlowDesignerFactoryImpl.eINSTANCE.createActionState();
        setStateAttributes(actionState, stateAttributes);
        return actionState;
    }

    private void setStateAttributes(NamedState state,
                                    HashMap<?, ?> stateAttributes
                                    ) {
        for (EAttribute eAttribute: state.eClass().getEAllAttributes()) {
            if (stateAttributes.get(eAttribute.getName()) instanceof String) {
                state.eSet(eAttribute,
                           (String) stateAttributes.get(eAttribute.getName())
                           );
            } else if (stateAttributes.get(eAttribute.getName()) instanceof HashMap) {
                HashMap<?, ?> action = (HashMap<?, ?>) stateAttributes.get(eAttribute.getName());
                if (action != null) {
                    state.eSet(eAttribute,
                               (String) action.get("method")
                               );
                }
            }
        }
    }

    private Event createEvent(HashMap<?, ?> eventAttributes,
                              Flow eFlow
                              ) {
        Event event = FlowDesignerFactoryImpl.eINSTANCE.createEvent();
        event.setName((String) eventAttributes.get("event"));
        event.setNextState(eFlow.findStateByName((String) eventAttributes.get("nextState")));

        for (EAttribute eAttribute: event.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                HashMap<?, ?> action = (HashMap<?, ?>) eventAttributes.get(eAttribute.getName());
                if (action != null) {
                    event.eSet(eAttribute,
                               (String) action.get("method")
                               );
                }
            }
        }

        return event;
    }
}
