// $Id: FinalStateFigure.java 115 2007-07-09 11:21:17Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.RGB;

/**
 * ファイナルステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FinalStateFigure extends SpecialStateFigure {
    
    private static final RGB FIGURE_COLOR = new RGB(255, 255, 255);

    /**
     * ファイナルステート・フィギュアーの背景色を返す.
     * 
     * @return ファイナルステート・フィギュアーの背景色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .SpecialStateFigure#getFigureColor()
     */
    @Override
    RGB getFigureColor() {
        return FIGURE_COLOR;
    }
}
