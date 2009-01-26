package FlowDesigner.diagram.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerFactory;
import FlowDesigner.ViewState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

public class FlowResourceSaveTest extends TestCase {
    private File fTestYamlFile;
    private FlowDesignerFactory fFactory;

    public FlowResourceSaveTest() {
        fTestYamlFile = new File(System.getProperty("user.dir") + "/tmp/test.yaml");
        fFactory = FlowDesignerFactoryImpl.eINSTANCE;
    }

    protected void setUp() throws Exception {
//        deleteYamlFile();
    }

    protected void tearDown() throws Exception {
//        deleteYamlFile();
    }

    public void testShouldConvertFlowIncludingInitialAndViewAndFinalStateIntoYAML() {
        Flow flow = fFactory.createFlow();

        FinalState finalState = fFactory.createFinalState();
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        flow.getStates().add(displayForm1);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setName("(FirstState)");
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event finalStateFromDisplayForm1 = fFactory.createEvent();
        finalStateFromDisplayForm1.setName("FinalStateFromDisplayForm1");
        finalStateFromDisplayForm1.setNextState(finalState);
        displayForm1.getEvents().add(finalStateFromDisplayForm1);

        FlowResource resource = new FlowResource(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.getContents().add(flow);
            resource.save(null);
        } catch (IOException e) {
            fail();
        }

        String yaml =
            "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";
        assertYaml(yaml);
    }

    private void assertYaml(String expectedYaml) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(fTestYamlFile);
            bufferedReader = new BufferedReader(reader);

            StringBuffer actualYaml = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                actualYaml.append(line);
                actualYaml.append("\n");
            }

            assertEquals(expectedYaml, actualYaml.toString());
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    fail();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
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
