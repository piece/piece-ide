package FlowDesigner.diagram.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;

import FlowDesigner.ActionState;
import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerFactory;
import FlowDesigner.ViewState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;
import FlowDesigner.util.FlowDesignerResourceImpl;

public class FlowDesignerResourceImplSaveTest extends TestCase {
    private File fTestYamlFile;
    private FlowDesignerFactory fFactory;

    public FlowDesignerResourceImplSaveTest() {
        fTestYamlFile = new File(System.getProperty("user.dir") + "/tmp/test.yaml");
        fFactory = FlowDesignerFactoryImpl.eINSTANCE;
    }

    protected void setUp() throws Exception {
        deleteYamlFile();
    }

    protected void tearDown() throws Exception {
        deleteYamlFile();
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
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event finalStateFromDisplayForm1 = fFactory.createEvent();
        finalStateFromDisplayForm1.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm1.setNextState(finalState);
        displayForm1.getEvents().add(finalStateFromDisplayForm1);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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

    public void testShouldConvertFlowIncludingAllKindsOfStateIntoYAML() {
        Flow flow = fFactory.createFlow();

        FinalState finalState = fFactory.createFinalState();
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        displayForm1.setEntry("doEntryOnDisplayForm1");
        displayForm1.setActivity("doActivityOnDisplayForm1");
        displayForm1.setExit("doExitOnDisplayForm1");
        flow.getStates().add(displayForm1);

        ActionState process1 = fFactory.createActionState();
        process1.setName("Process1");
        process1.setEntry("doEntryOnProcess1");
        process1.setActivity("doActivityOnProcess1");
        process1.setExit("doExitOnProcess1");
        flow.getStates().add(process1);

        ViewState displayForm2 = fFactory.createViewState();
        displayForm2.setName("DisplayForm2");
        displayForm2.setView("Form2");
        displayForm2.setEntry("doEntryOnDisplayForm2");
        displayForm2.setActivity("doActivityOnDisplayForm2");
        displayForm2.setExit("doExitOnDisplayForm2");
        flow.getStates().add(displayForm2);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event process1FromDisplayForm1 = fFactory.createEvent();
        process1FromDisplayForm1.setEvent("Process1FromDisplayForm1");
        process1FromDisplayForm1.setNextState(process1);
        process1FromDisplayForm1.setAction("doProcess1FromDisplayForm1");
        process1FromDisplayForm1.setGuard("guardProcess1FromDisplayForm1");
        displayForm1.getEvents().add(process1FromDisplayForm1);

        Event displayForm2FromProcess1 = fFactory.createEvent();
        displayForm2FromProcess1.setEvent("DisplayForm2FromProcess1");
        displayForm2FromProcess1.setNextState(displayForm2);
        process1.getEvents().add(displayForm2FromProcess1);

        Event displayForm1FromProcess1 = fFactory.createEvent();
        displayForm1FromProcess1.setEvent("DisplayForm1FromProcess1");
        displayForm1FromProcess1.setNextState(displayForm1);
        process1.getEvents().add(displayForm1FromProcess1);

        Event finalStateFromDisplayForm2 = fFactory.createEvent();
        finalStateFromDisplayForm2.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm2.setNextState(finalState);
        displayForm2.getEvents().add(finalStateFromDisplayForm2);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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
        assertYaml(yaml);
    }

    public void testShouldConvertFlowIncludingTwoLastStateIntoYAML() {
        Flow flow = fFactory.createFlow();

        FinalState finalState = fFactory.createFinalState();
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        flow.getStates().add(displayForm1);

        ViewState displayForm2 = fFactory.createViewState();
        displayForm2.setName("DisplayForm2");
        displayForm2.setView("Form2");
        flow.getStates().add(displayForm2);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event finalStateFromDisplayForm1 = fFactory.createEvent();
        finalStateFromDisplayForm1.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm1.setNextState(finalState);
        displayForm1.getEvents().add(finalStateFromDisplayForm1);

        Event finalStateFromDisplayForm2 = fFactory.createEvent();
        finalStateFromDisplayForm2.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm2.setNextState(finalState);
        displayForm2.getEvents().add(finalStateFromDisplayForm2);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "  - name: DisplayForm2\n"
            + "    view: Form2\n";
        assertYaml(yaml);
    }

    public void testShouldConvertFlowWithoutFirstStateIntoYAML() {
        Flow flow = fFactory.createFlow();

        FinalState finalState = fFactory.createFinalState();
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        flow.getStates().add(displayForm1);

        Event finalStateFromDisplayForm1 = fFactory.createEvent();
        finalStateFromDisplayForm1.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm1.setNextState(finalState);
        displayForm1.getEvents().add(finalStateFromDisplayForm1);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.getContents().add(flow);
            resource.save(null);
        } catch (IOException e) {
            fail();
        }

        String yaml =
            "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";
        assertYaml(yaml);
    }

