package com.piece_framework.piece_ide.form_designer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormDesignerEditor extends FormEditor {

    public FormDesignerEditor() {
        // TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
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
        // TODO �����������ꂽ���\�b�h�E�X�^�u

    }

    @Override
    public void doSaveAs() {
        // TODO �����������ꂽ���\�b�h�E�X�^�u

    }

    @Override
    public boolean isSaveAsAllowed() {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return false;
    }
}
