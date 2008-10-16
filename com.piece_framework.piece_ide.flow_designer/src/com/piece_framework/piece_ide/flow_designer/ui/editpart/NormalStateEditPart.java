// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.ui.figure.NormalStateFigure;

/**
 * ノーマルステート・抽象エディットパート.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public abstract class NormalStateEditPart extends StateEditPart {

    /**
     * フィギュアーをリフレッシュする.
     *
     * @param figure リフレッシュするフィギュアー
     */
    protected void refreshFigure(NormalStateFigure figure) {
        refreshName(figure);
        refreshEvent(figure);
    }

    /**
     * ステート名をリフレッシュする.
     *
     * @param figure フィギュアー
     */
    protected void refreshName(NormalStateFigure figure) {
        State state = (State) getModel();

        figure.setName(state.getName());
    }

    /**
     * イベントをリフレッシュする.
     *
     * @param figure フィギュアー
     */
    protected void refreshEvent(NormalStateFigure figure) {
        State state = (State) getModel();

        figure.removeAllBuiltinEvent();
        figure.removeAllTransitionEvent();
        figure.removeAllInternalEvent();

        for (Event event : state.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                figure.addBuiltinEvent(event.getName());
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                figure.addTransitionEvent(event.getName());
            } else if (event.getType() == Event.INTERNAL_EVENT) {
                figure.addInternalEvent(event.getName());
            }
        }
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
        if (event.getPropertyName().equals("State#Name")) { //$NON-NLS-1$
            refreshName((NormalStateFigure) getFigure());
        } else if (event.getPropertyName().equals(
                        "State#Event")) { //$NON-NLS-1$
            refreshEvent((NormalStateFigure) getFigure());
        }
        super.propertyChange(event);
    }

    /**
     * リクエストを処理する.
     * ダブルクリック時にビューステート・アクションステートの
     *
     * @param request リクエスト情報
     * @see org.eclipse.gef.editparts.AbstractEditPart
     *          #performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request request) {
        if (request.getType().equals(RequestConstants.REQ_OPEN)) {
            if (getParent() instanceof FlowEditPart) {
                ((FlowEditPart) getParent()).reorderEditPartToLast(this);
            }

            NormalStateFigure figure = (NormalStateFigure) getFigure();
            figure.setVisibleEventList(!figure.isVisibleEventList());

            return;
        }
        super.performRequest(request);
    }
}
