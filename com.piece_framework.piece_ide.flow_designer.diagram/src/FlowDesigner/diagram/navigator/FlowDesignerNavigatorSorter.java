package FlowDesigner.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class FlowDesignerNavigatorSorter extends ViewerSorter {

    /**
     * @generated
     */
    private static final int GROUP_CATEGORY = 4006;

    /**
     * @generated
     */
    public int category(Object element) {
        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) {
            FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem item = (FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) element;
            return FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(item.getView());
        }
        return GROUP_CATEGORY;
    }

}
