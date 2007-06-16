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
    
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        Composite composite = 
            getWidgetFactory().createFlatFormComposite(parent);
        
        FormData data;
        
        fStateName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(50, 0);
        data.top = new FormAttachment(0, 0);
        fStateName.setLayoutData(data);
        
        fStateNameLabel = getWidgetFactory().createCLabel(composite, "ステート名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right =
            new FormAttachment(fStateName, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(fStateName, 0, SWT.CENTER);
        fStateNameLabel.setLayoutData(data);
        
        fStateName.addListener(SWT.FocusOut, fListener);
        fStateName.addListener(SWT.Modify, fListener);
        fStateName.addListener(SWT.KeyDown, fListener);
        
        fViewName = getWidgetFactory().createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(50, 0);
        data.top = new FormAttachment(0, 25);
        fViewName.setLayoutData(data);
        
        fViewNameLabel = getWidgetFactory().createCLabel(composite, "ビュー名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right =
            new FormAttachment(fViewName, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(fViewName, 0, SWT.CENTER);
        fViewNameLabel.setLayoutData(data);
        
        fViewName.addListener(SWT.FocusOut, fListener);
        fViewName.addListener(SWT.Modify, fListener);
        fViewName.addListener(SWT.KeyDown, fListener);
    }

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

    @Override
    public void refresh() {
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
