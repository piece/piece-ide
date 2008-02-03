// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
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
import com.piece_framework.piece_ide.form_designer.model.Validator;

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
    private GeneralSection fGeneralSection;
    
    private TableViewer fValidatorViewer;
    private Label fValidatorNameLabel;
    private Text fValidatorMessageText;

    private Field fField;

    /**
     * �R���g���[���𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @see org.eclipse.ui.forms.IDetailsPage#createContents(
     * n         org.eclipse.swt.widgets.Composite)
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
        
        Section validatorSection = toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR);
        validatorSection.setText("Validator");
        GridData layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        validatorSection.setLayoutData(layoutData);

        Composite validatorComposite = toolkit.createComposite(
                                            validatorSection);
        layout = new GridLayout(VALIDATOR_ROW, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        validatorComposite.setLayout(layout);

        Table validatorTable = toolkit.createTable(
                                    validatorComposite, 
                                    SWT.SINGLE | SWT.FULL_SELECTION);
        validatorTable.setHeaderVisible(false);
        validatorTable.setLinesVisible(true);

        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        layoutData.verticalAlignment = GridData.FILL;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;
        validatorTable.setLayoutData(layoutData);

        TableColumn column1 = new TableColumn(validatorTable, SWT.NULL);
        column1.setWidth(100);

        fValidatorViewer = new TableViewer(validatorTable);
        fValidatorViewer.setContentProvider(new ArrayContentProvider());
        fValidatorViewer.setLabelProvider(new ITableLabelProvider() {
            public Image getColumnImage(Object element, int columnIndex) {
                return null;
            }

            public String getColumnText(Object element, int columnIndex) {
                Validator validator = (Validator) element;
                if (columnIndex == 0) {
                    return validator.getName();
                }
                return "";
            }

            public void addListener(ILabelProviderListener listener) {
            }

            public void dispose() {
            }

            public boolean isLabelProperty(
                                Object element, 
                                String property) {
                return false;
            }

            public void removeListener(ILabelProviderListener listener) {
            }
        });
        fValidatorViewer.addSelectionChangedListener(
                new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                Validator validator = (Validator) ((StructuredSelection) event.getSelection()).getFirstElement();
                fValidatorNameLabel.setText(validator.getName() + " - " + validator.getDescription());
            }
        });
        
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
                                        "");
        layoutData = new GridData();
        layoutData.horizontalSpan = VALIDATOR_DETAIL_ROW;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        fValidatorNameLabel.setLayoutData(layoutData);

        // TODO:1�����ł����͂��ꂽ�甽������悤�ȃC�x���g�𐶐�����B
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent event) {
            }
            
            public void focusLost(FocusEvent event) {
                // TODO:Field�I�u�W�F�N�g�ɒl���Z�b�g����
            }
        };

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
        fGeneralSection.selectionChanged(
                ((Field) ((IStructuredSelection) selection).getFirstElement()));

        fField = 
            (Field) ((IStructuredSelection) selection)
            .getFirstElement();
        for (Validator validator : fField.getValidators()) {
            fValidatorViewer.add(validator);
        }
    }
}
