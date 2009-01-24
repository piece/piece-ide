// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
                Event event = factory.createEvent();
                event.setName("(FirstState)");
                event.setNextState(eFlow.findStateByName((String) flow.get(key)));
                eFlow.getInitialState().getEvents().add(event);
            } else if (!key.equals("lastState")){
                ArrayList<?> states = (ArrayList<?>) flow.get(key);
                for (HashMap<?, ?> stateElements: states.toArray(new HashMap<?, ?>[0])) {
                    ArrayList<?> events = (ArrayList<?>) stateElements.get("transition");
                    if (events == null) {
                        continue;
                    }

                    NamedState state = eFlow.findStateByName((String) stateElements.get("name"));

                    for (HashMap<?, ?> eventElements: events.toArray(new HashMap<?, ?>[0])) {
                        Event event = factory.createEvent();
                        event.setName((String) eventElements.get("event"));
                        event.setNextState(eFlow.findStateByName((String) eventElements.get("nextState")));

                        HashMap<?, ?> action = (HashMap<?, ?>) eventElements.get("action");
                        HashMap<?, ?> guard = (HashMap<?, ?>) eventElements.get("guard");
                        if (action != null) {
                            event.setAction((String) action.get("method"));
                        }
                        if (guard != null) {
                            event.setGuard((String) guard.get("method"));
                        }

                        state.getEvents().add(event);
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
        viewState.setView((String) stateAttributes.get("view"));
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
        state.setName((String) stateAttributes.get("name"));
        HashMap<?, ?> entry = (HashMap<?, ?>) stateAttributes.get("entry");
        HashMap<?, ?> activity = (HashMap<?, ?>) stateAttributes.get("activity");
        HashMap<?, ?> exit = (HashMap<?, ?>) stateAttributes.get("exit");
        if (entry != null) {
            state.setEntry((String) entry.get("method"));
        }
        if (activity != null) {
            state.setActivity((String) activity.get("method"));
        }
        if (exit != null) {
            state.setExit((String) exit.get("method"));
        }
    }
}
