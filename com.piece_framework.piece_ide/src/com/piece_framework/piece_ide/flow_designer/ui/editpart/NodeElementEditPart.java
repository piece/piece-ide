// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import com.piece_framework.piece_ide.flow_designer.model.NodeElement;

public abstract class NodeElementEditPart extends AbstractModelEditPart 
                                             implements NodeEditPart {

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("x")) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals("y")) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals("incoming")) {
            refreshTargetConnections();
        } else if (evt.getPropertyName().equals("outgoing")) {
            refreshSourceConnections();
        }
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, 
                          new ElementComponentEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
                          new ElementGraphicalNodeEditPolicy());
        
    }

    protected void refreshVisuals() {
        NodeElement element = (NodeElement) getModel();
        
        Point point = new Point(element.getX(), element.getY());
        
        Dimension dimension = new Dimension(-1, -1);
        Rectangle rectangle = new Rectangle(point, dimension);
        
        GraphicalEditPart parent = (GraphicalEditPart) getParent();
        parent.setLayoutConstraint(this, getFigure(), rectangle);
    }
    
    @Override
    protected List getModelSourceConnections() {
        NodeElement element = (NodeElement) getModel();
        return element.getOutgoings();
    }

    @Override
    protected List getModelTargetConnections() {
        NodeElement element = (NodeElement) getModel();
        return element.getIncomings();
    }

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }
    
}
