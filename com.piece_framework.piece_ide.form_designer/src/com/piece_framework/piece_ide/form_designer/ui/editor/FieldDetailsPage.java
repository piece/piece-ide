// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
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
    private IManagedForm fForm;
    private GeneralSection fGeneralSection;
    private ValidatorSection fValidatorSection;

    /**
     * コントロールを生成する.
     * 
     * @param parent 親コンポジット
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

        fGeneralSection = new GeneralSection(
                                toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR), 
                                fForm);
        fValidatorSection = new ValidatorSection(
                                toolkit.createSection(
                                        parentComposite, 
                                        Section.TITLE_BAR), 
                                fForm);
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
        fValidatorSection.selectionChanged(
                ((Field) ((IStructuredSelection) selection).getFirstElement()));
    }
}
