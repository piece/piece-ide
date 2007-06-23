package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.model.State;

public class StateEventSection extends AbstractPropertySection {

    private CLabel fStateNameLabel;
    private Table fEventTable;
    
    private State fState;
    
    private IWorkbenchPart fPart;
    
    private Control fTab;
    
    @Override
    public void createControls(
                    Composite parent, 
                    TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        fTab = tabbedPropertySheetPage.getControl();
        
        Composite composite = 
            getWidgetFactory().createFlatFormComposite(parent);
        
        FormData data;
        
        fStateNameLabel = 
            getWidgetFactory().createCLabel(composite, "ステート名：");
        /*
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(50, 0);
        data.top = new FormAttachment(0, 0);
        fStateNameLabel.setLayoutData(data); 
        */
        fEventTable = 
            getWidgetFactory().createTable(composite, SWT.HORIZONTAL | SWT.VERTICAL | SWT.VIRTUAL | SWT.FULL_SELECTION | SWT.BORDER);
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(fStateNameLabel, 0);
        fEventTable.setLayoutData(data);
        
        fEventTable.setHeaderVisible(true);
        fEventTable.setLinesVisible(true);
        
        TableColumn columnEventName = new TableColumn(fEventTable, SWT.NONE);
        columnEventName.setText("イベント名");
        columnEventName.setWidth(50);
        
        TableColumn columnNextStateName = 
                        new TableColumn(fEventTable, SWT.NULL);
        columnNextStateName.setText("遷移先ステート名");
        columnNextStateName.setWidth(50);
        
        TableColumn columnEventHandler = new TableColumn(fEventTable, SWT.NONE);
        columnEventHandler.setText("イベントハンドラ");
        columnEventHandler.setWidth(50);
        
        TableColumn columnGuard = new TableColumn(fEventTable, SWT.NONE);
        columnGuard.setText("ガード");
        columnGuard.setWidth(50);


        fTab.addControlListener(new ControlListener() {

            public void controlMoved(ControlEvent e) {
            }

            public void controlResized(ControlEvent e) {
                Composite source = (Composite) e.getSource();
                resizeEventTable(source.getSize());
            }
        });
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        if (selection instanceof IStructuredSelection) {
            Object input = ((IStructuredSelection) selection).getFirstElement();
            if (input instanceof EditPart) {
                fState = (State) ((EditPart) input).getModel();
            }
        }
        
        fPart = part;
    }

    @Override
    public void refresh() {
        if (fState != null) {
            fStateNameLabel.setText("ステート名：");
            if (fState.getName() != null) {
                fStateNameLabel.setText(
                        fStateNameLabel.getText() + fState.getName());
            }
                
            fEventTable.removeAll();
            
            TableItem item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event");
            item.setText(1, "next state");
            item.setText(2, "event handler");
            item.setText(3, "guard");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event2");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event3");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event4");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event5");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event6");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event7");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event8");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event9");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event10");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event11");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event12");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            item = new TableItem(fEventTable, SWT.NONE);
            item.setText(0, "test event13");
            item.setText(1, "next state2");
            item.setText(2, "event handler2");
            item.setText(3, "guard2");
            
            resizeEventTable(fTab.getSize());
        }
    }
    
    private void resizeEventTable(Point tabSize) {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(fStateNameLabel, 0);
        data.bottom = new FormAttachment(0, tabSize.y - 40);
        fEventTable.setLayoutData(data);
    }
}
