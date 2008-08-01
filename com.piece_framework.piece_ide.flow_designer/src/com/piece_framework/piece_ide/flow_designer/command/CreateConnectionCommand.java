// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * 遷移作成コマンド.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class CreateConnectionCommand extends Command {
    private Flow fFlow;
    private State fState;
    private State fNextState;
    private Event fEvent;

    /**
     * コンストラクタ.
     *
     * @param event 遷移イベント
     */
    public CreateConnectionCommand(Event event) {
        super();
        fEvent = event;
    }

    /**
     * フローをセットする.
     *
     * @param flow フロー
     */
    public void setFlow(Flow flow) {
        fFlow = flow;
    }

    /**
     * 遷移元ステートをセットする.
     *
     * @param state 遷移元ステート
     */
    public void setState(State state) {
        fState = state;
    }

    /**
     * 遷移先ステートをセットする.
     *
     * @param nextState 遷移先ステート
     */
    public void setNextState(State nextState) {
        fNextState = nextState;
    }

    /**
     * コマンドを実行できるか判断する.
     * ・遷移元・遷移先ステートがnullでなく、遷移元・遷移先ステートが同一
     * でなければ、コマンドを実行できるものとする。<br>
     * ・イニシャルステートからはひとつだけ遷移できるものとする。<br>
     * ・イニシャルステートへの遷移はできない。<br>
     * ・ファイナルステートへの遷移はひとつだけ遷移できるものとする。<br>
     * ・ファイナルステートからの遷移はできない。<br>
     *
     * @return コマンドを実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        boolean executable = true;

        if (fFlow == null || fState == null || fNextState == null
            || fState.equals(fNextState)) {
            executable = false;
        } else if (fState.getType() == State.INITIAL_STATE
                    && fState.getTransitionEventList().size() > 0) {
            executable = false;
        } else if (fNextState.getType() == State.INITIAL_STATE) {
            executable = false;
        } else if (fNextState.getType() == State.FINAL_STATE
                    && fFlow.getStateListToOwnState(fNextState).size() > 0) {
            executable = false;
        } else if (fState.getType() == State.FINAL_STATE) {
            executable = false;
        }

        return executable;
    }

    /**
     * 遷移を作成する.
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fEvent.setName(fState.generateEventName(fNextState.getName()));
        fEvent.setNextState(fNextState);
        fState.addEvent(fEvent);
    }

    /**
     * 作成した遷移を元に戻す.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fState.removeEvent(fEvent);
    }
}
