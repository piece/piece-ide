// $Id: FlowGeneralSection.java 180 2007-07-27 00:57:08Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.command.SetFlowAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フロー・プロパティシートの一般セクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class FlowGeneralSection extends GeneralPropertySection {

    private Text fFlowName;
    private CLabel fFlowNameLabel;
    private Text fActionClassName;
    private CLabel fActionClassNameLabel;

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

        fFlowNameLabel = 
            getWidgetFactory().createCLabel(composite, "フロー名：");
        fFlowName = getWidgetFactory().createText(composite, "");
        
        fActionClassNameLabel = 
            getWidgetFactory().createCLabel(composite, "アクションクラス：");
        fActionClassName = getWidgetFactory().createText(composite, "");
        
        arrangeGroup(
                new CLabel[] {fFlowNameLabel, fActionClassNameLabel},
                new Text[] {fFlowName, fActionClassName});
        
        setTextListener(fFlowName);
        setTextListener(fActionClassName);
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
        fFlowName.setText("");
        fActionClassName.setText("");
        
        Flow flow = (Flow) getModel();
        if (flow != null) {
            if (flow.getName() != null) {
                fFlowName.setText(flow.getName());
            }
            if (flow.getActionClassName() != null) {
                fActionClassName.setText(flow.getActionClassName());
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
            attributeName = "name";
        } else if (control == fActionClassName) {
            attributeName = "actionClassName";
        }
        
        return attributeName;
    }

    /**
     * コントロールから変更前の属性値を返す.
     * 
     * @param control コントロール
     * @return 変更前の属性値
     * @see com.piece_framework.piece_ide.flow_designer.ui.property
     *          .GeneralPropertySection
     *              #getAttributeOldValue(org.eclipse.swt.widgets.Control)
     */
    @Override
    String getAttributeOldValue(Control control) {
        String attributeOldValue = null;
        Flow flow = (Flow) getModel();
        
        if (control == fFlowName) {
            attributeOldValue = flow.getName();
        } else if (control == fActionClassName) {
            attributeOldValue = flow.getActionClassName();
        }

        return attributeOldValue;
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
