package FlowDesigner.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class FlowDesignerVisualIDRegistry {

    /**
     * @generated
     */
    private static final String DEBUG_KEY = "com.piece_framework.piece_ide.flow_designer.diagram/debug/visualID"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if (FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                    .equals(view.getType())) {
                return FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        return FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view.getType());
    }

    /**
     * @generated
     */
    public static String getModelID(View view) {
        View diagram = view.getDiagram();
        while (view != diagram) {
            EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
            if (annotation != null) {
                return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
            }
            view = (View) view.eContainer();
        }
        return diagram != null ? diagram.getType() : null;
    }

    /**
     * @generated
     */
    public static int getVisualID(String type) {
        try {
            return Integer.parseInt(type);
        } catch (NumberFormatException e) {
            if (Boolean.TRUE.toString().equalsIgnoreCase(
                    Platform.getDebugOption(DEBUG_KEY))) {
                FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .getInstance().logError(
                                "Unable to parse view type as a visualID number: "
                                        + type);
            }
        }
        return -1;
    }

    /**
     * @generated
     */
    public static String getType(int visualID) {
        return String.valueOf(visualID);
    }

    /**
     * @generated
     */
    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getFlow().isSuperTypeOf(
                domainElement.eClass())
                && isDiagram((FlowDesigner.Flow) domainElement)) {
            return FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static int getNodeVisualID(View containerView, EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        String containerModelID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getModelID(containerView);
        if (!FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(containerModelID)) {
            return -1;
        }
        int containerVisualID;
        if (FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(containerModelID)) {
            containerVisualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        switch (containerVisualID) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getActionState()
                    .isSuperTypeOf(domainElement.eClass())) {
                return FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID;
            }
            if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getFinalState()
                    .isSuperTypeOf(domainElement.eClass())) {
                return FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID;
            }
            if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getInitialState()
                    .isSuperTypeOf(domainElement.eClass())) {
                return FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID;
            }
            if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getViewState()
                    .isSuperTypeOf(domainElement.eClass())) {
                return FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID;
            }
            break;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        String containerModelID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getModelID(containerView);
        if (!FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(containerModelID)) {
            return false;
        }
        int containerVisualID;
        if (FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(containerModelID)) {
            containerVisualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID;
            } else {
                return false;
            }
        }
        switch (containerVisualID) {
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            if (FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            if (FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (FlowDesigner.diagram.edit.parts.ViewStateViewEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            if (FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            if (FlowDesigner.diagram.edit.parts.EventEventEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        }
        return false;
    }

    /**
     * @generated
     */
    public static int getLinkWithClassVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (FlowDesigner.FlowDesignerPackage.eINSTANCE.getEvent()
                .isSuperTypeOf(domainElement.eClass())) {
            return FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * User can change implementation of this method to handle some specific
     * situations not covered by default logic.
     * 
     * @generated
     */
    private static boolean isDiagram(FlowDesigner.Flow element) {
        return true;
    }

}
