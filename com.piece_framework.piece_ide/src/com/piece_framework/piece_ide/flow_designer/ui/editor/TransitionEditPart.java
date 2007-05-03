// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;

public class TransitionEditPart extends AbstractConnectionEditPart 
                                  implements PropertyChangeListener {
    
    @Override
    protected void activateFigure() {
        super.activateFigure();
        AbstractModel model = (AbstractModel) getModel();
        model.addPropertyChangeListener(this);
    }

    @Override
    protected void deactivateFigure() {
        super.deactivateFigure();
        AbstractModel model = (AbstractModel) getModel();
        model.removePropertyChangeListener(this);
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, 
                          new ConnectionEndpointEditPolicy());
    }

    public void propertyChange(PropertyChangeEvent arg0) {
    }

}
