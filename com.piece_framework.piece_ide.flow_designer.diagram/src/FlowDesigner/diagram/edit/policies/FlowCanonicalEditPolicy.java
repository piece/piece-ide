package FlowDesigner.diagram.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FlowCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

    /**
     * @generated
     */
    Set myFeaturesToSynchronize;

    /**
     * @generated
     */
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        List result = new LinkedList();
        for (Iterator it = FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                .getFlow_1000SemanticChildren(viewObject).iterator(); it
                .hasNext();) {
            result
                    .add(((FlowDesigner.diagram.part.FlowDesignerNodeDescriptor) it
                            .next()).getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean shouldDeleteView(View view) {
        return true;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection semanticChildren, final View view) {
        int visualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view);
        switch (visualID) {
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            if (!semanticChildren.contains(view.getElement())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @generated
     */
    protected String getDefaultFactoryHint() {
        return null;
    }

    /**
     * @generated
     */
    protected Set getFeaturesToSynchronize() {
        if (myFeaturesToSynchronize == null) {
            myFeaturesToSynchronize = new HashSet();
            myFeaturesToSynchronize
                    .add(FlowDesigner.FlowDesignerPackage.eINSTANCE
                            .getFlow_States());
            myFeaturesToSynchronize
                    .add(FlowDesigner.FlowDesignerPackage.eINSTANCE
                            .getFlow_FinalState());
            myFeaturesToSynchronize
                    .add(FlowDesigner.FlowDesignerPackage.eINSTANCE
                            .getFlow_InitialState());
        }
        return myFeaturesToSynchronize;
    }

    /**
     * @generated
     */
    protected List getSemanticConnectionsList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    protected EObject getSourceElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected EObject getTargetElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected boolean shouldIncludeConnection(Edge connector,
            Collection children) {
        return false;
    }

    /**
     * @generated
     */
    protected void refreshSemantic() {
        List createdViews = new LinkedList();
        createdViews.addAll(refreshSemanticChildren());
        List createdConnectionViews = new LinkedList();
        createdConnectionViews.addAll(refreshSemanticConnections());
        createdConnectionViews.addAll(refreshConnections());

        if (createdViews.size() > 1) {
            // perform a layout of the container
            DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
                    .getEditingDomain(), createdViews, host());
            executeCommand(new ICommandProxy(layoutCmd));
        }

        createdViews.addAll(createdConnectionViews);
        makeViewsImmutable(createdViews);
    }

    /**
     * @generated
     */
    private Diagram getDiagram() {
        return ((View) getHost().getModel()).getDiagram();
    }

    /**
     * @generated
     */
    private Collection refreshConnections() {
        Map domain2NotationMap = new HashMap();
        Collection linkDescriptors = collectAllLinks(getDiagram(),
                domain2NotationMap);
        Collection existingLinks = new LinkedList(getDiagram().getEdges());
        for (Iterator linksIterator = existingLinks.iterator(); linksIterator
                .hasNext();) {
            Edge nextDiagramLink = (Edge) linksIterator.next();
            int diagramLinkVisualID = FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() != null
                        && nextDiagramLink.getTarget() != null) {
                    linksIterator.remove();
                }
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            for (Iterator LinkDescriptorsIterator = linkDescriptors.iterator(); LinkDescriptorsIterator
                    .hasNext();) {
                FlowDesigner.diagram.part.FlowDesignerLinkDescriptor nextLinkDescriptor = (FlowDesigner.diagram.part.FlowDesignerLinkDescriptor) LinkDescriptorsIterator
                        .next();
                if (diagramLinkObject == nextLinkDescriptor.getModelElement()
                        && diagramLinkSrc == nextLinkDescriptor.getSource()
                        && diagramLinkDst == nextLinkDescriptor
                                .getDestination()
                        && diagramLinkVisualID == nextLinkDescriptor
                                .getVisualID()) {
                    linksIterator.remove();
                    LinkDescriptorsIterator.remove();
                }
            }
        }
        deleteViews(existingLinks.iterator());
        return createConnections(linkDescriptors, domain2NotationMap);
    }

    /**
     * @generated
     */
    private Collection collectAllLinks(View view, Map domain2NotationMap) {
        if (!FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getModelID(view))) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getFlow_1000ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getActionState_2009ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getFinalState_2010ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getInitialState_2011ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getViewState_2012ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result
                        .addAll(FlowDesigner.diagram.part.FlowDesignerDiagramUpdater
                                .getEvent_4004ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        }
        for (Iterator children = view.getChildren().iterator(); children
                .hasNext();) {
            result.addAll(collectAllLinks((View) children.next(),
                    domain2NotationMap));
        }
        for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
            result.addAll(collectAllLinks((View) edges.next(),
                    domain2NotationMap));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection createConnections(Collection linkDescriptors,
            Map domain2NotationMap) {
        List adapters = new LinkedList();
        for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                .hasNext();) {
            final FlowDesigner.diagram.part.FlowDesignerLinkDescriptor nextLinkDescriptor = (FlowDesigner.diagram.part.FlowDesignerLinkDescriptor) linkDescriptorsIterator
                    .next();
            EditPart sourceEditPart = getEditPart(nextLinkDescriptor
                    .getSource(), domain2NotationMap);
            EditPart targetEditPart = getEditPart(nextLinkDescriptor
                    .getDestination(), domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) {
                continue;
            }
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
                    nextLinkDescriptor.getSemanticAdapter(), null,
                    ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost())
                            .getDiagramPreferencesHint());
            CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(
                    descriptor);
            ccr.setType(RequestConstants.REQ_CONNECTION_START);
            ccr.setSourceEditPart(sourceEditPart);
            sourceEditPart.getCommand(ccr);
            ccr.setTargetEditPart(targetEditPart);
            ccr.setType(RequestConstants.REQ_CONNECTION_END);
            Command cmd = targetEditPart.getCommand(ccr);
            if (cmd != null && cmd.canExecute()) {
                executeCommand(cmd);
                IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
                if (viewAdapter != null) {
                    adapters.add(viewAdapter);
                }
            }
        }
        return adapters;
    }

    /**
     * @generated
     */
    private EditPart getEditPart(EObject domainModelElement,
            Map domain2NotationMap) {
        View view = (View) domain2NotationMap.get(domainModelElement);
        if (view != null) {
            return (EditPart) getHost().getViewer().getEditPartRegistry().get(
                    view);
        }
        return null;
    }
}
