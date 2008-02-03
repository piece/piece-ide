// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

/**
 * �t�B�[���hDetails�y�[�W.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FieldDetailsPage implements IDetailsPage {
    private IManagedForm fForm;
    private GeneralSection fGeneralSection;
    private ValidatorSection fValidatorSection;

    /**
     * �R���g���[���𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @see org.eclipse.ui.forms.IDetailsPage#createContents(
     *          org.eclipse.swt.widgets.Composite)
     */
    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout(SWT.VERTICAL));

        FormToolkit toolkit = fForm.getToolkit();

        Composite parentComposite = toolkit.createComposite(parent);
        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        parentComposite.setLayout(layout);

        fGeneralSection = new GeneralSection(
                                toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR), 
                                fForm);
        fValidatorSection = new ValidatorSection(
                                toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR), 
                                fForm);
    }

    /**
     * �ύX���ꂽ���f�����R�~�b�g����.
     * 
     * @param onSave �ۑ��������̏ꍇ��true
     * @see org.eclipse.ui.forms.IFormPart#commit(boolean)
     */
    public void commit(boolean onSave) {
    }

    /**
     * �I���������s��.
     * 
     * @see org.eclipse.ui.forms.IFormPart#dispose()
     */
    public void dispose() {
    }

    /**
     * �������������s��.
     * 
     * @param form �Ǘ��t�H�[��
     * @see org.eclipse.ui.forms.IFormPart#initialize(
     *          org.eclipse.ui.forms.IManagedForm)
     */
    public void initialize(IManagedForm form) {
        fForm = form;
    }

    /**
     * ���[�h���ꂽ���f�����ύX����Ă��邩��Ԃ�.
     * 
     * @return ���[�h���ꂽ���f�����ύX����Ă���ꍇ��true
     * @see org.eclipse.ui.forms.IFormPart#isDirty()
     */
    public boolean isDirty() {
        return false;
    }

    /**
     * ���t���b�V�����K�v����Ԃ�.
     * 
     * @return ���t���b�V�����K�v�ȏꍇ��true
     * @see org.eclipse.ui.forms.IFormPart#isStale()
     */
    public boolean isStale() {
        return false;
    }

    /**
     * ���t���b�V�����s��.
     * 
     * @see org.eclipse.ui.forms.IFormPart#refresh()
     */
    public void refresh() {
    }

    /**
     * �t�H�[�J�X�擾���ɌĂяo�����.
     * 
     * @see org.eclipse.ui.forms.IFormPart#setFocus()
     */
    public void setFocus() {
    }

    /**
     * �t�H�[���C���v�b�g���Z�b�g����Ă���p�[�g�ւ̒ʒm.
     * 
     * @param input �t�H�[���C���v�b�g
     * @return �p�[�g���t�H�[���C���v�b�g��I�����Ă���ꍇ��true
     * @see org.eclipse.ui.forms.IFormPart#setFormInput(java.lang.Object)
     */
    public boolean setFormInput(Object input) {
        return false;
    }

    /**
     * �I�u�W�F�N�g���I�����ꂽ�ꍇ�ɒʒm�����.
     * 
     * @param part �p�[�g
     * @param selection �V�����I�����ꂽ�I�u�W�F�N�g
     * @see org.eclipse.ui.forms.IPartSelectionListener#selectionChanged(
     *          org.eclipse.ui.forms.IFormPart, 
     *          org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IFormPart part, ISelection selection) {
        fGeneralSection.selectionChanged(
                ((Field) ((IStructuredSelection) selection).getFirstElement()));
        fValidatorSection.selectionChanged(
                ((Field) ((IStructuredSelection) selection).getFirstElement()));
    }
}
