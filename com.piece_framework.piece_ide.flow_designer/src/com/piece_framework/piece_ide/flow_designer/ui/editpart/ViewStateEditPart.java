// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ViewStateFigure;

/**
 * ビューステート・エディットパート.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class ViewStateEditPart extends NormalStateEditPart {

    /**
     * フィギュアーを作成する.
     *
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ViewStateFigure figure = new ViewStateFigure();

        refreshFigure(figure);
        refreshView(figure);

        return figure;
    }

    /**
     * プロパティ変更イベントを処理する.
     *
     * @param evt プロパティ変更イベント
     * @see com.piece_framework.piece_ide.flow_designer.
     *          ui.editpart.StateEditPart
     *              #propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("State#View")) { //$NON-NLS-1$
            refreshView((ViewStateFigure) getFigure());
        }
        super.propertyChange(evt);
    }

    /**
     * ビュー名をリフレッシュする.
     *
     * @param figure フィギュアー
     */
    private void refreshView(ViewStateFigure figure) {
        State state = (State) getModel();

        figure.setView(state.getView());
    }
}
