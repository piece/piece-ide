// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 矩形フィギュアー(グラデーション対応).
 * グラデーション開始色から背景色へグラデーションする。<br>
 * グラデーションの方向は上から下へ行う。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class GradientRectangleFigure extends RectangleFigure {
    private Color fGradientStartColor = ColorConstants.white;
    private Color fOutlineColor = ColorConstants.black;

    /**
     * シェイプを塗りつぶす.
     *
     * @param graphics グラフィックス
     * @see org.eclipse.draw2d.RectangleFigure
     *          #fillShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void fillShape(Graphics graphics) {
        Rectangle f = Rectangle.SINGLETON;
        Rectangle r = getBounds();
        f.x = r.x + lineWidth / 2;
        f.y = r.y + lineWidth / 2;
        f.width = r.width - lineWidth;
        f.height = r.height - lineWidth;

        Color currentColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fGradientStartColor);
        graphics.fillGradient(f, true);
        graphics.setForegroundColor(currentColor);
    }

    /**
     * アウトラインを描画する.
     *
     * @param graphics グラフィックス
     * @see org.eclipse.draw2d.RectangleFigure
     *          #outlineShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void outlineShape(Graphics graphics) {
        Color currentColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fOutlineColor);
        super.outlineShape(graphics);
        graphics.setForegroundColor(currentColor);
    }

    /**
     * グラデーション開始色を返す.
     *
     * @return グラデーション開始色
     */
    public Color getGradientStartColor() {
        return fGradientStartColor;
    }

    /**
     * グラデーション開始色を設定する.
     *
     * @param color グラデーション開始色
     */
    public void setGradientStartColor(Color color) {
        if (color != null) {
            fGradientStartColor = color;
        }
    }

    /**
     * アウトライン色を返す.
     *
     * @return アウトライン色
     */
    public Color getOutlineColor() {
        return fOutlineColor;
    }

    /**
     * アウトライン色を設定する.
     *
     * @param color アウトライン色
     */
    public void setOutlineColor(Color color) {
        if (color != null) {
            fOutlineColor = color;
        }
    }
}
