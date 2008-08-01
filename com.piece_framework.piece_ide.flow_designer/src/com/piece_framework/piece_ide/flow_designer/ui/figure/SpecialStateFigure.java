// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.graphics.Image;

/**
 * スペシャルステート・抽象フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public abstract class SpecialStateFigure extends ImageFigure {

    private static final int FIGURE_WIDTH = 20;
    private static final int FIGURE_HEIGHT = 20;

    /**
     * コンストラクタ.
     *
     */
    public SpecialStateFigure() {
        setImage(getFigureImage());
        setSize(FIGURE_WIDTH, FIGURE_HEIGHT);
    }

    /**
     * フィギュアーのイメージを返す.
     *
     * @return フィギュアーのイメージ
     */
    abstract Image getFigureImage();

}
