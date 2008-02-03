// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;
import com.piece_framework.piece_ide.form_designer.model.Validator;

public class ValidatorSection {
    private TableViewer fValidatorViewer;
    private Label fValidatorNameLabel;
    private Text fValidatorMessageText;

    private Field fField;

    /**
     * コンストラクタ.
     * 
     * @param parentSection 親セクション
     * @param managedForm 管理フォーム
     */
    public ValidatorSection(Section parentSection, IManagedForm managedForm) {
        if (parentSection == null) {
            throw new NullPointerException();
        }
        if (managedForm == null) {
            throw new NullPointerException();
        }
        createContents(parentSection, managedForm.getToolkit());
    }

    private void createContents(final Section parentSection, final FormToolkit toolkit) {
        parentSection.setText("Validator");
        GridData layoutData = new GridData();
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        parentSection.setLayoutData(layoutData);
        
        Composite validatorComposite = toolkit.createComposite(
                                                parentSection);
        GridLayout layout = new GridLayout(3, false);
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
                "追加(&A)...", 
                SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        addButton.setLayoutData(layoutData);

        Button delButton = toolkit.createButton(
                validatorButtonComposite, 
                "削除(&D)...", 
                SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        delButton.setLayoutData(layoutData);

        Button upButton = toolkit.createButton(
                validatorButtonComposite, 
                "上へ", 
                SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        upButton.setLayoutData(layoutData);

        Button downButton = toolkit.createButton(
                validatorButtonComposite, 
                "下へ", 
                SWT.PUSH);
        layoutData = new GridData();
        layoutData.horizontalAlignment = GridData.FILL;
        downButton.setLayoutData(layoutData);

        Composite validatorDetailComposite = toolkit.createComposite(
                                                validatorComposite);
        layout = new GridLayout(3, false);
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
        layoutData.horizontalSpan = 3;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        fValidatorNameLabel.setLayoutData(layoutData);

        // TODO:1文字でも入力されたら反応するようなイベントを生成する。
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent event) {
            }

            public void focusLost(FocusEvent event) {
                // TODO:Fieldオブジェクトに値をセットする
            }
        };

        fValidatorMessageText = WidgetUtility.createText(
                validatorDetailComposite, "message", focusListener, toolkit);

        parentSection.setClient(validatorComposite);
    }

    public void selectionChanged(final Field field) {
        for (Validator validator : field.getValidators()) {
            fValidatorViewer.add(validator);
        }
        fField = field;
    }
}
