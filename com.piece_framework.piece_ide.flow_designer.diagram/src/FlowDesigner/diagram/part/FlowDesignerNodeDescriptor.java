package FlowDesigner.diagram.part;

import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 */
public class FlowDesignerNodeDescriptor {

    /**
     * @generated
     */
    private EObject myModelElement;

    /**
     * @generated
     */
    private int myVisualID;

    /**
     * @generated
     */
    private String myType;

    /**
     * @generated
     */
    public FlowDesignerNodeDescriptor(EObject modelElement, int visualID) {
        myModelElement = modelElement;
        myVisualID = visualID;
    }

    /**
     * @generated
     */
    public EObject getModelElement() {
        return myModelElement;
    }

    /**
     * @generated
     */
    public int getVisualID() {
        return myVisualID;
    }

    /**
     * @generated
     */
    public String getType() {
        if (myType == null) {
            myType = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getType(getVisualID());
        }
        return myType;
    }

}
