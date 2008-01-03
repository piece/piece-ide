// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public class FormDesignerEditor extends FormEditor {

    public FormDesignerEditor() {
        // TODO 自動生成されたコンストラクター・スタブ
    }

    @Override
    public void init(IEditorSite site, IEditorInput input)
            throws PartInitException {
        super.init(site, input);
        setPartName(getEditorInput().getName());
    }

    @Override
    protected void addPages() {
        try {
            addPage(new FieldsPage(this));
        } catch (PartInitException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void doSaveAs() {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public boolean isSaveAsAllowed() {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }
}
