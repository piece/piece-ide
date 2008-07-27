// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

/**
 * ステートテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.state
 *
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 */
public class StateTest extends TestCase {

    /**
     * コンストラクタテスト.
     * イニシャルステートを生成すると、自動的にInitialイベントが
     * 生成されることをテストする。
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
     * コンストラクタテスト.
     * ビューステートを生成すると、自動的にEntry,Exit,Activityイベントが
     * 生成されることをテストする。
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
     * コンストラクタテスト.
     * アクションテートを生成すると、自動的にEntry,Exit,Activityイベントが
     * 生成されることをテストする。
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
     * コンストラクタテスト.
     * ファイナルステートを生成すると、自動的にFinalイベントが
     * 生成されることをテストすることをテストする。
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
     * コンストラクタテスト.
     * 不正なステートタイプを渡すと、ステートタイプが不明のステートが
     * 生成されることをテストする。
     */
    public void testUnknownState_TypeIsUnknown() {
        State state = new State(9999);

        assertEquals(State.UNKNOWN_STATE, state.getType());
    }

    /**
     * addPropertyChangeListenerメソッドテスト.
     * ステートにリスナーを登録すると、ステートが保持している
     * イベントにもリスナーが登録される。
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
     * removePropertyChangeListenerメソッドテスト.
     * ステートからリスナーを削除すると、ステートが保持している
     * イベントからもリスナーが削除される。
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
     * setNameメソッドテスト.
     * ステート名を設定すると登録したリスナーが呼び出されることをテストする。
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
     * setSummaryメソッドテスト.
     * 概要を設定すると登録したリスナーが呼び出されることをテストする。
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
     * setViewメソッドテスト.
     * ビュー名を設定すると登録したリスナーが呼び出されることをテストする。
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
     * getViewメソッドテスト.
     * ビューステートでない場合、getViewメソッドはnullを返すことをテストする。
     */
    public void testGetView_ReturnNull() {
        State state = new State(State.ACTION_STATE);

        assertNull(state.getView());

        state.setView("NewView");

        assertNull(state.getView());
    }

    /**
     * setXメソッドテスト.
     * X座標を設定すると登録したリスナーが呼び出されることをテストする。
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
     * setYメソッドテスト.
     * Y座標を設定すると登録したリスナーが呼び出されることをテストする。
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
     * getEventByNameメソッドテスト.
     * イベント名からイベントを取得できることをテストする。
     */
    public void testGetEventByName_ReturnEvent() {
        State state = new State(State.VIEW_STATE);

        Event event = state.getEventByName("Activity");
        assertNotNull(event);
        assertEquals("Activity", event.getName());
    }

    /**
     * getEventByNameメソッドテスト.
     * 存在しないイベント名を指定するとnullが返されることをテストする。
     */
    public void testGetEventByName_ReturnNull() {
        State state = new State(State.VIEW_STATE);

        Event event = state.getEventByName("foo");
        assertNull(event);
    }

    /**
     * getEventByNameメソッドテスト.
     * イベント名にnullを指定した場合、nullが返されることをテストする。
     */
    public void testGetEventByName_ReturnNullParameterNull() {
        State state = new State(State.VIEW_STATE);

        Event event = state.getEventByName(null);
        assertNull(event);
    }

    /**
     * getTransitionEventListメソッドテスト.
     * 遷移イベントを返すことをテストする。
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
     * getTransitionEventListメソッドテスト.
     * 遷移イベントがない場合は空のリストを返すことをテストする。
     */
    public void testGetTransitionEventList_ReturnEmptyList() {
        State state = new State(State.ACTION_STATE);

        assertEquals(0, state.getTransitionEventList().size());
    }

