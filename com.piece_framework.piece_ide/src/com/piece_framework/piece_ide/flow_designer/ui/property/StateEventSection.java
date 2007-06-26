package com.piece_framework.piece_ide.flow_designer.ui.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
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

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class StateEventSection extends AbstractPropertySection {

    private static final int LABEL_WIDTH_PERCENT = 50;
    private static final int TABLE_WIDTH_PERCENT = 100;
    
    private static final int TABLE_HEIGHT_MARGIN = 40;
    
    private static final int EVENT_COLUMN_WIDTH = 100;
    private static final int NEXT_STATE_COLUMN_WIDTH = 100;
    private static final int EVENT_HANDLER_COLUMN_WIDTH = 150;
    private static final int GUARD_COLUMN_WIDTH = 150;
    
    private static final RGB EVENT_SPECIAL_COLOR = new RGB(156, 207, 255);
    private static final RGB EVENT_TRANSITION_COLOR = new RGB(206, 255, 206);
    private static final RGB EVENT_INTERNAL_COLOR = new RGB(255, 154, 206);
    
    private static final int EVENT_SPECIAL = 1;
    private static final int EVENT_TRANSITION = 2;
    private static final int EVENT_INTERNAL = 3;
    
    private CLabel fStateNameLabel;
    private TableViewer fEventTableViewer;
    
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

        fEventTableViewer = new TableViewer(composite, 
                                    SWT.HORIZONTAL | SWT.VERTICAL | SWT.VIRTUAL
                                    | SWT.FULL_SELECTION | SWT.BORDER);
        Table eventTable = fEventTableViewer.getTable();

        fEventTableViewer.setContentProvider(new ArrayContentProvider());
        fEventTableViewer.setLabelProvider(new EventTableLabelProvider());
        fEventTableViewer.setColumnProperties(new String[] {
                            "Event",
                            null,
                            "EventHandler",
                            "Guard" });
        fEventTableViewer.setCellEditors(new CellEditor[] {
                            new TextCellEditor(eventTable),
                            null,
                            new TextCellEditor(eventTable),
                            new TextCellEditor(eventTable) });
        fEventTableViewer.setCellModifier(
                            new EventTableCellModifier(fEventTableViewer));
        
        eventTable.setHeaderVisible(true);
        eventTable.setLinesVisible(true);
        
        TableColumn columnEventName = new TableColumn(eventTable, SWT.NONE);
        columnEventName.setText("イベント名");
        columnEventName.setWidth(EVENT_COLUMN_WIDTH);
        
        TableColumn columnNextStateName = 
                        new TableColumn(eventTable, SWT.NULL);
        columnNextStateName.setText("遷移先ステート名");
        columnNextStateName.setWidth(NEXT_STATE_COLUMN_WIDTH);
        
        TableColumn columnEventHandler = new TableColumn(eventTable, SWT.NONE);
        columnEventHandler.setText("イベントハンドラ");
        columnEventHandler.setWidth(EVENT_HANDLER_COLUMN_WIDTH);
        
        TableColumn columnGuard = new TableColumn(eventTable, SWT.NONE);
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
            
            fEventTableViewer.getTable().removeAll();
            fEventTableViewer.setInput(getItems());
            
            setTableBackground();
            
            resizeEventTable(fTab.getSize());
        }
    }
    
    private List<Event> getItems() {
        
        List<Event> eventList = new ArrayList<Event>();
        List<Event> specialEventList = new ArrayList<Event>();
        List<Event> transitionEventList = new ArrayList<Event>();
        List<Event> internalEventList = new ArrayList<Event>();
        
        for (Event event : fState.getEventList()) {
            int eventType = getEventType(event);
            if (eventType ==  EVENT_SPECIAL) {
                specialEventList.add(event);
            } else if (eventType ==  EVENT_TRANSITION) {
                transitionEventList.add(event);                    
            } else if (eventType ==  EVENT_INTERNAL) {
                internalEventList.add(event);
            }
        }
        
        for (Event event : specialEventList) {
            eventList.add(event);
        }
        for (Event event : transitionEventList) {
            eventList.add(event);
        }
        for (Event event : internalEventList) {
            eventList.add(event);
        }
        
        return eventList;
    }
    
    private void setTableBackground() {
        Table table = fEventTableViewer.getTable();
        for (int i = 0; i < table.getItemCount(); i++) {
            TableItem item = table.getItem(i);
            // モデルを取得するためにテキストを取得を呼び出しておく。
            // こうしないと次の getData メソッドが null を返す。
            item.getText();
            
            Event event = (Event) item.getData();
            
            RGB backColor = null;
            int eventType = getEventType(event);
            if (eventType ==  EVENT_SPECIAL) {
                backColor = EVENT_SPECIAL_COLOR;
            } else if (eventType ==  EVENT_TRANSITION) {
                backColor = EVENT_TRANSITION_COLOR;                    
            } else if (eventType ==  EVENT_INTERNAL) {
                backColor = EVENT_INTERNAL_COLOR;
            }
            item.setBackground(new Color(Display.getCurrent(), backColor));
        }
    }
    
    private int getEventType(Event event) {
        String nextStateName = "";
        if (event.getNextState() != null 
            && event.getNextState().getName() != null)  {
            nextStateName = event.getNextState().getName();
        }
        
        int eventType = 0;
        if (event.isSpecialEvent()) {
            eventType = EVENT_SPECIAL;
        } else if (!nextStateName.equals(fState.getName())) {
            eventType = EVENT_TRANSITION;                    
        } else {
            eventType = EVENT_INTERNAL;
        }
        
        return eventType;
    }
    
    private void resizeEventTable(Point tabSize) {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(TABLE_WIDTH_PERCENT, 0);
        data.top = new FormAttachment(fStateNameLabel, 0);
        data.bottom = new FormAttachment(0, tabSize.y - TABLE_HEIGHT_MARGIN);
        fEventTableViewer.getTable().setLayoutData(data);
    }
}
