// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ActionStateFigure extends RoundedRectangle {

    private Label fNameLabel;
    
    public ActionStateFigure() {
        Color bg = new Color(Display.getCurrent(), new RGB(200, 120, 100));
        setBackgroundColor(bg);
        
        setLayoutManager(new BorderLayout());
        fNameLabel = new Label();
        setBorder(new MarginBorder(20));
        add(fNameLabel, BorderLayout.CENTER);
    }
    
    public void setName(String name) {
        if (name != null) {
            fNameLabel.setText(name);
        }
    }
}
