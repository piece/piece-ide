// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

public class FormDesignerEditor extends FormEditor {

    public FormDesignerEditor() {
        // TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
    }

    @Override
    protected void addPages() {
        try {
            addPage(new FieldPage(this));
        } catch (PartInitException e) {
            // TODO �����������ꂽ catch �u���b�N
            e.printStackTrace();
        }
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
