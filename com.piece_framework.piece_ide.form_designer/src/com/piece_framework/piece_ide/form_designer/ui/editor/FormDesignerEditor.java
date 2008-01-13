// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * フォームデザイナー・エディター.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FormDesignerEditor extends FormEditor {

    /**
     * コンストラクタ.
     * 
     */
    public FormDesignerEditor() {
    }

    /**
     * エディターの初期化を行う.
     * 
     * @param site エディターサイト
     * @param input エディターインプット
     * @throws PartInitException パート初期化例外
     * @see org.eclipse.ui.forms.editor.FormEditor#init(
     *          org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
     */
    @Override
    public void init(IEditorSite site, IEditorInput input)
            throws PartInitException {
        super.init(site, input);
        setPartName(getEditorInput().getName());
    }

    /**
     * ページを追加する.
     * 
     * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
     */
    @Override
    protected void addPages() {
        try {
            addPage(new FieldsPage(this));
        } catch (PartInitException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    /**
     * フォーム定義を保存する.
     * 
     * @param monitor プログレスモニター
     * @see org.eclipse.ui.part.EditorPart#doSave(
     *          org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO:マッパーを使ってYAMLに保存する
    }

    /**
     * フォーム定義を異なるオブジェクトに保存する.
     * 
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    @Override
    public void doSaveAs() {
    }

    /**
     * フォーム定義を異なるオブジェクトに保存できるかどうかを返す.
     * 
     * @return フォーム定義を異なるオブジェクトに保存できる場合はtrue
     * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }
}
