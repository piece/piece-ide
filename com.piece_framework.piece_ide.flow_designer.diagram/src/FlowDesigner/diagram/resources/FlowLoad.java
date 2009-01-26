// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerFactory;
import FlowDesigner.NamedState;
import FlowDesigner.Target;
import FlowDesigner.ViewState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;
import FlowDesigner.impl.FlowDesignerPackageImpl;
import FlowDesigner.impl.ViewStateImpl;

public class FlowLoad {
    private FlowDesignerFactory fFactory;
    private Flow fFlow;

    public FlowLoad() {
        fFactory = FlowDesignerFactoryImpl.eINSTANCE;
    }

    public void load(InputStream inputStream) throws IOException {
        fFlow = fFactory.createFlow();
        try {
            HashMap<?, ?> flow = Yaml.loadType(inputStream, HashMap.class);
            createStates(flow);
            createEvents(flow);
        } catch (YamlException e) {
            throw new IOException(e);
        }
    }

    public Flow getFlow() {
        return fFlow;
    }

    private void createStates(HashMap<?, ?> flow) {
        Map<String, EClass> stateMap = new HashMap<String, EClass>();
        stateMap.put("viewState", FlowDesignerPackageImpl.eINSTANCE.getViewState());
        stateMap.put("actionState", FlowDesignerPackageImpl.eINSTANCE.getActionState());

        if (flow.get("lastState") != null) {
            FinalState finalState = fFactory.createFinalState();
            fFlow.setFinalState(finalState);

            for (HashMap<?, ?> stateAttributes : getStateList(flow, "lastState")) {
                String stateType = stateAttributes.containsKey("view") ?
                                   "viewState" : "actionSatte";

                NamedState state = (NamedState) fFactory.create(stateMap.get(stateType));
                setStateAttributes(state,
                                   stateAttributes
                                   );
                Event event = fFactory.createEvent();
                event.setName("FinalStateFrom" + state.getName());
                event.setNextState(finalState);
                state.getEvents().add(event);

                fFlow.getStates().add(state);
            }
        }

        for (String stateType: stateMap.keySet()) {
            if (flow.get(stateType) == null) {
                continue;
            }

            for (HashMap<?, ?> stateAttributes : getStateList(flow, stateType)) {
                NamedState state = (NamedState) fFactory.create(stateMap.get(stateType));
                setStateAttributes(state,
                                   stateAttributes
                                   );
                fFlow.getStates().add(state);
            }
        }

        if (flow.get("initial") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("initialize", (HashMap<?, ?>) flow.get("initial"));
            setAction(fFlow.getInitialState(), action);
        }
        if (flow.get("final") != null) {
            HashMap<String, HashMap<?, ?>> action = new HashMap<String, HashMap<?, ?>>();
            action.put("finalize", (HashMap<?, ?>) flow.get("final"));
            setAction(fFlow.getFinalState(), action);
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<HashMap<?, ?>> getStateList(HashMap<?, ?> flow,
                                                  Object key
                                                  ) {
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

    private void createEvents(HashMap<?, ?> flow) {
        if (flow.get("firstState") != null) {
            HashMap<String, String> eventAttributes = new HashMap<String, String>();
            eventAttributes.put("event", "(FirstState)");
            eventAttributes.put("nextState", (String) flow.get("firstState"));

            Event event = createEvent(eventAttributes);
            if (event != null) {
                fFlow.getInitialState().getEvents().add(event);
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
        for (HashMap<?, ?> stateAttributes : states) {
            ArrayList<?> events = (ArrayList<?>) stateAttributes
                    .get("transition");
            if (events == null) {
                continue;
            }

            NamedState state = fFlow.findStateByName((String) stateAttributes.get("name"));
            for (HashMap<?, ?> eventAttributes : events
                    .toArray(new HashMap<?, ?>[0])) {
                Event event = createEvent(eventAttributes);
                if (event != null) {
                    state.getEvents().add(event);
                }
            }
        }
    }

    private void setStateAttributes(NamedState state,
                                    HashMap<?, ?> stateAttributes
                                    ) {
        for (EAttribute eAttribute : state.eClass().getEAllAttributes()) {
            if (stateAttributes.get(eAttribute.getName()) instanceof String) {
                state.eSet(eAttribute, (String) stateAttributes.get(eAttribute.getName()));
            }
        }

        setAction(state, stateAttributes);
    }

    private Event createEvent(HashMap<?, ?> eventAttributes) {
        Target nextState = (Target) fFlow.findStateByName((String) eventAttributes.get("nextState"));
        if (nextState == null) {
            return null;
        }
        Event event = fFactory.createEvent();
        event.setName((String) eventAttributes.get("event"));
        event.setNextState(nextState);

        setAction(event, eventAttributes);

        return event;
    }

    private void setAction(EObject object, HashMap<?, ?> attributes) {
        for (EAttribute eAttribute : object.eClass().getEAllAttributes()) {
            if (eAttribute.getEType() == FlowDesignerPackageImpl.eINSTANCE.getAction()) {
                HashMap<?, ?> action = (HashMap<?, ?>) attributes.get(eAttribute.getName());
                if (action != null) {
                    object.eSet(eAttribute, (String) action.get("method"));
                }
            }
        }
    }
}
