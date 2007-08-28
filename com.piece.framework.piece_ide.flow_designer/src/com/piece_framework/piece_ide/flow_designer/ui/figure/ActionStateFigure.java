// $Id: ActionStateFigure.java 114 2007-07-08 03:25:46Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.RGB;

/**
 * アクションステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ActionStateFigure extends NormalStateFigure {

    private static final RGB STATE_COLOR = new RGB(225, 221, 170);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(174, 161, 90);
    
    /**
     * ステート色を返す.
     * 
     * @return ステート色.
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getStateColor()
     */
    @Override
    RGB getStateColor() {
        return STATE_COLOR;
    }
    
    /**
     * ステートのアウトライン色を返す.
     *
     * @return ステートのアウトライン色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getStateOutlineColor()
     */
    @Override
    RGB getStateOutlineColor() {
        return STATE_OUTLINE_COLOR;
    }
}
