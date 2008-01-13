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

        fNameText = createText(
                toolkit, composite, "name", focusListener);
        fDescriptionText = createText(
                toolkit, composite, "description", focusListener);
        fRequired = createCheckBox(
                toolkit, composite, "required", focusListener);
        fMessageText = createText(
                toolkit, composite, "message", focusListener);
        fForceValidation = createCheckBox(
                toolkit, composite, "force validation", focusListener);

        section.setClient(composite);
    }

    private Text createText(
                    FormToolkit toolkit, 
                    Composite parent,
                    String label,
                    FocusListener focusListener) {
        toolkit.createLabel(parent, label);
        toolkit.createLabel(parent, "：");
        Text text = toolkit.createText(parent, "");
        text.setLayoutData(
                new GridData(GridData.FILL_HORIZONTAL));
        text.addFocusListener(focusListener);

        return text;
    }

    private Button createCheckBox(
            FormToolkit toolkit, 
            Composite parent,
            String label,
            FocusListener focusListener) {
        toolkit.createLabel(parent, label);
        toolkit.createLabel(parent, "：");
        Button button = toolkit.createButton(parent, "", SWT.CHECK);
        button.addFocusListener(focusListener);
        
        return button;
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
