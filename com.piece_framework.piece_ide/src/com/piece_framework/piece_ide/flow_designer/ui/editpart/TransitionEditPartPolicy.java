// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import com.piece_framework.piece_ide.flow_designer.command.DeleteConnectionCommand;
import com.piece_framework.piece_ide.flow_designer.model.Transition;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * 遷移・エディットポリシー.
 * 遷移を削除するコマンドを発行するエディットポリシー。
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
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

        Transition transition = (Transition) getHost().getModel();

        DeleteConnectionCommand command 
                            = new DeleteConnectionCommand(transition);
        command.setSource(transition.getSource());
        command.setTarget(transition.getTarget());
    
        return command;
    }
}
