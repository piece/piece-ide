package FlowDesigner.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FlowDesignerViewProvider extends AbstractViewProvider {

    /**
     * @generated
     */
    protected Class getDiagramViewClass(IAdaptable semanticAdapter,
            String diagramKind) {
        EObject semanticElement = getSemanticElement(semanticAdapter);
        if (FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(diagramKind)
                && FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getDiagramVisualID(semanticElement) != -1) {
            return FlowDesigner.diagram.view.factories.FlowViewFactory.class;
        }
        return null;
    }

    /**
     * @generated
     */
    protected Class getNodeViewClass(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
        if (containerView == null) {
            return null;
        }
        IElementType elementType = getSemanticElementType(semanticAdapter);
        EObject domainElement = getSemanticElement(semanticAdapter);
        int visualID;
        if (semanticHint == null) {
            // Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
            // In this situation there should be NO elementType, visualID will be determined
            // by VisualIDRegistry.getNodeVisualID() for domainElement.
            if (elementType != null || domainElement == null) {
                return null;
            }
            visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getNodeVisualID(containerView, domainElement);
        } else {
            visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(semanticHint);
            if (elementType != null) {
                // Semantic hint is specified together with element type.
                // Both parameters should describe exactly the same diagram element.
                // In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
                // domainElement (if specified) is the same as in element type.
                if (!FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .isKnownElementType(elementType)
                        || (!(elementType instanceof IHintedType))) {
                    return null; // foreign element type
                }
                String elementTypeHint = ((IHintedType) elementType)
                        .getSemanticHint();
                if (!semanticHint.equals(elementTypeHint)) {
                    return null; // if semantic hint is specified it should be the same as in element type
                }
                if (domainElement != null
                        && visualID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                                .getNodeVisualID(containerView, domainElement)) {
                    return null; // visual id for node EClass should match visual id from element type
                }
            } else {
                // Element type is not specified. Domain element should be present (except pure design elements).
                // This method is called with EObjectAdapter as parameter from:
                //   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
                //   - generated ViewFactory.decorateView() for parent element
                if (!FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                        .equals(FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                                .getModelID(containerView))) {
                    return null; // foreign diagram
                }
                switch (visualID) {
                case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
                case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
                case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
                case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
                    if (domainElement == null
                            || visualID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                                    .getNodeVisualID(containerView,
                                            domainElement)) {
                        return null; // visual id in semantic hint should match visual id for domain element
                    }
                    break;
                case FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID:
                    if (FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                            .getVisualID(containerView)
                            || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID:
                case FlowDesigner.diagram.edit.parts.ViewStateViewEditPart.VISUAL_ID:
                    if (FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                            .getVisualID(containerView)
                            || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case FlowDesigner.diagram.edit.parts.EventEventEditPart.VISUAL_ID:
                    if (FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                            .getVisualID(containerView)
                            || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                default:
                    return null;
                }
            }
        }
        return getNodeViewClass(containerView, visualID);
    }

    /**
     * @generated
     */
    protected Class getNodeViewClass(View containerView, int visualID) {
        if (containerView == null
                || !FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .canCreateNode(containerView, visualID)) {
            return null;
        }
        switch (visualID) {
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.ActionStateViewFactory.class;
        case FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.ActionStateNameViewFactory.class;
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.FinalStateViewFactory.class;
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.InitialStateViewFactory.class;
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.ViewStateViewFactory.class;
        case FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.ViewStateNameViewFactory.class;
        case FlowDesigner.diagram.edit.parts.ViewStateViewEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.ViewStateViewViewFactory.class;
        case FlowDesigner.diagram.edit.parts.EventEventEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.EventEventViewFactory.class;
        }
        return null;
    }

    /**
     * @generated
     */
    protected Class getEdgeViewClass(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
        IElementType elementType = getSemanticElementType(semanticAdapter);
        if (!FlowDesigner.diagram.providers.FlowDesignerElementTypes
                .isKnownElementType(elementType)
                || (!(elementType instanceof IHintedType))) {
            return null; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (elementTypeHint == null) {
            return null; // our hint is visual id and must be specified
        }
        if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
            return null; // if semantic hint is specified it should be the same as in element type
        }
        int visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(elementTypeHint);
        EObject domainElement = getSemanticElement(semanticAdapter);
        if (domainElement != null
                && visualID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getLinkWithClassVisualID(domainElement)) {
            return null; // visual id for link EClass should match visual id from element type
        }
        return getEdgeViewClass(visualID);
    }

    /**
     * @generated
     */
    protected Class getEdgeViewClass(int visualID) {
        switch (visualID) {
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return FlowDesigner.diagram.view.factories.EventViewFactory.class;
        }
        return null;
    }

    /**
     * @generated
     */
    private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        return (IElementType) semanticAdapter.getAdapter(IElementType.class);
    }
}
