package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class EventTableLabelProvider extends LabelProvider implements
        ITableLabelProvider {

    private static final int EVENT_COLUMN = 0;
    private static final int NEXT_STATE_COLUMN = 1;
    private static final int EVENT_HANDLER_COLUMN = 2;
    private static final int GUARD_COLUMN = 3;
    
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }
    
    public String getColumnText(Object element, int columnIndex) {
        Event event = (Event) element;
        
        switch (columnIndex) {
        case EVENT_COLUMN:
            if (event.getName() != null) {
                return event.getName();
            }
            break;
        case NEXT_STATE_COLUMN:
            State nextState = event.getNextState();
            if (nextState != null && nextState.getName() != null) {
                return nextState.getName();
            }
            break;
        case EVENT_HANDLER_COLUMN:
            EventHandler eventHandler = event.getEventHandler();
            if (eventHandler != null) {
                String className = eventHandler.getClassName();
                String methodName = eventHandler.getMethodName();
                if (className != null && methodName != null) {
                    return className + ":" + methodName;
                } else if (methodName != null) {
                    return ":" + methodName;
                }
            }
            break;
        case GUARD_COLUMN:
            EventHandler guardEventHandler = event.getGuardEventHandler();
            if (guardEventHandler != null) {
                String className = guardEventHandler.getClassName();
                String methodName = guardEventHandler.getMethodName();
                if (className != null && methodName != null) {
                    return className + ":" + methodName;
                } else if (methodName != null) {
                    return ":" + methodName;
                }
            }
            break;
        }
        
        return "";
    }

}
