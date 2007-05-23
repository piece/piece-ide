// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.piece_framework.piece_ide.flow_designer.command.CreateConnectionCommand;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

public class ElementGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    @Override
    protected Command getConnectionCompleteCommand(
            CreateConnectionRequest request) {
        
        State element = (State) request.getTargetEditPart().getModel();
        CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
        command.setTarget(element);
        return command;
    }

    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        Transition transition = (Transition) request.getNewObject();
        State element = (State) request.getTargetEditPart().getModel();
        CreateConnectionCommand command = new CreateConnectionCommand(transition);
        command.setSource(element);
        request.setStartCommand(command);
        return command;
    }

    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        return null;
    }

    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }

}
