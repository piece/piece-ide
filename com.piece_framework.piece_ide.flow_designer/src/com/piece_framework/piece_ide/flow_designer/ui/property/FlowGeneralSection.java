// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.command.SetFlowAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * フロー・プロパティシートの一般セクション.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class FlowGeneralSection extends GeneralPropertySection {
    private Text fFlowName;
    private CLabel fFlowNameLabel;

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

        fFlowNameLabel =
            getWidgetFactory().createCLabel(composite,
                Messages.getString(
                    "FlowGeneralSection.FlowName")); //$NON-NLS-1$
        fFlowNameLabel.setLayoutData(new GridData(GridData.BEGINNING));

        fFlowName = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
        fFlowName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        setTextListener(fFlowName);
    }

    /**
     * 画面をリフレッシュする.
     * フローから必要な情報を取得し、コントロールにセットする。
     *
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *          #refresh()
     */
    @Override
    public void refresh() {
        fFlowName.setText(""); //$NON-NLS-1$

        Flow flow = (Flow) getModel();
        if (flow != null) {
            if (flow.getName() != null) {
                fFlowName.setText(flow.getName());
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

        if (control == fFlowName) {
            attributeName = "Name"; //$NON-NLS-1$
        }

        return attributeName;
    }

    /**
     * フロー属性を設定するコマンドを返す.
     *
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @return フロー属性を設定するコマンド
     * @see com.piece_framework.piece_ide.flow_designer.ui.property
     *          .GeneralPropertySection
     *              #getAttributeCommand(java.lang.String, java.lang.String)
     */
    @Override
    Command getAttributeCommand(String attributeName, String attributeValue) {
        return new SetFlowAttributeCommand(
                        attributeName, attributeValue, (Flow) getModel());
    }
}
