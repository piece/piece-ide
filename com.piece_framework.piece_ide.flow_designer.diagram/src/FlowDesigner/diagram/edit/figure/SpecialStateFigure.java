// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.graphics.Image;

public abstract class SpecialStateFigure extends ImageFigure {
    private static final int FIGURE_WIDTH = 20;
    private static final int FIGURE_HEIGHT = 20;

    public SpecialStateFigure() {
        setImage(getFigureImage());
        setSize(FIGURE_WIDTH, FIGURE_HEIGHT);
    }

    abstract Image getFigureImage();
}
