// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート移動コマンド.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class MoveStateCommand extends Command {
    private int fX;
    private int fOldX;
    private int fY;
    private int fOldY;
    private State fState;

    /**
     * コンストラクタ.
     *
     * @param x X座標
     * @param y Y座標
     * @param state 移動対象ステート
     */
    public MoveStateCommand(int x, int y, State state) {
        super();
        fX = x;
        fY = y;
        fState = state;
    }

    /**
     * ステートの移動を行う.
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        fOldX = fState.getX();
        fOldY = fState.getY();
        fState.setY(fY);
        fState.setX(fX);
    }

    /**
     * 移動したステートを元に戻す.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        fState.setX(fOldX);
        fState.setY(fOldY);
    }
}
