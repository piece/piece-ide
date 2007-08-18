// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import junit.framework.TestCase;


/**
 * フローテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.flow
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowTest extends TestCase {
    
    /**
     * setActionNameメソッドテスト.
     * アクションクラス名を指定した場合はその値が返されることをテストする。
     * 
     */
    public void testSetActionName_ReturnActionName() {
        Flow flow = new Flow("Wizard", "TestAction");
        assertEquals("TestAction", flow.getActionClassName());
    }
    
    /**
     * setActionNameメソッドテスト.
     * アクションクラス名を指定しなかった場合はフロー名＋"Action"が
     * 返されることをテストする。
     * 
     */
    public void testSetActionName_ReturnFlowNamePlusAction() {
        Flow flow = new Flow("Wizard", null);
        assertEquals("WizardAction", flow.getActionClassName());
    }
    
    /**
     * setActionNameメソッドテスト.
     * フロー名、アクションクラス名共にnullの場合はnullが返されることを
     * テストする。
     */
    public void testSetActionName_ReturnNull() {
        Flow flow = new Flow(null, null);
        assertNull(flow.getActionClassName());
    }
    
    /**
     * getStateListToOwnStateメソッドテスト.
     * 指定したステートへ遷移するステートが返されることをテストする。
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
     * getStateListToOwnStateメソッドテスト.
     * 指定したステートに遷移するステートがない場合、空のリストが返される
     * ことをテストする。
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
     * getStateListToOwnStateメソッドテスト.
     * 指定したステートがnullの場合、空のリストが返される
     * ことをテストする。
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
     * getTransitionEventListToOwnStateメソッドテスト.
     * 指定したステートに遷移する遷移イベントが返されることをテストする。
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
     * getTransitionEventListToOwnStateメソッドテスト.
     * 指定したステートに遷移する遷移イベントがない場合、空のリストが返される
     * ことをテストする。
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
     * generateStateNameメソッドテスト.
     * イニシャルステートのステート名が生成されることをテストする。
     * 
     */
    public void testGenerateStateName_ReturnInitialStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("InitialState", 
                flow.generateStateName(State.INITIAL_STATE));
    }
    
    /**
     * generateStateNameメソッドテスト.
     * ファイナルステートのステート名が生成されることをテストする。
     * 
     */
    public void testGenerateStateName_ReturnFinalStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("FinalState", flow.generateStateName(State.FINAL_STATE));
    }
    
    /**
     * generateStateNameメソッドテスト.
     * アクションテートのステート名が生成されるたびに後ろの連番が
     * インクリメントされたステート名が生成されることをテストする。
     * 
     */
    public void testGenerateStateName_ReturnActionStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("Process1", flow.generateStateName(State.ACTION_STATE));
        assertEquals("Process2", flow.generateStateName(State.ACTION_STATE));
        assertEquals("Process3", flow.generateStateName(State.ACTION_STATE));
    }
    
    /**
     * generateStateNameメソッドテスト.
     * ビューテートのステート名が生成されるたびに後ろの連番が
     * インクリメントされたステート名が生成されることをテストする。
     * 
     */
    public void testGenerateStateName_ReturnViewStateName() {
        Flow flow = new Flow(null, null);
        assertEquals("DisplayForm1", flow.generateStateName(State.VIEW_STATE));
        assertEquals("DisplayForm2", flow.generateStateName(State.VIEW_STATE));
        assertEquals("DisplayForm3", flow.generateStateName(State.VIEW_STATE));
    }
    
    /**
     * generateStateNameメソッドテスト.
     * 次に生成されるアクションテートのステート名を持つステートが既に
     * フローにある場合、連番がインクリメントされたステート名が生成さ
     * れることをテストする。
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
     * generateStateNameメソッドテスト.
     * 次に生成されるビューテートのステート名を持つステートが既に
     * フローにある場合、連番がインクリメントされたステート名が生
     * 成されることをテストする。
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
     * checkUsableStateNameメソッドテスト.
     * フローに追加されていないステート名を指定した場合はtrueが
     * 返されることをテストする。
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
     * checkUsableStateNameメソッドテスト.
     * フローに追加されているステート名を指定した場合はfalseが
     * 返されることをテストする。
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
     * checkUsableStateNameメソッドテスト.
     * 指定されたステート名がnullの場合はfalseが返されることをテストする。
     * 
     */
    public void testCheckUsableStateName_CheckNull() {
        Flow flow = new Flow(null, null);
        
        assertFalse(flow.checkUsableStateName(null));
    }
    
    /**
     * addStateメソッドテスト.
     * ステートを追加すると登録したリスナーが呼び出されることをテストする。
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
     * removeStateメソッドテスト.
     * ステートを削除すると登録したリスナーが呼び出されることをテストする。
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
     * getPropertyChangeListメソッドテスト.
     * リスナーが登録されていない場合はサイズ0の配列が返される
     * ことをテストする。
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
     * getStateByNameメソッドテスト.
     * 指定されたステート名のステートが返されることをテストする。
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
     * getStateByNameメソッドテスト.
     * 指定されたステート名がない場合のnullが返されることをテストする。
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
     * getStateByNameメソッドテスト.
     * ステート名にnullを渡した場合、nullが返されることをテストする。
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
     * getName/setName メソッドテスト.
     * フロー名に空文字を渡した場合、nullが返されることをテストする。
     * 
     */
    public void testGetNameShouldReturnNull() {
        Flow flow = new Flow(null, null);
        
        flow.setName("");
        assertNull(flow.getName());
    }
    
    /**
     * getActionClassName/setActionClassName メソッドテスト.
     * アクションクラス名に空文字を渡した場合、nullが返されることをテストする。
     * 
     */
    public void testGetActionClassNameShouldReturnNull() {
        Flow flow = new Flow(null, null);
        
        flow.setActionClassName("");
        assertNull(flow.getActionClassName());
    }
}
