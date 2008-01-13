// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * �t�H�[���f�U�C�i�[�E�G�f�B�^�[.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FormDesignerEditor extends FormEditor {

    /**
     * �R���X�g���N�^.
     * 
     */
    public FormDesignerEditor() {
    }

    /**
     * �G�f�B�^�[�̏��������s��.
     * 
     * @param site �G�f�B�^�[�T�C�g
     * @param input �G�f�B�^�[�C���v�b�g
     * @throws PartInitException �p�[�g��������O
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
     * �y�[�W��ǉ�����.
     * 
     * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
     */
    @Override
    protected void addPages() {
        try {
            addPage(new FieldsPage(this));
        } catch (PartInitException e) {
            // TODO �����������ꂽ catch �u���b�N
            e.printStackTrace();
        }
    }

    /**
     * �t�H�[����`��ۑ�����.
     * 
     * @param monitor �v���O���X���j�^�[
     * @see org.eclipse.ui.part.EditorPart#doSave(
     *          org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO:�}�b�p�[���g����YAML�ɕۑ�����
    }

    /**
     * �t�H�[����`���قȂ�I�u�W�F�N�g�ɕۑ�����.
     * 
     * @see org.eclipse.ui.part.EditorPart#doSaveAs()
     */
    @Override
    public void doSaveAs() {
    }

    /**
     * �t�H�[����`���قȂ�I�u�W�F�N�g�ɕۑ��ł��邩�ǂ�����Ԃ�.
     * 
     * @return �t�H�[����`���قȂ�I�u�W�F�N�g�ɕۑ��ł���ꍇ��true
     * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }
}
