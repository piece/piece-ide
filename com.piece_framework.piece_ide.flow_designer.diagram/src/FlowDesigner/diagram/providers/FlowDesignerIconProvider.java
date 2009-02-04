package FlowDesigner.diagram.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.swt.graphics.Image;

import FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin;
import FlowDesigner.impl.ActionStateImpl;
import FlowDesigner.impl.EventImpl;
import FlowDesigner.impl.FinalStateImpl;
import FlowDesigner.impl.FlowImpl;
import FlowDesigner.impl.InitialStateImpl;
import FlowDesigner.impl.ViewStateImpl;

/**
 * @generated
 */
public class FlowDesignerIconProvider extends AbstractProvider implements
        IIconProvider {
    private Map<Class, Image> fIconMap;

    /**
     * @generated NOT
     */
    public Image getIcon(IAdaptable hint, int flags) {
        if (hint instanceof EObjectAdapter) {
            Object realObject = ((EObjectAdapter) hint).getRealObject();
            if (fIconMap == null) {
                fIconMap = new HashMap<Class, Image>();
                fIconMap.put(FlowImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/FlowDesigner.gif").createImage()
                             );
                fIconMap.put(InitialStateImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/InitialState.gif").createImage()
                             );
                fIconMap.put(FinalStateImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/FinalState.gif").createImage()
                             );
                fIconMap.put(ViewStateImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/ViewState.gif").createImage()
                             );
                fIconMap.put(ActionStateImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/ActionState.gif").createImage()
                             );
                fIconMap.put(EventImpl.class,
                             FlowDesignerDiagramEditorPlugin
                                 .getBundledImageDescriptor("icons/Event.gif").createImage()
                             );
            }
            return fIconMap.get(realObject.getClass());
        }
        return FlowDesigner.diagram.providers.FlowDesignerElementTypes
                .getImage(hint);
    }

    /**
     * @generated
     */
    public boolean provides(IOperation operation) {
        if (operation instanceof GetIconOperation) {
            return ((GetIconOperation) operation).execute(this) != null;
        }
        return false;
    }
}
