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
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ViewStateEditPart extends StateEditPart {

    /**
     * フィギュアーを作成する.
     * 
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ViewStateFigure figure = new ViewStateFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
        figure.setView(state.getView());
        
        return figure;
    }

    /**
     * エディットポリシーを作成する.
     * 
     * @see com.piece_framework.piece_ide.flow_designer.
     *          ui.editpart.StateEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
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
        if (evt.getPropertyName().equals("name")) {
            refreshName();
        } else if (evt.getPropertyName().equals("view")) {
            refreshView();
        }
        super.propertyChange(evt);
    }

    /**
     * ステート名をリフレッシュする.
     */
    private void refreshName() {
        ViewStateFigure figure = (ViewStateFigure) getFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
    }
    
    /**
     * ビュー名をリフレッシュする.
     */
    private void refreshView() {
        ViewStateFigure figure = (ViewStateFigure) getFigure();
        State state = (State) getModel();
        
        figure.setView(state.getView());
    }
    
    
}
