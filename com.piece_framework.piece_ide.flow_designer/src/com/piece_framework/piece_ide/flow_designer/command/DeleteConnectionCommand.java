// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * 遷移削除コマンド.
 *
 * @author Seiichi Sugimoto
 * @since 0.1.0
 *
 */
public class DeleteConnectionCommand extends Command {

    private State fState;
    private Event fEvent;

    /**
     * コンストラクタ.
     *
     * @param state 削除対象が所属するステート
     * @param event 削除対象となる遷移イベント
     */
    public DeleteConnectionCommand(State state, Event event) {
        super();
        fState = state;
        fEvent = event;
    }

    /**
     * 遷移削除コマンドを実行する.
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fState.removeEvent(fEvent);
    }

    /**
     * 実行した遷移削除コマンドを元に戻す.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fState.addEvent(fEvent);
    }
}
