// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローデザイナー・エディットパートファクトリー.
 *
 * @author MATSUFUJI Hideharu
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
            if (state.getType() == State.INITIAL_STATE) {
                editPart = new InitialStateEditPart();
            } else if (state.getType() == State.FINAL_STATE) {
                editPart = new FinalStateEditPart();
            } else if (state.getType() == State.ACTION_STATE) {
                editPart = new ActionStateEditPart();
            } else if (state.getType() == State.VIEW_STATE) {
                editPart = new ViewStateEditPart();
            }
        } else if (model instanceof Event) {
            editPart = new TransitionEditPart();
        } else if (model instanceof Flow) {
            editPart = new FlowEditPart();
        }

        editPart.setModel(model);
        return editPart;
    }

}
