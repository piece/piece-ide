// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 角丸矩形グラデーションフィギュアー(影付き).
 *
 * @author MATSUFUJI Hideharu
 * @since 0.2.0
 */
public class GradientRoundedRectangleWithShadow extends
        GradientRoundedRectangle {
    private static final int DEFAULT_SHADOW_WIDTH = 3;

    private int fShadowWidth = DEFAULT_SHADOW_WIDTH;

    private Label fDummyHorizontalFigure;
    private Label fDummyVerticalFigure;

    /**
     * フィギュアーのサイズを調整するため、水平・垂直方向にダミーの
     * ラベルを配置する.
     * 親フィギュアーのサイズは子フィギュアーのサイズに影響される。
     * ダミーのラベルを配置することで実際に表示するフィギュアーのサ
     * イズ・配置によって親フィギュアーのサイズが変更されないように
     * する。
     * 水平のダミーラベルは右部分が一番端にあるフィギュアーの左端＋
     * 幅＋影の幅までのサイズで作成する。
     * 垂直のダミーラベルは下部分が一番下にあるフィギュアーの上端＋
     * 高さ＋影の幅までのサイズで作成する。
     *
     * @param graphics グラフィックス
     * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        int maxWidth = 0;
        int maxHeight = 0;

        List children = getChildren();
        Insets insets = getBorder().getInsets(this);
        for (int i = 0; i < children.size(); i++) {
            Figure figure = (Figure) children.get(i);
            if (figure == fDummyHorizontalFigure
                || figure == fDummyVerticalFigure) {
                continue;
            }
            Rectangle childBounds = figure.getBounds();
            int width = (childBounds.x - bounds.x - insets.left)
                            + childBounds.width;
            if (maxWidth < width) {
                maxWidth = width + fShadowWidth;
            }
            int height = (childBounds.y - bounds.y - insets.top)
                            + childBounds.height;
            if (maxHeight < height) {
                maxHeight = height + fShadowWidth;
            }
        }
        if (fDummyHorizontalFigure == null) {
            fDummyHorizontalFigure = new Label();
            add(fDummyHorizontalFigure);
        }
        if (fDummyVerticalFigure == null) {
            fDummyVerticalFigure = new Label();
            add(fDummyVerticalFigure);
        }

        Rectangle horizontalConstraint = new Rectangle(0, 0, maxWidth, 1);
        setConstraint(fDummyHorizontalFigure, horizontalConstraint);

        Rectangle verticalConstraint = new Rectangle(0, 0, 1, maxHeight);
        setConstraint(fDummyVerticalFigure, verticalConstraint);

        super.paint(graphics);
    }

    /**
     * 影の幅を返す.
     *
     * @return 影の幅
     */
    public int getShadowWidth() {
        return fShadowWidth;
    }

    /**
     * 影の幅を設定する.
     *
     * @param shadowWidth 影の幅
     */
    public void setShadowWidth(int shadowWidth) {
        fShadowWidth = shadowWidth;
    }

    /**
     * 影の部分を塗りつぶしてから、本体部分を塗りつぶす.
     *
     * @param graphics グラフィックス
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .GradientRoundedRectangle#fillShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void fillShape(Graphics graphics) {
        Color currentBackColor = graphics.getBackgroundColor();
        graphics.setBackgroundColor(ColorConstants.gray);
        Rectangle shadowRectangle = getShadowRectangle();
        graphics.fillRoundRectangle(shadowRectangle,
                                    corner.width,
                                    corner.height);
        graphics.setBackgroundColor(currentBackColor);

        super.fillShape(graphics);
    }

    /**
     * フィギュアーの実描画領域を矩形で返す.
     * 実際の描画領域の大きさから影の幅を引いたものを返す.
     *
     * @return フィギュアーの実描画領域の矩形
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .GradientRoundedRectangle#getShapeRectangle()
     */
    protected Rectangle getShapeRectangle() {
        Rectangle shapeRectangle = super.getShapeRectangle();
        shapeRectangle.width -= fShadowWidth;
        shapeRectangle.height -= fShadowWidth;

        return shapeRectangle;
    }

    /**
     * 影部分を矩形で返す.
     * 影は本体のフィギュアーと同じ形(角丸矩形)で、影の幅分ずらし
     * たものになる。
     *
     * @return 影部分の矩形
     */
    protected Rectangle getShadowRectangle() {
        Rectangle shadowRectangle = Rectangle.SINGLETON;
        Rectangle bounds = getBounds();
        shadowRectangle.x = bounds.x + fShadowWidth;
        shadowRectangle.y = bounds.y + fShadowWidth;
        shadowRectangle.width = bounds.width - fShadowWidth;
        shadowRectangle.height = bounds.height - fShadowWidth;

        return shadowRectangle;
    }
}
