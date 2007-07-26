package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import junit.framework.TestCase;

/**
 * ステートテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.state
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class StateTest extends TestCase {
  
    /**
     * コンストラクタテスト.
     * イニシャルステートを生成すると、自動的にInitializeイベントが
     * 生成されることをテストする。
     * 
     */
    public void testCreateInitialState_CheckBuiltinEvent() {
        State state = new State(State.INITIAL_STATE);
        
        assertEquals(1, state.getEventList().size());
        
        Event event = state.getEventList().get(0);
        assertEquals(Event.BUILTIN_EVENT, event.getType());
        assertEquals("Initial", event.getName());
        assertNull(event.getNextState());
        
        EventHandler eventHandler = event.getEventHandler();
        assertEquals(null, eventHandler.getClassName());
        assertEquals("doInitial", eventHandler.getMethodName());
        
        state.setName("State1");
        assertEquals("doInitial", 
                    event.getEventHandler().getMethodName());
    }
    
    /**
     * コンストラクタテスト.
     * ビューステートを生成すると、自動的にEntry,Exit,Activityイベントが
     * 生成されることをテストする。
     * 
     */
    public void testCreateViewState_CheckBuiltinEvent() {
        State state = new State(State.VIEW_STATE);
        
        assertEquals(3, state.getEventList().size());
        
        for (Event event : state.getEventList()) {
            assertEquals(Event.BUILTIN_EVENT, event.getType());
            assertNull(event.getNextState());
            
            EventHandler eventHandler = event.getEventHandler();
            if (event.getName().equals("Entry")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doEntry", eventHandler.getMethodName());
            } else if (event.getName().equals("Exit")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doExit", eventHandler.getMethodName());
            } else if (event.getName().equals("Activity")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doActivity", eventHandler.getMethodName());
            } else {
                fail();
            }
        }
        
        state.setName("State1");
        for (Event event : state.getEventList()) {
            EventHandler eventHandler = event.getEventHandler();
            if (event.getName().equals("Entry")) {
                assertEquals("doEntryOnState1", eventHandler.getMethodName());
            } else if (event.getName().equals("Exit")) {
                assertEquals("doExitOnState1", eventHandler.getMethodName());
            } else if (event.getName().equals("Activity")) {
                assertEquals("doActivityOnState1", 
                            eventHandler.getMethodName());
            } else {
                fail();
            }
        }
    }
    
    /**
     * コンストラクタテスト.
     * アクションテートを生成すると、自動的にEntry,Exit,Activityイベントが
     * 生成されることをテストする。
     * 
     */
    public void testCreateActionState_CheckBuiltinEvent() {
        State state = new State(State.ACTION_STATE);
        
        assertEquals(3, state.getEventList().size());
        
        for (Event event : state.getEventList()) {
            assertEquals(Event.BUILTIN_EVENT, event.getType());
            assertNull(event.getNextState());
            
            EventHandler eventHandler = event.getEventHandler();
            if (event.getName().equals("Entry")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doEntry", eventHandler.getMethodName());
            } else if (event.getName().equals("Exit")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doExit", eventHandler.getMethodName());
            } else if (event.getName().equals("Activity")) {
                assertEquals(null, eventHandler.getClassName());
                assertEquals("doActivity", eventHandler.getMethodName());
            } else {
                fail();
            }
        }

        state.setName("State1");
        for (Event event : state.getEventList()) {
            EventHandler eventHandler = event.getEventHandler();
            if (event.getName().equals("Entry")) {
                assertEquals("doEntryOnState1", eventHandler.getMethodName());
            } else if (event.getName().equals("Exit")) {
                assertEquals("doExitOnState1", eventHandler.getMethodName());
            } else if (event.getName().equals("Activity")) {
                assertEquals("doActivityOnState1", 
                            eventHandler.getMethodName());
            } else {
                fail();
            }
        }
    }
    
    /**
     * コンストラクタテスト.
     * ファイナルステートを生成すると、自動的にFinalizeイベントが
     * 生成されることをテストすることをテストする。
     * 
     */
    public void testCreateFinalState_CheckBuiltinEvent() {
        State state = new State(State.FINAL_STATE);
        
        assertEquals(1, state.getEventList().size());
        
        Event event = state.getEventList().get(0);
        assertEquals(Event.BUILTIN_EVENT, event.getType());
        assertEquals("Final", event.getName());
        assertNull(event.getNextState());
        
        EventHandler eventHandler = event.getEventHandler();
        assertEquals(null, eventHandler.getClassName());
        assertEquals("doFinal", eventHandler.getMethodName());
        
        state.setName("State1");
        assertEquals("doFinal", 
                    event.getEventHandler().getMethodName());
    }
    
    /**
     * コンストラクタテスト.
     * 不正なステートタイプを渡すと、ステートタイプが不明のステートが
     * 生成されることをテストする。
     * 
     */
    public void testUnknownState_TypeIsUnknown() {
        State state = new State(9999);
        
        assertEquals(State.UNKNOWN_STATE, state.getType());
    }
    
    /**
     * addPropertyChangeListenerメソッドテスト.
     * ステートにリスナーを登録すると、ステートが保持している
     * イベントにもリスナーが登録される。
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
     * removePropertyChangeListenerメソッドテスト.
     * ステートからリスナーを削除すると、ステートが保持している
     * イベントからもリスナーが削除される。
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
     * setNameメソッドテスト.
     * ステート名を設定すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testSetName_InvokeListener() {
        State state = new State(State.ACTION_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setName("OldName");
        state.setName("NewName");
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("name", event.getPropertyName());
        assertEquals("NewName", event.getNewValue());
        assertEquals("OldName", event.getOldValue());
    }
    
    /**
     * setSummaryメソッドテスト.
     * 概要を設定すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testSetSummary_InvokeListener() {
        State state = new State(State.ACTION_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setSummary("OldSummary");
        state.setSummary("NewSummary");
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("summary", event.getPropertyName());
        assertEquals("NewSummary", event.getNewValue());
        assertEquals("OldSummary", event.getOldValue());
    }
    
    /**
     * setViewメソッドテスト.
     * ビュー名を設定すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testSetView_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setView("OldView");
        state.setView("NewView");
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("view", event.getPropertyName());
        assertEquals("NewView", event.getNewValue());
        assertEquals("OldView", event.getOldValue());
    }
    
    /**
     * getViewメソッドテスト.
     * ビューステートでない場合、getViewメソッドはnullを返すことをテストする。
     * 
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
     * 
     */
    public void testSetX_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setX(1);
        state.setX(2);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("x", event.getPropertyName());
        assertEquals(2, ((Integer) event.getNewValue()).intValue());
        assertEquals(1, ((Integer) event.getOldValue()).intValue());
    }
    
    /**
     * setYメソッドテスト.
     * Y座標を設定すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testSetY_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.setY(1);
        state.setY(2);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("y", event.getPropertyName());
        assertEquals(2, ((Integer) event.getNewValue()).intValue());
        assertEquals(1, ((Integer) event.getOldValue()).intValue());
    }
    
    /**
     * getTransitionEventListメソッドテスト.
     * 遷移イベントを返すことをテストする。
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
     * getTransitionEventListメソッドテスト.
     * 遷移イベントがない場合は空のリストを返すことをテストする。
     * 
     */
    public void testGetTransitionEventList_ReturnEmptyList() {
        State state = new State(State.ACTION_STATE);
        
        assertEquals(0, state.getTransitionEventList().size());
    }
    
    /**
     * addEventメソッドテスト.
     * イベントを追加すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testAddEventInvokeListener() {
        State state = new State(State.VIEW_STATE);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        
        state.addEvent(internalEvent);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("event", event.getPropertyName());
        assertTrue(event.getNewValue() instanceof List);
        assertEquals(4, ((List) event.getNewValue()).size());
        assertEquals(null, event.getOldValue());
    }
    
    /**
     * addEventメソッドテスト.
     * 遷移イベントを追加した場合のみ遷移先のステートのリスナーが呼び出される
     * ことをテストする。
     * 
     */
    public void testAddEvent_IfTransitionEvent_InvokeListenerThatTargetStateHave() {
        State sourceState = new State(State.VIEW_STATE);
        
        State targetState = new State(State.ACTION_STATE);
        TestPropertyChangeListener targetListener = 
                            new TestPropertyChangeListener();
        targetState.addPropertyChangeListener(targetListener);
        
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setNextState(targetState);
        
        sourceState.addEvent(builtinEvent);
        
        assertNull(targetListener.getPropertyChangeEvent());
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        internalEvent.setNextState(targetState);
        
        sourceState.addEvent(internalEvent);
        
        assertNull(targetListener.getPropertyChangeEvent());
        
        Event transitionEvent = new Event(Event.TRANSITION_EVENT);
        transitionEvent.setNextState(targetState);
        
        sourceState.addEvent(transitionEvent);
        
        PropertyChangeEvent event = targetListener.getPropertyChangeEvent();
        assertEquals("target", event.getPropertyName());
        assertNull(event.getNewValue());
        assertNull(event.getOldValue());
    }
    
    /**
     * removeEventメソッドテスト.
     * イベントを削除すると登録したリスナーが呼び出されることをテストする。
     * 
     */
    public void testRemoveEvent_InvokeListener() {
        State state = new State(State.VIEW_STATE);
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        
        state.addEvent(internalEvent);
        
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
        
        state.removeEvent(internalEvent);
        
        PropertyChangeEvent event = listener.getPropertyChangeEvent();
        assertEquals("event", event.getPropertyName());
        assertTrue(event.getNewValue() instanceof List);
        assertEquals(3, ((List) event.getNewValue()).size());
        assertEquals(null, event.getOldValue());
    }
    
    /**
     * removeEventメソッドテスト.
     * 遷移イベントを削除した場合のみ遷移先のステートのリスナーが呼び出される
     * ことをテストする。
     * 
     */
    public void testRemoveEvent_IfTransitionEvent_InvokeListenerThatTargetStateHave() {
        State sourceState = new State(State.VIEW_STATE);
        
        State targetState = new State(State.ACTION_STATE);
        
        Event builtinEvent = new Event(Event.BUILTIN_EVENT);
        builtinEvent.setNextState(targetState);
        
        sourceState.addEvent(builtinEvent);
        
        Event internalEvent = new Event(Event.INTERNAL_EVENT);
        internalEvent.setNextState(targetState);
        
        sourceState.addEvent(internalEvent);
        
        Event transitionEvent = new Event(Event.TRANSITION_EVENT);
        transitionEvent.setNextState(targetState);
        
        sourceState.addEvent(transitionEvent);
        
        TestPropertyChangeListener targetListener = 
            new TestPropertyChangeListener();
        targetState.addPropertyChangeListener(targetListener);
        
        sourceState.removeEvent(builtinEvent);
        
        assertNull(targetListener.getPropertyChangeEvent());
        
        sourceState.removeEvent(internalEvent);
        
        assertNull(targetListener.getPropertyChangeEvent());
        
        sourceState.removeEvent(transitionEvent);
        
        PropertyChangeEvent event = targetListener.getPropertyChangeEvent();
        assertEquals("target", event.getPropertyName());
        assertNull(event.getNewValue());
        assertNull(event.getOldValue());
    }
    
    /**
     * generateEventNameメソッドテスト.
     * イベント名を生成すると遷移先ステート+"From"+遷移元ステートが
     * 返されることをテストする。
     * 
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
     * generateEventNameメソッドテスト.
     * ステート名がnullの場合、次ステート名がnullの場合は
     * 生成されるステート名がnullであることをテストする。
     * 
     */
    public void testGenerateEventName_ReturnNull() {
        State state = new State(State.ACTION_STATE);
        
        assertNull(state.generateEventName("TargetState"));
        
        state.setName("State1");
        assertNull(state.generateEventName(null));
    }
    
    /**
     * checkUsableEventNameメソッドテスト.
     * ステートに追加されていないイベント名を指定した場合はtrueが
     * 返されることをテストする。
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
     * checkUsableEventNameメソッドテスト.
     * ステートに追加されているイベント名を指定した場合はfalseが
     * 返されることをテストする。
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
     * checkUsableEventNameメソッドテスト.
     * 指定されたイベント名がnullの場合はfalseが返されることをテストする。
     * 
     */
    public void testCheckUsableEventName_CheckNull() {
        State state = new State(State.VIEW_STATE);
        
        assertFalse(state.checkUsableEventName(null));
    }
}
