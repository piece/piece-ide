// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

/**
 * フローデザイナー・エディットパートファクトリー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowDesignerEditFactory implements EditPartFactory {

    /**
     * エディットパートを作成する.
     * モデルに対応したエディットパートを作成する。
     * 
     * @param context エディットパート
     * @param model モデル
     * @return モデルに対応したエディットパート
     * @see org.eclipse.gef.EditPartFactory
     *          #createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart = null;
        
        if (model instanceof State) {
            State state = (State) model;
            if (state.getStateType() == State.INITIAL_STATE) {
                editPart = new InitialStateEditPart();
                
            } else if (state.getStateType() == State.FINAL_STATE) {
                editPart = new FinalStateEditPart();
                
            } else if (state.getStateType() == State.ACTION_STATE) {
                editPart = new ActionStateEditPart();
            
            } else if (state.getStateType() == State.VIEW_STATE) {
                editPart = new ViewStateEditPart();
                
            }
        } else if (model instanceof Transition) {
            editPart = new TransitionEditPart();
            
        } else if (model instanceof Flow) {
            editPart = new FlowEditPart();
        }
        
        editPart.setModel(model);
        return editPart;
    }

}
