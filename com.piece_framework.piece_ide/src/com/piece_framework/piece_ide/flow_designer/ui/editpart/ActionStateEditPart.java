// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ActionStateFigure;

/**
 * アクションステート・エディットパート.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ActionStateEditPart extends StateEditPart {

    /**
     * フィギュアーを作成する.
     * 
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ActionStateFigure figure = new ActionStateFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
        
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
        }
        super.propertyChange(evt);
    }
    
    /**
     * ステート名をリフレッシュする.
     */
    private void refreshName() {
        ActionStateFigure figure = (ActionStateFigure) getFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
    }
    
}
