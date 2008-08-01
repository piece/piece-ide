// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;

/**
 * モデル・エディットパート.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public abstract class AbstractModelEditPart
                    extends AbstractGraphicalEditPart
                    implements PropertyChangeListener {

    /**
     * アクティブ時に呼ばれるメソッド.
     * モデルのリスナーとしてい自身を登録する.
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        AbstractModel model = (AbstractModel) getModel();
        model.addPropertyChangeListener(this);
    }

    /**
     * 非アクティブ時に呼ばれるメソッド.
     * モデルから登録したリスナーを削除する.
     *
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        AbstractModel model = (AbstractModel) getModel();
        model.removePropertyChangeListener(this);
    }
}
