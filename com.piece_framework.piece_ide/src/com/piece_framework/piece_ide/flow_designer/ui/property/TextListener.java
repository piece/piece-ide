package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public abstract class TextListener implements Listener {

    private boolean fUserChange;
    
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.KeyDown:
            if (event.character == SWT.CR) {
                changeText((Control)event.widget);
            }
            break;
        case SWT.FocusOut:
            changeText((Control)event.widget);
            break;
        }
    }

    public void setUserChange(boolean userChange) {
        fUserChange = userChange;
    }
    
    public boolean isUserChange() {
        return fUserChange;
    }
    
    public abstract void changeText(Control control);
    
}
