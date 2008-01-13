// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import com.piece_framework.piece_ide.form_designer.model.Field;

public class FieldsPage extends FormPage {
    private FieldsBlock fBlock;

    public class FieldsBlock extends MasterDetailsBlock {

        @Override
        protected void createMasterPart(
                        final IManagedForm managedForm,
                        final Composite parent) {
            new FieldsMasterSection(parent, managedForm);
        }
        
        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
            // TODO 自動生成されたメソッド・スタブ
            
        }

        @Override
        protected void registerPages(DetailsPart detailsPart) {
            detailsPart.registerPage(Field.class, new FieldDetailsPage());
        }
    }
    
    public FieldsPage(FormEditor editor) {
        super(editor, null, "フィールド");
        fBlock = new FieldsBlock();
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        fBlock.createContent(managedForm);
    }
}