    public void testShouldConvertYAMLIntoFlowWithoutLastState() {
        Flow flow = fFactory.createFlow();

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        flow.getStates().add(displayForm1);

        ActionState process1 = fFactory.createActionState();
        process1.setName("Process1");
        flow.getStates().add(process1);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event process1FromDisplayForm1 = fFactory.createEvent();
        process1FromDisplayForm1.setEvent("Process1FromDisplayForm1");
        process1FromDisplayForm1.setNextState(process1);
        displayForm1.getEvents().add(process1FromDisplayForm1);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.getContents().add(flow);
            resource.save(null);
        } catch (IOException e) {
            fail();
        }

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
        assertYaml(yaml);
    }

    public void testShouldConvertFlowIncludingInitialAndFinalIntoYAML() {
        Flow flow = fFactory.createFlow();

        flow.getInitialState().setInitialize("InitialMethod");

        FinalState finalState = fFactory.createFinalState();
        finalState.setFinalize("FinalMethod");
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("Form1");
        flow.getStates().add(displayForm1);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event finalStateFromDisplayForm1 = fFactory.createEvent();
        finalStateFromDisplayForm1.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm1.setNextState(finalState);
        displayForm1.getEvents().add(finalStateFromDisplayForm1);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
        try {
            resource.getContents().add(flow);
            resource.save(null);
        } catch (IOException e) {
            fail();
        }

        String yaml =
            "initial:\n"
            + "  method: InitialMethod\n"
            + "\n"
            + "firstState: DisplayForm1\n"
            + "\n"
            + "final:\n"
            + "  method: FinalMethod\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";
        assertYaml(yaml);
    }

    public void testShouldNotSaveEmptyAttributes() {
        Flow flow = fFactory.createFlow();

        FinalState finalState = fFactory.createFinalState();
        flow.setFinalState(finalState);

        ViewState displayForm1 = fFactory.createViewState();
        displayForm1.setName("DisplayForm1");
        displayForm1.setView("");
        displayForm1.setEntry("");
        displayForm1.setActivity("");
        displayForm1.setExit("");
        flow.getStates().add(displayForm1);

        ActionState process1 = fFactory.createActionState();
        process1.setName("Process1");
        process1.setEntry("");
        process1.setActivity("");
        process1.setExit("");
        flow.getStates().add(process1);

        ViewState displayForm2 = fFactory.createViewState();
        displayForm2.setName("DisplayForm2");
        displayForm2.setView("");
        displayForm2.setEntry("");
        displayForm2.setActivity("");
        displayForm2.setExit("");
        flow.getStates().add(displayForm2);

        Event initialStateToDisplayForm1 = fFactory.createEvent();
        initialStateToDisplayForm1.setEvent(Event.FIRSTSTATE_EVENT);
        initialStateToDisplayForm1.setNextState(displayForm1);
        flow.getInitialState().getEvents().add(initialStateToDisplayForm1);

        Event process1FromDisplayForm1 = fFactory.createEvent();
        process1FromDisplayForm1.setEvent("Process1FromDisplayForm1");
        process1FromDisplayForm1.setNextState(process1);
        process1FromDisplayForm1.setAction("");
        process1FromDisplayForm1.setGuard("");
        displayForm1.getEvents().add(process1FromDisplayForm1);

        Event displayForm2FromProcess1 = fFactory.createEvent();
        displayForm2FromProcess1.setEvent("DisplayForm2FromProcess1");
        displayForm2FromProcess1.setNextState(displayForm2);
        process1.getEvents().add(displayForm2FromProcess1);

        Event displayForm1FromProcess1 = fFactory.createEvent();
        displayForm1FromProcess1.setEvent("DisplayForm1FromProcess1");
        displayForm1FromProcess1.setNextState(displayForm1);
        process1.getEvents().add(displayForm1FromProcess1);

        Event finalStateFromDisplayForm2 = fFactory.createEvent();
        finalStateFromDisplayForm2.setEvent(Event.LASTSTATE_EVENT);
        finalStateFromDisplayForm2.setNextState(finalState);
        displayForm2.getEvents().add(finalStateFromDisplayForm2);

        FlowDesignerResourceImpl resource = new FlowDesignerResourceImpl(URI.createFileURI(fTestYamlFile.getAbsolutePath()));
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
            + "  name: DisplayForm2\n"
            + "  view:\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view:\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";
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
System.out.println("===\n" + actualYaml.toString() + "===");
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
