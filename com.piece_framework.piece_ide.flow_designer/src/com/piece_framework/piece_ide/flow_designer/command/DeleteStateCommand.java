// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート削除コマンド.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class DeleteStateCommand extends Command {

    /**
     * ステート・イベント セットクラス.
     * ステートとイベントを1対1の関係で保持するクラス。
     *
     * @author MATSUFUJI Hideharu
     * @since 0.1.0
     *
     */
    private class StateEvent {
        private State fState;
        private Event fEvent;

        /**
         * ステートを設定する.
         *
         * @param state ステート
         */
        void setState(State state) {
            fState = state;
        }

        /**
         * イベントを設定する.
         *
         * @param event イベント
         */
        void setEvent(Event event) {
            fEvent = event;
        }

        /**
         * イベントを追加する.
         *
         */
        void addEvent() {
            fState.addEvent(fEvent);
        }

        /**
         * イベントを削除する.
         *
         */
        void removeEvent() {
            fState.removeEvent(fEvent);
        }
    }

    private Flow fFlow;
    private State fState;
    private List<Event> fEvents;
    private List<StateEvent> fToOwnState;

    /**
     * コンストラクタ.
     *
     * @param flow フロー
     * @param state 削除対象ステート
     */
    public DeleteStateCommand(Flow flow, State state) {
        fFlow = flow;
        fState = state;

        fEvents = new ArrayList<Event>();
        fEvents.addAll(fState.getEventList());

        fToOwnState = new ArrayList<StateEvent>();
        for (State stateToOwnState : fFlow.getStateListToOwnState(fState)) {
            for (Event event : stateToOwnState.getTransitionEventList()) {
                if (event.getNextState() == fState) {
                    StateEvent stateEvent = new StateEvent();
                    stateEvent.setState(stateToOwnState);
                    stateEvent.setEvent(event);
                    fToOwnState.add(stateEvent);
                }
            }
        }
    }

    /**
     * コマンドを実行できるか判断する.
     * イニシャルステートでなければ、コマンドを実行できるものとする。
     *
     * @return コマンドを実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        if (fState.getType() == State.INITIAL_STATE) {
            return false;
        }
        return true;
    }

    /**
     * ステート削除コマンドを実行する.
     * ステートの削除は以下の手順で行われる。<br>
     * 1.削除対象ステートが保持するイベントをすべて削除する。<br>
     * 　(遷移の線を削除するため)<br>
     * 2.フローからステートを削除する。<br>
     * 3.削除対象ステートが遷移先となっている遷移ステートを削除する。<br>
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        for (Event event : fEvents) {
            fState.removeEvent(event);
        }
        fFlow.removeState(fState);
        for (StateEvent stateEvent : fToOwnState) {
            stateEvent.removeEvent();
        }
    }

    /**
     * 実行したステート削除コマンドを元に戻す.
     * 削除したステートの復元は以下の手順で行われる。<br>
     * 1.削除対象ステートをフローに追加する。<br>
     * 2.削除対象ステートのイベントを追加する。<br>
     * 3.削除対象ステートが遷移先となっているイベントを追加する。<br>
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fFlow.addState(fState);
        for (Event event : fEvents) {
            fState.addEvent(event);
        }
        for (StateEvent stateEvent : fToOwnState) {
            stateEvent.addEvent();
        }
    }
}
