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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
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
    private static final int GENERAL_ROW = 3; 
    private static final int VALIDATOR_ROW = 3; 
    private static final int VALIDATOR_DETAIL_ROW = 3; 

    private IManagedForm fForm;
    private Text fNameText;
    private Text fDescriptionText;
    private Text fMessageText;
    private Button fRequired;
    private Button fForceValidation;

    private Table fValidatorTable;
    private Label fValidatorNameLabel;
    private Text fValidatorMessageText;

    private Field fField;

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
        
        Section generalSection = toolkit.createSection(
                                    parentComposite, 
                                    Section.TITLE_BAR);
        generalSection.setText("Field Detail");
        GridData layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        generalSection.setLayoutData(layoutData);
        
        Composite generalComposite = toolkit.createComposite(generalSection);
        layout = new GridLayout(GENERAL_ROW, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        generalComposite.setLayout(layout);
        
        // TODO:1�����ł����͂��ꂽ�甽������悤�ȃC�x���g�𐶐�����B
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
        
        generalSection.setClient(generalComposite);
        
        Section validatorSection = toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR);
        validatorSection.setText("Validator");
        layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        validatorSection.setLayoutData(layoutData);

        Composite validatorComposite = toolkit.createComposite(
                                            validatorSection);
        layout = new GridLayout(VALIDATOR_ROW, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        validatorComposite.setLayout(layout);

        fValidatorTable = toolkit.createTable(
                                    validatorComposite, 
                                    SWT.SINGLE | SWT.FULL_SELECTION);
        fValidatorTable.setHeaderVisible(false);
        fValidatorTable.setLinesVisible(true);

        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        layoutData.verticalAlignment = GridData.FILL;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;
        fValidatorTable.setLayoutData(layoutData);

        new TableColumn(fValidatorTable, SWT.NULL);

        Composite validatorButtonComposite = toolkit.createComposite(
                                                        validatorComposite);
        layoutData = new GridData();
        layoutData.verticalAlignment = GridData.FILL;
        validatorButtonComposite.setLayoutData(layoutData);

        layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        validatorButtonComposite.setLayout(layout);

        Button addButton = toolkit.createButton(
                                    validatorButtonComposite, 
                                    "�ǉ�(&A)...", 
                                    SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        addButton.setLayoutData(layoutData);

        Button delButton = toolkit.createButton(
                                    validatorButtonComposite, 
                                    "�폜(&D)...", 
                                    SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        delButton.setLayoutData(layoutData);
        
        Button upButton = toolkit.createButton(
                                    validatorButtonComposite, 
                                    "���", 
                                    SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        upButton.setLayoutData(layoutData);

        Button downButton = toolkit.createButton(
                                        validatorButtonComposite, 
                                        "����", 
                                        SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        downButton.setLayoutData(layoutData);

        Composite validatorDetailComposite = toolkit.createComposite(
                                                        validatorComposite);
        layout = new GridLayout(VALIDATOR_DETAIL_ROW, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        validatorDetailComposite.setLayout(layout);

        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        layoutData.verticalAlignment = GridData.FILL;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;
        validatorDetailComposite.setLayoutData(layoutData);

        fValidatorNameLabel = toolkit.createLabel(
                                        validatorDetailComposite, 
                                        "[validator name]");
        layoutData = new GridData();
        layoutData.horizontalSpan = VALIDATOR_DETAIL_ROW;
        fValidatorNameLabel.setLayoutData(layoutData);

        fValidatorMessageText = createText(
                validatorDetailComposite, "message", focusListener, toolkit);

        validatorSection.setClient(validatorComposite);

    }

    /**
     * Text�I�u�W�F�N�g�𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @param caption �L���v�V���� 
     * @param focusListener �t�H�[�J�X���X�i�[
     * @param toolkit �c�[���L�b�g
     * @return ��������Text�I�u�W�F�N�g
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
     * �`�F�b�N�{�b�N�X��Button�I�u�W�F�N�g�𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @param caption �L���v�V����
     * @param focusListener �t�H�[�J�X���X�i�[
     * @param toolkit �c�[���L�b�g
     * @return ��������Button�I�u�W�F�N�g
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