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
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class DeleteStateCommand extends Command {

    private Flow fFlow;
    private State fState;
    private List<Event> fEvents;
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
        fEvents.addAll(state.getEventList());
    }

    /**
     * ステート削除コマンドを実行する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        for (Event event : fEvents) {
            fState.removeEvent(event);
        }
        fFlow.removeState(fState);
    }

    /**
     * 実行したステート削除コマンドを元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fFlow.addState(fState);
        for (Event event : fEvents) {
            fState.addEvent(event);
        }
    }
}
