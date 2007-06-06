// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

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
    
    /**
     * コンストラクタ.
     * 
     * @param flow フロー
     * @param state 削除対象ステート
     */
    public DeleteStateCommand(Flow flow, State state) {
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
        // 削除対象のステートをソースとするコネクションの削除
        List sourceCons = fState.getIncomings();
        for (int i = 0; i < sourceCons.size(); i++) {
            Transition source = (Transition) sourceCons.get(i);
            source.getSource().removeOutgoing(source);
        }

        // 削除対象のステートをターゲットとするコネクションの削除
        List targetCons = fState.getOutgoings();
        for (int i = 0; i < targetCons.size(); i++) {
            Transition target = (Transition) targetCons.get(i);
            target.getTarget().removeIncoming(target);
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
    }
}
