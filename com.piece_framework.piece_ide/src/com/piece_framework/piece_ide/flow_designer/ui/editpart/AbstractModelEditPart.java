// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;

public abstract class AbstractModelEditPart 
                    extends AbstractGraphicalEditPart 
                    implements PropertyChangeListener {

    @Override
    public void activate() {
        super.activate();
        AbstractModel model = (AbstractModel) getModel();
        model.addPropertyChangeListener(this);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        AbstractModel model = (AbstractModel) getModel();
        model.removePropertyChangeListener(this);
    }
}
