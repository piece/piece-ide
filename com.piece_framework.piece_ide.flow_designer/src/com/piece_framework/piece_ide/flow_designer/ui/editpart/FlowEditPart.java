// $Id: FlowEditPart.java 168 2007-07-25 01:34:30Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;

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
        return flow.getStateList();
    }

    /**
     * プロパティー変更イベントを処理する.
     * 
     * @param event プロパティー変更イベント
     * @see java.beans.PropertyChangeListener
     *          #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("Flow#State")) {
            refreshChildren();
        }
    }
    
    /**
     * 指定された子エディットパートを最後に移動する.
     * さらに親フィギュアーも合わせて最後に移動し、指定された EditPart が
     * 遷移も含めた全体の中で最後になるようにする。<br>
     * このようにすることで、最前面に描画されるようになる。
     * 
     * @param editPart 最後に移動するエディットパート
     */
    public void reorderEditPartToLast(EditPart editPart) {
        reorderChild(editPart, children.size() - 1);
        
        IFigure printableLayer = 
            (IFigure) getLayer(LayerConstants.PRINTABLE_LAYERS);
        printableLayer.remove(getFigure().getParent());
        printableLayer.add(getFigure().getParent());
    }
}
