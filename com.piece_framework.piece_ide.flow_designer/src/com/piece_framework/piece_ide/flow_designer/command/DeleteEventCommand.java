// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント削除コマンド.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class DeleteEventCommand extends Command {

    private State fState;
    private Event fEvent;

    /**
     * コンストラクタ.
     *
     * @param state ステート
     * @param event イベント
     */
    public DeleteEventCommand(State state, Event event) {
        super();
        fState = state;
        fEvent = event;
    }

    /**
     * イベントを削除する.
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fState.removeEvent(fEvent);
    }

    /**
     * 削除したイベントを元に戻す.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fState.addEvent(fEvent);
    }
}
