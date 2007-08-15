package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowMapper;

/**
 * �t���[�}�b�p�[ �t���[���f���擾�e�X�g.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetFlowTest extends TestCase {

    /**
     * �e�X�g���\�b�h���s�O����.
     * 
     * @exception Exception ��ʗ�O
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * YAML����ȉ��̃t���[���擾�ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetFlowShouldReturn_InitialViewFinal_Flow() {
        String yamlString = 
                        "firstState: DisplayForm1\n"
                      + "\n"
                      + "lastState:\n"
                      + "  name: DisplayForm1\n"
                      + "  view: Form1\n"
                      + "  entry:\n"
                      + "    method: ActionClass:doEntryOnDisplayForm1\n"
                      + "  activity:\n"
                      + "    method: ActionClass:doActivityOnDisplayForm1\n"
                      + "  exit:\n"
                      + "    method: ActionClass:doExitOnDisplayForm1\n";
    
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        State viewState = null;
        State finalState = null;
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewState = state;
            } else {
                fail();
            }   
        }
        
        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertNotNull(viewState);
        assertEquals("DisplayForm1", viewState.getName());
        assertEquals("Form1", viewState.getView());
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(2, initialState.getEventList().size());
        Event initialToView = 
            initialState.getEventByName("DisplayForm1FromInitialState");
        assertNotNull(initialToView);
        assertEquals(viewState, initialToView.getNextState());
        
        assertEquals(4, viewState.getEventList().size());
        Event activityEvent = viewState.getEventByName("Activity");
        Event entryEvent = viewState.getEventByName("Entry");
        Event exitEvent = viewState.getEventByName("Exit");
        Event viewToFinal = viewState.getEventByName(
                                "FinalStateFromDisplayForm1");
        assertNotNull(activityEvent);
        assertNotNull(activityEvent.getEventHandler());
        assertEquals("ActionClass:doActivityOnDisplayForm1", 
                     activityEvent.getEventHandler().toString());
        assertNotNull(entryEvent);
        assertNotNull(entryEvent.getEventHandler());
        assertEquals("ActionClass:doEntryOnDisplayForm1", 
                     entryEvent.getEventHandler().toString());
        assertNotNull(exitEvent);
        assertNotNull(exitEvent.getEventHandler());
        assertEquals("ActionClass:doExitOnDisplayForm1", 
                     exitEvent.getEventHandler().toString());
        assertNotNull(viewToFinal);
        assertEquals(finalState, viewToFinal.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * YAML����ȉ��̃t���[���擾�ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
     *
     */
    public void testGetFlowShouldReturn_InitialViewActionViewFinal_Flow() {
        String yamlString = 
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
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(5, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }

        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(2, initialState.getEventList().size());
        Event initialToView1 = 
            initialState.getEventByName("DisplayForm1FromInitialState");
        assertNotNull(initialToView1);
        assertEquals(viewState1, initialToView1.getNextState());
        
        assertEquals(4, viewState1.getEventList().size());
        assertNormalStateBiuldinEvent(viewState1);
        Event view1ToAction = 
            viewState1.getEventByName("Process1FromDisplayForm1");
        assertNotNull(view1ToAction);
        assertEquals(actionState, view1ToAction.getNextState());
    
        assertEquals(4, viewState2.getEventList().size());
        assertNormalStateBiuldinEvent(viewState2);
        Event view2ToFinal = 
            viewState2.getEventByName("FinalStateFromDisplayForm2");
        assertNotNull(view2ToFinal);
        assertEquals(finalState, view2ToFinal.getNextState());

        assertEquals(5, actionState.getEventList().size());
        assertNormalStateBiuldinEvent(actionState);
        Event actionToView1 = 
            actionState.getEventByName("DisplayForm1FromProcess1");
        Event actionToView2 = 
            actionState.getEventByName("DisplayForm2FromProcess1");
        assertNotNull(actionToView1);
        assertEquals(viewState1, actionToView1.getNextState());
        assertNotNull(actionToView2);
        assertEquals(viewState2, actionToView2.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * �t�@�C�i���X�e�[�g�ւ̑J�ڂ��ӂ�����YAML����t���[
     * ���擾�ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]-->[View]-->[Final]
     *                         /|
     *             [View]--------
     *
     */
    public void testGetFlowShouldReturn_TwoStateToFinalState_Flow() {
        String yamlString = 
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
            + "          method: ActionClass:doDisplayForm2FromDisplayForm2\n"
            + "        guard:\n"
            + "          method: ActionClass:guardMethod\n";

        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(4, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else {
                fail();
            }
        }
        
        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(2, initialState.getEventList().size());
        Event initialToView1 = 
            initialState.getEventByName("DisplayForm1FromInitialState");
        assertNotNull(initialToView1);
        assertEquals(viewState1, initialToView1.getNextState());
        
        assertEquals(4, viewState1.getEventList().size());
        assertNormalStateBiuldinEvent(viewState1);
        Event view1ToFinal = 
            viewState1.getEventByName("FinalStateFromDisplayForm1");
        assertNotNull(view1ToFinal);
        assertEquals(finalState, view1ToFinal.getNextState());
    
        assertEquals(5, viewState2.getEventList().size());
        assertNormalStateBiuldinEvent(viewState2);
        Event view2ToView2 = 
            viewState2.getEventByName("DisplayForm2FromDisplayForm2");
        assertNotNull(view2ToView2);
        assertEquals(viewState2, view2ToView2.getNextState());
        Event view2ToFinal = 
            viewState2.getEventByName("FinalStateFromDisplayForm2");
        assertNotNull(view2ToFinal);
        assertEquals(finalState, view2ToFinal.getNextState());

        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * �C�j�V�����X�e�[�g����̑J�ڂ��Ȃ�YAML������t���[����
     * ���ł��邱�Ƃ��e�X�g����B<br>
     * [Initial]   [View]-->[Final]
     *
     */
    public void testGetFlowShouldReturn_Initial_ViewFinal_Flow() {
        String yamlString = 
              "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else {
                fail();
            }
        }
        
        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(1, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        assertEquals("Form1", viewState1.getView());
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(1, initialState.getEventList().size());
        assertEquals(0, initialState.getTransitionEventList().size());
        
        assertEquals(4, viewState1.getEventList().size());
        assertNormalStateBiuldinEvent(viewState1);
        Event view1ToFinal = 
            viewState1.getEventByName("FinalStateFromDisplayForm1");
        assertNotNull(view1ToFinal);
        assertEquals(finalState, view1ToFinal.getNextState());

        assertEquals(1, finalState.getEventList().size());
    }
    /**
     * getFlow ���\�b�h�e�X�g.
     * �t�@�C�i���X�e�[�g���Ȃ�YAML����t���[���擾�ł��邱�Ƃ�
     * �e�X�g����B<br>
     * [Initial]-->[View]-->[Action]
     *
     */
    public void testGetFlowShouldReturn_NothingFinal_Flow() {
        String yamlString = 
              "firstState: DisplayForm1\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    entry:\n"
            + "      method: ActionClass:doEntryOnDisplayForm1\n"
            + "    activity:\n"
            + "      method: ActionClass:doActivityOnDisplayForm1\n"
            + "    exit:\n"
            + "      method: ActionClass:doExitOnDisplayForm1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: FlowAction:doProcess1FromDisplayForm1\n"
            + "        guard:\n"
            + "          method: GuardAction:guardMethod\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    entry:\n"
            + "      method: ActionClass:doEntryOnProcess1\n"
            + "    activity:\n"
            + "      method: ActionClass:doActivityOnProcess1\n"
            + "    exit:\n"
            + "      method: ActionClass:doExitOnProcess1\n";

        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }

        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(1, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        assertEquals("Form1", viewState1.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(2, initialState.getEventList().size());
        Event initialToView1 = 
            initialState.getEventByName("DisplayForm1FromInitialState");
        assertNotNull(initialToView1);
        assertEquals(viewState1, initialToView1.getNextState());
        
        assertEquals(4, viewState1.getEventList().size());
        Event activityEventOnViewState1 = 
            viewState1.getEventByName("Activity");
        Event entryEventOnViewState1 = 
            viewState1.getEventByName("Entry");
        Event exitEventOnViewState1 = 
            viewState1.getEventByName("Exit");
        Event view1ToAction = 
            viewState1.getEventByName("Process1FromDisplayForm1");
        assertNotNull(activityEventOnViewState1);
        assertNotNull(activityEventOnViewState1.getEventHandler());
        assertEquals("ActionClass:doActivityOnDisplayForm1", 
                activityEventOnViewState1.getEventHandler().toString());
        assertNotNull(entryEventOnViewState1);
        assertNotNull(entryEventOnViewState1.getEventHandler());
        assertEquals("ActionClass:doEntryOnDisplayForm1", 
                entryEventOnViewState1.getEventHandler().toString());
        assertNotNull(exitEventOnViewState1);
        assertNotNull(exitEventOnViewState1.getEventHandler());
        assertEquals("ActionClass:doExitOnDisplayForm1", 
                exitEventOnViewState1.getEventHandler().toString());
        assertNotNull(view1ToAction);
        assertEquals(actionState, view1ToAction.getNextState());

        assertEquals(3, actionState.getEventList().size());
        Event activityEventOnActionState = 
            actionState.getEventByName("Activity");
        Event entryEventOnActionState = 
            actionState.getEventByName("Entry");
        Event exitEventOnActionState = 
            actionState.getEventByName("Exit");
        assertNotNull(activityEventOnActionState);
        assertNotNull(activityEventOnActionState.getEventHandler());
        assertEquals("ActionClass:doActivityOnProcess1", 
                activityEventOnActionState.getEventHandler().toString());
        assertNotNull(entryEventOnActionState);
        assertNotNull(entryEventOnActionState.getEventHandler());
        assertEquals("ActionClass:doEntryOnProcess1", 
                entryEventOnActionState.getEventHandler().toString());
        assertNotNull(exitEventOnActionState);
        assertNotNull(exitEventOnActionState.getEventHandler());
        assertEquals("ActionClass:doExitOnProcess1", 
                exitEventOnActionState.getEventHandler().toString());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * �J�ڐ�̃X�e�[�g���̒Ԃ肪�Ԉ���Ă���YAML����t���[���擾�ł��邱�Ƃ�
     * �e�X�g����B<br>
     *
     */
    public void testGetFlowShouldReturn_NothingNextState_Flow() {
        String yamlString = 
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
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2xx\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(5, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }
        
        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(1, initialState.getEventList().size());
        assertEquals(0, initialState.getTransitionEventList().size());

        assertEquals(3, viewState1.getEventList().size());
        assertNormalStateBiuldinEvent(viewState1);
        Event view1ToAction = 
            viewState1.getEventByName("Process1FromDisplayForm1");
        assertNull(view1ToAction);

        assertEquals(4, viewState2.getEventList().size());
        assertNormalStateBiuldinEvent(viewState2);
        Event view2ToFinal = 
            viewState2.getEventByName("FinalStateFromDisplayForm2");
        assertNotNull(view2ToFinal);
        assertEquals(finalState, view2ToFinal.getNextState());

        assertEquals(3, actionState.getEventList().size());
        assertNormalStateBiuldinEvent(actionState);
        Event actionToView1 = 
            actionState.getEventByName("DisplayForm1FromProcess1");
        Event actionToView2 = 
            actionState.getEventByName("DisplayForm2FromProcess1");
        assertNull(actionToView1);
        assertNull(actionToView2);
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * �C���f���g���ُ��YAML����t���[���擾�ł��邱�Ƃ��e�X�g����B<br>
     *
     */
    public void testGetFlowShouldReturn_Null() {
        String yamlString = 
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
                + "method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "- name: Process1\n"
                + "transition:\n"
                + "- event: DisplayForm2FromProcess1\n"
                + "nextState: DisplayForm2xx\n"
                + "- event: DisplayForm1FromProcess1\n"
                + "nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNull(flow);
    }

    /**
     * getFlow ���\�b�h�e�X�g.
     * �g�b�v���x���̃L�[�ɕs���Ȓl���܂܂�Ă���YAML����t���[��
     * �擾�ł��邱�Ƃ��e�X�g����B<br>
     *
     */
    public void testGetFlowShouldReturn_NothingAction_Flow() {
        String yamlString = 
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
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionStateXX:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(4, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }

        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(0, actionStateList.size());
        
        assertNotNull(finalState);
        
        // �C�x���g�̃A�T�[�V����
        assertEquals(2, initialState.getEventList().size());
        Event initialToView1 = 
            initialState.getEventByName("DisplayForm1FromInitialState");
        assertNotNull(initialToView1);
        assertEquals(viewState1, initialToView1.getNextState());
        
        assertEquals(3, viewState1.getEventList().size());
        assertNormalStateBiuldinEvent(viewState1);
        Event view1ToAction = 
            viewState1.getEventByName("Process1FromDisplayForm1");
        assertNull(view1ToAction);

        assertEquals(4, viewState2.getEventList().size());
        assertNormalStateBiuldinEvent(viewState2);
        Event view2ToFinal = 
            viewState2.getEventByName("FinalStateFromDisplayForm2");
        assertNotNull(view2ToFinal);
        assertEquals(finalState, view2ToFinal.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getFlow ���\�b�h�e�X�g.
     * �r���[�����Ȃ��r���[�X�e�[�g�̂���YAML����r���[�E�A�N�V������
     * ��ʂ��������s��ꂽ�t���[���擾�ł��邱�Ƃ��e�X�g����B<br>
     *
     */
    public void testGetFlowShouldReturn_NothingView_Flow() {
        String yamlString = 
                  "firstState: DisplayForm1xx\n"
                + "\n"
                + "lastState:\n"
                + "  name: DisplayForm2\n"
                + "  view: ~\n"
                + "\n"
                + "viewState:\n"
                + "  - name: DisplayForm1\n"
                + "    view: ~\n"
                + "    transition:\n"
                + "      - event: Process1FromDisplayForm1\n"
                + "        nextState: Process1xx\n"
                + "        action:\n"
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2xx\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(5, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }
        
        // �X�e�[�g�̃A�T�[�V����
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertNull(viewState1.getView());
        assertNull(viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        
        assertNotNull(finalState);
    }
    
    /**
     * �m�[�}���X�e�[�g(�r���[�X�e�[�g�A�A�N�V�����X�e�[�g)�̃r���g�C��
     * �C�x���g�̃A�T�[�V�������s��.
     * �ȉ��̓��e��\������B<br>
     * �E�r���g�C���C�x���g���̂� null �ł͂Ȃ��B<br>
     * �E�C�x���g�n���h���� null �ł���B<br>
     * 
     * @param state �m�[�}���X�e�[�g(�r���[�X�e�[�g�A�A�N�V�����X�e�[�g)
     */
    private void assertNormalStateBiuldinEvent(State state) {
        Event activityEvent = state.getEventByName("Activity");
        Event entryEvent = state.getEventByName("Entry");
        Event exitEvent = state.getEventByName("Exit");
        
        assertNotNull(activityEvent);
        assertNull(activityEvent.getEventHandler());
        assertNotNull(entryEvent);
        assertNull(entryEvent.getEventHandler());
        assertNotNull(exitEvent);
        assertNull(exitEvent.getEventHandler());
    }
}
