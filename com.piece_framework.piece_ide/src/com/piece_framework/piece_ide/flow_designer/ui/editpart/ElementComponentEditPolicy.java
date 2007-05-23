// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.piece_framework.piece_ide.flow_designer.command.DeleteElementCommand;
import com.piece_framework.piece_ide.flow_designer.model.Diagram;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class ElementComponentEditPolicy extends ComponentEditPolicy {

    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        
        Diagram parent = (Diagram) getHost().getParent().getModel();
        State element = (State) getHost().getModel();
        
        return new DeleteElementCommand(parent, element);
    }    
}
