// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.draw2d.IFigure;

import com.piece_framework.piece_ide.flow_designer.ui.figure.FinalStateFigure;

public class FinalStateEditPart extends NodeElementEditPart {

    @Override
    protected IFigure createFigure() {
        FinalStateFigure figure = new FinalStateFigure();
        
        return figure;
    }

    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
    }
    
}
