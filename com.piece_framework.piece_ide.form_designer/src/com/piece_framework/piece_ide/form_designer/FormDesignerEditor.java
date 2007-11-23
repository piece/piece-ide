package com.piece_framework.piece_ide.form_designer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormDesignerEditor extends FormEditor {

    public FormDesignerEditor() {
        // TODO 自動生成されたコンストラクター・スタブ
    }

    @Override
    protected void addPages() {
        FormToolkit formToolkit = new FormToolkit(getContainer().getDisplay());
        Form form = formToolkit.createForm(getContainer());
        form.setText("Hello Eclipse Forms!!");
        
        addPage(form);
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
