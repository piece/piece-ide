package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowMapper;

/**
 * フローマッパーモデル取得テスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetModelTest extends TestCase {

    /**
     * テストメソッド実行前処理.
     * 
     * @exception Exception 一般例外
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    }
    
    /**
     * getModel メソッドテスト.
     * YAMLから以下のフローを取得できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetModelShouldReturn_Initial_View_Final_Flow() {
        String yamlString = 
                        "firstState: DisplayForm1\n"
                      + "\n"
                      + "lastState:\n"
                      + "  name: DisplayForm1\n"
                      + "  view: Form1\n"
                      + "  entry:\n"
                      + "    method: ActionClass:doEntryOnDisplayForm1\n"
                      + "  activity:\n"
                      + "    method: ActionClass:doActivityOnDisplayForm1\n"
                      + "  exit:\n"
                      + "    method: ActionClass:doExitOnDisplayForm1\n";
    
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = (Flow) flowMapper.getModel(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        State viewState = null;
        State finalState = null;
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewState = state;
            } else {
                fail();
            }   
        }
        
        // ステートのアサーション
        assertNotNull(initialState);
        
        assertNotNull(viewState);
        assertEquals("DisplayForm1", viewState.getName());
        assertEquals("Form1", viewState.getView());
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        Event initialToView = null;
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                initialToView = event;
            }
        }
        assertNotNull(initialToView);
        assertEquals(viewState, initialToView.getNextState());
        
        assertEquals(4, viewState.getEventList().size());
        Event activityEvent = null;
        Event entryEvent = null;
        Event exitEvent = null;
        Event viewToFinal = null;
        for (Event event : viewState.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                if (event.getName().equals("Activity")) {
                    activityEvent = event;
                } else if (event.getName().equals("Entry")) {
                    entryEvent = event;
                } else if (event.getName().equals("Exit")) {
                    exitEvent = event;
                }
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                viewToFinal = event;
            } else {
                fail();
            }
        }
        assertNotNull(activityEvent);
        assertNotNull(activityEvent.getEventHandler());
        assertEquals("ActionClass:doActivityOnDisplayForm1", 
                     activityEvent.getEventHandler().toString());
        assertNotNull(entryEvent);
        assertNotNull(entryEvent.getEventHandler());
        assertEquals("ActionClass:doEntryOnDisplayForm1", 
                     entryEvent.getEventHandler().toString());
        assertNotNull(exitEvent);
        assertNotNull(exitEvent.getEventHandler());
        assertEquals("ActionClass:doExitOnDisplayForm1", 
                     exitEvent.getEventHandler().toString());
        assertNotNull(viewToFinal);
        assertEquals(finalState, viewToFinal.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getModel メソッドテスト.
     * YAMLから以下のフローを取得できることをテストする。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
     *
     */
    public void testGetModelShouldReturn_Initial_View_Action_View_Final_Flow() {
        String yamlString = 
                  "firstState: DisplayForm1\n"
                + "\n"
                + "lastState:\n"
                + "  name: DisplayForm2\n"
                + "  view: Form2\n"
                + "\n"
                + "viewState:\n"
                + "  - name: DisplayForm1\n"
                + "    view: Form1\n"
                + "    transition:\n"
                + "      - event: Process1FromDisplayForm1\n"
                + "        nextState: Process1\n"
                + "        action:\n"
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = (Flow) flowMapper.getModel(yamlString);
        
        assertNotNull(flow);
        assertEquals(5, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            } else {
                fail();
            }
        }

        // ステートのアサーション
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = null;
        State viewState2 = null;
        for (State state : viewStateList) {
            if (state.getName().equals("DisplayForm1")) {
                viewState1 = state;
            } else if (state.getName().equals("DisplayForm2")) {
                viewState2 = state;
            } else {
                fail();
            }
        }
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = actionStateList.get(0);
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        Event initialToView1 = null;
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                initialToView1 = event;
            }
        }
        assertNotNull(initialToView1);
        assertEquals(viewState1, initialToView1.getNextState());
        
        assertEquals(4, viewState1.getEventList().size());
        Event activityEventOnViewState1 = null;
        Event entryEventOnViewState1 = null;
        Event exitEventOnViewState1 = null;
        Event view1ToAction = null;
        for (Event event : viewState1.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                if (event.getName().equals("Activity")) {
                    activityEventOnViewState1 = event;
                } else if (event.getName().equals("Entry")) {
                    entryEventOnViewState1 = event;
                } else if (event.getName().equals("Exit")) {
                    exitEventOnViewState1 = event;
                }
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                view1ToAction = event;
            } else {
                fail();
            }
        }
        assertNotNull(activityEventOnViewState1);
        assertNull(activityEventOnViewState1.getEventHandler());
        assertNotNull(entryEventOnViewState1);
        assertNull(entryEventOnViewState1.getEventHandler());
        assertNotNull(exitEventOnViewState1);
        assertNull(exitEventOnViewState1.getEventHandler());
        assertNotNull(view1ToAction);
        assertEquals(actionState, view1ToAction.getNextState());
    
        assertEquals(4, viewState2.getEventList().size());
        Event activityEventOnViewState2 = null;
        Event entryEventOnViewState2 = null;
        Event exitEventOnViewState2 = null;
        Event view2ToFinal = null;
        for (Event event : viewState2.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                if (event.getName().equals("Activity")) {
                    activityEventOnViewState2 = event;
                } else if (event.getName().equals("Entry")) {
                    entryEventOnViewState2 = event;
                } else if (event.getName().equals("Exit")) {
                    exitEventOnViewState2 = event;
                }
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                view2ToFinal = event;
            } else {
                fail();
            }
        }
        assertNotNull(activityEventOnViewState2);
        assertNull(activityEventOnViewState2.getEventHandler());
        assertNotNull(entryEventOnViewState2);
        assertNull(entryEventOnViewState2.getEventHandler());
        assertNotNull(exitEventOnViewState2);
        assertNull(exitEventOnViewState2.getEventHandler());
        assertNotNull(view2ToFinal);
        assertEquals(finalState, view2ToFinal.getNextState());

        assertEquals(5, actionState.getEventList().size());
        Event activityEventOnActionState = null;
        Event entryEventOnActionState = null;
        Event exitEventOnActionState = null;
        Event actionToView1 = null;
        Event actionToView2 = null;
        for (Event event : actionState.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                if (event.getName().equals("Activity")) {
                    activityEventOnActionState = event;
                } else if (event.getName().equals("Entry")) {
                    entryEventOnActionState = event;
                } else if (event.getName().equals("Exit")) {
                    exitEventOnActionState = event;
                }
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                if (event.getNextState().equals(viewState1)) {
                    actionToView1 = event;
                } else if (event.getNextState().equals(viewState2)) {
                    actionToView2 = event;
                } else {
                    fail();
                }
            } else {
                fail();
            }
        }
        assertNotNull(activityEventOnActionState);
        assertNull(activityEventOnActionState.getEventHandler());
        assertNotNull(entryEventOnActionState);
        assertNull(entryEventOnActionState.getEventHandler());
        assertNotNull(exitEventOnActionState);
        assertNull(exitEventOnActionState.getEventHandler());
        assertNotNull(actionToView1);
        assertEquals(viewState1, actionToView1.getNextState());
        assertNotNull(actionToView2);
        assertEquals(viewState2, actionToView2.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    /**
     * getYAML メソッドテスト.
     * ファイナルステートへの遷移がふたつあるフローをYAMLに
     * 出力できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *                         /|
     *             [View]--------
     *
     */
    
    /**
     * getYAML メソッドテスト.
     * イニシャルステートからの遷移がないフローをYAMLに
     * 出力できることをテストする。<br>
     * [Initial]   [View]-->[Final]
     *
     */
    
    /**
     * getYAML メソッドテスト.
     * ファイナルステートがないフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Action]
     *
     */
    
    // YAMLが不正な場合
    // トップレベルで不正なキーが含まれている
    
}
