// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.ui.figure.InitialStateFigure;

public class InitialStateEditPart extends NodeElementEditPart {

    @Override
    protected IFigure createFigure() {
        InitialStateFigure figure = new InitialStateFigure();
        
        return figure;
    }

    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
    }
    
}
