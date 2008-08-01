// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント作成コマンド.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class CreateEventCommand extends Command {

    private State fState;
    private State fNextState;
    private Event fEvent;

    /**
     * コンストラクタ.
     *
     * @param state ステート
     * @param nextState 遷移先ステート
     * @param event イベント
     */
    public CreateEventCommand(State state, State nextState, Event event) {
        super();
        fState = state;
        fNextState = nextState;
        fEvent = event;
    }

    /**
     * イベントを作成する.
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
     * 作成したイベントを元に戻す.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fState.removeEvent(fEvent);
    }
}
