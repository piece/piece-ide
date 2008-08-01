// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.piece_framework.piece_ide.flow_designer.command.DeleteStateCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステートコンポーネント・エディットポリシー.
 * ステートを削除するコマンドを発行するエディットポリシー。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class StateComponentEditPolicy extends ComponentEditPolicy {

    /**
     * ステートを削除するコマンドを返す.
     *
     * @param request 削除リクエスト情報
     * @return ステートを削除するコマンド
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy
     *          #getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        Flow parent = (Flow) getHost().getParent().getModel();
        State state = (State) getHost().getModel();
        return new DeleteStateCommand(parent, state);
    }
}
