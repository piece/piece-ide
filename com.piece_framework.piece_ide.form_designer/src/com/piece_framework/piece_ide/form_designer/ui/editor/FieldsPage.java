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
 * フィールドページ.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FieldsPage extends FormPage {
    private FieldsBlock fBlock;

    /**
     * Master/Detailsブロック.
     * 
     * @version 0.1.0
     * @since 0.1.0
     *
     */
   public class FieldsBlock extends MasterDetailsBlock {
        /**
         * Masterパートを生成する.
         * 
         * @param managedForm 管理フォーム 
         * @param parent 親コンポジット
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
         * ツールバーアクションを生成する.
         * 
         * @param managedForm 管理フォーム
         * @see org.eclipse.ui.forms.MasterDetailsBlock#createToolBarActions(
         *          org.eclipse.ui.forms.IManagedForm)
         */
        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
        }

        /**
         * Detailsページを登録する.
         * 
         * @param detailsPart Detailsパート
         * @see org.eclipse.ui.forms.MasterDetailsBlock#registerPages(
         *          org.eclipse.ui.forms.DetailsPart)
         */
        @Override
        protected void registerPages(DetailsPart detailsPart) {
            detailsPart.registerPage(Field.class, new FieldDetailsPage());
        }
    }
    
    /**
     * コンストラクタ.
     * 
     * @param editor エディター
     */
    public FieldsPage(FormEditor editor) {
        super(editor, null, "フィールド");
        fBlock = new FieldsBlock();
    }

    /**
     * フォームのコントロールを生成する.
     * 
     * @param managedForm 管理フォーム
     * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(
     *          org.eclipse.ui.forms.IManagedForm)
     */
    @Override
    protected void createFormContent(IManagedForm managedForm) {
        fBlock.createContent(managedForm);
    }
}
