package FlowDesigner.diagram.resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.InitialState;
import FlowDesigner.NamedState;
import FlowDesigner.Target;
import FlowDesigner.ViewState;
import FlowDesigner.util.FlowDesignerResourceImpl;

public class FlowDesignerResourceImplLoadTest extends TestCase {
    private File fTestYamlFile;

    public FlowDesignerResourceImplLoadTest() {
        fTestYamlFile = new File(System.getProperty("user.dir") + "/tmp/test.yaml");
    }

    @Override
    protected void setUp() throws Exception {
        deleteYamlFile();
    }

    @Override
    protected void tearDown() throws Exception {
        deleteYamlFile();
    }

    public void testShouldConvertYAMLIntoFlowIncludingInitialAndViewAndFinalState() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(1, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );

        assertEquals(1, initialState.getEvents().size());
        Event displayForm1FromInitialState = initialState.getEvents().get(0);
        assertEvent(displayForm1FromInitialState,
                    Event.FIRSTSTATE_EVENT,
                    null,
                    null,
                    displayForm1
                    );

        assertEquals(1, displayForm1.getEvents().size());
        Event finalStateFromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEvent(finalStateFromDisplayForm1,
                    Event.LASTSTATE_EVENT,
                    null,
                    null,
                    finalState
                    );
    }

    public void testShouldConvertYAMLIntoFlowIncludingAllKindsOfState() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm2\n"
            + "  view: Form2\n"
            + "  entry:\n"
            + "    method: doEntryOnDisplayForm2\n"
            + "  activity:\n"
            + "    method: doActivityOnDisplayForm2\n"
            + "  exit:\n"
            + "    method: doExitOnDisplayForm2\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    entry:\n"
            + "      method: doEntryOnDisplayForm1\n"
            + "    activity:\n"
            + "      method: doActivityOnDisplayForm1\n"
            + "    exit:\n"
            + "      method: doExitOnDisplayForm1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "        guard:\n"
            + "          method: guardProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    entry:\n"
            + "      method: doEntryOnProcess1\n"
            + "    activity:\n"
            + "      method: doActivityOnProcess1\n"
            + "    exit:\n"
            + "      method: doExitOnProcess1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(3, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ActionState process1 = (ActionState) eFlow.findStateByName("Process1");
        ViewState displayForm2 = (ViewState) eFlow.findStateByName("DisplayForm2");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        "doEntryOnDisplayForm1",
                        "doActivityOnDisplayForm1",
                        "doExitOnDisplayForm1"
                        );
        assertActionState(process1,
                          "Process1",
                          "doEntryOnProcess1",
                          "doActivityOnProcess1",
                          "doExitOnProcess1"
                          );
        assertViewState(displayForm2,
                        "DisplayForm2",
                        "Form2",
                        "doEntryOnDisplayForm2",
                        "doActivityOnDisplayForm2",
                        "doExitOnDisplayForm2"
                        );

        assertEquals(1, initialState.getEvents().size());
        Event displayForm1FromInitialState = initialState.getEvents().get(0);
        assertEvent(displayForm1FromInitialState,
                    Event.FIRSTSTATE_EVENT,
                    null,
                    null,
                    displayForm1
                    );

        assertEquals(1, displayForm1.getEvents().size());
        Event process1FromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEvent(process1FromDisplayForm1,
                    "Process1FromDisplayForm1",
                    "doProcess1FromDisplayForm1",
                    "guardProcess1FromDisplayForm1",
                    process1
                    );

        assertEquals(2, process1.getEvents().size());
        Event displayForm1FromProcess1 = null;
        Event displayForm2FromProcess1 = null;
        for (Event event: process1.getEvents()) {
            if (event.getEvent().equals("DisplayForm1FromProcess1")) {
                displayForm1FromProcess1 = event;
            } else if (event.getEvent().equals("DisplayForm2FromProcess1")) {
                displayForm2FromProcess1 = event;
            } else {
                fail();
            }
        }
        assertEvent(displayForm1FromProcess1,
                    "DisplayForm1FromProcess1",
                    null,
                    null,
                    displayForm1
                    );
        assertEvent(displayForm2FromProcess1,
                    "DisplayForm2FromProcess1",
                    null,
                    null,
                    displayForm2
                    );

        assertEquals(1, displayForm2.getEvents().size());
        Event finalStateFromDisplayForm2 = displayForm2.getEvents().get(0);
        assertEvent(finalStateFromDisplayForm2,
                    Event.LASTSTATE_EVENT,
                    null,
                    null,
                    finalState
                    );
    }

    public void testShouldConvertYAMLIntoFlowIncludingTwoLastState() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "  - name: DisplayForm2\n"
            + "    view: Form2\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(2, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ViewState displayForm2 = (ViewState) eFlow.findStateByName("DisplayForm2");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );
        assertViewState(displayForm2,
                        "DisplayForm2",
                        "Form2",
                        null,
                        null,
                        null
                        );

        assertEquals(1, initialState.getEvents().size());
        Event displayForm1FromInitialState = initialState.getEvents().get(0);
        assertEvent(displayForm1FromInitialState,
                    Event.FIRSTSTATE_EVENT,
                    null,
                    null,
                    displayForm1
                    );

        assertEquals(1, displayForm1.getEvents().size());
        Event finalStateFromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEvent(finalStateFromDisplayForm1,
                    Event.LASTSTATE_EVENT,
                    null,
                    null,
                    finalState
                    );
        assertEquals(1, displayForm2.getEvents().size());
        Event finalStateFromDisplayForm2 = displayForm2.getEvents().get(0);
        assertEvent(finalStateFromDisplayForm2,
                    Event.LASTSTATE_EVENT,
                    null,
                    null,
                    finalState
                    );
    }

    public void testShouldConvertYAMLIntoFlowWithoutFirstState() {
        String yaml =
            "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(1, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );

        assertEquals(0, initialState.getEvents().size());

        assertEquals(1, displayForm1.getEvents().size());
        Event finalStateFromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEvent(finalStateFromDisplayForm1,
                    Event.LASTSTATE_EVENT,
                    null,
                    null,
                    finalState
                    );
    }

    public void testShouldConvertYAMLIntoFlowWithoutLastState() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNull(finalState);

        assertEquals(2, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ActionState process1 = (ActionState) eFlow.findStateByName("Process1");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );
        assertActionState(process1,
                          "Process1",
                          null,
                          null,
                          null
                          );

        assertEquals(1, initialState.getEvents().size());
        Event displayForm1FromInitialState = initialState.getEvents().get(0);
        assertEvent(displayForm1FromInitialState,
                    Event.FIRSTSTATE_EVENT,
                    null,
                    null,
                    displayForm1
                    );

        assertEquals(1, displayForm1.getEvents().size());
        Event process1FromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEvent(process1FromDisplayForm1,
                    "Process1FromDisplayForm1",
                    null,
                    null,
                    process1
                    );

        assertEquals(0, process1.getEvents().size());
    }

    public void testShouldConvertYAMLIncludingSpellMissIntoFlow() {
        String yaml =
            "firstState: DisplayForm1xx\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm2\n"
            + "  view: Form2\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1xx\n"
            + "        action:\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2xx\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1xx\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(3, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ActionState process1 = (ActionState) eFlow.findStateByName("Process1");
        ViewState displayForm2 = (ViewState) eFlow.findStateByName("DisplayForm2");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );
        assertActionState(process1,
                          "Process1",
                          null,
                          null,
                          null
                          );
        assertViewState(displayForm2,
                        "DisplayForm2",
                        "Form2",
                        null,
                        null,
                        null
                        );

        assertEquals(0, initialState.getEvents().size());
        assertEquals(0, displayForm1.getEvents().size());
        assertEquals(0, process1.getEvents().size());
        assertEquals(1, displayForm2.getEvents().size());
    }

    public void testShouldThrowIOExceptionIfYAMLIncludingSyntaxErrorInto() {
        String yaml =
            "firstState: DisplayForm1xx\n"
            + "\n"
            + "lastState:\n"
            + "name: DisplayForm2\n"
            + "view: Form2\n"
            + "\n"
            + "viewState:\n"
            + "- name: DisplayForm1\n"
            + "view: Form1\n"
            + "transition:\n"
            + "- event: Process1FromDisplayForm1\n"
            + "nextState: Process1xx\n"
            + "action:\n"
            + "class: ActionClass\n"
            + "method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "- name: Process1\n"
            + "transition:\n"
            + "- event: DisplayForm2FromProcess1\n"
            + "nextState: DisplayForm2xx\n"
            + "- event: DisplayForm1FromProcess1\n"
            + "nextState: DisplayForm1xx\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
            fail();
        } catch (IOException e) {
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        assertNotNull(eFlow.getInitialState());
        assertNull(eFlow.getFinalState());
        assertEquals(0, eFlow.getStates().size());
    }

    public void testShouldIgnoreIncorrectKey() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm2\n"
            + "  view: Form2\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionStateXX:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(2, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ViewState displayForm2 = (ViewState) eFlow.findStateByName("DisplayForm2");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );
        assertViewState(displayForm2,
                        "DisplayForm2",
                        "Form2",
                        null,
                        null,
                        null
                        );

        assertEquals(1, initialState.getEvents().size());
        assertEquals(0, displayForm1.getEvents().size());
        assertEquals(1, displayForm2.getEvents().size());
    }

    public void testShouldConvertYAMLIntoFlowIncludingViewStateWithoutViewElement() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm2\n"
            + "  view: ~\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: ~\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromDisplayForm1\n"
            + "        nextState: DisplayForm2\n"
            + "\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);

        assertEquals(2, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        ViewState displayForm2 = (ViewState) eFlow.findStateByName("DisplayForm2");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        null,
                        null,
                        null,
                        null
                        );
        assertViewState(displayForm2,
                        "DisplayForm2",
                        null,
                        null,
                        null,
                        null
                        );

        assertEquals(1, initialState.getEvents().size());
        assertEquals(1, displayForm1.getEvents().size());
        assertEquals(1, displayForm2.getEvents().size());
    }

    public void testShouldConvertYAMLIntoFlowIncludingInitialAndFinal() {
        String yaml =
            "initial:\n"
            + "  method: InitialMethod\n"
            + "firstState: DisplayForm1\n"
            + "\n"
            + "final:\n"
            + "  method: FinalMethod\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        generateYamlFile(yaml);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);

        Flow eFlow = (Flow) eList.get(0);

        InitialState initialState = eFlow.getInitialState();
        assertNotNull(initialState);
        assertEquals("InitialMethod", initialState.getInitialize());

        FinalState finalState = eFlow.getFinalState();
        assertNotNull(finalState);
        assertEquals("FinalMethod", finalState.getFinalize());

        assertEquals(1, eFlow.getStates().size());
        ViewState displayForm1 = (ViewState) eFlow.findStateByName("DisplayForm1");
        assertViewState(displayForm1,
                        "DisplayForm1",
                        "Form1",
                        null,
                        null,
                        null
                        );

        assertEquals(1, initialState.getEvents().size());
        assertEquals(1, displayForm1.getEvents().size());
    }

    private void assertViewState(ViewState state,
                                 String name,
                                 String view,
                                 String entry,
                                 String activity,
                                 String exit
                                 ) {
        assertNamedState(state,
                         name,
                         entry,
                         activity,
                         exit
                         );
        assertEquals(view, state.getView());
    }

    private void assertActionState(ActionState state,
                                   String name,
                                   String entry,
                                   String activity,
                                   String exit
                                   ) {
        assertNamedState(state,
                         name,
                         entry,
                         activity,
                         exit
                         );
    }

    private void assertNamedState(NamedState state,
                                  String name,
                                  String entry,
                                  String activity,
                                  String exit
                                  ) {
        assertNotNull(state);
        assertEquals(name, state.getName());
        assertEquals(entry, state.getEntry());
        assertEquals(activity, state.getActivity());
        assertEquals(exit, state.getExit());
    }

    private void assertEvent(Event event,
                             String name,
                             String action,
                             String guard,
                             Target nextState) {
        assertNotNull(event);
        assertEquals(name, event.getEvent());
        assertEquals(action, event.getAction());
        assertEquals(guard, event.getGuard());
        assertEquals(nextState, event.getNextState());
    }

    private void generateYamlFile(String yaml) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fTestYamlFile);
            writer.write(yaml);
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    fail();
                }
            }
        }
    }

    private void deleteYamlFile() {
        if (fTestYamlFile.exists()) {
            fTestYamlFile.delete();
        }
    }
}
