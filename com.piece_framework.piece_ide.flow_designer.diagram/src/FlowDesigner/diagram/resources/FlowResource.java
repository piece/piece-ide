// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.ho.yaml.Yaml;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

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
        FlowLoad load = new FlowLoad();
        try {
            load.load(inputStream);
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            getContents().add(load.getFlow());
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
