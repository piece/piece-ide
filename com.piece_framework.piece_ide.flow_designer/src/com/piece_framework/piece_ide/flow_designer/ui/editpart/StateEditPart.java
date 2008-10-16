// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ActionStateFigure;
import com.piece_framework.piece_ide.flow_designer.ui.figure.FinalStateFigure;
import com.piece_framework.piece_ide.flow_designer.ui.figure.InitialStateFigure;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ViewStateFigure;

/**
 * ステート・エディットパート.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public abstract class StateEditPart extends AbstractModelEditPart
                                             implements NodeEditPart {

    /**
     * フィギュアーを生成する.
     * ステートタイプに合ったフィギュアーを生成する。
     *
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        IFigure figure = null;

        State state = (State) getModel();
        if (state.getType() == State.INITIAL_STATE) {
            figure = new InitialStateFigure();
        } else if (state.getType() == State.VIEW_STATE) {
            figure = new ViewStateFigure();
        } else if (state.getType() == State.ACTION_STATE) {
            figure = new ActionStateFigure();
        } else if (state.getType() == State.FINAL_STATE) {
            figure = new FinalStateFigure();
        }

        return figure;
    }

    /**
     * プロパティ変更イベント対応メソッド.
     *
     * @param event プロパティ変更イベント
     * @see java.beans.PropertyChangeListener
     *          #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("State#X")) { //$NON-NLS-1$
            refreshVisuals();
        } else if (event.getPropertyName().equals("State#Y")) { //$NON-NLS-1$
            refreshVisuals();
        } else if (event.getPropertyName().equals(
                        "State#Event")) { //$NON-NLS-1$
            refreshSourceConnections();
        } else if (event.getPropertyName().equals(
                        "State#TransitionEvent")) { //$NON-NLS-1$
            refreshTargetConnections();
        }
    }

    /**
     * ステートに関するエディットポリシーを作成する.
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE,
                          new StateComponentEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
                          new StateGraphicalNodeEditPolicy());
    }

    /**
     * ビジュアルを再描画する.
     *
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        State state = (State) getModel();

        Point point = new Point(state.getX(), state.getY());

        Dimension dimension = new Dimension(-1, -1);
        Rectangle rectangle = new Rectangle(point, dimension);

        GraphicalEditPart parent = (GraphicalEditPart) getParent();
        parent.setLayoutConstraint(this, getFigure(), rectangle);
    }

    /**
     * ステートが遷移元になっている遷移リストを返す.
     *
     * @return 遷移リスト
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
     *          #getModelSourceConnections()
     */
    @Override
    protected List getModelSourceConnections() {
        return ((State) getModel()).getTransitionEventList();
    }

    /**
     * ステートが遷移先になっている遷移リストを返す.
     * フローが保持する自身を除く全ステートの遷移イベントの
     * 遷移先ステートを調べる。
     *
     * @return 遷移リスト
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
     *          #getModelTargetConnections()
     */
    @Override
    protected List getModelTargetConnections() {
        Flow flow = (Flow) getParent().getModel();

        return flow.getTransitionEventListToOwnState((State) getModel());
    }

    /**
     * 遷移の線の遷移元のアンカーを返す.
     *
     * @param connection コネクション・エディットパート
     * @return アンカー
     * @see org.eclipse.gef.NodeEditPart
     *          #getSourceConnectionAnchor(
     *              org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(
                                ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * 遷移の線の遷移元のアンカーを返す.
     *
     * @param request リクエスト情報
     * @return アンカー
     * @see org.eclipse.gef.NodeEditPart
     *          #getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * 遷移の線の遷移先のアンカーを返す.
     *
     * @param connection コネクション・エディットパート
     * @return アンカー
     * @see org.eclipse.gef.NodeEditPart
     *          #getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(
                                ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    /**
     * 遷移の線の遷移先のアンカーを返す.
     *
     * @param request リクエスト情報
     * @return アンカー
     * @see org.eclipse.gef.NodeEditPart
     *          #getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }
}
