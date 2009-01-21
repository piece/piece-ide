package FlowDesigner.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class FlowDesignerNavigatorLabelProvider extends LabelProvider implements
        ICommonLabelProvider, ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem
                && !isOwnView(((FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) element)
                        .getView())) {
            return;
        }
        label.setText(getText(element));
        label.setImage(getImage(element));
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup) {
            FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup group = (FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup) element;
            return FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance().getBundledImage(group.getIcon());
        }

        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) {
            FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem navigatorItem = (FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return super.getImage(element);
            }
            return getImage(navigatorItem.getView());
        }

        return super.getImage(element);
    }

    /**
     * @generated
     */
    public Image getImage(View view) {
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Diagram?http://flowdesigner/1.0?Flow", FlowDesigner.diagram.providers.FlowDesignerElementTypes.Flow_1000); //$NON-NLS-1$
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://flowdesigner/1.0?ActionState", FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2005); //$NON-NLS-1$
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://flowdesigner/1.0?ViewState", FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2006); //$NON-NLS-1$
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://flowdesigner/1.0?InitialState", FlowDesigner.diagram.providers.FlowDesignerElementTypes.InitialState_2007); //$NON-NLS-1$
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://flowdesigner/1.0?FinalState", FlowDesigner.diagram.providers.FlowDesignerElementTypes.FinalState_2008); //$NON-NLS-1$
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://flowdesigner/1.0?Event", FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4003); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getInstance().getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null
                && elementType != null
                && FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .isKnownElementType(elementType)) {
            image = FlowDesigner.diagram.providers.FlowDesignerElementTypes
                    .getImage(elementType);
            imageRegistry.put(key, image);
        }

        if (image == null) {
            image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
            imageRegistry.put(key, image);
        }
        return image;
    }

    /**
     * @generated
     */
    public String getText(Object element) {
        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup) {
            FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup group = (FlowDesigner.diagram.navigator.FlowDesignerNavigatorGroup) element;
            return group.getGroupName();
        }

        if (element instanceof FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) {
            FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem navigatorItem = (FlowDesigner.diagram.navigator.FlowDesignerNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return null;
            }
            return getText(navigatorItem.getView());
        }

        return super.getText(element);
    }

    /**
     * @generated
     */
    public String getText(View view) {
        if (view.getElement() != null && view.getElement().eIsProxy()) {
            return getUnresolvedDomainElementProxyText(view);
        }
        switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                .getVisualID(view)) {
        case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
            return getFlow_1000Text(view);
        case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
            return getActionState_2005Text(view);
        case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
            return getViewState_2006Text(view);
        case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
            return getInitialState_2007Text(view);
        case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
            return getFinalState_2008Text(view);
        case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
            return getEvent_4003Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getFlow_1000Text(View view) {
        return ""; //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private String getActionState_2005Text(View view) {
        IAdaptable hintAdapter = new FlowDesigner.diagram.providers.FlowDesignerParserProvider.HintAdapter(
                FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2005,
                (view.getElement() != null ? view.getElement() : view),
                FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getType(FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID));
        IParser parser = ParserService.getInstance().getParser(hintAdapter);

        if (parser != null) {
            return parser.getPrintString(hintAdapter, ParserOptions.NONE
                    .intValue());
        } else {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance().logError(
                            "Parser was not found for label " + 5003); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }

    }

    /**
     * @generated
     */
    private String getViewState_2006Text(View view) {
        IAdaptable hintAdapter = new FlowDesigner.diagram.providers.FlowDesignerParserProvider.HintAdapter(
                FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2006,
                (view.getElement() != null ? view.getElement() : view),
                FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getType(FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID));
        IParser parser = ParserService.getInstance().getParser(hintAdapter);

        if (parser != null) {
            return parser.getPrintString(hintAdapter, ParserOptions.NONE
                    .intValue());
        } else {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance().logError(
                            "Parser was not found for label " + 5004); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }

    }

    /**
     * @generated
     */
    private String getInitialState_2007Text(View view) {
        FlowDesigner.InitialState domainModelElement = (FlowDesigner.InitialState) view
                .getElement();
        if (domainModelElement != null) {
            return String.valueOf(domainModelElement.getInitialize());
        } else {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance()
                    .logError(
                            "No domain element for view with visualID = " + 2007); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getFinalState_2008Text(View view) {
        FlowDesigner.FinalState domainModelElement = (FlowDesigner.FinalState) view
                .getElement();
        if (domainModelElement != null) {
            return String.valueOf(domainModelElement.getFinalize());
        } else {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance()
                    .logError(
                            "No domain element for view with visualID = " + 2008); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEvent_4003Text(View view) {
        IAdaptable hintAdapter = new FlowDesigner.diagram.providers.FlowDesignerParserProvider.HintAdapter(
                FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4003,
                (view.getElement() != null ? view.getElement() : view),
                FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getType(FlowDesigner.diagram.edit.parts.EventNameEditPart.VISUAL_ID));
        IParser parser = ParserService.getInstance().getParser(hintAdapter);

        if (parser != null) {
            return parser.getPrintString(hintAdapter, ParserOptions.NONE
                    .intValue());
        } else {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                    .getInstance().logError(
                            "Parser was not found for label " + 6002); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }

    }

    /**
     * @generated
     */
    private String getUnknownElementText(View view) {
        return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @generated
     */
    private String getUnresolvedDomainElementProxyText(View view) {
        return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public String getDescription(Object anElement) {
        return null;
    }

    /**
     * @generated
     */
    private boolean isOwnView(View view) {
        return FlowDesigner.diagram.edit.parts.FlowEditPart.MODEL_ID
                .equals(FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                        .getModelID(view));
    }

}
