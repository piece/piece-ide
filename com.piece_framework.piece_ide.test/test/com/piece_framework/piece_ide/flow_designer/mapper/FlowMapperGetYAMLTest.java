// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローマッパーYAML取得テスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetYAMLTest extends TestCase {

    private Flow fFlow;
    
    /**
     * テストメソッド実行前処理.
     * 
     * @exception Exception 一般例外
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        fFlow = new Flow("FlowName", "ActionClass");
    }

    /**
     * getYAML メソッドテスト.
     * 以下のフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetYAML_InitialViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);
        
        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append(
              "lastState:\n"
            + getYAMLofStateInformation(viewState, 2)
            + getYAMLofBuiltinEvent(viewState, 2));                
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
    
    /**
     * getYAML メソッドテスト.
     * 以下のフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
     *
     */
    public void testGetYAML_InitialViewActionViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event actionToView2 = new Event(Event.TRANSITION_EVENT);
        actionToView2.setName("DisplayForm1FromProcess1");
        actionToView2.setNextState(viewState1);
        actionState.addEvent(actionToView2);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState1);
        fFlow.addState(actionState);
        fFlow.addState(viewState2);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState1.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append(
                "lastState:\n"
              + getYAMLofStateInformation(viewState2, 2)
              + getYAMLofBuiltinEvent(viewState2, 2));                
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append("viewState:\n");
        String view1YAML = 
            "  - "
          + getYAMLofStateInformation(viewState1, 0)
          + getYAMLofBuiltinEvent(viewState1, 0)
          + getYAMLofTransitionEvent(viewState1, 0);
        view1YAML = view1YAML.replace("\n", "\n    ");
        if (view1YAML.endsWith("    ")) {
            view1YAML = view1YAML.substring(0, view1YAML.length() - 4);
        }
        expectedYAMLBuffer.append(view1YAML);
        
        expectedYAMLBuffer.append("\n");
        expectedYAMLBuffer.append("actionState:\n");
        String actionYAML = 
            "  - "
          + getYAMLofStateInformation(actionState, 0)
          + getYAMLofBuiltinEvent(actionState, 0)
          + getYAMLofTransitionEvent(actionState, 0);
        actionYAML = actionYAML.replace("\n", "\n    ");
        if (actionYAML.endsWith("    ")) {
            actionYAML = actionYAML.substring(0, actionYAML.length() - 4);
        }
        expectedYAMLBuffer.append(actionYAML);
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
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
    public void testGetYAML_TwoStateToFinalState_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView1 = new Event(Event.TRANSITION_EVENT);
        initialToView1.setNextState(viewState1);
        initialState.addEvent(initialToView1);
        
        Event view1ToFinal = new Event(Event.TRANSITION_EVENT);
        view1ToFinal.setName("FinalStateFromDisplayForm1");
        view1ToFinal.setNextState(finalState);
        view1ToFinal.setEventHandler("doFinaltStateFromDisplayForm1");
        viewState1.addEvent(view1ToFinal);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        view2ToFinal.setEventHandler("doFinaltStateFromDisplayForm2");
        viewState2.addEvent(view2ToFinal);
        
        Event view2ToView2 = new Event(Event.INTERNAL_EVENT);
        view2ToView2.setName("DisplayForm2FromDisplayForm2");
        view2ToView2.setNextState(viewState2);
        view2ToView2.setEventHandler("doDisplayForm2FromDisplayForm2");
        view2ToView2.setGuardEventHandler("guardMethod");
        viewState2.addEvent(view2ToView2);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState1);
        fFlow.addState(viewState2);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState1.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        String lastStateYAML = getYAMLofStateInformation(viewState1, 4)
                            + getYAMLofBuiltinEvent(viewState1, 4)
                            + getYAMLofTransitionEvent(viewState1, 4)
                            + getYAMLofStateInformation(viewState2, 4)
                            + getYAMLofBuiltinEvent(viewState2, 4)
                            + getYAMLofTransitionEvent(viewState2, 4);
        lastStateYAML = lastStateYAML.replace("    name:", "  - name:");
        expectedYAMLBuffer.append("lastState:\n" + lastStateYAML);
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }

    /**
     * getYAML メソッドテスト.
     * イニシャルステートからの遷移がないフローをYAMLに
     * 出力できることをテストする。<br>
     * [Initial]   [View]-->[Final]
     *
     */
    public void testGetYAML_Initial_ViewFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(finalState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();

        expectedYAMLBuffer.append(
              "lastState:\n"
            + getYAMLofStateInformation(viewState, 2)
            + getYAMLofBuiltinEvent(viewState, 2));                
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
    
    /**
     * getYAML メソッドテスト.
     * ファイナルステートがないフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Action]
     *
     */
    public void testGetYAML_NothingFinal_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);
        
        Event viewToAction = new Event(Event.TRANSITION_EVENT);
        viewToAction.setName("Process1FromDisplayForm1");
        viewToAction.setNextState(actionState);
        viewToAction.setEventHandler("FlowAction:doProcess1FromDisplayForm1");
        viewToAction.setGuardEventHandler("GuardAction:guardMethod");
        viewState.addEvent(viewToAction);
        
        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(actionState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append("viewState:\n");
        String viewYAML = 
            "  - "
          + getYAMLofStateInformation(viewState, 0)
          + getYAMLofBuiltinEvent(viewState, 0)
          + getYAMLofTransitionEvent(viewState, 0);
        viewYAML = viewYAML.replace("\n", "\n    ");
        if (viewYAML.endsWith("    ")) {
            viewYAML = viewYAML.substring(0, viewYAML.length() - 4);
        }
        expectedYAMLBuffer.append(viewYAML);
        
        expectedYAMLBuffer.append("\n");
        expectedYAMLBuffer.append("actionState:\n");
        String actionYAML = 
            "  - "
          + getYAMLofStateInformation(actionState, 0)
          + getYAMLofBuiltinEvent(actionState, 0)
          + getYAMLofTransitionEvent(actionState, 0);
        actionYAML = actionYAML.replace("\n", "\n    ");
        if (actionYAML.endsWith("    ")) {
            actionYAML = actionYAML.substring(0, actionYAML.length() - 4);
        }
        expectedYAMLBuffer.append(actionYAML);
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
    
    /**
     * getYAML メソッドテスト.
     * ステートの登録順序がイニシャル、ビュー・アクション、ファイナルの
     * 順番に登録されていないのフローをYAMLに出力できることをテストす
     * る。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *
     */
    public void testGetYAML_NoOrder_Flow_IsOutputWithYAML() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow.addState(finalState);
        fFlow.addState(viewState2);
        fFlow.addState(actionState);
        fFlow.addState(viewState1);
        fFlow.addState(initialState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState1.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append(
                "lastState:\n"
              + getYAMLofStateInformation(viewState2, 2)
              + getYAMLofBuiltinEvent(viewState2, 2));                
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append("viewState:\n");
        String view1YAML = 
            "  - "
          + getYAMLofStateInformation(viewState1, 0)
          + getYAMLofBuiltinEvent(viewState1, 0)
          + getYAMLofTransitionEvent(viewState1, 0);
        view1YAML = view1YAML.replace("\n", "\n    ");
        if (view1YAML.endsWith("    ")) {
            view1YAML = view1YAML.substring(0, view1YAML.length() - 4);
        }
        expectedYAMLBuffer.append(view1YAML);
        
        expectedYAMLBuffer.append("\n");
        expectedYAMLBuffer.append("actionState:\n");
        String actionYAML = 
            "  - "
          + getYAMLofStateInformation(actionState, 0)
          + getYAMLofBuiltinEvent(actionState, 0)
          + getYAMLofTransitionEvent(actionState, 0);
        actionYAML = actionYAML.replace("\n", "\n    ");
        if (actionYAML.endsWith("    ")) {
            actionYAML = actionYAML.substring(0, actionYAML.length() - 4);
        }
        expectedYAMLBuffer.append(actionYAML);
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
    
    /**
     * getYAML メソッドテスト.
     * フローにアクションクラスが指定されていないかつイベントハンドラ
     * にもアクションクラスが指定されていない場合、メソッドのみが返さ
     * れることをチェックする。
     * 
     */
    public void testGetYAMLShouldReturnTheYAMLWhichTheEventHandlerHasMethodOnly() {
        State initialState = new State(State.INITIAL_STATE);
        State viewState1 = new State(State.VIEW_STATE);
        
        viewState1.setName("DisplayForm1");
        viewState1.setView("Form1");
        
        State actionState = new State(State.ACTION_STATE);
        actionState.setName("Process1");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setName("DisplayForm2");
        viewState2.setView("Form2");
        
        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setName("DisplayForm1FromInitialState");
        initialToView.setNextState(viewState1);
        initialState.addEvent(initialToView);
        
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);
        
        Event actionToView1 = new Event(Event.TRANSITION_EVENT);
        actionToView1.setName("DisplayForm2FromProcess1");
        actionToView1.setNextState(viewState2);
        actionState.addEvent(actionToView1);
        
        Event view2ToFinal = new Event(Event.TRANSITION_EVENT);
        view2ToFinal.setName("FinalStateFromDisplayForm2");
        view2ToFinal.setNextState(finalState);
        viewState2.addEvent(view2ToFinal);
        
        fFlow = new Flow(null, null);
        fFlow.addState(finalState);
        fFlow.addState(viewState2);
        fFlow.addState(actionState);
        fFlow.addState(viewState1);
        fFlow.addState(initialState);
        
        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState1.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append(
                "lastState:\n"
              + getYAMLofStateInformation(viewState2, 2)
              + getYAMLofBuiltinEvent(viewState2, 2));                
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append("viewState:\n");
        String view1YAML = 
            "  - "
          + getYAMLofStateInformation(viewState1, 0)
          + getYAMLofBuiltinEvent(viewState1, 0)
          + getYAMLofTransitionEvent(viewState1, 0);
        view1YAML = view1YAML.replace("\n", "\n    ");
        if (view1YAML.endsWith("    ")) {
            view1YAML = view1YAML.substring(0, view1YAML.length() - 4);
        }
        expectedYAMLBuffer.append(view1YAML);
        
        expectedYAMLBuffer.append("\n");
        expectedYAMLBuffer.append("actionState:\n");
        String actionYAML = 
            "  - "
          + getYAMLofStateInformation(actionState, 0)
          + getYAMLofBuiltinEvent(actionState, 0)
          + getYAMLofTransitionEvent(actionState, 0);
        actionYAML = actionYAML.replace("\n", "\n    ");
        if (actionYAML.endsWith("    ")) {
            actionYAML = actionYAML.substring(0, actionYAML.length() - 4);
        }
        expectedYAMLBuffer.append(actionYAML);
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
    
    /**
     * ステート名・ビュー名のYAMLを返す.
     * 
     * @param state ステート
     * @param space スペース数
     * @return ステート名・ビュー名のYAML
     */
    private String getYAMLofStateInformation(State state, int space) {
        StringBuffer yamlBuffer = new StringBuffer();
        
        StringBuffer sp = new StringBuffer();
        for (int i = 1; i <= space; i++) {
            sp.append(" ");
        }
        
        yamlBuffer.append(
                sp + "name: " + state.getName() + "\n");
        if (state.getType() == State.VIEW_STATE) {
            yamlBuffer.append(
                    sp + "view: " + state.getView() + "\n");
        }
        
        return yamlBuffer.toString();
    }
    
    /**
     * ビルトインイベント部分のYAMLを返す.
     * 
     * @param state ステート
     * @param space スペース数
     * @return ビルトインイベント部分のYAML
     */
    private String getYAMLofBuiltinEvent(State state, int space) {
        StringBuffer yamlBuffer = new StringBuffer();
        
        StringBuffer sp = new StringBuffer();
        for (int i = 1; i <= space; i++) {
            sp.append(" ");
        }
        
        Event activitiyEvent = state.getEventByName("Activity");
        Event entryEvent = state.getEventByName("Entry");
        Event exitEvent = state.getEventByName("Exit");
        
        if (entryEvent.getEventHandler() != null) {
            yamlBuffer.append(
                    sp + "entry:\n"
                  + sp + "  method: "
                       + entryEvent.getEventHandlerMethodName() + "\n");
        }
        if (activitiyEvent.getEventHandler() != null) {
            yamlBuffer.append(
                    sp + "activity:\n"
                  + sp + "  method: "
                       + activitiyEvent.getEventHandlerMethodName() + "\n");
        }
        if (exitEvent.getEventHandler() != null) {
            yamlBuffer.append(
                    sp + "exit:\n"
                  + sp + "  method: "
                       + exitEvent.getEventHandlerMethodName() + "\n");
        }
        return yamlBuffer.toString();
    }
    
    /**
     * 遷移イベント・内部イベント部分のYAMLを返す.
     * 
     * @param state ステート
     * @param space スペース数
     * @return 遷移イベント・内部イベント部分のYAML
     */
    private String getYAMLofTransitionEvent(State state, int space) {
        StringBuffer yamlBuffer = new StringBuffer();
        
        StringBuffer sp = new StringBuffer();
        for (int i = 1; i <= space; i++) {
            sp.append(" ");
        }
        
        for (Event event : state.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT
                || event.getType() == Event.INTERNAL_EVENT) {
                if (event.getNextState().getType() == State.FINAL_STATE) {
                    continue;
                }
                
                if (yamlBuffer.toString().length() == 0) {
                    yamlBuffer.append(sp + "transition:\n");
                }
                yamlBuffer.append(sp + "  - event: "
                                + event.getName() + "\n");
                yamlBuffer.append(sp + "    nextState: "
                                + event.getNextState().getName() + "\n");
                if (event.getEventHandler() != null) {
                    yamlBuffer.append(
                            sp + "    action:\n"
                          + sp + "      method: "
                              + event.getEventHandlerMethodName() + "\n");
                }
                if (event.getGuardEventHandler() != null) {
                    yamlBuffer.append(
                            sp + "    guard:\n"
                          + sp + "      method: "
                              + event.getGuardEventHandlerMethodName() + "\n");
                }
            }
        }
        
        return yamlBuffer.toString();
    }
}
