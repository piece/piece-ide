// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント調整コマンドテスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.command
 *                  .AdjustEventsCommand
 *
 * @author MATSUFUJI Hideharu
 * @since 0.2.0
 *
 */
public class AdjustEventsCommandTest extends TestCase {
    private Flow fFlow;

    private State fInitialState;
    private State fViewState;
    private State fActionState;
    private State fFinalState;

    private Event fInitialToView;
    private Event fViewToAction;
    private Event fViewToView;
    private Event fActionToFinal;

    /**
     * フローを生成する.
     * 生成するフローは下記のとおり。
     * [Initial]-->[View]-->[Action]-->[Final]
     *              |_|
     *
     * @exception Exception 一般例外
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        fFlow = new Flow(null, null);

        fInitialState = new State(State.INITIAL_STATE);
        fInitialState.setName(fFlow.generateStateName(fInitialState.getType()));
        fViewState = new State(State.VIEW_STATE);
        fViewState.setName("ViewState");
        fActionState = new State(State.ACTION_STATE);
        fActionState.setName("ActionState");
        fFinalState = new State(State.FINAL_STATE);
        fFinalState.setName(fFlow.generateStateName(fFinalState.getType()));

        fInitialToView = new Event(Event.TRANSITION_EVENT);
        fInitialToView.setName("(FirstState)");
        fInitialToView.setNextState(fViewState);
        fInitialState.addEvent(fInitialToView);

        fViewToAction = new Event(Event.TRANSITION_EVENT);
        fViewToAction.setName("ActionStateFromViewState");
        fViewToAction.setNextState(fActionState);
        fViewToAction.setEventHandler("ActionClass:doActionStateFromViewState");
        fViewState.addEvent(fViewToAction);

        fViewToView = new Event(Event.INTERNAL_EVENT);
        fViewToView.setName("OnViewState");
        fViewToView.setNextState(fViewState);
        fViewToView.setEventHandler("doOnViewState");
        fViewState.addEvent(fViewToView);

        fActionToFinal = new Event(Event.TRANSITION_EVENT);
        fActionToFinal.setName("FinalStateFromActionState");
        fActionToFinal.setNextState(fFinalState);
        fActionToFinal.setEventHandler("doFinalStateFromActionState");
        fActionState.addEvent(fActionToFinal);

        fFlow.addState(fInitialState);
        fFlow.addState(fViewState);
        fFlow.addState(fActionState);
        fFlow.addState(fFinalState);

        for (State state : fFlow.getStateList()) {
            for (Event event : state.getEventList()) {
                boolean isNormalState =
                    state.getType() == State.VIEW_STATE
                    || state.getType() == State.ACTION_STATE;
                boolean isBuildinEvent =
                            event.getType() == Event.BUILTIN_EVENT;

                if (!isBuildinEvent) {
                    continue;
                }
                if (isNormalState) {
                    event.setEventHandler(
                            event.generateEventHandlerMethodName()
                            + "On" + state.getName());
                } else {
                    event.setEventHandler(
                            event.generateEventHandlerMethodName());
                }
            }
        }
    }

    /**
     * コマンドを実行することで、ステート名に合わせてイベント名・
     * イベントハンドラ名が更新されることをテストする.
     */
    public void testTheCommandShouldAdjustTheEventAndEventHandlerName() {
        fViewState.setName("DisplayForm1");
        fActionState.setName("Process1");

        AdjustEventsCommand command = new AdjustEventsCommand(fFlow);
        command.execute();

        refreshEvent();

        assertTranstionAndInternalEvent(
                fViewState.getName(), fActionState.getName());
        assertBuildinEvent(
                fViewState.getName(), fActionState.getName());
    }

    /**
     * 変更されていないステートに関連するイベント・イベントハンドラ名
     * は変更されないことをテストする.
     */
    public void testTheCommandShouldNotAdjustTheStateThatHasNotBeenChanged() {
        fActionState.setName("Process1");

        AdjustEventsCommand command = new AdjustEventsCommand(fFlow);
        command.execute();

        refreshEvent();

        assertTranstionAndInternalEvent(
                fViewState.getName(), fActionState.getName());
        assertBuildinEvent(
                fViewState.getName(), fActionState.getName());
    }

