// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

public final class GeneralSection {
    private Text fNameText;
    private Text fDescriptionText;
    private Text fMessageText;
    private Button fRequired;
    private Button fForceValidation;

    private Field fField;

    /**
     * コンストラクタ.
     * 
     * @param parentSection 親セクション
     * @param managedForm 管理フォーム
     */
    public GeneralSection(Section parentSection, IManagedForm managedForm) {
        if (parentSection == null) {
            throw new NullPointerException();
        }
        if (managedForm == null) {
            throw new NullPointerException();
        }
        createContents(parentSection, managedForm.getToolkit());
    }

    private void createContents(final Section parentSection, final FormToolkit toolkit) {
        parentSection.setText("Field Detail");

        GridData layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        parentSection.setLayoutData(layoutData);

        Composite generalComposite = toolkit.createComposite(parentSection);
        GridLayout layout = new GridLayout(3, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        generalComposite.setLayout(layout);

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
                generalComposite, "name", focusListener, toolkit);
        fDescriptionText = createText(
                generalComposite, "description", focusListener, toolkit);
        fRequired = createCheckBox(
                generalComposite, "required", focusListener, toolkit);
        fMessageText = createText(
                generalComposite, "message", focusListener, toolkit);
        fForceValidation = createCheckBox(
                generalComposite, "force validation", focusListener, toolkit);

        parentSection.setClient(generalComposite);
    }

    /**
     * Textオブジェクトを生成する.
     * 
     * @param parent 親コンポジット
     * @param caption キャプション 
     * @param focusListener フォーカスリスナー
     * @param toolkit ツールキット
     * @return 生成したTextオブジェクト
     */
    private Text createText(
                    Composite parent,
                    String caption,
                    FocusListener focusListener,
                    FormToolkit toolkit) {
        toolkit.createLabel(parent, caption);
        toolkit.createLabel(parent, ":");
        Text text = toolkit.createText(parent, "");
        text.setLayoutData(
                new GridData(
                        GridData.FILL_HORIZONTAL
                        | GridData.GRAB_HORIZONTAL));
        text.addFocusListener(focusListener);

        return text;
    }

    /**
     * チェックボックスのButtonオブジェクトを生成する.
     * 
     * @param parent 親コンポジット
     * @param caption キャプション
     * @param focusListener フォーカスリスナー
     * @param toolkit ツールキット
     * @return 生成したButtonオブジェクト
     */
    private Button createCheckBox(
                        Composite parent,
                        String caption,
                        FocusListener focusListener,
                        FormToolkit toolkit) { 
        toolkit.createLabel(parent, caption);
        toolkit.createLabel(parent, ":");
        Button button = toolkit.createButton(parent, "", SWT.CHECK);
        button.addFocusListener(focusListener);
        
        return button;
    }

    public void selectionChanged(final Field field) {
        fNameText.setText("");
        fDescriptionText.setText("");
        fRequired.setSelection(false);
        fMessageText.setText("");
        fForceValidation.setSelection(false);

        fNameText.setText(field.getName());
        fDescriptionText.setText(field.getDescription());
        fRequired.setSelection(field.isRequired());
        fMessageText.setText(field.getMessage());
        fForceValidation.setSelection(field.isForceValidation());

        fField = field; 
    }
}
