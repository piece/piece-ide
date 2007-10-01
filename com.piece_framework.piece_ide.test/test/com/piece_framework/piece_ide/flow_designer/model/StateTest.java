// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

/**
 * �X�e�[�g�e�X�g�N���X.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.model.state
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class StateTest extends TestCase {
  
    /**
     * �R���X�g���N�^�e�X�g.
     * �C�j�V�����X�e�[�g�𐶐�����ƁA�����I��Initial�C�x���g��
     * ��������邱�Ƃ��e�X�g����B
     * 
     */
    public void testCreateInitialState_CheckBuiltinEvent() {
        State state = new State(State.INITIAL_STATE);
        
        assertEquals(1, state.getEventList().size());
        
        Event event = state.getEventList().get(0);
        assertEquals(Event.BUILTIN_EVENT, event.getType());
        assertEquals("Initial", event.getName());
        assertNull(event.getNextState());
        assertNull(event.getEventHandler());
        assertNull(event.getGuardEventHandler());
    }
    
    /**
     * �R���X�g���N�^�e�X�g.
     * �r���[�X�e�[�g�𐶐�����ƁA�����I��Entry,Exit,Activity�C�x���g��
     * ��������邱�Ƃ��e�X�g����B
     * 
     */
    public void testCreateViewState_CheckBuiltinEvent() {
        State state = new State(State.VIEW_STATE);
        
        assertEquals(3, state.getEventList().size());
        
        for (Event event : state.getEventList()) {
            assertEquals(Event.BUILTIN_EVENT, event.getType());
            if (!event.getName().equals("Entry")
                && !event.getName().equals("Exit")
                && !event.getName().equals("Activity")) {
                fail();
            }
            assertNull(event.getNextState());
            assertNull(event.getEventHandler());
            assertNull(event.getGuardEventHandler());
        }
    }
    
    /**
     * �R���X�g���N�^�e�X�g.
     * �A�N�V�����e�[�g�𐶐�����ƁA�����I��Entry,Exit,Activity�C�x���g��
     * ��������邱�Ƃ��e�X�g����B
     * 
     */
    public void testCreateActionState_CheckBuiltinEvent() {
        State state = new State(State.ACTION_STATE);
        
        assertEquals(3, state.getEventList().size());
        
        for (Event event : state.getEventList()) {
            assertEquals(Event.BUILTIN_EVENT, event.getType());
            if (!event.getName().equals("Entry")
                && !event.getName().equals("Exit")
                && !event.getName().equals("Activity")) {
                fail();
            }
            assertNull(event.getNextState());
            assertNull(event.getEventHandler());
            assertNull(event.getGuardEventHandler());
        }
    }
    
    /**
     * �R���X�g���N�^�e�X�g.
     * �t�@�C�i���X�e�[�g�𐶐�����ƁA�����I��Final�C�x���g��
     * ��������邱�Ƃ��e�X�g���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCreateFinalState_CheckBuiltinEvent() {
        State state = new State(State.FINAL_STATE);
        
        assertEquals(1, state.getEventList().size());
        
        Event event = state.getEventList().get(0);
        assertEquals(Event.BUILTIN_EVENT, event.getType());
        assertEquals("Final", event.getName());
        assertNull(event.getNextState());
        assertNull(event.getEventHandler());
        assertNull(event.getGuardEventHandler());
    }
    
    /**
     * �R���X�g���N�^�e�X�g.
     * �s���ȃX�e�[�g�^�C�v��n���ƁA�X�e�[�g�^�C�v���s���̃X�e�[�g��
     * ��������邱�Ƃ��e�X�g����B
     * 
     */
    public void testUnknownState_TypeIsUnknown() {
        State state = new State(9999);
        
        assertEquals(State.UNKNOWN_STATE, state.getType());
    }
    
    /**
     * addPropertyChangeListener���\�b�h�e�X�g.
     * �X�e�[�g�Ƀ��X�i�[��o�^����ƁA�X�e�[�g���ێ����Ă���
     * �C�x���g�ɂ����X�i�[���o�^�����B
     * 
     */
    public void testAddPropertyChangeListener_AddEvents() {
        State state = new State(State.ACTION_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        for (Event event : state.getEventList()) {
            PropertyChangeListener[] listeners = 
                            event.getPropertyChangeListener();
            assertEquals(1, listeners.length);
            assertEquals(listener, listeners[0]);
        }
    }
    
    /**
     * removePropertyChangeListener���\�b�h�e�X�g.
     * �X�e�[�g���烊�X�i�[���폜����ƁA�X�e�[�g���ێ����Ă���
     * �C�x���g��������X�i�[���폜�����B
     * 
     */
    public void testRemovePropertyChangeListener_AddEvents() {
        State state = new State(State.ACTION_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        state.removePropertyChangeListener(listener);
        
        for (Event event : state.getEventList()) {
            PropertyChangeListener[] listeners = 
                            event.getPropertyChangeListener();
            assertEquals(0, listeners.length);
        }
    }
    
    /**
     * setName���\�b�h�e�X�g.
     * �X�e�[�g����ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetName_InvokeListener() {
        State state = new State(State.ACTION_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setName("State1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("State#Name", event1.getPropertyName());
        assertEquals("State1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        state.setName("State2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("State#Name", event2.getPropertyName());
        assertEquals("State2", event2.getNewValue());
        assertEquals("State1", event2.getOldValue());
    }
    
    /**
     * setSummary���\�b�h�e�X�g.
     * �T�v��ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetSummary_InvokeListener() {
        State state = new State(State.ACTION_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setSummary("Summary1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("State#Summary", event1.getPropertyName());
        assertEquals("Summary1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        state.setSummary("Summary2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("State#Summary", event2.getPropertyName());
        assertEquals("Summary2", event2.getNewValue());
        assertEquals("Summary1", event2.getOldValue());
    }
    
    /**
     * setView���\�b�h�e�X�g.
     * �r���[����ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetView_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setView("View1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("State#View", event1.getPropertyName());
        assertEquals("View1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        state.setView("View2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("State#View", event2.getPropertyName());
        assertEquals("View2", event2.getNewValue());
        assertEquals("View1", event2.getOldValue());
    }
    
    /**
     * getView���\�b�h�e�X�g.
     * �r���[�X�e�[�g�łȂ��ꍇ�AgetView���\�b�h��null��Ԃ����Ƃ��e�X�g����B
     * 
     */
    public void testGetView_ReturnNull() {
        State state = new State(State.ACTION_STATE);
        
        assertNull(state.getView());
        
        state.setView("NewView");
        
        assertNull(state.getView());
    }
    
    /**
     * setX���\�b�h�e�X�g.
     * X���W��ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetX_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setX(1);
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("State#X", event1.getPropertyName());
        assertEquals(1, ((Integer) event1.getNewValue()).intValue());
        assertEquals(0, ((Integer) event1.getOldValue()).intValue());
        
        state.setX(2);
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("State#X", event2.getPropertyName());
        assertEquals(2, ((Integer) event2.getNewValue()).intValue());
        assertEquals(1, ((Integer) event2.getOldValue()).intValue());
    }
    
    /**
     * setY���\�b�h�e�X�g.
     * Y���W��ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testSetY_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setY(1);
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("State#Y", event1.getPropertyName());
        assertEquals(1, ((Integer) event1.getNewValue()).intValue());
        assertEquals(0, ((Integer) event1.getOldValue()).intValue());
        
        state.setY(2);
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("State#Y", event2.getPropertyName());
        assertEquals(2, ((Integer) event2.getNewValue()).intValue());
        assertEquals(1, ((Integer) event2.getOldValue()).intValue());
    }
    
    /**
     * getEventByName���\�b�h�e�X�g.
     * �C�x���g������C�x���g���擾�ł��邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetEventByName_ReturnEvent() {
        State state = new State(State.VIEW_STATE);
        
        Event event = state.getEventByName("Activity");
        assertNotNull(event);
        assertEquals("Activity", event.getName());
    }
    
    /**
     * getEventByName���\�b�h�e�X�g.
     * ���݂��Ȃ��C�x���g�����w�肷���null���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetEventByName_ReturnNull() {
        State state = new State(State.VIEW_STATE);
        
        Event event = state.getEventByName("foo");
        assertNull(event);
    }
    
    /**
     * getEventByName���\�b�h�e�X�g.
     * �C�x���g����null���w�肵���ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetEventByName_ReturnNullParameterNull() {
        State state = new State(State.VIEW_STATE);
        
        Event event = state.getEventByName(null);
        assertNull(event);
    }
    
    /**
     * getTransitionEventList���\�b�h�e�X�g.
     * �J�ڃC�x���g��Ԃ����Ƃ��e�X�g����B
     * 
     */
    public void testGetTransitionEventList_ReturnTheTransitionEventList() {
        State state = new State(State.ACTION_STATE);
        
        Event transitionEvent1 = new Event(Event.TRANSITION_EVENT);
        transitionEvent1.setName("event1");
        state.addEvent(transitionEvent1);
        
        Event transitionEvent2 = new Event(Event.TRANSITION_EVENT);
        transitionEvent2.setName("event2");
        state.addEvent(transitionEvent2);
        
        assertEquals(2, state.getTransitionEventList().size());
        
        for (Event event : state.getTransitionEventList()) {
            if (event.getName().equals("event1")) {
                assertEquals(transitionEvent1, event);
            } else if (event.getName().equals("event2")) {
                assertEquals(transitionEvent2, event);
            } else {
                fail();
            }
        }
    }
    
    /**
     * getTransitionEventList���\�b�h�e�X�g.
     * �J�ڃC�x���g���Ȃ��ꍇ�͋�̃��X�g��Ԃ����Ƃ��e�X�g����B
     * 
     */
    public void testGetTransitionEventList_ReturnEmptyList() {
        State state = new State(State.ACTION_STATE);
        
        assertEquals(0, state.getTransitionEventList().size());
    }
    
    /**
     * addEvent���\�b�h�e�X�g.
     * �C�x���g��ǉ�����Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testAddEventInvokeListener() {
        State state = new State(State.VIEW_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        state.addEvent(internalEvent);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("State#Event", event.getPropertyName());
        assertEquals(internalEvent, event.getNewValue());
        assertEquals(null, event.getOldValue());
    }
    
    /**
     * addEvent���\�b�h�e�X�g.
     * �J�ڃC�x���g��ǉ������ꍇ�A���̑J�ڐ�̃X�e�[�g�̃��X�i�[���Ăяo�����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testAddEvent_IfTransitionEvent_InvokeListenerThatTargetStateHave() {
        State sourceState = new State(State.VIEW_STATE);
        State targetState = new State(State.ACTION_STATE);
        TestPropertyChangeListener targetListener = 
                            new TestPropertyChangeListener();
        targetState.addPropertyChangeListener(targetListener);
        
        Event transitionEvent = new Event(Event.TRANSITION_EVENT);
        transitionEvent.setNextState(targetState);
        
        sourceState.addEvent(transitionEvent);
        
        PropertyChangeEvent event = targetListener.getPropertyChangeEvent();
        assertEquals("State#TransitionEvent", event.getPropertyName());
        assertEquals(transitionEvent, event.getNewValue());
        assertNull(event.getOldValue());
    }
    
    /**
     * removeEvent���\�b�h�e�X�g.
     * �C�x���g���폜����Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     * 
     */
    public void testRemoveEvent_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        state.removeEvent(internalEvent);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("State#Event", event.getPropertyName());
        assertEquals(null, event.getNewValue());
        assertEquals(internalEvent, event.getOldValue());
    }
    
    /**
     * removeEvent���\�b�h�e�X�g.
     * �J�ڃC�x���g���폜�����ꍇ�̂ݑJ�ڐ�̃X�e�[�g�̃��X�i�[���Ăяo�����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testRemoveEvent_IfTransitionEvent_InvokeListenerThatTargetStateHave() {
        State sourceState = new State(State.VIEW_STATE);
        State targetState = new State(State.ACTION_STATE);
        TestPropertyChangeListener targetListener = 
            new TestPropertyChangeListener();
        targetState.addPropertyChangeListener(targetListener);
        
        Event transitionEvent = new Event(Event.TRANSITION_EVENT);
        transitionEvent.setNextState(targetState);
        
        sourceState.addEvent(transitionEvent);
        sourceState.removeEvent(transitionEvent);
        
        PropertyChangeEvent event = targetListener.getPropertyChangeEvent();
        assertEquals("State#TransitionEvent", event.getPropertyName());
        assertEquals(null, event.getNewValue());
        assertEquals(transitionEvent, event.getOldValue());
        
        assertEquals(0, transitionEvent.getPropertyChangeListener().length);
    }
    
    /**
     * generateEventName���\�b�h�e�X�g.
     * �C�x���g���𐶐�����ƑJ�ڐ�X�e�[�g+"From"+�J�ڌ��X�e�[�g��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventName_ReturnEventName() {
        State state = new State(State.ACTION_STATE);
        state.setName("SourceState");
        
        String eventName = state.generateEventName("TargetState");
        
        assertEquals("TargetStateFromSourceState", eventName);
    }
    
    /**
     * generateEventName���\�b�h�e�X�g.
     * ���������C�x���g�������ɃX�e�[�g�ɂ���ꍇ�A�A�Ԃ��C���N��
     * �����g���ꂽ�C�x���g������������邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventName_ReturnUsableEventName() {
        State state = new State(State.ACTION_STATE);
        state.setName("SourceState");
        
        Event event = new Event(Event.TRANSITION_EVENT);
        event.setName("TargetStateFromSourceState");
        state.addEvent(event);
        
        String eventName = state.generateEventName("TargetState");
        assertEquals("TargetStateFromSourceState2", eventName);
    }
    
    /**
     * generateEventName���\�b�h�e�X�g.
     * �X�e�[�g����null�̏ꍇ�A���X�e�[�g����null�̏ꍇ��
     * ���������X�e�[�g����null�ł��邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventName_ReturnNull() {
        State state = new State(State.ACTION_STATE);
        
        assertNull(state.generateEventName("TargetState"));
        
        state.setName("State1");
        assertNull(state.generateEventName(null));
    }
    
    /**
     * generateEventName���\�b�h�e�X�g.
     * �C�j�V�����X�e�[�g�̑J�ڃC�x���g����null�ɂȂ邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventNameShouldReturnTheInitialEvent() {
        State state = new State(State.INITIAL_STATE);
        
        assertEquals("(FirstState)", state.generateEventName("FirstState"));
    }
    
    /**
     * generateEventName���\�b�h�e�X�g.
     * �����C�x���g�̃C�x���g����On + �X�e�[�g���ɂȂ邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventNameShouldReturnTheInternalEventName() {
        State state = new State(State.VIEW_STATE);
        state.setName("DisplayForm");
        
        assertEquals("OnDisplayForm", state.generateEventName(state.getName()));
    }
    
    /**
     * checkUsableEventName���\�b�h�e�X�g.
     * �X�e�[�g�ɒǉ�����Ă��Ȃ��C�x���g�����w�肵���ꍇ��true��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableEventName_CheckUsableEventName() {
        State state = new State(State.VIEW_STATE);
        Event event = new Event(Event.INTERNAL_EVENT);
        event.setName("Event1");
        state.addEvent(event);
        
        assertTrue(state.checkUsableEventName("Event2"));
    }
    
    /**
     * checkUsableEventName���\�b�h�e�X�g.
     * �X�e�[�g�ɒǉ�����Ă���C�x���g�����w�肵���ꍇ��false��
     * �Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableEventName_CheckUnusableEventName() {
        State state = new State(State.VIEW_STATE);
        Event event = new Event(Event.TRANSITION_EVENT);
        event.setName("Event1");
        state.addEvent(event);

        assertFalse(state.checkUsableEventName("Event1"));
    }
    
    /**
     * checkUsableEventName���\�b�h�e�X�g.
     * �w�肳�ꂽ�C�x���g����null�̏ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCheckUsableEventName_CheckNull() {
        State state = new State(State.VIEW_STATE);
        
        assertFalse(state.checkUsableEventName(null));
    }
    
    /**
     * getName/setName ���\�b�h�e�X�g.
     * �X�e�[�g���ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetNameShouldReturnNull() {
        State state = new State(State.VIEW_STATE);
        
        state.setName("");
        assertNull(state.getName());
    }
    
    /**
     * getSummary/setSummary ���\�b�h�e�X�g.
     * �T�v�ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetSummaryShouldReturnNull() {
        State state = new State(State.VIEW_STATE);
        
        state.setSummary("");
        assertNull(state.getSummary());
    }
    
    /**
     * getView/setView ���\�b�h�e�X�g.
     * �r���[���ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetViewShouldReturnNull() {
        State state = new State(State.VIEW_STATE);
        
        state.setView("");
        assertNull(state.getView());
    }
}
