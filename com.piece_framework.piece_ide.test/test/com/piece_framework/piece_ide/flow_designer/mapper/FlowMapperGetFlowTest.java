package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローマッパー フローモデル取得テスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetFlowTest extends TestCase {

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
     * getFlow メソッドテスト.
     * YAMLから以下のフローを取得できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetFlowShouldReturn_InitialViewFinal_Flow() {
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
        Flow flow = flowMapper.getFlow(yamlString);
        
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
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(initialState, 
                "DisplayForm1FromInitialState", 
                Event.TRANSITION_EVENT,
                viewState, 
                null, 
                null);

        assertEquals(4, viewState.getEventList().size());
        assertEvent(viewState, 
                "Activity", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doActivityOnDisplayForm1", 
                null);
        assertEvent(viewState, 
                "Entry", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doEntryOnDisplayForm1", 
                null);
        assertEvent(viewState, 
                "Exit", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doExitOnDisplayForm1", 
                null);
        assertEvent(viewState, 
                "FinalStateFromDisplayForm1", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * YAMLから以下のフローを取得できることをテストする。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
     *
     */
    public void testGetFlowShouldReturn_InitialViewActionViewFinal_Flow() {
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
        Flow flow = flowMapper.getFlow(yamlString);
        
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
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(initialState, 
                "DisplayForm1FromInitialState", 
                Event.TRANSITION_EVENT,
                viewState1, 
                null, 
                null);
       
        assertEquals(4, viewState1.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState1);
        assertEvent(viewState1, 
                "Process1FromDisplayForm1", 
                Event.TRANSITION_EVENT,
                actionState, 
                "ActionClass:doProcess1FromDisplayForm1", 
                null);

        assertEquals(4, viewState2.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState2);
        assertEvent(viewState2, 
                "FinalStateFromDisplayForm2", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(5, actionState.getEventList().size());
        assertNullNormalStateBiuldinEvent(actionState);
        assertEvent(actionState, 
                "DisplayForm1FromProcess1", 
                Event.TRANSITION_EVENT,
                viewState1, 
                null, 
                null);
        assertEvent(actionState, 
                "DisplayForm2FromProcess1", 
                Event.TRANSITION_EVENT,
                viewState2, 
                null, 
                null);

        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * ファイナルステートへの遷移がふたつあるYAMLからフロー
     * を取得できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *                         /|
     *             [View]--------
     *
     */
    public void testGetFlowShouldReturn_TwoStateToFinalState_Flow() {
        String yamlString = 
              "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "  - name: DisplayForm2\n"
            + "    view: Form2\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromDisplayForm2\n"
            + "        nextState: DisplayForm2\n"
            + "        action:\n"
            + "          method: ActionClass:doDisplayForm2FromDisplayForm2\n"
            + "        guard:\n"
            + "          method: ActionClass:guardMethod\n";

        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(4, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else {
                fail();
            }
        }
        
        // ステートのアサーション
        assertNotNull(initialState);
        
        assertEquals(2, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(initialState, 
                "DisplayForm1FromInitialState", 
                Event.TRANSITION_EVENT,
                viewState1, 
                null, 
                null);
        
        assertEquals(4, viewState1.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState1);
        assertEvent(viewState1, 
                "FinalStateFromDisplayForm1", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(5, viewState2.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState2);
        assertEvent(viewState2, 
                "DisplayForm2FromDisplayForm2", 
                Event.INTERNAL_EVENT,
                viewState2, 
                "ActionClass:doDisplayForm2FromDisplayForm2", 
                "ActionClass:guardMethod");
        assertEvent(viewState2, 
                "FinalStateFromDisplayForm2", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * イニシャルステートからの遷移がないYAMLからをフローを取
     * 得できることをテストする。<br>
     * [Initial]   [View]-->[Final]
     *
     */
    public void testGetFlowShouldReturn_Initial_ViewFinal_Flow() {
        String yamlString = 
              "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else {
                fail();
            }
        }
        
        // ステートのアサーション
        assertNotNull(initialState);
        
        assertEquals(1, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        assertEquals("Form1", viewState1.getView());
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(1, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        
        assertEquals(4, viewState1.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState1);
        assertEvent(viewState1, 
                "FinalStateFromDisplayForm1", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    /**
     * getFlow メソッドテスト.
     * ファイナルステートがないYAMLからフローが取得できることを
     * テストする。<br>
     * [Initial]-->[View]-->[Action]
     *
     */
    public void testGetFlowShouldReturn_NothingFinal_Flow() {
        String yamlString = 
              "firstState: DisplayForm1\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    entry:\n"
            + "      method: ActionClass:doEntryOnDisplayForm1\n"
            + "    activity:\n"
            + "      method: ActionClass:doActivityOnDisplayForm1\n"
            + "    exit:\n"
            + "      method: ActionClass:doExitOnDisplayForm1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: FlowAction:doProcess1FromDisplayForm1\n"
            + "        guard:\n"
            + "          method: GuardAction:guardMethod\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    entry:\n"
            + "      method: ActionClass:doEntryOnProcess1\n"
            + "    activity:\n"
            + "      method: ActionClass:doActivityOnProcess1\n"
            + "    exit:\n"
            + "      method: ActionClass:doExitOnProcess1\n";

        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
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
        
        assertEquals(1, viewStateList.size());
        State viewState1 = flow.getStateByName("DisplayForm1");
        assertEquals("Form1", viewState1.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(initialState, 
                "DisplayForm1FromInitialState", 
                Event.TRANSITION_EVENT,
                viewState1, 
                null, 
                null);
 
        assertEquals(4, viewState1.getEventList().size());
        assertEvent(viewState1, 
                "Activity", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doActivityOnDisplayForm1", 
                null);
        assertEvent(viewState1, 
                "Entry", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doEntryOnDisplayForm1", 
                null);
        assertEvent(viewState1, 
                "Exit", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doExitOnDisplayForm1", 
                null);
        assertEvent(viewState1, 
                "Process1FromDisplayForm1", 
                Event.TRANSITION_EVENT,
                actionState, 
                "FlowAction:doProcess1FromDisplayForm1", 
                "GuardAction:guardMethod");

        assertEquals(3, actionState.getEventList().size());
        assertEvent(actionState, 
                "Activity", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doActivityOnProcess1", 
                null);
        assertEvent(actionState, 
                "Entry", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doEntryOnProcess1", 
                null);
        assertEvent(actionState, 
                "Exit", 
                Event.BUILTIN_EVENT,
                null, 
                "ActionClass:doExitOnProcess1", 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * 遷移先のステート名の綴りが間違っているYAMLからフローが取得できることを
     * テストする。<br>
     *
     */
    public void testGetFlowShouldReturn_NothingNextState_Flow() {
        String yamlString = 
                  "firstState: DisplayForm1xx\n"
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
                + "        nextState: Process1xx\n"
                + "        action:\n"
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2xx\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
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
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        State actionState = flow.getStateByName("Process1");
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(1, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);

        assertEquals(3, viewState1.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState1);

        assertEquals(4, viewState2.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState2);
        assertEvent(viewState2, 
                "FinalStateFromDisplayForm2", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);

        assertEquals(3, actionState.getEventList().size());
        assertNullNormalStateBiuldinEvent(actionState);
        
        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * インデントが異常なYAMLからフローが取得できることをテストする。<br>
     *
     */
    public void testGetFlowShouldReturn_Null() {
        String yamlString = 
                  "firstState: DisplayForm1xx\n"
                + "\n"
                + "lastState:\n"
                + "name: DisplayForm2\n"
                + "view: Form2\n"
                + "\n"
                + "viewState:\n"
                + "- name: DisplayForm1\n"
                + "view: Form1\n"
                + "transition:\n"
                + "- event: Process1FromDisplayForm1\n"
                + "nextState: Process1xx\n"
                + "action:\n"
                + "method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "- name: Process1\n"
                + "transition:\n"
                + "- event: DisplayForm2FromProcess1\n"
                + "nextState: DisplayForm2xx\n"
                + "- event: DisplayForm1FromProcess1\n"
                + "nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNull(flow);
    }

    /**
     * getFlow メソッドテスト.
     * トップレベルのキーに不正な値が含まれているYAMLからフローが
     * 取得できることをテストする。<br>
     *
     */
    public void testGetFlowShouldReturn_NothingAction_Flow() {
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
                + "actionStateXX:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
        assertNotNull(flow);
        assertEquals(4, flow.getStateList().size());
        
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
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertEquals("Form1", viewState1.getView());
        assertEquals("Form2", viewState2.getView());
        
        assertEquals(0, actionStateList.size());
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        assertEvent(initialState, 
                "Initial", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(initialState, 
                "DisplayForm1FromInitialState", 
                Event.TRANSITION_EVENT,
                viewState1, 
                null, 
                null);

        assertEquals(3, viewState1.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState1);

        assertEquals(4, viewState2.getEventList().size());
        assertNullNormalStateBiuldinEvent(viewState2);
        assertEvent(viewState2, 
                "FinalStateFromDisplayForm2", 
                Event.TRANSITION_EVENT,
                finalState, 
                null, 
                null);
 
        assertEquals(1, finalState.getEventList().size());
        assertEvent(finalState, 
                "Final", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * getFlow メソッドテスト.
     * ビュー名がないビューステートのあるYAMLからビュー・アクションの
     * 区別が正しく行われたフローが取得できることをテストする。<br>
     *
     */
    public void testGetFlowShouldReturn_NothingView_Flow() {
        String yamlString = 
                  "firstState: DisplayForm1xx\n"
                + "\n"
                + "lastState:\n"
                + "  name: DisplayForm2\n"
                + "  view: ~\n"
                + "\n"
                + "viewState:\n"
                + "  - name: DisplayForm1\n"
                + "    view: ~\n"
                + "    transition:\n"
                + "      - event: Process1FromDisplayForm1\n"
                + "        nextState: Process1xx\n"
                + "        action:\n"
                + "          method: ActionClass:doProcess1FromDisplayForm1\n"
                + "\n"
                + "actionState:\n"
                + "  - name: Process1\n"
                + "    transition:\n"
                + "      - event: DisplayForm2FromProcess1\n"
                + "        nextState: DisplayForm2xx\n"
                + "      - event: DisplayForm1FromProcess1\n"
                + "        nextState: DisplayForm1xx\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = flowMapper.getFlow(yamlString);
        
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
        State viewState1 = flow.getStateByName("DisplayForm1");
        State viewState2 = flow.getStateByName("DisplayForm2");
        assertNull(viewState1.getView());
        assertNull(viewState2.getView());
        
        assertEquals(1, actionStateList.size());
        
        assertNotNull(finalState);
    }
    
    /**
     * ノーマルステート(ビューステート、アクションステート)のビルトイン
     * イベントのアサーションを行う.
     * 以下の内容を表明する。<br>
     * ・ビルトインイベント自体は null ではない。<br>
     * ・イベントハンドラは null である。<br>
     * 
     * @param state ノーマルステート(ビューステート、アクションステート)
     */
    private void assertNullNormalStateBiuldinEvent(State state) {
        assertEvent(state, 
                "Activity", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(state, 
                "Entry", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
        assertEvent(state, 
                "Exit", 
                Event.BUILTIN_EVENT,
                null, 
                null, 
                null);
    }
    
    /**
     * イベントのアサーションを行う.
     * 
     * @param state 対象ステート
     * @param eventName イベント名
     * @param expectedEventType イベントタイプ
     * @param expectedNextState 予期している次ステート
     * @param expectedEventHandler 予期しているイベントハンドラ
     * @param expectedGuardEventHandler 予期しているガード
     */
    private void assertEvent(
            State state, 
            String eventName, 
            int expectedEventType,
            State expectedNextState,
            String expectedEventHandler, 
            String expectedGuardEventHandler) {
        Event event = state.getEventByName(eventName);
        
        assertNotNull(event);
        
        assertEquals(expectedNextState, event.getNextState());
        assertEquals(expectedEventType, event.getType());
        
        if (expectedEventHandler == null) {
            assertNull(event.getEventHandler());
        } else {
            assertNotNull(event.getEventHandler());
            assertEquals(expectedEventHandler, 
                         event.getEventHandler().toString());
        }
        
        if (expectedGuardEventHandler == null) {
            assertNull(event.getGuardEventHandler());
        } else {
            assertNotNull(event.getGuardEventHandler());
            assertEquals(expectedGuardEventHandler, 
                         event.getGuardEventHandler().toString());
        }
    }
}
