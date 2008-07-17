// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

/**
 * フローデザイナー・エディターのアクションバー.
 *
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 */
public class FlowDesignerActionBarContributor extends ActionBarContributor {

    /**
     * アクションをビルトする.
     *
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
     */
    @Override
    protected void buildActions() {
        addRetargetAction(new DeleteRetargetAction());
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new AdjustEventsRetargetAction());
    }

    /**
     * ツールバーへコントリビュートする.
     *
     * @param toolBarManager ツールバー・マネージャ
     * @see org.eclipse.ui.part.EditorActionBarContributor
     *          #contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    @Override
    public void contributeToToolBar(IToolBarManager toolBarManager) {
        toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
        toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));

        toolBarManager.add(new Separator());
        toolBarManager.add(getAction(AdjustEventsAction.ADJUST_EVENTS));
    }

    /**
     * グローバル・アクションキーを定義する.
     *
     * @see org.eclipse.gef.ui.actions.ActionBarContributor
     *          #declareGlobalActionKeys()
     */
    @Override
    protected void declareGlobalActionKeys() {
    }
}
