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
 * フィールドDetailsページ.
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
     * コントロールを生成する.
     * 
     * @param parent 親コンポジット
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

        // TODO:1文字でも入力されたら反応するようなイベントを生成する。
        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent event) {
            }
            
            public void focusLost(FocusEvent event) {
                // TODO:Fieldオブジェクトに値をセットする
            }
        };

        fValidatorMessageText = createText(
                validatorDetailComposite, "message", focusListener, toolkit);

        validatorSection.setClient(validatorComposite);

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

    /**
     * 変更されたモデルをコミットする.
     * 
     * @param onSave 保存処理中の場合はtrue
     * @see org.eclipse.ui.forms.IFormPart#commit(boolean)
     */
    public void commit(boolean onSave) {
    }

    /**
     * 終了処理を行う.
     * 
     * @see org.eclipse.ui.forms.IFormPart#dispose()
     */
    public void dispose() {
    }

    /**
     * 初期化処理を行う.
     * 
     * @param form 管理フォーム
     * @see org.eclipse.ui.forms.IFormPart#initialize(
     *          org.eclipse.ui.forms.IManagedForm)
     */
    public void initialize(IManagedForm form) {
        fForm = form;
    }

    /**
     * ロードされたモデルが変更されているかを返す.
     * 
     * @return ロードされたモデルが変更されている場合はtrue
     * @see org.eclipse.ui.forms.IFormPart#isDirty()
     */
    public boolean isDirty() {
        return false;
    }

    /**
     * リフレッシュが必要かを返す.
     * 
     * @return リフレッシュが必要な場合はtrue
     * @see org.eclipse.ui.forms.IFormPart#isStale()
     */
    public boolean isStale() {
        return false;
    }

    /**
     * リフレッシュを行う.
     * 
     * @see org.eclipse.ui.forms.IFormPart#refresh()
     */
    public void refresh() {
    }

    /**
     * フォーカス取得時に呼び出される.
     * 
     * @see org.eclipse.ui.forms.IFormPart#setFocus()
     */
    public void setFocus() {
    }

    /**
     * フォームインプットがセットされているパートへの通知.
     * 
     * @param input フォームインプット
     * @return パートがフォームインプットを選択している場合はtrue
     * @see org.eclipse.ui.forms.IFormPart#setFormInput(java.lang.Object)
     */
    public boolean setFormInput(Object input) {
        return false;
    }

    /**
     * オブジェクトが選択された場合に通知される.
     * 
     * @param part パート
     * @param selection 新しく選択されたオブジェクト
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
