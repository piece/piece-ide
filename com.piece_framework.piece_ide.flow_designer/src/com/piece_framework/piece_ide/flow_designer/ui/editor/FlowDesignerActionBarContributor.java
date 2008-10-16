// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

/**
 * フローデザイナー・エディターのアクションバー.
 *
 * @author MATSUFUJI Hideharu
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

        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());
        addRetargetAction(
            new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY,
                               "&Grid",
                               IAction.AS_CHECK_BOX
                               ));
        addRetargetAction(
            new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY,
                               "Snap To Geo&metry",
                               IAction.AS_CHECK_BOX
                               ));
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

        toolBarManager.add(new Separator());
        String[] zoomStrings = new String[] {ZoomManager.FIT_ALL,
                                             ZoomManager.FIT_HEIGHT,
                                             ZoomManager.FIT_WIDTH};
        toolBarManager.add(new ZoomComboContributionItem(getPage(),
                                                         zoomStrings)
                           );
    }

    /**
     * メニューバーへコントリビュートする.
     *
     * @param menuManager メニューバー・マネージャ
     * @see org.eclipse.ui.part.EditorActionBarContributor
     *          #contributeToMenu(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void contributeToMenu(IMenuManager menuManager) {
        super.contributeToMenu(menuManager);
        MenuManager viewMenu = new MenuManager("&View");
        viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
        viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
        viewMenu.add(new Separator());
        viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
        viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
        menuManager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
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
