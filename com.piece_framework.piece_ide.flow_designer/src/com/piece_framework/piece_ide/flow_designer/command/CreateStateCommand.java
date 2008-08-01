// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート作成コマンド.
 *
 * @author MATSUFUJI Hideharu
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
     * @param x X座標
     * @param y Y座標
     */
    public CreateStateCommand(Flow flow, State state, int x, int y) {
        super();
        fFlow = flow;
        fState = state;
        fX = x;
        fY = y;
    }

    /**
     * コマンドが実行できるか判断する.
     * 既にフローにファイナルステートがあるときは
     * ファイナルステートは追加できない。
     *
     * @return コマンドを実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        if (fState.getType() == State.FINAL_STATE) {
            for (State state : fFlow.getStateList()) {
                if (state.getType() == State.FINAL_STATE) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ステートを作成する.
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fState.setName(fFlow.generateStateName(fState.getType()));
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
