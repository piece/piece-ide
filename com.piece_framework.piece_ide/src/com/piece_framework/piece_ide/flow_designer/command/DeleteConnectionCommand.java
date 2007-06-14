// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

/**
 * 遷移削除コマンド.
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class DeleteConnectionCommand extends Command {
  
    private State fSource;
    private State fTarget;
    private Transition fTransition;
   
    /**
     * コンストラクタ.
     * 
     * @param transtion 削除対象となる遷移
     */
    public DeleteConnectionCommand(Transition transtion) {
        super();
        fTransition = transtion;
    }
    
    /**
     * 遷移削除コマンドを実行する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fSource.removeOutgoing(fTransition);
        fTransition.setSource(null);
        fTarget.removeIncoming(fTransition);
        fTransition.setTarget(null);
    }
    
    /**
     * 実行した遷移削除コマンドを元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fSource.addOutgoing(fTransition);
        fTransition.setSource(fSource);
        fTarget.addIncoming(fTransition);
        fTransition.setTarget(fTarget);
    }
    
    /**
     * 遷移元ステートを設定する.
     * 
     * @param source 遷移元ステート
     */
    public void setSource(State source) {
        fSource = source;
    }
    
    /**
     * 遷移先ステートを設定する.
     * 
     * @param target 遷移先ステート
     */
    public void setTarget(State target) {
        fTarget = target;
    }
}
