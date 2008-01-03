// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

public class FieldPage extends FormPage {
    
    public FieldPage(FormEditor editor) {
        super(editor, null, "フィールド");
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        managedForm.getForm().setText("Hello Eclipse Forms2!!");
    }
}
