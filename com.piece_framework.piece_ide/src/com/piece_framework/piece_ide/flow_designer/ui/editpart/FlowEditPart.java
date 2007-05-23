// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class FlowEditPart extends AbstractModelEditPart {

    @Override
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        return layer;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, 
                          new FlowLayoutEditPolicy());
    }
    
    @Override
    protected List getModelChildren() {
        Flow flow = (Flow) getModel();
        return flow.getStates();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            refreshChildren();
        }
    }

}
