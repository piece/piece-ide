// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.piece_framework.piece_ide.flow_designer.model.ActionState;
import com.piece_framework.piece_ide.flow_designer.model.Diagram;
import com.piece_framework.piece_ide.flow_designer.model.FinalState;
import com.piece_framework.piece_ide.flow_designer.model.InitialState;
import com.piece_framework.piece_ide.flow_designer.model.Transition;
import com.piece_framework.piece_ide.flow_designer.model.ViewState;
import com.piece_framework.piece_ide.flow_designer.ui.editor.TransitionEditPart;

public class FlowDesignerEditFactory implements EditPartFactory {

    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart = null;
        
        if (model instanceof InitialState) {
            editPart = new InitialStateEditPart();
            
        } else if (model instanceof FinalState) {
            editPart = new FinalStateEditPart();
            
        } else if (model instanceof ViewState) {
            editPart = new ViewStateEditPart();
            
        } else if (model instanceof ActionState) {
            editPart = new ActionStateEditPart();
        
        } else if (model instanceof Transition) {
            editPart = new TransitionEditPart();
            
        } else if (model instanceof Diagram) {
            editPart = new DiagramEditPart();
        }
        
        editPart.setModel(model);
        return editPart;
    }

}
