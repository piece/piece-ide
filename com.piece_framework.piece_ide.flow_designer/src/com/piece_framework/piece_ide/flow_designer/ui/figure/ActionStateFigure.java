// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * アクションステート・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class ActionStateFigure extends NormalStateFigure {

    private static final RGB STATE_COLOR = new RGB(204, 204, 204);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(153, 153, 153);

    private static final String ICON = "icons/ActionState.gif"; //$NON-NLS-1$

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

    /**
     * ステートのアイコンを返す.
     *
     * @return ステートのアイコンイメージ
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getIcon()
     */
    @Override
    Image getIcon() {
        return FlowDesignerPlugin.getImageDescriptor(ICON).createImage();
    }
}