    /**
     * コマンド実行後、元に戻せることを確認する.
     */
    public void testTheCommandShouldUndoTheFlow() {
        String oldViewStateName = fViewState.getName();
        String oldActionStateName = fActionState.getName();

        fViewState.setName("DisplayForm1");
        fActionState.setName("Process1");

        AdjustEventsCommand command = new AdjustEventsCommand(fFlow);
        command.execute();

        refreshEvent();

        assertTranstionAndInternalEvent(
                fViewState.getName(), fActionState.getName());
        assertBuildinEvent(
                fViewState.getName(), fActionState.getName());

        command.undo();

        refreshEvent();

        assertTranstionAndInternalEvent(
                oldViewStateName, oldActionStateName);
        assertBuildinEvent(
                oldViewStateName, oldActionStateName);
    }

    /**
     * フローがnullでもコマンド実行が例外を投げないことをテストする.
     */
    public void testTheCommandShouldNotThrowAnException() {
        AdjustEventsCommand command = new AdjustEventsCommand(null);

        try {
            command.execute();
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * 遷移イベント・内部イベントをチェックする.
     *
     * @param viewStateName ビューステート名
     * @param actionStateName アクションステート名
     */
    private void assertTranstionAndInternalEvent(
                        String viewStateName,
                        String actionStateName) {
        assertEquals("(FirstState)", fInitialToView.getName());
        assertEquals(actionStateName + "From" + viewStateName,
                            fViewToAction.getName());
        assertEquals("ActionClass:"
                     + "do" + actionStateName + "From" + viewStateName,
                            fViewToAction.getEventHandler());
        assertEquals("On" + viewStateName, fViewToView.getName());
        assertEquals("doOn" + viewStateName,
                            fViewToView.getEventHandler());
        assertEquals("FinalStateFrom" + actionStateName,
                            fActionToFinal.getName());
        assertEquals("doFinalStateFrom" + actionStateName,
                            fActionToFinal.getEventHandler());
    }

    /**
     * ビルトインイベントをチェックする.
     *
     * @param viewStateName ビューステート名
     * @param actionStateName アクションステート名
     */
    private void assertBuildinEvent(
                        String viewStateName,
                        String actionStateName) {
        assertEquals(2, fInitialState.getEventList().size());
        assertEquals("doInitial",
                fInitialState.getEventByName("Initial").getEventHandler());

        assertEquals(5, fViewState.getEventList().size());
        assertEquals("doEntryOn" + viewStateName,
                fViewState.getEventByName("Entry").getEventHandler());
        assertEquals("doActivityOn" + viewStateName,
                fViewState.getEventByName("Activity").getEventHandler());
        assertEquals("doExitOn" + viewStateName,
                fViewState.getEventByName("Exit").getEventHandler());

        assertEquals(4, fActionState.getEventList().size());
        assertEquals("doEntryOn" + actionStateName,
                fActionState.getEventByName("Entry").getEventHandler());
        assertEquals("doActivityOn" + actionStateName,
                fActionState.getEventByName("Activity").getEventHandler());
        assertEquals("doExitOn" + actionStateName,
                fActionState.getEventByName("Exit").getEventHandler());

        assertEquals(1, fFinalState.getEventList().size());
        assertEquals("doFinal",
                fFinalState.getEventByName("Final").getEventHandler());
    }

    /**
     * 遷移イベント・内部イベントを再取得する.
     *
     */
    private void refreshEvent() {
        for (Event event : fInitialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                fInitialToView = event;
            }
        }
        for (Event event : fViewState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                fViewToAction = event;
            } else if (event.getType() == Event.INTERNAL_EVENT) {
                fViewToView = event;
            }
        }
        for (Event event : fActionState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                fActionToFinal = event;
            }
        }
    }
}
