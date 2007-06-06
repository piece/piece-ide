// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート作成コマンド.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class CreateStateCommand extends Command {

    private Flow fFlow;
    private State fState;
    
    private int fX;
    private int fY;
    
    /**
     * コンストラクタ.
     * 
     * @param flow フロー
     * @param state 作成するステート
     * @param x 作成するステートのX座標
     * @param y 作成するステートのY座標
     */
    public CreateStateCommand(Flow flow, 
                                 State state, 
                                 int x, int y) {
        super();
        fFlow = flow;
        fState = state;
        fX = x;
        fY = y;
    }

    /**
     * ステートを作成する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        //ファイナルステート作成時、既にファイナルステートが存在する場合は作成しない。
        if (fState.getStateType() == State.FINAL_STATE) {
            List states = fFlow.getStates();
            for (int i = 0; i < states.size(); i++) {
                State state = (State) states.get(i);
                if (state.getStateType() == State.FINAL_STATE) {
                    return;
                }
            }
        }
        
        fState.setX(fX);
        fState.setY(fY);
        fFlow.addState(fState);
    }

    /**
     * 作成したステートを元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fFlow.removeState(fState);
    }
}
