// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 角丸矩形フィギュアー(グラデーション対応).
 * グラデーション開始色から背景色へグラデーションする。<br>
 * グラデーションの方向は上から下へ行う。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class GradientRoundedRectangle extends RoundedRectangle {
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
        Rectangle shapeRectangle = getShapeRectangle();

        Color currentColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fGradientStartColor);
        graphics.fillGradient(shapeRectangle, true);
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
        Rectangle shapeRectangle = getShapeRectangle();

        Color currentColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fOutlineColor);
        graphics.drawRoundRectangle(shapeRectangle,
                                    corner.width,
                                    corner.height);
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

    /**
     * フィギュアーの実描画領域を矩形で返す.
     * 実描画領域の左上はフィギュアー自身の位置から、境界線幅
     * を足したもの、大きさはフィギュアー自身の大きさから境界
     * 線の幅を引いたもとになる。
     *
     * @return フィギュアーの実描画領域の矩形
     */
    protected Rectangle getShapeRectangle() {
        Rectangle shapeRectangle = Rectangle.SINGLETON;
        Rectangle bounds = getBounds();
        shapeRectangle.x = bounds.x + lineWidth / 2;
        shapeRectangle.y = bounds.y + lineWidth / 2;
        shapeRectangle.width = bounds.width - lineWidth;
        shapeRectangle.height = bounds.height - lineWidth;

        return shapeRectangle;
    }
}
