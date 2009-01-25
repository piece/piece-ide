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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.Target;
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
        Flow eFlow = FlowDesignerFactoryImpl.eINSTANCE.createFlow();
        try {
            HashMap<?, ?> flow = Yaml.loadType(inputStream, HashMap.class);
            createStates(eFlow, flow);
            createEvents(eFlow, flow);
        } catch (YamlException e) {
            throw new IOException(e);
        } finally {
            getContents().add(eFlow);
        }
    }

    @Override
    protected void doSave(OutputStream outputStream,
                          Map<?, ?> options
                          ) throws IOException {
        // TODO Auto-generated method stub
        super.doSave(outputStream, options);
    }

    private void createStates(Flow eFlow,
                              HashMap<?, ?> flow
                              ) {
        if (flow.get("lastState") != null) {
            FinalState finalState = FlowDesignerFactoryImpl.eINSTANCE.createFinalState();
            eFlow.setFinalState(finalState);

            for (HashMap<?, ?> stateAttributes: getStateList(flow, "lastState")) {
                if (stateAttributes.containsKey("view")) {
                    eFlow.getStates().add(createViewState(stateAttributes));
                } else {
                    eFlow.getStates().add(createActionState(stateAttributes));
                }
            }
        }
        if (flow.get("viewState") != null) {
            for (HashMap<?, ?> stateAttributes: getStateList(flow, "viewState")) {
                eFlow.getStates().add(createViewState(stateAttributes));
            }
        }
        if (flow.get("actionState") != null) {
            for (HashMap<?, ?> stateAttributes: getStateList(flow, "actionState")) {
                eFlow.getStates().add(createActionState(stateAttributes));
            }
        }

        if (flow.get("initial") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("initialize", (HashMap<?, ?>) flow.get("initial"));
            setAction(eFlow.getInitialState(),
                      action
                      );
        }
        if (flow.get("final") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("finalize", (HashMap<?, ?>) flow.get("final"));
            setAction(eFlow.getFinalState(),
                      action
                      );
        }
    }

    private ArrayList<HashMap<?, ?>> getStateList(HashMap<?, ?> flow, Object key) {
        if (flow.get(key) == null) {
            return null;
        }

        ArrayList<HashMap<?, ?>> states = null;
        if (flow.get(key) instanceof HashMap) {
            ArrayList<HashMap<?, ?>> list = new ArrayList<HashMap<?, ?>>();
            HashMap<?, ?> stateAttributes = (HashMap<?, ?>) flow.get(key);
            list.add(stateAttributes);
            states = list;
        } else {
            ArrayList<HashMap<?, ?>> list = new ArrayList<HashMap<?, ?>>();
            list.addAll((ArrayList<HashMap<?, ?>>) flow.get(key));
            states = list;
        }
        return states;
    }

    private void createEvents(Flow eFlow, HashMap<?, ?> flow) {
        if (flow.get("firstState") != null) {
            HashMap<String, String> eventAttributes = new HashMap<String, String>();
            eventAttributes.put("event", "(FirstState)");
            eventAttributes.put("nextState", (String) flow.get("firstState"));

            Event event = createEvent(eventAttributes, eFlow);
            if (event != null) {
                eFlow.getInitialState().getEvents().add(event);
            }
        }

        ArrayList<HashMap<?, ?>> viewStates = getStateList(flow, "viewState");
        ArrayList<HashMap<?, ?>> actionStates = getStateList(flow, "actionState");
        ArrayList<HashMap<?, ?>> states = new ArrayList<HashMap<?, ?>>();
        if (viewStates != null) {
            states.addAll(viewStates);
        }
        if (actionStates != null) {
            states.addAll(actionStates);
        }
        for (HashMap<?, ?> stateAttributes: states) {
            ArrayList<?> events = (ArrayList<?>) stateAttributes.get("transition");
            if (events == null) {
                continue;
            }

            NamedState state = eFlow.findStateByName((String) stateAttributes.get("name"));
            for (HashMap<?, ?> eventAttributes: events.toArray(new HashMap<?, ?>[0])) {
                Event event = createEvent(eventAttributes, eFlow);
                if (event != null) {
                    state.getEvents().add(event);
                }
            }
        }
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
            }
        }

        setAction(state, stateAttributes);
    }

    private Event createEvent(HashMap<?, ?> eventAttributes,
                              Flow eFlow
                              ) {
        Target nextState = (Target) eFlow.findStateByName((String) eventAttributes.get("nextState"));
        if (nextState == null) {
            return null;
        }
        Event event = FlowDesignerFactoryImpl.eINSTANCE.createEvent();
        event.setName((String) eventAttributes.get("event"));
        event.setNextState(nextState);

        setAction(event, eventAttributes);

        return event;
    }

    private void setAction(EObject object,
                           HashMap<?, ?> attributes
                           ) {
        for (EAttribute eAttribute: object.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                HashMap<?, ?> action = (HashMap<?, ?>) attributes.get(eAttribute.getName());
                if (action != null) {
                    object.eSet(eAttribute,
                                (String) action.get("method")
                                );
                }
            }
        }
    }
}
