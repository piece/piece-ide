// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Rectangle;

public class GradientRoundedRectangleWithShadow extends
        GradientRoundedRectangle {
    private static final int DIFF = 3;
    private RoundedRectangle fShadow;

    @Override
    public void setBounds(Rectangle rectangle) {
        if (fShadow == null) {
            createShadowRectangle();
        }

        Rectangle shadowRectangle = new Rectangle();
        shadowRectangle.x = rectangle.x + DIFF;
        shadowRectangle.y = rectangle.y + DIFF;
        shadowRectangle.width = rectangle.width;
        shadowRectangle.height = rectangle.height;
        fShadow.setBounds(shadowRectangle);

        Rectangle parentRectangle = new Rectangle();
        parentRectangle.x = rectangle.x;
        parentRectangle.y = rectangle.y;
        parentRectangle.width = rectangle.width + DIFF;
        parentRectangle.height = rectangle.height + DIFF;

        getParent().setBounds(parentRectangle);
        super.setBounds(rectangle);
    }

    private void createShadowRectangle() {
        fShadow = new RoundedRectangle();
        fShadow.setBackgroundColor(ColorConstants.gray);

        IFigure parent = getParent();
        int index = 0;
        for (int i = 0; i < parent.getChildren().size(); ++i) {
            if (parent.getChildren().get(i) == getParent()) {
                index = i;
                break;
            }
        }
        parent.add(fShadow, index);
    }
}
