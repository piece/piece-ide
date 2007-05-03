// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ViewStateFigure extends RoundedRectangle {

    private Label fNameLabel;
    
    private Label fViewLabel;
    
    public ViewStateFigure() {
        Color bg = new Color(Display.getCurrent(), new RGB(80, 80, 200));
        setBackgroundColor(bg);
        
        setLayoutManager(new ToolbarLayout());
        fNameLabel = new Label();
        fViewLabel = new Label();
        setBorder(new MarginBorder(20));
        add(fViewLabel, ToolbarLayout.ALIGN_CENTER);
        add(fNameLabel, ToolbarLayout.ALIGN_CENTER);
        
    }
    
    public void setName(String name) {
        if (name != null) {
            fNameLabel.setText(name);
        }
    }
    
    public void setView(String view) {
        if (view != null) {
            fViewLabel.setText("[" + view + "]");
        }
    }
}
