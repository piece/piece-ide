// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Container;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class DeleteElementCommand extends Command {

    private Container fContainer;
    private State fElement;
    
    public DeleteElementCommand(Container container, State element) {
        fContainer = container;
        fElement = element;
    }

    @Override
    public void execute() {
        fContainer.removeContents(fElement);
    }

    @Override
    public void undo() {
        fContainer.addContents(fElement);
    }
}
