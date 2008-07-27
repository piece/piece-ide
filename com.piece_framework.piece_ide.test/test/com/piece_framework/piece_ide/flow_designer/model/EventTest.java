// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;

import junit.framework.TestCase;

/**
 * イベントテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.event
 *
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 *
 */
public class EventTest extends TestCase {

    /**
     * ビルトインイベントを生成すると、イベントタイプがビルトインイベント
     * であることをテストする.
     */
    public void testCreateBuiltinEvent() {
        Event event = new Event(Event.BUILTIN_EVENT);

        assertEquals(Event.BUILTIN_EVENT, event.getType());
    }

    /**
     * 内部イベントを生成すると、イベントタイプが内部イベント
     * であることをテストする.
     */
    public void testCreateInternalEvent() {
        Event event = new Event(Event.INTERNAL_EVENT);

        assertEquals(Event.INTERNAL_EVENT, event.getType());
    }

    /**
     * 遷移イベントを生成すると、イベントタイプが遷移イベント
     * であることをテストする.
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
     * setNameメソッドテスト.
     * イベント名を設定すると登録したリスナーが呼び出されることをテストする。
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
     * setNextStateメソッドテスト.
     * 次ステートを設定すると登録したリスナーが呼び出されることをテストする。
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
     * setEventHandlerメソッドテスト.
     * イベントハンドラを設定すると登録したリスナーが呼び出されることをテストする。
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
     * setGuardEventHandlerメソッドテスト.
     * イベントハンドラを設定すると登録したリスナーが呼び出されることをテストする。
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
     */
    public void testGenerateEventHandlerMethodName_ReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);

        assertNull(event.generateEventHandlerMethodName());
    }

    /**
     * getName/setName メソッドテスト.
     * イベント名に空文字を渡した場合、nullが返されることをテストする。
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
     */
    public void testGetGuardEventHandlerShouldReturnNull() {
        Event event = new Event(Event.INTERNAL_EVENT);

        event.setGuardEventHandler("");
        assertNull(event.getGuardEventHandler());
        assertNull(event.getGuardEventHandlerClassName());
        assertNull(event.getGuardEventHandlerMethodName());
    }

    /**
     * getNextState/setNextState メソッドテスト.
     * ビルトイベントには次ステートを設定できないことをテストする。
     */
    public void testGetNextStateShouldReturnNullBecauseOfTheEventIsBuiltin() {
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setNextState(new State(State.VIEW_STATE));
        assertNull(builtinEvent.getNextState());
    }

    /**
     * getGuardEventHandler/setGuardEventHandler メソッドテスト.
     * ビルトイベントにはガードイベントハンドラを設定できないことをテストする。
     */
    public void testGetGuardEventHandlerShouldReturnNullBecauseOfTheEventIsBuiltin() {
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setGuardEventHandler("TestClass:TestMethod");
        assertNull(builtinEvent.getGuardEventHandler());
    }
}
