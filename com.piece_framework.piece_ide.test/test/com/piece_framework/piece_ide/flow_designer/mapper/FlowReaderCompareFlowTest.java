// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローリーダーフロー比較テスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.mapper.FlowReader
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class FlowReaderCompareFlowTest extends TestCase {
    private Flow fFlow1;
    private Flow fFlow2;

    /**
     * テストメソッド実行前処理.
     *
     * @exception Exception 一般例外
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        fFlow1 = new Flow(null, null);
        setState(fFlow1);

        fFlow2 = new Flow(null, null);
        setState(fFlow2);
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが完全に一致する場合はtrueを返すことをテストする。
     */
    public void testComapreFlowShouldReturnTrueBecauseOfTheSameFlow() {
        assertTrue(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのXY座標のみが異なる場合はtrueを返す
     * ことをテストする。
     */
    public void testComapreFlowShouldReturnTrueBecauseOfFlowTheSameExcludingCoordinates() {
        for (State state : fFlow1.getStateList()) {
            state.setX(1);
        }
        assertTrue(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのサマリーのみが異なる場合はtrueを返す
     * ことをテストする。
     */
    public void testComapreFlowShouldReturnTrueBecauseOfFlowTheSameExcludingSummary() {
        for (State state : fFlow1.getStateList()) {
            state.setSummary("Test Summary");
        }
        assertTrue(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのステート名が異なる場合はfalseを返す
     * ことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheStateNameIsDifferent() {
        fFlow1.getStateList().get(0).setName("TestState");
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つビューステートのビュー名が異なる場合はfalseを返す
     * ことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheViewNameIsDifferent() {
        for (State state : fFlow1.getStateList()) {
            if (state.getType() == State.VIEW_STATE) {
                state.setView("TestView");
            }
        }
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのイベントのイベント名が異なる場合はfalse
     * を返すことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheEventNameIsDifferent() {
        State state = fFlow1.getStateList().get(2);
        state.getEventList().get(2).setName("TestEvent");
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのイベントの次ステートが異なる場合はfalse
     * を返すことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheNextStateIsDifferent() {
        State nextState = new State(State.VIEW_STATE);
        State state = fFlow1.getStateList().get(2);
        state.getTransitionEventList().get(0).setNextState(nextState);
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのイベントのイベントハンドラが異なる場合は
     * falseを返すことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheEventHandlerIsDifferent() {
        State state = fFlow1.getStateList().get(2);
        state.getEventList().get(0).setEventHandler("TestClass:TestMethod");
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * ふたつのフローが持つステートのイベントのガードイベントハンドラが異なる場合は
     * falseを返すことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheGuardIsDifferent() {
        State state = fFlow1.getStateList().get(2);
        state.getTransitionEventList().get(0).setGuardEventHandler(
                                            "TestClass:TestMethod");
        assertFalse(FlowReader.compareFlow(fFlow1, fFlow2));
    }

    /**
     * compareFlowメソッドテスト.
     * フローがnullの場合はfalseを返すことをテストする。
     */
    public void testComapreFlowShouldReturnFalseBecauseOfTheFlowIsNull() {
        assertFalse(FlowReader.compareFlow(null, fFlow2));
        assertFalse(FlowReader.compareFlow(fFlow1, null));
    }

    /**
     * * 以下のフローを作成する.
     * [Initial]-->[View]-->[Action]-->[View]-->[Final]<br>
     *               /|         |
     *                -----------
     * @param flow フロー
     */
    private void setState(Flow flow) {
        State initialState = new State(State.INITIAL_STATE);
        initialState.setX(10);
        initialState.setY(10);
        initialState.setName("Initial");
        initialState.setSummary("InitialState Summary");

        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setX(10);
        viewState1.setY(20);
        viewState1.setName("DisplayForm1");
        viewState1.setSummary("DisplayForm1 Summary");
        viewState1.setView("Form1");

        State actionState = new State(State.ACTION_STATE);
        actionState.setX(10);
        actionState.setY(30);
        actionState.setName("Process1");
        actionState.setSummary("Process1 Summary");

        State viewState2 = new State(State.VIEW_STATE);
        viewState2.setX(10);
        viewState2.setY(40);
        viewState2.setName("DisplayForm2");
        viewState2.setSummary("DisplayForm2 Summary");
        viewState2.setView("Form2");

        State finalState = new State(State.FINAL_STATE);
        finalState.setX(10);
        finalState.setY(50);
        finalState.setName("Final");
        finalState.setSummary("FinalState Summary");

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

        flow.addState(initialState);
        flow.addState(viewState1);
        flow.addState(actionState);
        flow.addState(viewState2);
        flow.addState(finalState);
    }
}
