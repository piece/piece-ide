// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class GradientRoundedRectangle extends RoundedRectangle {
    private Color fGradientStartColor = ColorConstants.white;
    private Color fOutlineColor = ColorConstants.black;

    @Override
    protected void fillShape(Graphics graphics) {
        Rectangle shapeRectangle = getShapeRectangle();

        Color currentColor = graphics.getForegroundColor();
        graphics.setForegroundColor(fGradientStartColor);
        graphics.fillGradient(shapeRectangle, true);
        graphics.setForegroundColor(currentColor);
    }

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

    public Color getGradientStartColor() {
        return fGradientStartColor;
    }

    public void setGradientStartColor(Color color) {
        if (color != null) {
            fGradientStartColor = color;
        }
    }

    public Color getOutlineColor() {
        return fOutlineColor;
    }

    public void setOutlineColor(Color color) {
        if (color != null) {
            fOutlineColor = color;
        }
    }

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
