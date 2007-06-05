package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class EventListFigure extends Figure {
    
    private static final RGB EVENTLIST_COLOR = new RGB(255, 255, 128); 
    
    public EventListFigure() {
        Color bg = new Color(Display.getCurrent(), EVENTLIST_COLOR);
        setBackgroundColor(bg);
        
        setLayoutManager(new XYLayout());
        setBorder(new FrameBorder());
        
        setPreferredSize(100,100);
        
    }
    
}
