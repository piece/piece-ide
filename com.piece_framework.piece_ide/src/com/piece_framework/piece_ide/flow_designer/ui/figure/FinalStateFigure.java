// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class FinalStateFigure extends Ellipse {

    public FinalStateFigure() {
        Color bg = new Color(Display.getCurrent(), new RGB(255, 255, 255));
        
        setBackgroundColor(bg);
        setSize(20, 20);
    }
}
