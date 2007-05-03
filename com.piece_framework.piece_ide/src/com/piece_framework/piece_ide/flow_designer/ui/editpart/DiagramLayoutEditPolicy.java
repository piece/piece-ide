// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.piece_framework.piece_ide.flow_designer.command.CreateElementCommand;
import com.piece_framework.piece_ide.flow_designer.command.MoveElementCommand;
import com.piece_framework.piece_ide.flow_designer.model.Diagram;
import com.piece_framework.piece_ide.flow_designer.model.NodeElement;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

    @Override
    protected Command createChangeConstraintCommand(EditPart child,
            Object constraint) {
        
        Rectangle rectangle = (Rectangle) constraint;
        NodeElement element = (NodeElement) child.getModel();
        
        return new MoveElementCommand(rectangle.x, rectangle.y, element);
    }

    @Override
    protected Command getCreateCommand(CreateRequest request) {
        
        Point point = request.getLocation();
        NodeElement element = (NodeElement) request.getNewObject();
        Diagram diagram = (Diagram) getHost().getModel();
        
        return new CreateElementCommand(diagram, element, point.x, point.y);
    }

}
