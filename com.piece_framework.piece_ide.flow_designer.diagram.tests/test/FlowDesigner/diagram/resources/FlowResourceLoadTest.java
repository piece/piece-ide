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
        assertTrue(eFlow.getStates().get(0) instanceof ViewState);
        ViewState viewState = (ViewState) eFlow.getStates().get(0);
        assertEquals("DisplayForm1", viewState.getName());
        assertEquals("Form1", viewState.getView());

        assertEquals(1, initialState.getEvents().size());
        Event event = initialState.getEvents().get(0);
        assertEquals("(FirstState)", event.getName());
        assertEquals(viewState, event.getNextState());
    }

    public void testShouldConvertYAMLIntoFlowIncludingAllKindsOfState() {
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
            + "          class: ActionClass\n"
            + "          method: doProcess1FromDisplayForm1\n"
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
        ViewState displayForm1 = null;
        ActionState process1 = null;
        ViewState displayForm2 = null;
        for (NamedState state: eFlow.getStates()) {
            if (state.getName().equals("DisplayForm1")) {
                displayForm1 = (ViewState) state;
            } else if (state.getName().equals("Process1")) {
                process1 = (ActionState) state;
            } else if (state.getName().equals("DisplayForm2")) {
                displayForm2 = (ViewState) state;
            } else {
                fail();
            }
        }
        assertNotNull(displayForm1);
        assertEquals("Form1", displayForm1.getView());
        assertEquals("doEntryOnDisplayForm1", displayForm1.getEntry());
        assertEquals("doActivityOnDisplayForm1", displayForm1.getActivity());
        assertEquals("doExitOnDisplayForm1", displayForm1.getExit());
        assertNotNull(process1);
        assertEquals("doEntryOnProcess1", process1.getEntry());
        assertEquals("doActivityOnProcess1", process1.getActivity());
        assertEquals("doExitOnProcess1", process1.getExit());
        assertNotNull(displayForm2);
        assertEquals("Form2", displayForm2.getView());
        assertNull(displayForm2.getEntry());
        assertNull(displayForm2.getActivity());
        assertNull(displayForm2.getExit());
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
