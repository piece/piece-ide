package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class StateEventSection extends AbstractPropertySection {

    private static final int LABEL_WIDTH_PERCENT = 50;
    private static final int TABLE_WIDTH_PERCENT = 100;
    
    private static final int TABLE_HEIGHT_MARGIN = 40;
    
    private static final int EVENT_COLUMN_WIDTH = 100;
    private static final int NEXT_STATE_COLUMN_WIDTH = 100;
    private static final int EVENT_HANDLER_COLUMN_WIDTH = 150;
    private static final int GUARD_COLUMN_WIDTH = 150;
    
    private static final int EVENT_COLUMN = 0;
    private static final int NEXT_STATE_COLUMN = 1;
    private static final int EVENT_HANDLER_COLUMN = 2;
    private static final int GUARD_COLUMN = 3;
    
    private CLabel fStateNameLabel;
    private Table fEventTable;
    
    private State fState;
    
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
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(LABEL_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(0, 0);
        fStateNameLabel.setLayoutData(data); 

        fEventTable = getWidgetFactory().createTable(composite, 
                            SWT.HORIZONTAL | SWT.VERTICAL | SWT.VIRTUAL
                            | SWT.FULL_SELECTION | SWT.BORDER);
        
        fEventTable.setHeaderVisible(true);
        fEventTable.setLinesVisible(true);
        
        TableColumn columnEventName = new TableColumn(fEventTable, SWT.NONE);
        columnEventName.setText("イベント名");
        columnEventName.setWidth(EVENT_COLUMN_WIDTH);
        
        TableColumn columnNextStateName = 
                        new TableColumn(fEventTable, SWT.NULL);
        columnNextStateName.setText("遷移先ステート名");
        columnNextStateName.setWidth(NEXT_STATE_COLUMN_WIDTH);
        
        TableColumn columnEventHandler = new TableColumn(fEventTable, SWT.NONE);
        columnEventHandler.setText("イベントハンドラ");
        columnEventHandler.setWidth(EVENT_HANDLER_COLUMN_WIDTH);
        
        TableColumn columnGuard = new TableColumn(fEventTable, SWT.NONE);
        columnGuard.setText("ガード");
        columnGuard.setWidth(GUARD_COLUMN_WIDTH);

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
            
            for (Event event : fState.getEventList()) {
                TableItem item = new TableItem(fEventTable, SWT.NONE);
                
                if (event.getName() != null) {
                    item.setText(EVENT_COLUMN, event.getName());
                }
                State nextState = event.getNextState();
                if (nextState != null && nextState.getName() != null) {
                    item.setText(NEXT_STATE_COLUMN, nextState.getName());
                }
                EventHandler eventHandler = event.getEventHandler();
                if (eventHandler != null) {
                    String className = eventHandler.getClassName();
                    String methodName = eventHandler.getMethodName();
                    if (className != null && methodName != null) {
                        item.setText(EVENT_HANDLER_COLUMN, 
                                        className + ":" + methodName);
                    } else if (methodName != null) {
                        item.setText(EVENT_HANDLER_COLUMN, ":" + methodName);
                    }
                }
                eventHandler = event.getGuardEventHandler();
                if (eventHandler != null) {
                    String className = eventHandler.getClassName();
                    String methodName = eventHandler.getMethodName();
                    if (className != null && methodName != null) {
                        item.setText(GUARD_COLUMN, 
                                        className + ":" + methodName);
                    } else if (methodName != null) {
                        item.setText(GUARD_COLUMN, ":" + methodName);
                    }
                }
            }
            
            resizeEventTable(fTab.getSize());
        }
    }
    
    private void resizeEventTable(Point tabSize) {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(TABLE_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(fStateNameLabel, 0);
        data.bottom = new FormAttachment(0, tabSize.y - TABLE_HEIGHT_MARGIN);
        fEventTable.setLayoutData(data);
    }
}
