// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.command.SetStateAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * ステート・プロパティシートの一般セクション.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class StateGeneralSection extends GeneralPropertySection {

    /**
     * ラベルとテキストをまとめて処理するクラス.
     *
     * @author MATSUFUJI Hideharu
     * @since 0.2.0
     */
    private class LabelText {
        private CLabel fLabel;
        private Text fText;

        /**
         * ラベル・テキストのコントロールを作成する.
         *
         * @param parent 親コンポーネント
         */
        void create(Composite parent) {
            fLabel = getWidgetFactory().createCLabel(parent, null);
            fLabel.setLayoutData(new GridData(GridData.BEGINNING));

            fText = getWidgetFactory().createText(parent, ""); //$NON-NLS-1$
            fText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            setTextListener(fText);
        }

        /**
         * ラベルの文字列をセットする.
         *
         * @param label ラベル文字列
         */
        void setLabel(String label) {
            fLabel.setText(label);
        }

        /**
         * 表示・非表示を設定する.
         *
         * @param visible 表示・非表示
         */
        void setVisible(boolean visible) {
            fLabel.setVisible(visible);
            fText.setVisible(visible);
        }

        /**
         * ラベルコントロールを返す.
         *
         * @return ラベルコントロール
         */
        CLabel getLabel() {
            return fLabel;
        }

        /**
         * テキストコントロールを返す.
         *
         * @return テキストコントロール
         */
        Text getText() {
            return fText;
        }
    }

    private static final int LABELTEXT_NUM = 3;
    private LabelText[] fLabelText;

    /**
     * コントロールを作成する.
     *
     * @param parent 親コンテナ
     * @param tabbedPropertySheetPage プロパティシートページ
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *        #createControls(
     *          org.eclipse.swt.widgets.Composite,
     *          org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(
                    Composite parent,
                    TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        Composite composite =
            getWidgetFactory().createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));

        fLabelText = new LabelText[LABELTEXT_NUM];
        for (int i = 0; i < LABELTEXT_NUM; i++) {
            fLabelText[i] = new LabelText();
            fLabelText[i].create(composite);
        }
    }

    /**
     * インプットオブジェクトのセッターメソッド.
     * インプットオブジェクトからステートを取得する。
     * 各アクションによって表示するコントロールを決定する。<br>
     * ・イニシャルステート・ファイナルステート：なにも表示しない<br>
     * ・アクションステート：ステート名を表示<br>
     * ・ビューステート：ステート名・ビュー名を表示<br>
     *
     * @param part ワークベンチパート
     * @param selection セレクトオブジェクト
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *          #setInput(
     *              org.eclipse.ui.IWorkbenchPart,
     *              org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        for (int i = 0; i < LABELTEXT_NUM; i++) {
            fLabelText[i].setVisible(false);
        }

        State state = (State) getModel();
        if (state.getType() == State.ACTION_STATE) {
            fLabelText[0].setVisible(true);
            fLabelText[0].setLabel(Messages.getString(
                            "StateGeneralSection.StateName")); //$NON-NLS-1$
            fLabelText[1].setVisible(true);
            fLabelText[1].setLabel(Messages.getString(
                            "StateGeneralSection.Summary")); //$NON-NLS-1$
            fLabelText[2].setVisible(false);
        } else if (state.getType() == State.VIEW_STATE) {
            fLabelText[0].setVisible(true);
            fLabelText[0].setLabel(Messages.getString(
                            "StateGeneralSection.StateName")); //$NON-NLS-1$
            fLabelText[1].setVisible(true);
            fLabelText[1].setLabel(Messages.getString(
                            "StateGeneralSection.ViewName")); //$NON-NLS-1$
            fLabelText[2].setVisible(true);
            fLabelText[2].setLabel(Messages.getString(
                            "StateGeneralSection.Summary")); //$NON-NLS-1$
        }

        for (int i = 0; i < LABELTEXT_NUM; ++i) {
            fLabelText[i].getLabel().pack();
            fLabelText[i].getLabel().getParent().redraw();
        }
    }

    /**
     * 画面をリフレッシュする.
     * ステートから必要な情報を取得し、コントロールにセットする。
     *
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *          #refresh()
     */
    @Override
    public void refresh() {
        State state = (State) getModel();
        for (int i = 0; i < LABELTEXT_NUM; i++) {
            fLabelText[i].getText().setText("");    //$NON-NLS-1$
        }
        if (state != null) {
            if (state.getType() == State.ACTION_STATE) {
                if (state.getName() != null) {
                    fLabelText[0].getText().setText(state.getName());
                }
                if (state.getSummary() != null) {
                    fLabelText[1].getText().setText(state.getSummary());
                }
            } else if (state.getType() == State.VIEW_STATE) {
                if (state.getName() != null) {
                    fLabelText[0].getText().setText(state.getName());
                }
                if (state.getView() != null) {
                    fLabelText[1].getText().setText(state.getView());
                }
                if (state.getSummary() != null) {
                    fLabelText[2].getText().setText(state.getSummary());
                }
            }
        }
    }

    /**
     * コントロールから属性名を返す.
     *
     * @param control コントロール
     * @return 属性名
     * @see com.piece_framework.piece_ide.flow_designer.ui.property
     *          .GeneralPropertySection
     *              #getAttributeName(org.eclipse.swt.widgets.Control)
     */
    @Override
    String getAttributeName(Control control) {
        String attributeName = null;

        State state = (State) getModel();
        if (state.getType() == State.ACTION_STATE) {
            if (control == fLabelText[0].getText()) {
                attributeName = "Name"; //$NON-NLS-1$
            } else if (control == fLabelText[1].getText()) {
                attributeName = "Summary"; //$NON-NLS-1$
            }
        } else if (state.getType() == State.VIEW_STATE) {
            if (control == fLabelText[0].getText()) {
                attributeName = "Name"; //$NON-NLS-1$
            } else if (control == fLabelText[1].getText()) {
                attributeName = "View"; //$NON-NLS-1$
            } else if (control == fLabelText[2].getText()) {
                attributeName = "Summary"; //$NON-NLS-1$
            }
        }

        return attributeName;
    }

    /**
     * ステート属性を設定するコマンドを返す.
     *
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @return 属性を設定するコマンド
     * @see com.piece_framework.piece_ide.flow_designer.ui.property
     *          .GeneralPropertySection
     *              #getAttributeCommand(java.lang.String, java.lang.String)
     */
    @Override
    Command getAttributeCommand(String attributeName, String attributeValue) {
        Flow flow = null;
        if (getSelection() instanceof IStructuredSelection) {
            Object input =
                ((IStructuredSelection) getSelection()).getFirstElement();
            if (input instanceof EditPart) {
                flow = (Flow) ((EditPart) input).getParent().getModel();
            }
        }
        return new SetStateAttributeCommand(
                attributeName, attributeValue, flow, (State) getModel());
    }
}
