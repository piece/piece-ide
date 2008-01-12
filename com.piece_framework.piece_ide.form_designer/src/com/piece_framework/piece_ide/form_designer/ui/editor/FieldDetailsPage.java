// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

public class FieldDetailsPage implements IDetailsPage {
    private IManagedForm fForm;
    private Text fNameText;
    private Text fDescriptionText;
    private Text fMessageText;
    private Button fRequired;
    private Button fForceValidation;

    private Field fField;

    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout());
        FormToolkit toolkit = fForm.getToolkit();
        Section section = toolkit.createSection(
                    parent, 
                    Section.TITLE_BAR | Section.DESCRIPTION);
        section.setText("フィールド詳細");

        Composite composite = toolkit.createComposite(section);
        composite.setLayout(new GridLayout(3, false));

        // TODO:1文字でも入力されたら反応するようなイベントを生成する。
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent event) {
            }

            public void focusLost(FocusEvent event) {
                if (fNameText == event.widget) {
                    fField.setName(fNameText.getText());
                } else if (fDescriptionText == event.widget) {
                    fField.setDescription(fDescriptionText.getText());
                } else if (fRequired == event.widget) {
                    fField.setRequired(fRequired.getSelection());
                } else if (fMessageText == event.widget) {
                    fField.setMessage(fMessageText.getText());
                } else if (fForceValidation == event.widget) {
                    fField.setForceValidation(fForceValidation.getSelection());
                }
            }
        };

        toolkit.createLabel(composite, "name");
        toolkit.createLabel(composite, "：");
        fNameText = toolkit.createText(composite, "");
        fNameText.setLayoutData(
                new GridData(GridData.FILL_HORIZONTAL));
        fNameText.addFocusListener(focusListener);

        toolkit.createLabel(composite, "description");
        toolkit.createLabel(composite, "：");
        fDescriptionText = toolkit.createText(composite, "");
        fDescriptionText.setLayoutData(
                new GridData(GridData.FILL_HORIZONTAL));
        fDescriptionText.addFocusListener(focusListener);

        toolkit.createLabel(composite, "required");
        toolkit.createLabel(composite, "：");
        fRequired = toolkit.createButton(composite, "", SWT.CHECK);
        fRequired.addFocusListener(focusListener);

        toolkit.createLabel(composite, "message");
        toolkit.createLabel(composite, "：");
        fMessageText = toolkit.createText(composite, "");
        fMessageText.setLayoutData(
                new GridData(GridData.FILL_HORIZONTAL));
        fMessageText.addFocusListener(focusListener);

        toolkit.createLabel(composite, "force validation");
        toolkit.createLabel(composite, "：");
        fForceValidation = toolkit.createButton(composite, "", SWT.CHECK);
        fForceValidation.addFocusListener(focusListener);

        section.setClient(composite);
    }

    public void commit(boolean onSave) {
    }

    public void dispose() {
    }

    public void initialize(IManagedForm form) {
        fForm = form;
    }

    public boolean isDirty() {
        return false;
    }

    public boolean isStale() {
        return false;
    }

    public void refresh() {
    }

    public void setFocus() {
    }

    public boolean setFormInput(Object input) {
        return false;
    }

    public void selectionChanged(IFormPart part, ISelection selection) {
        fNameText.setText("");
        fDescriptionText.setText("");
        fRequired.setSelection(false);
        fMessageText.setText("");
        fForceValidation.setSelection(false);

        fField = 
            (Field) ((IStructuredSelection) selection)
            .getFirstElement();
        fNameText.setText(fField.getName());
        fDescriptionText.setText(fField.getDescription());
        fRequired.setSelection(fField.isRequired());
        fMessageText.setText(fField.getMessage());
        fForceValidation.setSelection(fField.isForceValidation());
    }

}
