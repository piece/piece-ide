// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.piece_framework.piece_ide.flow_designer.command.CreateStateCommand;
import com.piece_framework.piece_ide.flow_designer.command.MoveStateCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローレイアウト・エディットポリシー.
 * ステートの作成・位置情報のコマンドを発行するエディットポリシー。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy
 */
public class FlowLayoutEditPolicy extends XYLayoutEditPolicy {
    /**
     * ステートの位置が変化したときのコマンドを返す.
     *
     * @param child エディットパート
     * @param constraint ステートの座標
     * @return ステートを移動するコマンド
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy
     *          #createChangeConstraintCommand(
     *              org.eclipse.gef.EditPart, java.lang.Object)
     */
    @Override
    protected Command createChangeConstraintCommand(EditPart child,
                                                    Object constraint
                                                    ) {
        Rectangle rectangle = (Rectangle) constraint;
        State state = (State) child.getModel();

        return new MoveStateCommand(rectangle.x, rectangle.y, state);
    }

    /**
     * 新しいステートを作成するコマンドを返す.
     *
     * @param request リクエスト情報
     * @return ステートを作成するコマンド
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy
     *          #getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        Point point = request.getLocation();
        Flow flow = (Flow) getHost().getModel();
        State state = (State) request.getNewObject();

        return new CreateStateCommand(flow, state, point.x, point.y);
    }

}
