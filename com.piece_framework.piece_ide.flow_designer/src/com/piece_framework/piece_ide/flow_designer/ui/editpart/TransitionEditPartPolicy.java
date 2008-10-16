// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.piece_framework.piece_ide.flow_designer.command.DeleteConnectionCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * 遷移・エディットポリシー.
 * 遷移を削除するコマンドを発行するエディットポリシー。
 *
 * @author Seiichi Sugimoto
 * @since 0.1.0
 *
 */
public class TransitionEditPartPolicy extends ConnectionEditPolicy {

    /**
     * 遷移を削除するコマンドを返す.
     *
     * @param request リクエスト情報
     * @return 遷移削除コマンド
     * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy
     *          #getDeleteCommand(
     *              org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        State state = null;
        Event event = (Event) getHost().getModel();
        if (request.getEditParts().size() > 0) {
            TransitionEditPart transitionEditPart =
                (TransitionEditPart) request.getEditParts().get(0);
            state = (State) transitionEditPart.getSource().getModel();
        }
        return new DeleteConnectionCommand(state, event);
    }
}
