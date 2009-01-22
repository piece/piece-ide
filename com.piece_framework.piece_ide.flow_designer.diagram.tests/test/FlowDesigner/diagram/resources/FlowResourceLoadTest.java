package FlowDesigner.diagram.resources;

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

    public void testShouldConvertYAMLIntoFlowIncludingInitialAndViewAndFinalState() {
        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";
        String yamlPath = System.getProperty("user.dir") + "/tmp/test.yaml";

        FileWriter writer = null;
        try {
            writer = new FileWriter(yamlPath);
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

        URI uri = URI.createFileURI(yamlPath);
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
}
