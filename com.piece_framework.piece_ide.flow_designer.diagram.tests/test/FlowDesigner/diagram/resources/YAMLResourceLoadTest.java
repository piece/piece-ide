package FlowDesigner.diagram.resources;

import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public class YAMLResourceLoadTest extends TestCase {

    public void testShouldConvertYAMLIntoFlowIncludingInitialViewFinalStates() {
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
        YAMLResource resource = new YAMLResource(uri);
        try {
            resource.load(null);
        } catch (IOException e) {
            fail();
        }

        EList<EObject> eList = resource.getContents();
        assertNotNull(eList);
        assertEquals(1, eList.size());
    }
}
