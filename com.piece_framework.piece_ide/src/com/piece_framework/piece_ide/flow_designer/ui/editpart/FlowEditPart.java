// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フロー・エディットパート.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowEditPart extends AbstractModelEditPart {

    /**
     * フィギュアーを作成する.
     * フィギュアーとしてレイアウトを作成し、それを返す。
     * 
     * @return レイアウト
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        return layer;
    }

    /**
     * エディットポリシーを作成する.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, 
                          new FlowLayoutEditPolicy());
    }
    
    /**
     * フローに属する子モデルをリストで返す.
     * 
     * @return 子モデル
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @Override
    protected List getModelChildren() {
        Flow flow = (Flow) getModel();
        return flow.getStates();
    }

    /**
     * プロパティー変更イベントを処理する.
     * 
     * @param event プロパティー変更イベント
     * @see java.beans.PropertyChangeListener
     *          #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("state")) {
            refreshChildren();
        }
    }
}