    /**
     * addEventメソッドテスト.
     * イベントを追加すると登録したリスナーが呼び出されることをテストする。
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
     * addEventメソッドテスト.
     * 遷移イベントを追加した場合、その遷移先のステートのリスナーが呼び出される
     * ことをテストする。
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
     * removeEventメソッドテスト.
     * イベントを削除すると登録したリスナーが呼び出されることをテストする。
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
     * removeEventメソッドテスト.
     * 遷移イベントを削除した場合のみ遷移先のステートのリスナーが呼び出される
     * ことをテストする。
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
     * generateEventNameメソッドテスト.
     * イベント名を生成すると遷移先ステート+"From"+遷移元ステートが
     * 返されることをテストする。
     */
    public void testGenerateEventName_ReturnEventName() {
        State state = new State(State.ACTION_STATE);
        state.setName("SourceState");

        String eventName = state.generateEventName("TargetState");

        assertEquals("TargetStateFromSourceState", eventName);
    }

    /**
     * generateEventNameメソッドテスト.
     * 生成されるイベント名が既にステートにある場合、連番がインクリ
     * メントされたイベント名が生成されることをテストする。
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
     * generateEventNameメソッドテスト.
     * ステート名がnullの場合、次ステート名がnullの場合は
     * 生成されるステート名がnullであることをテストする。
     */
    public void testGenerateEventName_ReturnNull() {
        State state = new State(State.ACTION_STATE);

        assertNull(state.generateEventName("TargetState"));

        state.setName("State1");
        assertNull(state.generateEventName(null));
    }

    /**
     * generateEventNameメソッドテスト.
     * イニシャルステートの遷移イベント名がnullになることをテストする。
     */
    public void testGenerateEventNameShouldReturnTheInitialEvent() {
        State state = new State(State.INITIAL_STATE);

        assertEquals("(FirstState)", state.generateEventName("FirstState"));
    }

    /**
     * generateEventNameメソッドテスト.
     * 内部イベントのイベント名をOn + ステート名になることをテストする。
     */
    public void testGenerateEventNameShouldReturnTheInternalEventName() {
        State state = new State(State.VIEW_STATE);
        state.setName("DisplayForm");

        assertEquals("OnDisplayForm", state.generateEventName(state.getName()));
    }

    /**
     * checkUsableEventNameメソッドテスト.
     * ステートに追加されていないイベント名を指定した場合はtrueが
     * 返されることをテストする。
     */
    public void testCheckUsableEventName_CheckUsableEventName() {
        State state = new State(State.VIEW_STATE);
        Event event = new Event(Event.INTERNAL_EVENT);
        event.setName("Event1");
        state.addEvent(event);

        assertTrue(state.checkUsableEventName("Event2"));
    }

    /**
     * checkUsableEventNameメソッドテスト.
     * ステートに追加されているイベント名を指定した場合はfalseが
     * 返されることをテストする。
     */
    public void testCheckUsableEventName_CheckUnusableEventName() {
        State state = new State(State.VIEW_STATE);
        Event event = new Event(Event.TRANSITION_EVENT);
        event.setName("Event1");
        state.addEvent(event);

        assertFalse(state.checkUsableEventName("Event1"));
    }

    /**
     * checkUsableEventNameメソッドテスト.
     * 指定されたイベント名がnullの場合はfalseが返されることをテストする。
     */
    public void testCheckUsableEventName_CheckNull() {
        State state = new State(State.VIEW_STATE);

        assertFalse(state.checkUsableEventName(null));
    }

    /**
     * getName/setName メソッドテスト.
     * ステート名に空文字を渡した場合、nullが返されることをテストする。
     */
    public void testGetNameShouldReturnNull() {
        State state = new State(State.VIEW_STATE);

        state.setName("");
        assertNull(state.getName());
    }

    /**
     * getSummary/setSummary メソッドテスト.
     * 概要に空文字を渡した場合、nullが返されることをテストする。
     */
    public void testGetSummaryShouldReturnNull() {
        State state = new State(State.VIEW_STATE);

        state.setSummary("");
        assertNull(state.getSummary());
    }

    /**
     * getView/setView メソッドテスト.
     * ビュー名に空文字を渡した場合、nullが返されることをテストする。
     */
    public void testGetViewShouldReturnNull() {
        State state = new State(State.VIEW_STATE);

        state.setView("");
        assertNull(state.getView());
    }
}
