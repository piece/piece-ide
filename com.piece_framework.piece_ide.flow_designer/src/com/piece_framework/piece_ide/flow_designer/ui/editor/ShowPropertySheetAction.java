// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * プロパティーシート表示アクション.
 * プロパティーシートを表示するアクション。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.2.0
 *
 */
public class ShowPropertySheetAction extends EditorPartAction {
    /** プロパティーシート表示アクションID. */
    public static final String SHOW_PROPERTY_SHEET = "ShowPropertySheet";
    
    /**
     * コンストラクタ.
     * 
     * @param editor エディター
     */
    public ShowPropertySheetAction(IEditorPart editor) {
        super(editor);
        
        setId(SHOW_PROPERTY_SHEET);
        setText("プロパティー");
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
                        "org.eclipse.ui.views.PropertySheet");
        } catch (PartInitException pie) {
            pie.printStackTrace();
        }
    }    
}
