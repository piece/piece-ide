package FlowDesigner.diagram.resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.InitialState;
import FlowDesigner.ViewState;

public class FlowResourceLoadTest extends TestCase {
    private String fTestYamlFile;

    public FlowResourceLoadTest() {
        fTestYamlFile = System.getProperty("user.dir") + "/tmp/test.yaml";
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

        URI uri = URI.createFileURI(fTestYamlFile);
        FlowResource resource = new FlowResource(uri);
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
            + "      class: ActionClass\n"
            + "      method: doEntryOnDisplayForm1\n"
            + "    activity:\n"
            + "      class: ActionClass\n"
            + "      method: doActivityOnDisplayForm1\n"
            + "    exit:\n"
            + "      class: ActionClass\n"
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
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";

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

        URI uri = URI.createFileURI(fTestYamlFile);
        FlowResource resource = new FlowResource(uri);
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
        assertTrue(eList.get(0) instanceof Flow);
    }

    private void deleteYamlFile() {
        File yamlFile = new File(fTestYamlFile);
        if (yamlFile.exists()) {
            yamlFile.delete();
        }
    }
}
