// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Diagram;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class CreateElementCommand extends Command {

    private Diagram fParent;
    private State fElement;
    
    private int fX;
    private int fY;
    
    public CreateElementCommand(Diagram parent, 
                                 State element, 
                                 int x, int y) {
        super();
        fParent = parent;
        fElement = element;
        fX = x;
        fY = y;
    }

    @Override
    public void execute() {
        fElement.setX(fX);
        fElement.setY(fY);
        fParent.addContents(fElement);
    }

    @Override
    public void undo() {
        fParent.removeContents(fElement);
    }
}
