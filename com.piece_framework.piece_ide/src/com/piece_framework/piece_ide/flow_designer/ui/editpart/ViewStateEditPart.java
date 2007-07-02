// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.piece_framework.piece_ide.flow_designer.model.Event;
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
        
        refreshName(figure);
        refreshView(figure);
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
            refreshName((ViewStateFigure) getFigure());
        } else if (evt.getPropertyName().equals("view")) {
            refreshView((ViewStateFigure) getFigure());
        } else if (evt.getPropertyName().equals("event")) {
            refreshEvent((ViewStateFigure) getFigure());
        }
        super.propertyChange(evt);
    }

    /**
     * ステート名をリフレッシュする.
     *
     * @param figure フィギュアー
     */
    private void refreshName(ViewStateFigure figure) {
        State state = (State) getModel();
        
        figure.setName(state.getName());
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
    
    /**
     * イベントをリフレッシュする.
     * 
     * @param figure フィギュアー
     */
    private void refreshEvent(ViewStateFigure figure) {
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
            ViewStateFigure figure = (ViewStateFigure) getFigure();
            figure.setVisibleEventList(!figure.isVisibleEventList());
            
            return;
        }
        super.performRequest(request);
    }
}
