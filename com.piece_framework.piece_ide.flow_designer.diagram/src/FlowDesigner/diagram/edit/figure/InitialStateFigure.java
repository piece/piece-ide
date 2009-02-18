// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.swt.graphics.Image;

public class InitialStateFigure extends SpecialStateFigure {
    @Override
    Image getFigureImage() {
        return FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getBundledImageDescriptor("icons/InitialStateLarge.gif").createImage(); //$NON-NLS-1$
    }
}
