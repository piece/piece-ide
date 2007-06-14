// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.ui.figure.TransitionFigure;

/**
 * 遷移・エディットパート.
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class TransitionEditPart extends AbstractConnectionEditPart implements
        PropertyChangeListener {

    /**
     * フィギュアーを作成する.
     * 
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        TransitionFigure figure = new TransitionFigure();

        return figure;
    }

    /**
     * フィギュアーのアクティブ時処理.
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart
     * 　　　　　　　　　　　　　　　　　　　　　　　#createEditPolicies()
     */
    @Override
    protected void activateFigure() {
        super.activateFigure();
        AbstractModel model = (AbstractModel) getModel();
        model.addPropertyChangeListener(this);
    }

    /**
     * フィギュアーの非アクティブ時処理.
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart
     *                                               #createEditPolicies()
     */
    @Override
    protected void deactivateFigure() {
        super.deactivateFigure();
        AbstractModel model = (AbstractModel) getModel();
        model.removePropertyChangeListener(this);
    }

    /**
     * エディットポリシーを作成する.
     * 
     * @see java.beans.PropertyChangeListener#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
                new ConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ROLE,
                new TransitionEditPartPolicy());
    }

    /**
     * プロパティ変更イベントを処理する.
     * 
     * @param evt
     *            プロパティ変更イベント
     * @see java.beans.PropertyChangeListener
     *                       #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
