// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;

import junit.framework.TestCase;

/**
 * �C�x���g�e�X�g�N���X.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.model.event
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class EventTest extends TestCase {

    /**
     * �r���g�C���C�x���g�𐶐�����ƁA�C�x���g�^�C�v���r���g�C���C�x���g
     * �ł��邱�Ƃ��e�X�g����.
     * 
     */
    public void testCreateBuiltinEvent() {
        Event event = new Event(Event.BUILTIN_EVENT);
        
        assertEquals(Event.BUILTIN_EVENT, event.getType());
    }
    
    /**
     * �����C�x���g�𐶐�����ƁA�C�x���g�^�C�v�������C�x���g
     * �ł��邱�Ƃ��e�X�g����.
     * 
     */
    public void testCreateInternalEvent() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertEquals(Event.INTERNAL_EVENT, event.getType());
    }
    
    /**
     * �J�ڃC�x���g�𐶐�����ƁA�C�x���g�^�C�v���J�ڃC�x���g
     * �ł��邱�Ƃ��e�X�g����.
     * 
     */
    public void testCreateTransitionEvent() {
        Event event = new Event(Event.TRANSITION_EVENT);
        
        assertEquals(Event.TRANSITION_EVENT, event.getType());
    }
    
    /**
     * �C�x���g�^�C�v�ɕs���Ȓl���Z�b�g����ƁA�C�x���g�^�C�v��
     * �s���ŕԂ���邱�Ƃ��e�X�g����.
     */
    public void testCreateUnknownEvent() {
        Event event = new Event(9999);
        
        assertEquals(Event.UNKNOWN_EVENT, event.getType());
    }

    /**
     * setName���\�b�h�e�X�g.
     * �C�x���g����ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     */
    public void testSetNameShouldInvokeListener() {
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        internalEvent.addPropertyChangeListener(listener);
        
        internalEvent.setName("Event1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("Event#Name", event1.getPropertyName());
        assertEquals("Event1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        internalEvent.setName("Event2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("Event#Name", event2.getPropertyName());
        assertEquals("Event2", event2.getNewValue());
        assertEquals("Event1", event2.getOldValue());
    }
    
    /**
     * setNextState���\�b�h�e�X�g.
     * ���X�e�[�g��ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     */
    public void testSetNextStateShouldInvokeListener() {
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        internalEvent.addPropertyChangeListener(listener);
        
        State state1 = new State(State.VIEW_STATE);
        internalEvent.setNextState(state1);
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("Event#NextState", event1.getPropertyName());
        assertEquals(state1, event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        State state2 = new State(State.ACTION_STATE);
        internalEvent.setNextState(state2);
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("Event#NextState", event2.getPropertyName());
        assertEquals(state2, event2.getNewValue());
        assertEquals(state1, event2.getOldValue());
    }
    
    /**
     * setEventHandler���\�b�h�e�X�g.
     * �C�x���g�n���h����ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     */
    public void testSetEventHandlerShouldInvokeListener() {
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        internalEvent.addPropertyChangeListener(listener);
        
        internalEvent.setEventHandler("TestClass1:TestMethod1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("Event#EventHandler", event1.getPropertyName());
        assertEquals("TestClass1:TestMethod1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        internalEvent.setEventHandler("TestClass2:TestMethod2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("Event#EventHandler", event2.getPropertyName());
        assertEquals("TestClass2:TestMethod2", event2.getNewValue());
        assertEquals("TestClass1:TestMethod1", event2.getOldValue());
    }
    
    /**
     * setGuardEventHandler���\�b�h�e�X�g.
     * �C�x���g�n���h����ݒ肷��Ɠo�^�������X�i�[���Ăяo����邱�Ƃ��e�X�g����B
     */
    public void testSetGuardEventHandlerShouldInvokeListener() {
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        internalEvent.addPropertyChangeListener(listener);
        
        internalEvent.setGuardEventHandler("TestClass1:TestMethod1");
        
        PropertyChangeEvent event1 = listener.getPropertyChangeEvent();
        assertEquals("Event#GuardEventHandler", event1.getPropertyName());
        assertEquals("TestClass1:TestMethod1", event1.getNewValue());
        assertEquals(null, event1.getOldValue());
        
        internalEvent.setGuardEventHandler("TestClass2:TestMethod2");
        
        PropertyChangeEvent event2 = listener.getPropertyChangeEvent();
        assertEquals("Event#GuardEventHandler", event2.getPropertyName());
        assertEquals("TestClass2:TestMethod2", event2.getNewValue());
        assertEquals("TestClass1:TestMethod1", event2.getOldValue());
    }
    
    /**
     * generateEventHandlerMethodName���\�b�h�e�X�g.
     * �C�x���g�n���h�����𐶐������do+�C�x���g�����Ԃ���邱��
     * ���e�X�g����B
     */
    public void testGenerateEventHandlerMethodName_ReturnEventHandlerMethodName() {
        Event event = new Event(Event.TRANSITION_EVENT);
        event.setName("State2FromState1");
        String eventHandlerName = 
                    event.generateEventHandlerMethodName();
        
        assertEquals("doState2FromState1", eventHandlerName);
    }
    
    /**
     * generateEventHandlerMethodName���\�b�h�e�X�g.
     * �C�x���g����null�̏ꍇ�͐��������C�x���g�n���h���̃��\�b
     * �h����null�ł��邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventHandlerMethodName_ReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertNull(event.generateEventHandlerMethodName());
    }    
    

    /**
     * getName/setName ���\�b�h�e�X�g.
     * �C�x���g���ɋ󕶎���n�����ꍇ�Anull���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetNameShouldReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setName("");
        assertNull(event.getName());
    }
    
    /**
     * getEventHandler/setEventHandler ���\�b�h�e�X�g.
     * �C�x���g�n���h����"�N���X��:���\�b�h��"��n�����ꍇ�A�C�x���g�n
     * ���h�����������Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetEventHandlerShouldReturnStringObject() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setEventHandler("TestClass:TestMethod");
        assertEquals("TestClass:TestMethod", event.getEventHandler());
        assertEquals("TestClass", event.getEventHandlerClassName());
        assertEquals("TestMethod", event.getEventHandlerMethodName());
    }
    
    /**
     * getEventHandler/setEventHandler ���\�b�h�e�X�g.
     * �C�x���g�n���h����"�N���X��:"��n�����ꍇ�A�C�x���g�n���h����
     * �N���X���ȊO��null�ŕԂ����Ƃ��e�X�g����B
     * 
     */
    public void testGetEventHandlerShouldReturnNullBecauseOfTheMethodNameIsEmpty() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setEventHandler("TestClass:");
        assertNull(event.getEventHandler());
        assertEquals("TestClass", event.getEventHandlerClassName());
        assertNull(event.getEventHandlerMethodName());
    }
    
    /**
     * getEventHandler/setEventHandler ���\�b�h�e�X�g.
     * �C�x���g�n���h����":���\�b�h��"��n�����ꍇ�A�C�x���g�n���h����
     * �N���X����null�ŕԂ����Ƃ��e�X�g����B
     * 
     */
    public void testGetEventHandlerShouldReturnTheMethodNameBecauseOfTheClassNameIsEmpty() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setEventHandler(":TestMethod");
        assertEquals("TestMethod", event.getEventHandler());
        assertNull(null, event.getEventHandlerClassName());
        assertEquals("TestMethod", event.getEventHandlerMethodName());
    }
    
    /**
     * getEventHandler/setEventHandler ���\�b�h�e�X�g.
     * �C�x���g�n���h���ɋ󕶎���n�����ꍇ�A�C�x���g�n���h�����g��
     * null�ŕԂ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetEventHandlerShouldReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setEventHandler("");
        assertNull(event.getEventHandler());
        assertNull(event.getEventHandlerClassName());
        assertNull(event.getEventHandlerMethodName());
    }
    
    /**
     * getGuardEventHandler/setGuardEventHandler ���\�b�h�e�X�g.
     * ���[�ƃC�x���g�n���h����"�N���X��:���\�b�h��"��n�����ꍇ�A�K�[
     * �h�C�x���g�n���h�����������Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testGetGuardEventHandlerShouldReturnStringObject() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setGuardEventHandler("TestClass:TestMethod");
        assertEquals("TestClass:TestMethod", event.getGuardEventHandler());
        assertEquals("TestClass", event.getGuardEventHandlerClassName());
        assertEquals("TestMethod", event.getGuardEventHandlerMethodName());
    }
    
    /**
     * getGuardEventHandler/setGuardEventHandler ���\�b�h�e�X�g.
     * �K�[�h�C�x���g�n���h����"�N���X��:"��n�����ꍇ�A�K�[�h�C�x���g�n
     * ���h���̓N���X���ȊO��null�ŕԂ����Ƃ��e�X�g����B
     * 
     */
    public void testGetGuardEventHandlerShouldReturnNullBecauseOfTheMethodNameIsEmpty() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setGuardEventHandler("TestClass:");
        assertNull(event.getGuardEventHandler());
        assertEquals("TestClass", event.getGuardEventHandlerClassName());
        assertNull(event.getGuardEventHandlerMethodName());
    }
    
    /**
     * getGuardEventHandler/setEventHandler ���\�b�h�e�X�g.
     * �K�[�h�C�x���g�n���h����":���\�b�h��"��n�����ꍇ�A�K�[�h�C�x���g
     * �n���h���̓N���X����null�ŕԂ����Ƃ��e�X�g����B
     * 
     */
    public void testGetGuardEventHandlerShouldReturnTheMethodNameBecauseOfTheClassNameIsEmpty() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setGuardEventHandler(":TestMethod");
        assertEquals("TestMethod", event.getGuardEventHandler());
        assertNull(null, event.getGuardEventHandlerClassName());
        assertEquals("TestMethod", event.getGuardEventHandlerMethodName());
    }
    
    /**
     * getGuardEventHandler/setGuardEventHandler ���\�b�h�e�X�g.
     * �K�[�h�ɋ󕶎���n�����ꍇ�A�K�[�h���g��null�ŕԂ���邱�Ƃ�
     * �e�X�g����B
     * 
     */
    public void testGetGuardEventHandlerShouldReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setGuardEventHandler("");
        assertNull(event.getGuardEventHandler());
        assertNull(event.getGuardEventHandlerClassName());
        assertNull(event.getGuardEventHandlerMethodName());
    }
    
    /**
     * getNextState/setNextState ���\�b�h�e�X�g.
     * �r���g�C�x���g�ɂ͎��X�e�[�g��ݒ�ł��Ȃ����Ƃ��e�X�g����B
     * 
     */
    public void testGetNextStateShouldReturnNullBecauseOfTheEventIsBuiltin() {
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setNextState(new State(State.VIEW_STATE));
        assertNull(builtinEvent.getNextState());
    }
    
    /**
     * getGuardEventHandler/setGuardEventHandler ���\�b�h�e�X�g.
     * �r���g�C�x���g�ɂ̓K�[�h�C�x���g�n���h����ݒ�ł��Ȃ����Ƃ��e�X�g����B
     * 
     */
    public void testGetGuardEventHandlerShouldReturnNullBecauseOfTheEventIsBuiltin() {
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setGuardEventHandler("TestClass:TestMethod");
        assertNull(builtinEvent.getGuardEventHandler());
    }
}
