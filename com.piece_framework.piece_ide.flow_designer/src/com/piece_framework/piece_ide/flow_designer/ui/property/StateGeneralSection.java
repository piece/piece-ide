// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.CLabel;
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
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class StateGeneralSection extends GeneralPropertySection {

    private Text fStateName;
    private CLabel fStateNameLabel;
    private Text fSummary;
    private CLabel fSummaryLabel;
    private Text fViewName;
    private CLabel fViewNameLabel;
    
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
        
        fStateNameLabel = 
            getWidgetFactory().createCLabel(composite, 
                    Messages.getString("StateGeneralSection.StateName"));
        fStateName = getWidgetFactory().createText(composite, "");
        fSummaryLabel = 
            getWidgetFactory().createCLabel(composite, 
                    Messages.getString("StateGeneralSection.Summary"));
        fSummary = getWidgetFactory().createText(composite, "");
        fViewNameLabel = 
            getWidgetFactory().createCLabel(composite, 
                    Messages.getString("StateGeneralSection.ViewName"));
        fViewName = getWidgetFactory().createText(composite, "");
        
        arrangeGroup(
                new CLabel[] {fStateNameLabel, fSummaryLabel, fViewNameLabel},
                new Text[] {fStateName, fSummary, fViewName});

        setTextListener(fStateName);
        setTextListener(fSummary);
        setTextListener(fViewName);
    }

    /**
     * インプットオブジェクトのセッターメソッド.
     * インプットオブジェクトからステートを取得する。
     * 各アクションによって表示するコントロールを決定する。<br>
     * ・イニシャルステート・ファイナルステート：なにも表示しない<br>
     * ・アクションステート：ステート名を表示<br>
     * ・ビューステート：ステート名・ビュー名を表示<br>
     * s
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
        
        State state = (State) getModel();
        setGroupVisible(fStateNameLabel, fStateName, false);
        setGroupVisible(fSummaryLabel, fSummary, false);
        setGroupVisible(fViewNameLabel, fViewName, false);
        
        if (state.getType() == State.ACTION_STATE
            || state.getType() == State.VIEW_STATE) {
            setGroupVisible(fStateNameLabel, fStateName, true);
            setGroupVisible(fSummaryLabel, fSummary, true);
        }
        if (state.getType() == State.VIEW_STATE) {
            setGroupVisible(fViewNameLabel, fViewName, true);
        }
    }

    /**
     * ラベルとテキストの表示・非表示を設定する.
     * 
     * @param label ラベル
     * @param text テキスト
     * @param visible 表示・非表示
     */
    private void setGroupVisible(CLabel label, Text text, boolean visible) {
        label.setVisible(visible);
        text.setVisible(visible);
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
        fStateName.setText("");
        fSummary.setText("");
        fViewName.setText("");
        
        if (state != null) {
            if (state.getType() == State.ACTION_STATE
                || state.getType() == State.VIEW_STATE) {
                if (state.getName() != null) {
                    fStateName.setText(state.getName());
                }
                if (state.getSummary() != null) {
                    fSummary.setText(state.getSummary());
                }
            }
            
            if (state.getType() == State.VIEW_STATE) {
                if (state.getView() != null) {
                    fViewName.setText(state.getView());
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
        
        if (control == fStateName) {
            attributeName = "Name";
        } else if (control == fSummary) {
            attributeName = "Summary";
        } else if (control == fViewName) {
            attributeName = "View";
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
