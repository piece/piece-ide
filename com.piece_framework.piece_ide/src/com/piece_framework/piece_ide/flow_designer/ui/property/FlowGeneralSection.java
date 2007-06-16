package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フロー・プロパティシートの一般セクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class FlowGeneralSection extends AbstractPropertySection {
    
    private static final int TEXT_WIDTH_PERCENT = 70;
    
    private Text fFlowName;
    private CLabel fFlowNameLabel;
    private Text fActionClassName;
    private CLabel fActionClassNameLabel;
    
    private Flow fFlow;
    
    private TextListener fListener = new TextListener() {
        @Override
        public void changeText(Control control) {
            if (control == fFlowName) {
                fFlow.setName(((Text) control).getText());
            } else if (control == fActionClassName) {
                fFlow.setActionClassName(((Text) control).getText());
            }
        }
    };

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
        
        FormData data;
        
        fFlowNameLabel = 
            getWidgetFactory().createCLabel(composite, "フロー名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.top = new FormAttachment(0, 0);
        fFlowNameLabel.setLayoutData(data);
        
        fFlowName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(fFlowNameLabel, 0);
        data.right = new FormAttachment(TEXT_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(0, 0);
        fFlowName.setLayoutData(data);
        
        fActionClassNameLabel = 
            getWidgetFactory().createCLabel(composite, "アクションクラス：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.top = new FormAttachment(fFlowNameLabel, 0);
        fActionClassNameLabel.setLayoutData(data);
        
        fActionClassName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(fActionClassNameLabel, 0);
        data.right = new FormAttachment(TEXT_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(fFlowName, 0);
        fActionClassName.setLayoutData(data);
        
        fFlowName.addListener(SWT.FocusOut, fListener);
        fFlowName.addListener(SWT.Modify, fListener);
        fFlowName.addListener(SWT.KeyDown, fListener);
    
        fActionClassName.addListener(SWT.FocusOut, fListener);
        fActionClassName.addListener(SWT.Modify, fListener);
        fActionClassName.addListener(SWT.KeyDown, fListener);
    }

    /**
     * インプットオブジェクトのセッターメソッド.
     * インプットオブジェクトからフローを取得する。
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
        if (selection instanceof IStructuredSelection) {
            Object input = ((IStructuredSelection) selection).getFirstElement();
            if (input instanceof EditPart) {
                fFlow = (Flow) ((EditPart) input).getModel();
            }
        }
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
        if (fFlow != null) {
            if (fFlow.getName() != null) {
                fFlowName.setText(fFlow.getName());
            }
            if (fFlow.getActionClassName() != null) {
                fActionClassName.setText(fFlow.getActionClassName());
            }
        }
    }
}
