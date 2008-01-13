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

/**
 * フィールドDetailsページ.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FieldDetailsPage implements IDetailsPage {
    private static final int GRID_ROW = 3; 
    private IManagedForm fForm;
    private Text fNameText;
    private Text fDescriptionText;
    private Text fMessageText;
    private Button fRequired;
    private Button fForceValidation;

    private Field fField;

    /**
     * コントロールを生成する.
     * 
     * @param parent 親コンポジット
     * @see org.eclipse.ui.forms.IDetailsPage#createContents(
     *          org.eclipse.swt.widgets.Composite)
     */
    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout());
        FormToolkit toolkit = fForm.getToolkit();
        Section section = toolkit.createSection(
                    parent, 
                    Section.TITLE_BAR | Section.DESCRIPTION);
        section.setText("フィールド詳細");

        Composite composite = toolkit.createComposite(section);
        composite.setLayout(new GridLayout(GRID_ROW, false));

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
                composite, "name", focusListener, toolkit);
        fDescriptionText = createText(
                composite, "description", focusListener, toolkit);
        fRequired = createCheckBox(
                composite, "required", focusListener, toolkit);
        fMessageText = createText(
                composite, "message", focusListener, toolkit);
        fForceValidation = createCheckBox(
                composite, "force validation", focusListener, toolkit);

        section.setClient(composite);
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
        toolkit.createLabel(parent, "：");
        Text text = toolkit.createText(parent, "");
        text.setLayoutData(
                new GridData(GridData.FILL_HORIZONTAL));
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
        toolkit.createLabel(parent, "：");
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
