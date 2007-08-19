// $Id$
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
        
        event.setEventHandler("class1:method1");
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
        
        event.setGuardEventHandler("guard_class1:guard_method1");
        assertEvent(listener, event);
        listener.initializePropertyChangeEvent();
        assertNull(listener.getPropertyChangeEvent());
    }
    
    /**
     * generateEventHandlerMethodNameメソッドテスト.
     * イベントハンドラ名を生成するとdo+イベント名が返されること
     * をテストする。
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
     * イベント名がnullの場合は生成されるイベントハンドラのメソッ
     * ド名がnullであることをテストする。
     * 
     */
    public void testGenerateEventHandlerMethodName_ReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        assertNull(event.generateEventHandlerMethodName());
    }    
    

    /**
     * getName/setName メソッドテスト.
     * イベント名に空文字を渡した場合、nullが返されることをテストする。
     * 
     */
    public void testGetNameShouldReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);
        
        event.setName("");
        assertNull(event.getName());
    }
    
    /**
     * getEventHandler/setEventHandler メソッドテスト.
     * イベントハンドラに"クラス名:メソッド名"を渡した場合、イベントハ
     * ンドラが正しく返されることをテストする。
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
     * getEventHandler/setEventHandler メソッドテスト.
     * イベントハンドラに"クラス名:"を渡した場合、イベントハンドラは
     * クラス名以外をnullで返すことをテストする。
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
     * getEventHandler/setEventHandler メソッドテスト.
     * イベントハンドラに":メソッド名"を渡した場合、イベントハンドラは
     * クラス名をnullで返すことをテストする。
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
     * getEventHandler/setEventHandler メソッドテスト.
     * イベントハンドラに空文字を渡した場合、イベントハンドラ自身が
     * nullで返されることをテストする。
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
     * getGuardEventHandler/setGuardEventHandler メソッドテスト.
     * がーとイベントハンドラに"クラス名:メソッド名"を渡した場合、ガー
     * ドイベントハンドラが正しく返されることをテストする。
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
     * getGuardEventHandler/setGuardEventHandler メソッドテスト.
     * ガードイベントハンドラに"クラス名:"を渡した場合、ガードイベントハ
     * ンドラはクラス名以外をnullで返すことをテストする。
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
     * getGuardEventHandler/setEventHandler メソッドテスト.
     * ガードイベントハンドラに":メソッド名"を渡した場合、ガードイベント
     * ハンドラはクラス名をnullで返すことをテストする。
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
     * getGuardEventHandler/setGuardEventHandler メソッドテスト.
     * ガードに空文字を渡した場合、ガード自身がnullで返されることを
     * テストする。
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
