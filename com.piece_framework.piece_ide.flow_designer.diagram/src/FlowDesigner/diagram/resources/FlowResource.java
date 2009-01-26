// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
        Flow eFlow = (Flow) getContents().get(0);

        Map<String, String> initialStateMap = new LinkedHashMap<String, String>();
        NamedState firstState = (NamedState) eFlow.getInitialState().getEvents().get(0).getNextState();
        initialStateMap.put("firstState", firstState.getName());
        String initialStateYaml = Yaml.dump(initialStateMap);

        Map<String, Map<String, Object>> lastStateMap = new LinkedHashMap<String, Map<String, Object>>();
        if (eFlow.getFinalState() != null) {
            for (NamedState state: eFlow.getStates()) {
                for (Event event: state.getEvents()) {
                    if (event.getNextState() == eFlow.getFinalState()) {
                        Map<String, Object> lastState = new LinkedHashMap<String, Object>();
                        lastState.put("name", state.getName());
                        if (state instanceof ViewState) {
                            lastState.put("view", ((ViewState) state).getView());
                        }
                        if (state.getEntry() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getEntry());
                            lastState.put("entry", action);
                        }
                        if (state.getActivity() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getActivity());
                            lastState.put("activity", action);
                        }
                        if (state.getExit() != null) {
                            Map<String, String> action = new LinkedHashMap<String, String>();
                            action.put("method", state.getExit());
                            lastState.put("exit", action);
                        }

                        lastStateMap.put("lastState", lastState);
                    }
                }
            }
        }
        String lastStateYaml = Yaml.dump(lastStateMap);

        List<Map<String, Object>> viewStateList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> actionStateList = new ArrayList<Map<String, Object>>();
        for (NamedState state: eFlow.getStates()) {
            boolean hasFinalState = false;
            for (Event event: state.getEvents()) {
                if (event.getNextState() == eFlow.getFinalState()) {
                    hasFinalState = true;
                    break;
                }
            }
            if (hasFinalState) {
                continue;
            }

            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            stateMap.put("name", state.getName());
            if (state instanceof ViewState) {
                stateMap.put("view", ((ViewState) state).getView());
            }
            if (state.getEntry() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getEntry());
                stateMap.put("entry", action);
            }
            if (state.getActivity() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getActivity());
                stateMap.put("activity", action);
            }
            if (state.getExit() != null) {
                Map<String, String> action = new LinkedHashMap<String, String>();
                action.put("method", state.getExit());
                stateMap.put("exit", action);
            }
            List<Map<String, Object>> transitionList = new ArrayList<Map<String, Object>>();
            for (Event event: state.getEvents()) {
                Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
                eventMap.put("event", event.getName());
                eventMap.put("nextState", ((NamedState) event.getNextState()).getName());
                if (event.getAction() != null) {
                    Map<String, String> action = new LinkedHashMap<String, String>();
                    action.put("method", event.getAction());
                    eventMap.put("action", action);
                }
                if (event.getGuard() != null) {
                    Map<String, String> action = new LinkedHashMap<String, String>();
                    action.put("method", event.getGuard());
                    eventMap.put("guard", action);
                }

                transitionList.add(eventMap);
            }
            if (transitionList.size() > 0) {
                stateMap.put("transition", transitionList);
            }

            if (state instanceof ViewState) {
                viewStateList.add(stateMap);
            } else if (state instanceof ActionState) {
                actionStateList.add(stateMap);
            }
        }
        String viewStateYaml = null;
        if (viewStateList.size() > 0) {
            Map<String, List<?>> viewStateMap = new LinkedHashMap<String, List<?>>();
            viewStateMap.put("viewState", viewStateList);
            viewStateYaml = Yaml.dump(viewStateMap);
        }

        String actionStateYaml = null;
        if (actionStateList.size() > 0) {
            Map<String, List<?>> actionStateMap = new LinkedHashMap<String, List<?>>();
            actionStateMap.put("actionState", actionStateList);
            actionStateYaml = Yaml.dump(actionStateMap);
        }

        StringBuffer yamlBuffer = new StringBuffer();
        yamlBuffer.append(formatYaml(initialStateYaml));
        yamlBuffer.append("\n");
        yamlBuffer.append(formatYaml(lastStateYaml));
        if (viewStateYaml != null) {
            yamlBuffer.append("\n");
            yamlBuffer.append(formatYaml(viewStateYaml));
        }
        if (actionStateYaml != null) {
            yamlBuffer.append("\n");
            yamlBuffer.append(formatYaml(actionStateYaml));
        }

        outputStream.write(yamlBuffer.toString().getBytes());
    }

    private void createStates(Flow eFlow,
                              HashMap<?, ?> flow
                              ) {
        if (flow.get("lastState") != null) {
            FinalState finalState = FlowDesignerFactoryImpl.eINSTANCE.createFinalState();
            eFlow.setFinalState(finalState);

            for (HashMap<?, ?> stateAttributes: getStateList(flow, "lastState")) {
                NamedState state = null;
                if (stateAttributes.containsKey("view")) {
                    state = createViewState(stateAttributes);
                } else {
                    state = createActionState(stateAttributes);
                }

                Event event = FlowDesignerFactoryImpl.eINSTANCE.createEvent();
                event.setName("FinalStateFrom" + state.getName());
                event.setNextState(finalState);
                state.getEvents().add(event);

                eFlow.getStates().add(state);
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
    
    private String formatYaml(String sourceYaml) {
        String yaml = new String(sourceYaml);
        yaml = yaml.replace("\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("--- !java.util.LinkedHashMap\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("--- \n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace(
                " !java.util.LinkedHashMap", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replaceAll("-\n *", "- "); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace(": \n", ":\n"); //$NON-NLS-1$ //$NON-NLS-2$
//        if (yaml.length() > 0) {
//            yaml = yaml.substring(0, yaml.length() - 1);
//        }

        return yaml;
    }
}
