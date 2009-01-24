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
import FlowDesigner.ViewState;

public class FlowResourceLoadTest extends TestCase {
    private File fTestYamlFile;

    public FlowResourceLoadTest() {
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

        FlowResource resource = new FlowResource(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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
        Event event = initialState.getEvents().get(0);
        assertEquals("(FirstState)", event.getName());
        assertEquals(displayForm1, event.getNextState());
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

        FlowResource resource = new FlowResource(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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
        assertEquals("(FirstState)", displayForm1FromInitialState.getName());
        assertEquals(displayForm1, displayForm1FromInitialState.getNextState());

        assertEquals(1, displayForm1.getEvents().size());
        Event process1FromDisplayForm1 = displayForm1.getEvents().get(0);
        assertEquals("Process1FromDisplayForm1", process1FromDisplayForm1.getName());
        assertEquals("doProcess1FromDisplayForm1", process1FromDisplayForm1.getAction());
        assertEquals("guardProcess1FromDisplayForm1", process1FromDisplayForm1.getGuard());
        assertEquals(process1, process1FromDisplayForm1.getNextState());

        assertEquals(2, process1.getEvents().size());
        Event displayForm1FromProcess1 = null;
        Event displayForm2FromProcess1 = null;
        for (Event event: process1.getEvents()) {
            if (event.getName().equals("DisplayForm1FromProcess1")) {
                displayForm1FromProcess1 = event;
            } else if (event.getName().equals("DisplayForm2FromProcess1")) {
                displayForm2FromProcess1 = event;
            } else {
                fail();
            }
        }
        assertNotNull(displayForm1FromProcess1);
        assertNull(displayForm1FromProcess1.getAction());
        assertNull(displayForm1FromProcess1.getGuard());
        assertEquals(displayForm1, displayForm1FromProcess1.getNextState());
        assertNotNull(displayForm2FromProcess1);
        assertNull(displayForm2FromProcess1.getAction());
        assertNull(displayForm2FromProcess1.getGuard());
        assertEquals(displayForm2, displayForm2FromProcess1.getNextState());
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
