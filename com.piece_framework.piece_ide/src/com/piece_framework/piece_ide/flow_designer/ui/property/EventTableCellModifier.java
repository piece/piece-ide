package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Item;

import com.piece_framework.piece_ide.flow_designer.command.SetEventAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;

public class EventTableCellModifier implements ICellModifier {

    private GraphicalEditor fEditor;
    private TableViewer fEventTableViewer;
    
    public EventTableCellModifier(
                GraphicalEditor editor, 
                TableViewer eventTableViewer) {
        fEventTableViewer = eventTableViewer;
        fEditor = editor;
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
            if (event.isBuiltinEvent()) {
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
            if (event.isBuiltinEvent()) {
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
        
        Object attributeValue = null;
        
        if (property.equals("Event")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isBuiltinEvent()) {
                attributeValue = value;
            }
        } else if (property.equals("EventHandler")) {
            String eventHandlerString = String.valueOf(value);
            if (eventHandlerString != null) {
                String[] arrayValue = eventHandlerString.split(":");
                
                String className = null;
                String methodName = null;
                
                if (arrayValue.length == 2) {
                    className = arrayValue[0];
                    methodName = arrayValue[1];
                } else if (arrayValue.length == 1) {
                    methodName = arrayValue[0];
                }
                
                attributeValue = new EventHandler(className, methodName);
            }
        } else if (property.equals("Guard")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isBuiltinEvent()) {
                String guardEventHandlerString = String.valueOf(value);
                if (guardEventHandlerString != null) {
                    String[] arrayValue = guardEventHandlerString.split(":");
                    
                    String className = null;
                    String methodName = null;
                    
                    if (arrayValue.length == 2) {
                        className = arrayValue[0];
                        methodName = arrayValue[1];
                    } else if (arrayValue.length == 1) {
                        methodName = arrayValue[0];
                    }
                    
                    attributeValue = new EventHandler(className, methodName);
                }
            }
        }

        if (attributeValue != null) {
            CommandStack commandStack = 
                (CommandStack) fEditor.getAdapter(CommandStack.class);
            SetEventAttributeCommand command =
                new SetEventAttributeCommand(
                        property, attributeValue, event);
            commandStack.execute(command);
        }
        fEventTableViewer.update(element, null);
    }
}
