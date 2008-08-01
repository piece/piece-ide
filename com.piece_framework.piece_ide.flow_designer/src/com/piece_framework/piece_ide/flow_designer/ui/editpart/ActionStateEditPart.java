// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.ui.figure.ActionStateFigure;

/**
 * アクションステート・エディットパート.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class ActionStateEditPart extends NormalStateEditPart {

    /**
     * フィギュアーを作成する.
     *
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ActionStateFigure figure = new ActionStateFigure();

        refreshFigure(figure);

        return figure;
    }
}
