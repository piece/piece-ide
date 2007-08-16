// $Id: SpecialStateFigure.java 180 2007-07-27 00:57:08Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * スペシャルステート・抽象フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public abstract class SpecialStateFigure extends Ellipse {

    private static final int FIGURE_WIDTH = 20;
    private static final int FIGURE_HEIGHT = 20;
    
    /**
     * コンストラクタ.
     * 
     */
    public SpecialStateFigure() {
        Color bg = new Color(Display.getCurrent(), getFigureColor());
        setBackgroundColor(bg);
        setSize(FIGURE_WIDTH, FIGURE_HEIGHT);
    }
    
    /**
     * フィギュアーの背景色を返す.
     * 
     * @return フィギュアーの背景色
     */
    abstract RGB getFigureColor();
    
}