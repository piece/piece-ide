package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;

import junit.framework.TestCase;

/**
 * イベントテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.event
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class EventTest extends TestCase {

    /**
     * ビルトインイベントを生成すると、イベントタイプがビルトインイベント
     * であることをテストする.
     * 
     */
    public void testCreateBuiltinEvent() {
        Event event = new Event(Event.BUILTIN_EVENT);
        
        assertEquals(Event.BUILTIN_EVENT, event.getType());
    }
    
    /**
     * 内部イベントを生成すると、イベントタイプが内部イベント
     * であることをテストする.
     * 
     */
    public void testCreateInternalEvent() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertEquals(Event.INTERNAL_EVENT, event.getType());
    }
    
    /**
     * 遷移イベントを生成すると、イベントタイプが遷移イベント
     * であることをテストする.
     * 
     */
    public void testCreateTransitionEvent() {
        Event event = new Event(Event.TRANSITION_EVENT);
        
        assertEquals(Event.TRANSITION_EVENT, event.getType());
    }
    
    /**
     * イベントタイプに不正な値をセットすると、イベントタイプが
     * 不明で返されることをテストする.
     */
    public void testCreateUnknownEvent() {
        Event event = new Event(9999);
        
        assertEquals(Event.UNKNOWN_EVENT, event.getType());
    }
    
    /**
     * プロパティーを変更するとイベントが通知されることをテストする.
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
     * generateEventHandlerMethodNameメソッドテスト.
     * イベントハンドラ名を生成するとdo+イベント名+"On"+ステート名が
     * 返されることをテストする。
     */
    public void testGenerateEventHandlerMethodName_ReturnEventHandlerMethodName() {
        Event event = new Event(Event.TRANSITION_EVENT);
        event.setName("State2FromState1");
        String eventHandlerName = 
                    event.generateEventHandlerMethodName();
        
        assertEquals("doState2FromState1", eventHandlerName);
    }
    
    /**
     * generateEventHandlerMethodNameメソッドテスト.
     * ステート名がnullの場合、イベント名がnullの場合は生成されるイベ
     * ントハンドラのメソッド名がnullであることをテストする。
     * 
     */
    public void testGenerateEventHandlerMethodName_ReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertNull(event.generateEventHandlerMethodName());
    }    
    
    /**
     * イベントの変更通知をチェックする.
     * 
     * @param listener リスナー
     * @param event 対象のイベント
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
