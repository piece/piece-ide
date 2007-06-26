package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Item;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;

public class EventTableCellModifier implements ICellModifier {

    private TableViewer fEventTableViewer;
    
    public EventTableCellModifier(TableViewer eventTableViewer) {
        fEventTableViewer = eventTableViewer;
    }
    
    public boolean canModify(Object element, String property) {
        if (property != null) {
            return true;
        }
        return false;
    }

    public Object getValue(Object element, String property) {
        Event event = (Event) element;
        
        if (property.equals("Event")) {
            // ビルトインイベントの場合は編集不可
            if (event.isSpecialEvent()) {
                return null;
            }
            
            return event.getName();
        } else if (property.equals("EventHandler")) {
            EventHandler eventHandler = event.getEventHandler();
            if (eventHandler != null) {
                String className = "";
                String methodName = "";
                
                if (eventHandler.getClassName() != null) {
                    className = eventHandler.getClassName();
                }
                if (eventHandler.getMethodName() != null) {
                    methodName = eventHandler.getMethodName();
                }
                return className + ":" + methodName;
            }
        } else if (property.equals("Guard")) {
            // ビルトインイベントの場合は編集不可
            if (event.isSpecialEvent()) {
                return null;
            }
            
            EventHandler guardEventHandler = event.getGuardEventHandler();
            if (guardEventHandler != null) {
                String className = "";
                String methodName = "";
                
                if (guardEventHandler.getClassName() != null) {
                    className = guardEventHandler.getClassName();
                }
                if (guardEventHandler.getMethodName() != null) {
                    methodName = guardEventHandler.getMethodName();
                }
                return className + ":" + methodName;
            }
        }
        
        return "";
    }

    public void modify(Object element, String property, Object value) {
        if (element instanceof Item) {
            element = ((Item) element).getData();
        }
        Event event = (Event) element;
        
        if (property.equals("Event")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isSpecialEvent()) {
                event.setName(String.valueOf(value));
            }
        } else if (property.equals("EventHandler")) {
            String eventHandler = String.valueOf(value);
            if (eventHandler != null) {
                String[] arrayValue = eventHandler.split(":");
                
                String className = null;
                String methodName = null;
                
                if (arrayValue.length == 2) {
                    className = arrayValue[0];
                    methodName = arrayValue[1];
                } else if (arrayValue.length == 1) {
                    methodName = arrayValue[0];
                }
                
                event.setEventHandler(className, methodName);
            }
        } else if (property.equals("Guard")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isSpecialEvent()) {
                String guardEventHandler = String.valueOf(value);
                if (guardEventHandler != null) {
                    String[] arrayValue = guardEventHandler.split(":");
                    
                    String className = null;
                    String methodName = null;
                    
                    if (arrayValue.length == 2) {
                        className = arrayValue[0];
                        methodName = arrayValue[1];
                    } else if (arrayValue.length == 1) {
                        methodName = arrayValue[0];
                    }
                    
                    event.setGuardEventHandler(className, methodName);
                }
            }
        }
        
        fEventTableViewer.update(element, null);
    }
}
