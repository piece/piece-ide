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
     * �v���p�e�B�[��ύX����ƃC�x���g���ʒm����邱�Ƃ��e�X�g����.
     * 
     */
    public void testInvokeListener() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        event.addPropertyChangeListener(listener);
        
        event.setName("event1");
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
        
        event.setNextState(new State(State.ACTION_STATE));
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
        
        event.setEventHandler("class1", "method1");
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
        
        event.setGuardEventHandler("guard_class1", "guard_method1");
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
    }
    
    /**
     * generateEventHandlerMethodName���\�b�h�e�X�g.
     * �C�x���g�n���h�����𐶐������do+�C�x���g��+"On"+�X�e�[�g����
     * �Ԃ���邱�Ƃ��e�X�g����B
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
     * �X�e�[�g����null�̏ꍇ�A�C�x���g����null�̏ꍇ�͐��������C�x
     * ���g�n���h���̃��\�b�h����null�ł��邱�Ƃ��e�X�g����B
     * 
     */
    public void testGenerateEventHandlerMethodName_ReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertNull(event.generateEventHandlerMethodName());
    }    
    
    /**
     * �C�x���g�̕ύX�ʒm���`�F�b�N����.
     * 
     * @param listener ���X�i�[
     * @param event �Ώۂ̃C�x���g
     */
    private void assertEvent(
                        TestPropertyChangeListener listener, Event event) {
        PropertyChangeEvent propertyChangeEvent = 
                                listener.getPropertyChangeEvent();
        assertNotNull(propertyChangeEvent);
        assertEquals("event", propertyChangeEvent.getPropertyName());
        assertNull(propertyChangeEvent.getOldValue());
        assertEquals(event, (Event) propertyChangeEvent.getNewValue());
    }
}
