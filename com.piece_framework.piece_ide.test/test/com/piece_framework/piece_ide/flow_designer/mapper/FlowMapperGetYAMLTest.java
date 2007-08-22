// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * �t���[�}�b�p�[YAML�擾�e�X�g.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetYAMLTest extends TestCase {

    private Flow fFlow;
    
    /**
     * �e�X�g���\�b�h���s�O����.
     * 
     * @exception Exception ��ʗ�O
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        fFlow = new Flow("FlowName", "ActionClass");
    }

    /**
     * getYAML ���\�b�h�e�X�g.
     * �ȉ��̃t���[��YAML�ɏo�͂ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetYAML_InitialViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);
        
        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        assertEquals(expectedYAML, yaml);
    }
    
    /**
     * getYAML ���\�b�h�e�X�g.
     * �ȉ��̃t���[��YAML�ɏo�͂ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
     *
     */
    public void testGetYAML_InitialViewActionViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event actionToView2 = new Event(Event.TRANSITION_EVENT);
        actionToView2.setName("DisplayForm1FromProcess1");
        actionToView2.setNextState(viewState1);
        actionState.addEvent(actionToView2);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState1);
        fFlow.addState(actionState);
        fFlow.addState(viewState2);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML = 
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
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";

        assertEquals(expectedYAML, yaml);
    }
    
    /**
     * getYAML ���\�b�h�e�X�g.
     * �t�@�C�i���X�e�[�g�ւ̑J�ڂ��ӂ�����t���[��YAML��
     * �o�͂ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Final]
     *                         /|
     *             [View]--------
     *
     */
    public void testGetYAML_TwoStateToFinalState_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView1 = new Event(Event.TRANSITION_EVENT);
        initialToView1.setNextState(viewState1);
        initialState.addEvent(initialToView1);
        
        Event view1ToFinal = new Event(Event.TRANSITION_EVENT);
        view1ToFinal.setName("FinalStateFromDisplayForm1");
        view1ToFinal.setNextState(finalState);
        view1ToFinal.setEventHandler("doFinaltStateFromDisplayForm1");
        viewState1.addEvent(view1ToFinal);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        view2ToFinal.setEventHandler("doFinaltStateFromDisplayForm2");
        viewState2.addEvent(view2ToFinal);
        
        Event view2ToView2 = new Event(Event.INTERNAL_EVENT);
        view2ToView2.setName("DisplayForm2FromDisplayForm2");
        view2ToView2.setNextState(viewState2);
        view2ToView2.setEventHandler("doDisplayForm2FromDisplayForm2");
        view2ToView2.setGuardEventHandler("guardMethod");
        viewState2.addEvent(view2ToView2);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState1);
        fFlow.addState(viewState2);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "  - name: DisplayForm2\n"
            + "    view: Form2\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromDisplayForm2\n"
            + "        nextState: DisplayForm2\n"
            + "        action:\n"
            + "          method: doDisplayForm2FromDisplayForm2\n"
            + "        guard:\n"
            + "          method: guardMethod\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML ���\�b�h�e�X�g.
     * �C�j�V�����X�e�[�g����̑J�ڂ��Ȃ��t���[��YAML��
     * �o�͂ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]   [View]-->[Final]
     *
     */
    public void testGetYAML_Initial_ViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML =
              "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";
        
        assertEquals(expectedYAML, yaml);
    }
    
    /**
     * getYAML ���\�b�h�e�X�g.
     * �t�@�C�i���X�e�[�g���Ȃ��t���[��YAML�ɏo�͂ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Action]
     *
     */
    public void testGetYAML_NothingFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);
        
        Event viewToAction = new Event(Event.TRANSITION_EVENT);
        viewToAction.setName("Process1FromDisplayForm1");
        viewToAction.setNextState(actionState);
        viewToAction.setEventHandler("FlowAction:doProcess1FromDisplayForm1");
        viewToAction.setGuardEventHandler("GuardAction:guardMethod");
        viewState.addEvent(viewToAction);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(actionState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "        guard:\n"
            + "          method: guardMethod\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n";

        assertEquals(expectedYAML, yaml);
    }
    
    /**
     * getYAML ���\�b�h�e�X�g.
     * �X�e�[�g�̓o�^�������C�j�V�����A�r���[�E�A�N�V�����A�t�@�C�i����
     * ���Ԃɓo�^����Ă��Ȃ��̃t���[��YAML�ɏo�͂ł��邱�Ƃ��e�X�g��
     * ��B<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *
     */
    public void testGetYAML_NoOrder_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow.addState(finalState);
        fFlow.addState(viewState2);
        fFlow.addState(actionState);
        fFlow.addState(viewState1);
        fFlow.addState(initialState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML = 
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
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n";
        
        assertEquals(expectedYAML, yaml);
    }
    
    /**
     * getYAML ���\�b�h�e�X�g.
     * �t���[�ɃA�N�V�����N���X���w�肳��Ă��Ȃ����C�x���g�n���h��
     * �ɂ��A�N�V�����N���X���w�肳��Ă��Ȃ��ꍇ�A���\�b�h�݂̂��Ԃ�
     * ��邱�Ƃ��`�F�b�N����B
     * 
     */
    public void testGetYAMLShouldReturnTheYAMLWhichTheEventHandlerHasMethodOnly() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow = new Flow(null, null);
        fFlow.addState(finalState);
        fFlow.addState(viewState2);
        fFlow.addState(actionState);
        fFlow.addState(viewState1);
        fFlow.addState(initialState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        String expectedYAML = 
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
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n";
        
        assertEquals(expectedYAML, yaml);
    }
}
