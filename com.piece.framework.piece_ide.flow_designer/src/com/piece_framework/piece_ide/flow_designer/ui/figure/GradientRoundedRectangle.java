// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 角丸矩形フィギュアー(グラデーション対応).
 * グラデーション開始色から背景色へグラデーションする。<br>
 * グラデーションの方向は上から下へ行う。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class GradientRoundedRectangle extends RoundedRectangle {
    private Color fGradientStartColor = ColorConstants.white;
    private Color fOutlineColor = ColorConstants.black;

    private int fShadowWidth = 3;
    
    private Label fDummyHorizontalFigure;
    private Label fDummyVerticalFigure;
    
    @Override
    public void paint(Graphics graphics) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        List children = getChildren();
        Rectangle figureBounds = getBounds();
        Point point = bounds.getTopLeft();
        Point point2 = this.getLocation();
        Insets insets = getBorder().getInsets(this);
        for (int i = 0; i < children.size(); i++) {
            Figure figure = (Figure) children.get(i);
            if (figure == fDummyHorizontalFigure
                || figure == fDummyVerticalFigure) {
                continue;
            }
            Rectangle childBounds = figure.getBounds();
            if (maxWidth < (childBounds.x - bounds.x - insets.left) + childBounds.width) {
                maxWidth = (childBounds.x - bounds.x - insets.left) + childBounds.width + fShadowWidth;
            }
            if (maxHeight < (childBounds.y - bounds.y - insets.top) + childBounds.height) {
                maxHeight = (childBounds.y - bounds.y - insets.top) + childBounds.height + fShadowWidth;
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

    public void setFigureHorizontalCenter(IFigure figure, int y) {
        Dimension size = null;
        if (fDummyHorizontalFigure != null) {
            size = fDummyHorizontalFigure.getSize();
        } else {
            size = getSize();
        }
        Rectangle figureBounds = figure.getBounds();
        int x = (int) (((size.width - fShadowWidth) - figureBounds.width) / 2);
        
        Rectangle constraint = new Rectangle(x, y, -1, -1);
        setConstraint(figure, constraint);
    }
    
    /**
     * シェイプを塗りつぶす.
     * 
     * @param graphics グラフィックス
     * @see org.eclipse.draw2d.RectangleFigure
     *          #fillShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void fillShape(Graphics graphics) {
        Color currentBackColor = graphics.getBackgroundColor();
        graphics.setBackgroundColor(ColorConstants.gray);
        Rectangle shadowRectangle = getShadowRectangle();
        graphics.fillRoundRectangle(shadowRectangle, corner.width, corner.height);
        graphics.setBackgroundColor(currentBackColor);
        
        Color currentForeColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fGradientStartColor);
        Rectangle shapeRectangle = getShapeRectangle();
        graphics.fillGradient(shapeRectangle, true);
        graphics.setForegroundColor(currentForeColor);
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
        Color currentForeColor = graphics.getForegroundColor();
        
//        graphics.setForegroundColor(ColorConstants.gray);
//        Rectangle shadowRectangle = getShadowRectangle();
//        graphics.drawRoundRectangle(shadowRectangle, corner.width, corner.height);
        
        graphics.setForegroundColor(fOutlineColor);
        Rectangle shapeRectangle = getShapeRectangle();
        graphics.drawRoundRectangle(shapeRectangle, corner.width, corner.height);
        
        graphics.setForegroundColor(currentForeColor);
    }

    private Rectangle getShapeRectangle() {
        Rectangle shapeRectangle = Rectangle.SINGLETON;
        Rectangle bounds = getBounds();
        shapeRectangle.x = bounds.x + lineWidth / 2;
        shapeRectangle.y = bounds.y + lineWidth / 2;
        shapeRectangle.width = bounds.width - lineWidth - fShadowWidth;
        shapeRectangle.height = bounds.height - lineWidth - fShadowWidth;

        return shapeRectangle;
    }
    
    private Rectangle getShadowRectangle() {
        Rectangle shadowRectangle = Rectangle.SINGLETON;
        Rectangle bounds = getBounds();
        shadowRectangle.x = bounds.x + fShadowWidth;
        shadowRectangle.y = bounds.y + fShadowWidth;
        shadowRectangle.width = bounds.width - fShadowWidth;
        shadowRectangle.height = bounds.height - fShadowWidth;
        
        return shadowRectangle;
        
//        Rectangle f = getShapeRectangle();
//        f.x += fShadowWidth;
//        f.y += fShadowWidth;
//        
//        return f;
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
    
    public Dimension getSizeWithoutShadow() {
        Dimension size = getSize();
        size.width -= fShadowWidth + lineWidth;
        size.height -= fShadowWidth + lineWidth;
//        size.width -= lineWidth - fShadowWidth;
//        size.height -= lineWidth - fShadowWidth;
        return size;
    }
}
