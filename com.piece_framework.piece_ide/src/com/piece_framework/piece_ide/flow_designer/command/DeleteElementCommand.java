// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.IFlow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート削除コマンド.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class DeleteElementCommand extends Command {

    private IFlow fFlow;
    private State fState;
    
    /**
     * コンストラクタ.
     * 
     * @param flow フロー
     * @param state 削除対象ステート
     */
    public DeleteElementCommand(IFlow flow, State state) {
        fFlow = flow;
        fState = state;
    }

    /**
     * ステート削除コマンドを実行する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
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
    }
}
