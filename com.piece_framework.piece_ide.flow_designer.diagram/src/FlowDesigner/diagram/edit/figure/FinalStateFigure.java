// $Id$

package FlowDesigner.diagram.edit.figure;

import org.eclipse.swt.graphics.Image;

public class FinalStateFigure extends SpecialStateFigure {
    @Override
    Image getFigureImage() {
        return FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getBundledImageDescriptor("icons/FinalStateLarge.gif").createImage(); //$NON-NLS-1$
    }
}
