package FlowDesigner.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @generated
 */
public abstract class FlowDesignerAbstractNavigatorItem extends PlatformObject {

    /**
     * @generated
     */
    static {
        final Class[] supportedTypes = new Class[] { ITabbedPropertySheetPageContributor.class };
        final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {
            public String getContributorId() {
                return "com.piece_framework.piece_ide.flow_designer.diagram"; //$NON-NLS-1$
            }
        };
        Platform.getAdapterManager().registerAdapters(
                new IAdapterFactory() {

                    public Object getAdapter(Object adaptableObject,
                            Class adapterType) {
                        if (adaptableObject instanceof FlowDesigner.diagram.navigator.FlowDesignerAbstractNavigatorItem
                                && adapterType == ITabbedPropertySheetPageContributor.class) {
                            return propertySheetPageContributor;
                        }
                        return null;
                    }

                    public Class[] getAdapterList() {
                        return supportedTypes;
                    }
                },
                FlowDesigner.diagram.navigator.FlowDesignerAbstractNavigatorItem.class);
    }

    /**
     * @generated
     */
    private Object myParent;

    /**
     * @generated
     */
    protected FlowDesignerAbstractNavigatorItem(Object parent) {
        myParent = parent;
    }

    /**
     * @generated
     */
    public Object getParent() {
        return myParent;
    }

}
