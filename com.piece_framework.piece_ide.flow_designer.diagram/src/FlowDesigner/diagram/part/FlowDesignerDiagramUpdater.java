package FlowDesigner.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FlowDesignerDiagramUpdater {

    /**
     * @generated
     */
    public static List getSemanticChildren(View view) {
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            return getFlow_1000SemanticChildren(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getFlow_1000SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        FlowDesigner.Flow modelElement = (FlowDesigner.Flow) view.getElement();
        List result = new LinkedList();
        for (Iterator it = modelElement.getStates().iterator(); it.hasNext();) {
            FlowDesigner.NamedState childElement = (FlowDesigner.NamedState) it
                    .next();
            int visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getNodeVisualID(view, childElement);
            if (visualID == FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID) {
                result
                        .add(new FlowDesigner.diagram.part.FlowDesignerNodeDescriptor(
                                childElement, visualID));
                continue;
            }
            if (visualID == FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID) {
                result
                        .add(new FlowDesigner.diagram.part.FlowDesignerNodeDescriptor(
                                childElement, visualID));
                continue;
            }
        }
        {
            FlowDesigner.FinalState childElement = modelElement.getFinalState();
            int visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getNodeVisualID(view, childElement);
            if (visualID == FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID) {
                result
                        .add(new FlowDesigner.diagram.part.FlowDesignerNodeDescriptor(
                                childElement, visualID));
            }
        }
        {
            FlowDesigner.InitialState childElement = modelElement
                    .getInitialState();
            int visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getNodeVisualID(view, childElement);
            if (visualID == FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID) {
                result
                        .add(new FlowDesigner.diagram.part.FlowDesignerNodeDescriptor(
                                childElement, visualID));
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List getContainedLinks(View view) {
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            return getFlow_1000ContainedLinks(view);
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return getActionState_2009ContainedLinks(view);
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return getFinalState_2010ContainedLinks(view);
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return getInitialState_2011ContainedLinks(view);
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return getViewState_2012ContainedLinks(view);
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return getEvent_4004ContainedLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getIncomingLinks(View view) {
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return getActionState_2009IncomingLinks(view);
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return getFinalState_2010IncomingLinks(view);
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return getInitialState_2011IncomingLinks(view);
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return getViewState_2012IncomingLinks(view);
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return getEvent_4004IncomingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getOutgoingLinks(View view) {
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return getActionState_2009OutgoingLinks(view);
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return getFinalState_2010OutgoingLinks(view);
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return getInitialState_2011OutgoingLinks(view);
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return getViewState_2012OutgoingLinks(view);
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return getEvent_4004OutgoingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getFlow_1000ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getActionState_2009ContainedLinks(View view) {
        FlowDesigner.ActionState modelElement = (FlowDesigner.ActionState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getFinalState_2010ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getInitialState_2011ContainedLinks(View view) {
        FlowDesigner.InitialState modelElement = (FlowDesigner.InitialState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getViewState_2012ContainedLinks(View view) {
        FlowDesigner.ViewState modelElement = (FlowDesigner.ViewState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getEvent_4004ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getActionState_2009IncomingLinks(View view) {
        FlowDesigner.ActionState modelElement = (FlowDesigner.ActionState) view
                .getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Event_4004(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getFinalState_2010IncomingLinks(View view) {
        FlowDesigner.FinalState modelElement = (FlowDesigner.FinalState) view
                .getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Event_4004(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getInitialState_2011IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getViewState_2012IncomingLinks(View view) {
        FlowDesigner.ViewState modelElement = (FlowDesigner.ViewState) view
                .getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Event_4004(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getEvent_4004IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getActionState_2009OutgoingLinks(View view) {
        FlowDesigner.ActionState modelElement = (FlowDesigner.ActionState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getFinalState_2010OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getInitialState_2011OutgoingLinks(View view) {
        FlowDesigner.InitialState modelElement = (FlowDesigner.InitialState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getViewState_2012OutgoingLinks(View view) {
        FlowDesigner.ViewState modelElement = (FlowDesigner.ViewState) view
                .getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Event_4004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getEvent_4004OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_Event_4004(
            FlowDesigner.Source container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getEvents().iterator(); links.hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof FlowDesigner.Event) {
                continue;
            }
            FlowDesigner.Event link = (FlowDesigner.Event) linkObject;
            if (FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            FlowDesigner.Target dst = link.getNextState();
            result
                    .add(new FlowDesigner.diagram.part.FlowDesignerLinkDescriptor(
                            container,
                            dst,
                            link,
                            FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4004,
                            FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_Event_4004(
            FlowDesigner.Target target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
                    .next();
            if (setting.getEStructuralFeature() != FlowDesigner.FlowDesignerPackage.eINSTANCE
                    .getEvent_NextState()
                    || false == setting.getEObject() instanceof FlowDesigner.Event) {
                continue;
            }
            FlowDesigner.Event link = (FlowDesigner.Event) setting.getEObject();
            if (FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID != FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            if (false == link.eContainer() instanceof FlowDesigner.Source) {
                continue;
            }
            FlowDesigner.Source container = (FlowDesigner.Source) link
                    .eContainer();
            result
                    .add(new FlowDesigner.diagram.part.FlowDesignerLinkDescriptor(
                            container,
                            target,
                            link,
                            FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4004,
                            FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID));

        }
        return result;
    }

}
