// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import com.piece_framework.piece_ide.form_designer.model.Field;

/**
 * �t�B�[���h�y�[�W.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FieldsPage extends FormPage {
    private FieldsBlock fBlock;

    /**
     * Master/Details�u���b�N.
     * 
     * @version 0.1.0
     * @since 0.1.0
     *
     */
   public class FieldsBlock extends MasterDetailsBlock {
        /**
         * Master�p�[�g�𐶐�����.
         * 
         * @param managedForm �Ǘ��t�H�[�� 
         * @param parent �e�R���|�W�b�g
         * @see org.eclipse.ui.forms.MasterDetailsBlock#createMasterPart(
         *          org.eclipse.ui.forms.IManagedForm, 
         *          org.eclipse.swt.widgets.Composite)
         */
        @Override
        protected void createMasterPart(
                        final IManagedForm managedForm,
                        final Composite parent) {
            new FieldsMasterSection(parent, managedForm);
        }
        
        /**
         * �c�[���o�[�A�N�V�����𐶐�����.
         * 
         * @param managedForm �Ǘ��t�H�[��
         * @see org.eclipse.ui.forms.MasterDetailsBlock#createToolBarActions(
         *          org.eclipse.ui.forms.IManagedForm)
         */
        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
        }

        /**
         * Details�y�[�W��o�^����.
         * 
         * @param detailsPart Details�p�[�g
         * @see org.eclipse.ui.forms.MasterDetailsBlock#registerPages(
         *          org.eclipse.ui.forms.DetailsPart)
         */
        @Override
        protected void registerPages(DetailsPart detailsPart) {
            detailsPart.registerPage(Field.class, new FieldDetailsPage());
        }
    }
    
    /**
     * �R���X�g���N�^.
     * 
     * @param editor �G�f�B�^�[
     */
    public FieldsPage(FormEditor editor) {
        super(editor, null, "�t�B�[���h");
        fBlock = new FieldsBlock();
    }

    /**
     * �t�H�[���̃R���g���[���𐶐�����.
     * 
     * @param managedForm �Ǘ��t�H�[��
     * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(
     *          org.eclipse.ui.forms.IManagedForm)
     */
    @Override
    protected void createFormContent(IManagedForm managedForm) {
        fBlock.createContent(managedForm);
    }
}
