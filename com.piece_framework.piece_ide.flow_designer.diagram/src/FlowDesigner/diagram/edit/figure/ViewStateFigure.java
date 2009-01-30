// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ViewStateFigure extends GradientRoundedRectangleWithShadow {
    private static final RGB STATE_COLOR = new RGB(225, 221, 170);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(174, 161, 90);

    public ViewStateFigure() {
        setGradientStartColor(new Color(Display.getCurrent(), STATE_COLOR));
        setOutlineColor(new Color(Display.getCurrent(), STATE_OUTLINE_COLOR));
    }
}
