// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.piece_framework.piece_ide.flow_designer.command.CreateConnectionCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステートグラフィックノード・エディットポリシー.
 * 遷移の作成に関するコマンドを発行するエディットポリシー。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class StateGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    /**
     * 遷移作成コマンドを返す.
     * このメソッドは遷移先が決定したときに呼び出される。<br>
     * そこで、遷移作成コマンド (CreateStateConnection) をリクエストから取り出し、<br>
     * 遷移先ステートを設定して返す。
     *
     * @param request リクエスト情報
     * @return 遷移作成コマンド
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
     *          #getConnectionCompleteCommand(
     *              org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCompleteCommand(
                            CreateConnectionRequest request) {
        State nextState = (State) request.getTargetEditPart().getModel();
        CreateConnectionCommand command =
            (CreateConnectionCommand) request.getStartCommand();
        command.setNextState(nextState);

        return command;
    }

    /**
     * 遷移作成コマンドを返す.
     * このメソッドは遷移元が決定したときに呼び出される。<br>
     * この時点では遷移先は決定していないので、
     * 遷移作成コマンド (CreateStateConnection) には遷移元ステート
     * のみを設定して返す。
     *
     * @param request リクエスト情報
     * @return 遷移作成コマンド
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
     *          #getConnectionCreateCommand(
     *              org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCreateCommand(
                            CreateConnectionRequest request) {
        Event event = (Event) request.getNewObject();
        State state = (State) request.getTargetEditPart().getModel();
        Flow flow = (Flow) request.getTargetEditPart().getParent().getModel();
        CreateConnectionCommand command =
            new CreateConnectionCommand(event);
        command.setFlow(flow);
        command.setState(state);
        request.setStartCommand(command);

        return command;
    }

    /**
     * 遷移元のコネクションを設定し直したときのコマンドを返す.
     *
     * @param request リクエスト情報
     * @return コネクション再設定コマンド
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
     *          #getReconnectSourceCommand(
     *              org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        return null;
    }

    /**
     * 遷移先のコネクションを設定し直したときのコマンドを返す.
     *
     * @param request リクエスト情報
     * @return コネクション再設定コマンド
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
     *          #getReconnectTargetCommand(
     *              org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }
}
