// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * プロパティーシート表示アクション.
 * プロパティーシートを表示するアクション。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.2.0
 *
 */
public class ShowPropertySheetAction extends EditorPartAction {
    /** プロパティーシート表示アクションID. */
    public static final String SHOW_PROPERTY_SHEET =
                                    "ShowPropertySheet"; //$NON-NLS-1$

    /**
     * コンストラクタ.
     *
     * @param editor エディター
     */
    public ShowPropertySheetAction(IEditorPart editor) {
        super(editor);

        setId(SHOW_PROPERTY_SHEET);
        setText(Messages.getString("ShowPropertySheet.Label")); //$NON-NLS-1$
    }

    /**
     * 実行可能状態を返す.
     * 常にtrueを返す。
     *
     * @return 実行可能状態
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * プロパティーシートを表示する.
     *
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().showView(
                        "org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
        } catch (PartInitException pie) {
            pie.printStackTrace();
        }
    }
}
