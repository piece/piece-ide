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
 * @version 0.2.0
 * @since 0.1.0
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

        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * 以下のフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
     *               /|         |
     *                -----------
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

        String expectedYAML =
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
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n"
            + "      - event: DisplayForm1FromProcess1\n"
            + "        nextState: DisplayForm1\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * ファイナルステートへの遷移がふたつあるフローをYAMLに
     * 出力できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *                         /|
     *             [View]--------
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

        String expectedYAML =
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
            + "          method: doDisplayForm2FromDisplayForm2\n"
            + "        guard:\n"
            + "          method: guardMethod\n";

        assertEquals(expectedYAML, yaml);
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

        String expectedYAML =
              "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * ファイナルステートがないフローをYAMLに出力できることをテストする。<br>
     * [Initial]-->[View]-->[Action]
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

        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          class: FlowAction\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "        guard:\n"
            + "          class: GuardAction\n"
            + "          method: guardMethod\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * ステートの登録順序がイニシャル、ビュー・アクション、ファイナルの
     * 順番に登録されていないのフローをYAMLに出力できることをテストす
     * る。<br>
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]
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

        String expectedYAML =
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
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * フローにアクションクラスが指定されていないかつイベントハンドラ
     * にもアクションクラスが指定されていない場合、メソッドのみが返さ
     * れることをテストする。
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

        String expectedYAML =
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
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * ビルトインイベントが正しく出力されることをテストする。
     */
    public void testGetYAMLShouldReturnTheYAMLWhichHasTheStateWithBuildinEvent() {
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

        viewState1.getEventByName("Activity").setEventHandler("ActivityMethod");
        viewState1.getEventByName("Entry").setEventHandler("EntryMethod");
        viewState1.getEventByName("Exit").setEventHandler("ExitMethod");
        Event view1ToAction = new Event(Event.TRANSITION_EVENT);
        view1ToAction.setName("Process1FromDisplayForm1");
        view1ToAction.setNextState(actionState);
        view1ToAction.setEventHandler("doProcess1FromDisplayForm1");
        viewState1.addEvent(view1ToAction);

        actionState.getEventByName("Activity").setEventHandler(
                                        "ActivityClass:ActivityMethod");
        actionState.getEventByName("Entry").setEventHandler(
                                        "EntryClass:EntryMethod");
        actionState.getEventByName("Exit").setEventHandler(
                                        "ExitClass:ExitMethod");
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

        String expectedYAML =
              "firstState: DisplayForm1\n"
            + "\n"
            + "lastState:\n"
            + "  name: DisplayForm2\n"
            + "  view: Form2\n"
            + "\n"
            + "viewState:\n"
            + "  - name: DisplayForm1\n"
            + "    view: Form1\n"
            + "    entry:\n"
            + "      method: EntryMethod\n"
            + "    activity:\n"
            + "      method: ActivityMethod\n"
            + "    exit:\n"
            + "      method: ExitMethod\n"
            + "    transition:\n"
            + "      - event: Process1FromDisplayForm1\n"
            + "        nextState: Process1\n"
            + "        action:\n"
            + "          method: doProcess1FromDisplayForm1\n"
            + "\n"
            + "actionState:\n"
            + "  - name: Process1\n"
            + "    entry:\n"
            + "      class: EntryClass\n"
            + "      method: EntryMethod\n"
            + "    activity:\n"
            + "      class: ActivityClass\n"
            + "      method: ActivityMethod\n"
            + "    exit:\n"
            + "      class: ExitClass\n"
            + "      method: ExitMethod\n"
            + "    transition:\n"
            + "      - event: DisplayForm2FromProcess1\n"
            + "        nextState: DisplayForm2\n";

        assertEquals(expectedYAML, yaml);
    }

    /**
     * getYAML メソッドテスト.
     * イニシャルステートとファイナルステートにそれぞれInitialイベント、
     * Finalイベントを持つ場合、トップレベルにinitial/final要素が出力
     * されることをテストする。
     */
    public void testGetYAMLShouldReturnTheYAMLWhichHasInitialAndFinal() {
        State initialState = new State(State.INITIAL_STATE);

        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");

        State finalState = new State(State.FINAL_STATE);
        finalState.setName("FinalState");

        Event initialEvent = initialState.getEventByName("Initial");
        initialEvent.setEventHandler("InitialClass:InitialMethod");

        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);

        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);

        Event finalEvent = finalState.getEventByName("Final");
        finalEvent.setEventHandler("FinalClass:FinalMethod");

        fFlow.addState(initialState);
        fFlow.addState(viewState);
        fFlow.addState(finalState);

        FlowMapper flowMapper = new FlowMapper();
        String yaml = flowMapper.getYAML(fFlow);

        String expectedYAML =
              "initial:\n"
            + "  class: InitialClass\n"
            + "  method: InitialMethod\n"
            + "firstState: DisplayForm1\n"
            + "\n"
            + "final:\n"
            + "  class: FinalClass\n"
            + "  method: FinalMethod\n"
            + "lastState:\n"
            + "  name: DisplayForm1\n"
            + "  view: Form1\n";

        assertEquals(expectedYAML, yaml);
    }
}
