// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.model.ActionState;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ActionStateFigure;

public class ActionStateEditPart extends StateEditPart {

    @Override
    protected IFigure createFigure() {
        ActionStateFigure figure = new ActionStateFigure();
        ActionState model = (ActionState) getModel();
        
        figure.setName(model.getName());
        
        return figure;
    }

    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("name")) {
            refreshName();
        }
        super.propertyChange(evt);
    }
    
    private void refreshName() {
        ActionStateFigure figure = (ActionStateFigure) getFigure();
        ActionState model = (ActionState) getModel();
        
        figure.setName(model.getName());
    }
    
}
