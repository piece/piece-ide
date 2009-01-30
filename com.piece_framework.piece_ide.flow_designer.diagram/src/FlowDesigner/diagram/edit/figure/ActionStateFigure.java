// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * アクションステート・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class ActionStateFigure extends GradientRoundedRectangleWithShadow {
    private static final RGB STATE_COLOR = new RGB(204, 204, 204);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(153, 153, 153);

    public ActionStateFigure() {
        setGradientStartColor(new Color(Display.getCurrent(), STATE_COLOR));
        setOutlineColor(new Color(Display.getCurrent(), STATE_OUTLINE_COLOR));
    }
}
