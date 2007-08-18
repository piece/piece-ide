// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import junit.framework.TestCase;


/**
 * �t���[�e�X�g�N���X.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.model.flow
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowTest extends TestCase {
    
    /**
     * setActionName���\�b�h�e�X�g.
     * �A�N�V�����N���X�����w�肵���ꍇ�͂��̒l���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetActionName_ReturnActionName() {
        Flow flow = new Flow("Wizard", "TestAction");
        assertEquals("TestAction", flow.getActionClassName());
    }
    
    /**
     * setActionName���\�b�h�e�X�g.
     * �A�N�V�����N���X�����w�肵�Ȃ������ꍇ�̓t���[���{"Action"��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetActionName_ReturnFlowNamePlusAction() {
        Flow flow = new Flow("Wizard", null);
        assertEquals("WizardAction", flow.getActionClassName());
    }
    
    /**
     * setActionName���\�b�h�e�X�g.
     * �t���[���A�A�N�V�����N���X������null�̏ꍇ��null���Ԃ���邱�Ƃ�
     * �e�X�g����B
     */
    public void testSetActionName_ReturnNull() {
        Flow flow = new Flow(null, null);
        assertNull(flow.getActionClassName());
    }
    
    /**
     * getStateListToOwnState���\�b�h�e�X�g.
     * �w�肵���X�e�[�g�֑J�ڂ���X�e�[�g���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetStateListToOwnState_ReturnTheStateListToOwnState() {
        Flow flow = new Flow(null, null);
        
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        State viewState = new State(State.VIEW_STATE);
        Event fromViewToActionEvent = new Event(Event.TRANSITION_EVENT);
        fromViewToActionEvent.setNextState(actionState);
        viewState.addEvent(fromViewToActionEvent);
        flow.addState(viewState);
        
        List<State> stateList = flow.getStateListToOwnState(actionState);
        
        assertEquals(1, stateList.size());
        State state = stateList.get(0);
        assertEquals(viewState, state);
    }
    
    /**
     * getStateListToOwnState���\�b�h�e�X�g.
     * �w�肵���X�e�[�g�ɑJ�ڂ���X�e�[�g���Ȃ��ꍇ�A��̃��X�g���Ԃ����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testGetStateListToOwnState_ReturnEmptyList() {
        Flow flow = new Flow(null, null);
        
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        State viewState = new State(State.VIEW_STATE);
        flow.addState(viewState);
        
        List<State> stateList = flow.getStateListToOwnState(actionState);
        
        assertEquals(0, stateList.size());
    }
    
    /**
     * getStateListToOwnState���\�b�h�e�X�g.
     * �w�肵���X�e�[�g��null�̏ꍇ�A��̃��X�g���Ԃ����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testGetStateListToOwnState_ReturnEmptyListParameterNull() {
        Flow flow = new Flow(null, null);
        
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        State viewState = new State(State.VIEW_STATE);
        flow.addState(viewState);
        
        List<State> stateList = flow.getStateListToOwnState(null);
        
        assertEquals(0, stateList.size());
    }
    
    /**
     * getTransitionEventListToOwnState���\�b�h�e�X�g.
     * �w�肵���X�e�[�g�ɑJ�ڂ���J�ڃC�x���g���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetTransitionEventListToOwnState_ReturnTheEventListToOwnState() {
        Flow flow = new Flow(null, null);
        
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        State viewState = new State(State.VIEW_STATE);
        Event fromViewToActionEvent = new Event(Event.TRANSITION_EVENT);
        fromViewToActionEvent.setNextState(actionState);
        viewState.addEvent(fromViewToActionEvent);
        flow.addState(viewState);
        
        List<Event> eventList = 
            flow.getTransitionEventListToOwnState(actionState);
        
        assertEquals(1, eventList.size());
        Event event = eventList.get(0);
        assertEquals(fromViewToActionEvent, event);
    }
    
    /**
     * getTransitionEventListToOwnState���\�b�h�e�X�g.
     * �w�肵���X�e�[�g�ɑJ�ڂ���J�ڃC�x���g���Ȃ��ꍇ�A��̃��X�g���Ԃ����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testGetTransitionEventListToOwnState_ReturnEmptyList() {
        Flow flow = new Flow(null, null);
        
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        State viewState = new State(State.VIEW_STATE);
        flow.addState(viewState);
        
        List<Event> eventList = 
            flow.getTransitionEventListToOwnState(actionState);
        
        assertEquals(0, eventList.size());
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * �C�j�V�����X�e�[�g�̃X�e�[�g������������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnInitialStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("InitialState", 
                flow.generateStateName(State.INITIAL_STATE));
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * �t�@�C�i���X�e�[�g�̃X�e�[�g������������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnFinalStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("FinalState", flow.generateStateName(State.FINAL_STATE));
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * �A�N�V�����e�[�g�̃X�e�[�g������������邽�тɌ��̘A�Ԃ�
     * �C���N�������g���ꂽ�X�e�[�g������������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnActionStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("Process1", flow.generateStateName(State.ACTION_STATE));
        assertEquals("Process2", flow.generateStateName(State.ACTION_STATE));
        assertEquals("Process3", flow.generateStateName(State.ACTION_STATE));
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * �r���[�e�[�g�̃X�e�[�g������������邽�тɌ��̘A�Ԃ�
     * �C���N�������g���ꂽ�X�e�[�g������������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnViewStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("DisplayForm1", flow.generateStateName(State.VIEW_STATE));
        assertEquals("DisplayForm2", flow.generateStateName(State.VIEW_STATE));
        assertEquals("DisplayForm3", flow.generateStateName(State.VIEW_STATE));
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * ���ɐ��������A�N�V�����e�[�g�̃X�e�[�g�������X�e�[�g������
     * �t���[�ɂ���ꍇ�A�A�Ԃ��C���N�������g���ꂽ�X�e�[�g����������
     * ��邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnUsableActionStateName() {
        Flow flow = new Flow(null, null);
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");
        flow.addState(actionState);
        
        assertEquals("Process2", flow.generateStateName(State.ACTION_STATE));
    }
    
    /**
     * generateStateName���\�b�h�e�X�g.
     * ���ɐ��������r���[�e�[�g�̃X�e�[�g�������X�e�[�g������
     * �t���[�ɂ���ꍇ�A�A�Ԃ��C���N�������g���ꂽ�X�e�[�g������
     * ������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateStateName_ReturnUsableViewStateName() {
        Flow flow = new Flow(null, null);
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        flow.addState(viewState);
        
        assertEquals("DisplayForm2", flow.generateStateName(State.VIEW_STATE));
    }
    
    /**
     * checkUsableStateName���\�b�h�e�X�g.
     * �t���[�ɒǉ�����Ă��Ȃ��X�e�[�g�����w�肵���ꍇ��true��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableStateName_CheckUsableStateName() {
        Flow flow = new Flow(null, null);
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        flow.addState(viewState);
        
        assertTrue(flow.checkUsableStateName("DisplayForm2"));
    }
    
    /**
     * checkUsableStateName���\�b�h�e�X�g.
     * �t���[�ɒǉ�����Ă���X�e�[�g�����w�肵���ꍇ��false��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableStateName_CheckUnusableStateName() {
        Flow flow = new Flow(null, null);
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        flow.addState(viewState);
        
        assertFalse(flow.checkUsableStateName("DisplayForm1"));
    }
    
    /**
     * checkUsableStateName���\�b�h�e�X�g.
     * �w�肳�ꂽ�X�e�[�g����null�̏ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableStateName_CheckNull() {
        Flow flow = new Flow(null, null);
        
        assertFalse(flow.checkUsableStateName(null));
    }
    
    /**
     * addState���\�b�h�e�X�g.
     * �X�e�[�g��ǉ�����Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testAddState_InvokeListener() {
        Flow flow = new Flow(null, null);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        flow.addPropertyChangeListener(listener);
        State actionState = new State(State.ACTION_STATE);
        
        flow.addState(actionState);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("state", event.getPropertyName());
        assertEquals(null, event.getNewValue());
        assertEquals(null, event.getOldValue());
    }
    
    /**
     * removeState���\�b�h�e�X�g.
     * �X�e�[�g���폜����Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testRemoveState_InvokeListener() {
        Flow flow = new Flow(null, null);
        State actionState = new State(State.ACTION_STATE);
        flow.addState(actionState);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        flow.addPropertyChangeListener(listener);
        
        flow.removeState(actionState);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("state", event.getPropertyName());
        assertEquals(null, event.getNewValue());
        assertEquals(null, event.getOldValue());
    }
    
    /**
     * getPropertyChangeList���\�b�h�e�X�g.
     * ���X�i�[���o�^����Ă��Ȃ��ꍇ�̓T�C�Y0�̔z�񂪕Ԃ����
     * ���Ƃ��e�X�g����B
     */
    public void testGetPropertyChangeListener_ReturnEmptyArray() {
        State state = new State(State.ACTION_STATE);
        
        for (Event event : state.getEventList()) {
            PropertyChangeListener[] listeners = 
                            event.getPropertyChangeListener();
            assertEquals(0, listeners.length);
        }
    }
    
    /**
     * getStateByName���\�b�h�e�X�g.
     * �w�肳�ꂽ�X�e�[�g���̃X�e�[�g���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetStateByNameShouldReturnTheState() {
        Flow flow = new Flow(null, null);
        
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");
        
        flow.addState(viewState1);
        flow.addState(viewState2);
        flow.addState(actionState);
        
        State state = flow.getStateByName("DisplayForm2");
        assertEquals(viewState2, state);
    }
    
    /**
     * getStateByName���\�b�h�e�X�g.
     * �w�肳�ꂽ�X�e�[�g�����Ȃ��ꍇ��null���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetStateByNameShouldReturnNullBecauseOfTheStateNotFound() {
        Flow flow = new Flow(null, null);
        
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        
        flow.addState(viewState1);
        
        State state = flow.getStateByName("DisplayForm2");
        assertNull(state);
    }
    
    /**
     * getStateByName���\�b�h�e�X�g.
     * �X�e�[�g����null��n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetStateByNameShouldReturnNullBecauseOfStateNameIsNull() {
        Flow flow = new Flow(null, null);
        
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        
        flow.addState(viewState1);
        
        State state = flow.getStateByName(null);
        assertNull(state);
    }
    
    /**
     * getName/setName ���\�b�h�e�X�g.
     * �t���[���ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetNameShouldReturnNull() {
        Flow flow = new Flow(null, null);
        
        flow.setName("");
        assertNull(flow.getName());
    }
    
    /**
     * getActionClassName/setActionClassName ���\�b�h�e�X�g.
     * �A�N�V�����N���X���ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetActionClassNameShouldReturnNull() {
        Flow flow = new Flow(null, null);
        
        flow.setActionClassName("");
        assertNull(flow.getActionClassName());
    }
}
