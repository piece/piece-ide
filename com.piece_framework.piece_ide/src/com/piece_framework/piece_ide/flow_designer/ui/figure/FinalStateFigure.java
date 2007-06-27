// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * ファイナルステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FinalStateFigure extends Ellipse {
    
    private static final int FIGURE_WIDTH = 20;
    private static final int FIGURE_HEIGHT = 20;
    
    private static final RGB FIGURE_COLOR = new RGB(255, 255, 255);    
    
    /**
     * コンストラクタ.
     * 
     */
    public FinalStateFigure() {
        Color bg = new Color(Display.getCurrent(), FIGURE_COLOR);
        setBackgroundColor(bg);
        setSize(FIGURE_WIDTH, FIGURE_HEIGHT);
    }
}
