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

import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート・プロパティシートの一般セクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class StateGeneralSection extends AbstractPropertySection {

    private static final int TEXT_WIDTH_PERCENT = 70;
    
    private Text fStateName;
    private CLabel fStateNameLabel;
    private Text fViewName;
    private CLabel fViewNameLabel;
    
    private State fState;
    
    private TextListener fListener = new TextListener() {

        @Override
        public void changeText(Control control) {
            if (control == fStateName) {
                fState.setName(((Text) control).getText());
            } else if (control == fViewName) {
                fState.setView(((Text) control).getText());
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
        
        fStateNameLabel = 
            getWidgetFactory().createCLabel(composite, "ステート名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.top = new FormAttachment(0, 0);
        fStateNameLabel.setLayoutData(data);
        
        fStateName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(fStateNameLabel, 0);
        data.right = new FormAttachment(TEXT_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(0, 0);
        fStateName.setLayoutData(data);
        
        fViewNameLabel = 
            getWidgetFactory().createCLabel(composite, "ビュー名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.top = new FormAttachment(fStateNameLabel, 0);
        fViewNameLabel.setLayoutData(data);
        
        fViewName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(fViewNameLabel, 0);
        data.right = new FormAttachment(TEXT_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(fStateName, 0);
        fViewName.setLayoutData(data);
        
        fStateName.addListener(SWT.FocusOut, fListener);
        fStateName.addListener(SWT.Modify, fListener);
        fStateName.addListener(SWT.KeyDown, fListener);
        
        fViewName.addListener(SWT.FocusOut, fListener);
        fViewName.addListener(SWT.Modify, fListener);
        fViewName.addListener(SWT.KeyDown, fListener);
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
        if (selection instanceof IStructuredSelection) {
            Object input = ((IStructuredSelection) selection).getFirstElement();
            if (input instanceof EditPart) {
                fState = (State) ((EditPart) input).getModel();
                
                fStateName.setVisible(false);
                fStateNameLabel.setVisible(false);
                fViewName.setVisible(false);
                fViewNameLabel.setVisible(false);
                
                if (fState.getStateType() == State.ACTION_STATE
                    || fState.getStateType() == State.VIEW_STATE) {
                    fStateName.setVisible(true);
                    fStateNameLabel.setVisible(true);
                }
                if (fState.getStateType() == State.VIEW_STATE) {
                    fViewName.setVisible(true);
                    fViewNameLabel.setVisible(true);
                }
            }
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
        fStateName.setText("");
        fViewName.setText("");
        
        if (fState != null) {
            if (fState.getStateType() == State.ACTION_STATE
                || fState.getStateType() == State.VIEW_STATE) {
                if (fState.getName() != null) {
                    fStateName.setText(fState.getName());
                }
            }
            
            if (fState.getStateType() == State.VIEW_STATE) {
                if (fState.getView() != null) {
                    fViewName.setText(fState.getView());
                }
            }
        }
    }    
}
