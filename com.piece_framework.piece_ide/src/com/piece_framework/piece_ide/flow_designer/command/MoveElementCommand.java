// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.State;

public class MoveElementCommand extends Command {
    
    private int fX;
    private int fOldX;
    
    private int fY;
    private int fOldY;
    
    private State fElement;
    
    public MoveElementCommand(int x, int y, State element) {
        super();
        fX = x;
        fY = y;
        fElement = element;
    }
    
    public void execute() {
        fOldX = fElement.getX();
        fOldY = fElement.getY();
        fElement.setX(fX);
        fElement.setY(fY);
    }
    
    public void undo() {
        fElement.setX(fOldX);
        fElement.setY(fOldY);
    }

}
