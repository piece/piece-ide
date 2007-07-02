// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.piece_framework.piece_ide.flow_designer.model.Event;
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
        
        refreshName(figure);
        refreshEvent(figure);
        
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
     * プロパティー変更イベントを処理する.
     * 
     * @param event プロパティー変更イベント
     * @see com.piece_framework.piece_ide.flow_designer.
     *          ui.editpart.StateEditPart
     *              #propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("name")) {
            refreshName((ActionStateFigure) getFigure());
        } else if (event.getPropertyName().equals("event")) {
            refreshEvent((ActionStateFigure) getFigure());
        }
        super.propertyChange(event);
    }
    
    /**
     * ステート名をリフレッシュする.
     *
     * @param figure フィギュアー
     */
    private void refreshName(ActionStateFigure figure) {
        State state = (State) getModel();
        
        figure.setName(state.getName());
    }
    
    /**
     * イベントをリフレッシュする.
     * 
     * @param figure フィギュアー
     */
    private void refreshEvent(ActionStateFigure figure) {
        State state = (State) getModel();
        
        figure.removeAllBuiltinEvent();
        figure.removeAllTransitionEvent();
        figure.removeAllInternalEvent();
        
        for (Event event : state.getEventList()) {
            if (event.isBuiltinEvent()) {
                figure.addBuiltinEvent(event.getName());
            } else if (event.isTransitionEvent()) {
                figure.addTransitionEvent(event.getName());
            } else if (event.isInternalEvent()) {
                figure.addInternalEvent(event.getName());
            }
        }
    }
    
    /**
     * リクエストを処理する.
     * 
     * @param request リクエスト情報
     * @see org.eclipse.gef.editparts.AbstractEditPart
     *          #performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request request) {
        if (request.getType().equals(RequestConstants.REQ_OPEN)) {
            ActionStateFigure figure = (ActionStateFigure) getFigure();
            figure.setVisibleEventList(!figure.isVisibleEventList());
            
            return;
        }
        super.performRequest(request);
    }
}
