// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.piece_framework.piece_ide.flow_designer.model.Diagram;

public class DiagramEditPart extends AbstractModelEditPart {

    @Override
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        return layer;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, 
                          new DiagramLayoutEditPolicy());
    }
    
    @Override
    protected List getModelChildren() {
        Diagram diagram = (Diagram) getModel();
        return diagram.getContents();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("contents")) {
            refreshChildren();
        }
    }

}
