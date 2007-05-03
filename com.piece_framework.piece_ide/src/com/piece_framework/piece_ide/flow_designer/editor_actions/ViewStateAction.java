// $Id$
package com.piece_framework.piece_ide.flow_designer.editor_actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

/**
 * フローデザイナー・ビューステートアクションクラス.
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 */
public class ViewStateAction implements IEditorActionDelegate {

    /**
     * コンストラクタ.
     */
    public ViewStateAction() {
    }

    /**
     * アクティブ時処理.
     * @param action アクション
     * @param targetEditor ターゲットとなるエディタ
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
    }

    /**
     * 選択時処理.
     * @param action アクション
     * @param selection セレクション
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }
    
    /**
     * 実行時処理.
     * @param action アクション
     */
    public void run(IAction action) {
        System.out.println("ビューステート");
    }
}
