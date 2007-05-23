// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.model.ViewState;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ViewStateFigure;

public class ViewStateEditPart extends StateEditPart {

    @Override
    protected IFigure createFigure() {
        ViewStateFigure figure = new ViewStateFigure();
        ViewState model = (ViewState) getModel();
        
        figure.setName(model.getName());
        figure.setView(model.getView());
        
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
        } else if (evt.getPropertyName().equals("view")) {
            refreshView();
        }
        super.propertyChange(evt);
    }
    
    private void refreshName() {
        ViewStateFigure figure = (ViewStateFigure) getFigure();
        ViewState model = (ViewState) getModel();
        
        figure.setName(model.getName());
    }
    
    private void refreshView() {
        ViewStateFigure figure = (ViewStateFigure) getFigure();
        ViewState model = (ViewState) getModel();
        
        figure.setView(model.getView());
    }
    
    
}
