// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

/**
 * 遷移作成コマンド.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class CreateConnectionCommand extends Command {

    private State fSource;
    private State fTarget;
    private Transition fTransition;
    private Event fEvent;
    /**
     * コンストラクタ.
     * 
     * @param transtion 遷移
     */
    public CreateConnectionCommand(Transition transtion) {
        super();
        fTransition = transtion;
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
    
    /**
     * コマンドを実行できるか判断する.
     * 
     * @return コマンドを実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        return fSource != null && fTarget != null && !fSource.equals(fTarget);
    }
    
    /**
     * 遷移を作成する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fSource.addOutgoing(fTransition);
        fTransition.setSource(fSource);
        fTarget.addIncoming(fTransition);
        fEvent = new Event();
        fEvent.setName("Transition");
        fEvent.setInternalEvent(false);
        fEvent.setEventHandler(null, "transition");
        fSource.addEvent(fEvent);
        
        fTransition.setTarget(fTarget);
    }
    
    /**
     * 作成した遷移を元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fSource.removeOutgoing(fTransition);
        fTransition.setSource(null);
        fTarget.removeIncoming(fTransition);
        fSource.removeEvent(fEvent);
        fTransition.setTarget(null);
    }
}
