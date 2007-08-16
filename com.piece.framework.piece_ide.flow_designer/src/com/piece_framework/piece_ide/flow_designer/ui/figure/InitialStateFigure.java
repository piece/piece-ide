// $Id: InitialStateFigure.java 115 2007-07-09 11:21:17Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.RGB;

/**
 * イニシャルステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class InitialStateFigure extends SpecialStateFigure {

    private static final RGB FIGURE_COLOR = new RGB(0, 0, 0);    
    
    /**
     * イニシャルステート・フィギュアーの背景色を返す.
     * 
     * @return イニシャルステート・フィギュアーの背景色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .SpecialStateFigure#getFigureColor()
     */
    @Override
    RGB getFigureColor() {
        return FIGURE_COLOR;
    }
}
