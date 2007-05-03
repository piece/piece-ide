// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.NodeElement;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

public class CreateConnectionCommand extends Command {

    private NodeElement fSource;
    private NodeElement fTarget;
    private Transition fTransition;
    
    public CreateConnectionCommand(Transition transtion) {
        super();
        fTransition = transtion;
    }
    
    public void setSource(NodeElement source) {
        fSource = source;
    }
    
    public void setTarget(NodeElement target) {
        fTarget = target;
    }
    
    @Override
    public boolean canExecute() {
        return fSource != null && fTarget != null && !fSource.equals(fTarget);
    }
    
    @Override
    public void execute() {
        fSource.addOutgoing(fTransition);
        fTransition.setSource(fSource);
        fTarget.addIncoming(fTransition);
        fTransition.setTarget(fTarget);
    }
    
    @Override
    public void undo() {
        fSource.removeOutgoing(fTransition);
        fTransition.setSource(null);
        fTarget.removeIncoming(fTransition);
        fTransition.setTarget(null);
    }
}
