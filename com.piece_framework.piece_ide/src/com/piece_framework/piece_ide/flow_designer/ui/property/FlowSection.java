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
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class FlowSection extends AbstractPropertySection {

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
    

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        Composite composite = 
            getWidgetFactory().createFlatFormComposite(parent);
        
        FormData data;
        
        fFlowName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH + 20);
        data.right = new FormAttachment(70, 0);
        data.top = new FormAttachment(0, 0);
        fFlowName.setLayoutData(data);
        
        fFlowNameLabel = getWidgetFactory().createCLabel(composite, "フロー名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right =
            new FormAttachment(fFlowName, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(fFlowName, 0, SWT.CENTER);
        fFlowNameLabel.setLayoutData(data);
        
        fFlowName.addListener(SWT.FocusOut, fListener);
        fFlowName.addListener(SWT.Modify, fListener);
        fFlowName.addListener(SWT.KeyDown, fListener);
        
        fActionClassName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH + 20);
        data.right = new FormAttachment(70, 0);
        data.top = new FormAttachment(0, 25);
        fActionClassName.setLayoutData(data);
        
        fActionClassNameLabel = getWidgetFactory().createCLabel(composite, "アクションクラス：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right =
            new FormAttachment(fActionClassName, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(fActionClassName, 0, SWT.CENTER);
        fActionClassNameLabel.setLayoutData(data);
        
        fActionClassName.addListener(SWT.FocusOut, fListener);
        fActionClassName.addListener(SWT.Modify, fListener);
        fActionClassName.addListener(SWT.KeyDown, fListener);
    }

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
