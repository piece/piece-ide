package FlowDesigner.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.InitialState;
import FlowDesigner.diagram.parsers.EventEventParser;
import FlowDesigner.diagram.parsers.NamedStateNameParser;

/**
 * @generated
 */
public class FlowDesignerParserProvider extends AbstractProvider implements
        IParserProvider {

    /**
     * @generated
     */
    private IParser actionStateName_5003Parser;

    /**
     * @generated
     */
    private IParser getActionStateName_5003Parser() {
        if (actionStateName_5003Parser == null) {
            actionStateName_5003Parser = createActionStateName_5003Parser();
        }
        return actionStateName_5003Parser;
    }

    /**
     * @generated NOT
     */
    protected IParser createActionStateName_5003Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getNamedState_Name(), };
        return new NamedStateNameParser(features);
    }

    /**
     * @generated
     */
    private IParser viewStateName_5004Parser;

    /**
     * @generated
     */
    private IParser getViewStateName_5004Parser() {
        if (viewStateName_5004Parser == null) {
            viewStateName_5004Parser = createViewStateName_5004Parser();
        }
        return viewStateName_5004Parser;
    }

    /**
     * @generated NOT
     */
    protected IParser createViewStateName_5004Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getNamedState_Name(), };
        return new NamedStateNameParser(features);
    }

    /**
     * @generated
     */
    private IParser viewStateView_5005Parser;

    /**
     * @generated
     */
    private IParser getViewStateView_5005Parser() {
        if (viewStateView_5005Parser == null) {
            viewStateView_5005Parser = createViewStateView_5005Parser();
        }
        return viewStateView_5005Parser;
    }

    /**
     * @generated
     */
    protected IParser createViewStateView_5005Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getViewState_View(), };
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features);
        return parser;
    }

    /**
     * @generated
     */
    private IParser eventEvent_6002Parser;

    /**
     * @generated
     */
    private IParser getEventEvent_6002Parser() {
        if (eventEvent_6002Parser == null) {
            eventEvent_6002Parser = createEventEvent_6002Parser();
        }
        return eventEvent_6002Parser;
    }

    /**
     * @generated NOT
     */
    protected IParser createEventEvent_6002Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getEvent_Event(), };
        return new EventEventParser(features);
    }

    private IParser fAnonymouseStateParser;

    private IParser getAnonymouseStateParser() {
        if (fAnonymouseStateParser == null) {
            fAnonymouseStateParser = createAnonymouseStateParser();
        }
        return fAnonymouseStateParser;
    }

    private IParser createAnonymouseStateParser() {
        EAttribute[] features = new EAttribute[] {};
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features) {
            @Override
            public String getPrintString(IAdaptable adapter, int flags) {
                if (adapter instanceof EObjectAdapter) {
                    EObject realObject = (EObject) ((EObjectAdapter) adapter)
                            .getRealObject();
                    return realObject.eClass().getName();
                }
                return super.getPrintString(adapter, flags);
            }
        };
        return parser;
    }

    private IParser fFlowParser;

    private IParser getFlowParser() {
        if (fFlowParser == null) {
            fFlowParser = createFlowParser();
        }
        return fFlowParser;
    }

    private IParser createFlowParser() {
        EAttribute[] features = new EAttribute[] {};
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features) {
            @Override
            public String getPrintString(IAdaptable adapter, int flags) {
                if (adapter instanceof EObjectAdapter) {
                    EObject realObject = (EObject) ((EObjectAdapter) adapter)
                            .getRealObject();
                    if (realObject instanceof Flow) {
                        URI uri = realObject.eResource().getURI();
                        String flowName = uri.lastSegment().replaceFirst(
                                "." + uri.fileExtension(), "");
                        return flowName;
                    }
                }
                return super.getPrintString(adapter, flags);
            }
        };
        return parser;
    }

    /**
     * @generated
     */
    protected IParser getParser(int visualID) {
        switch (visualID) {
        case FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID:
            return getActionStateName_5003Parser();
        case FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID:
            return getViewStateName_5004Parser();
        case FlowDesigner.diagram.edit.parts.WrappingLabelEditPart.VISUAL_ID:
            return getViewStateView_5005Parser();
        case FlowDesigner.diagram.edit.parts.EventEventEditPart.VISUAL_ID:
            return getEventEvent_6002Parser();
        }
        return null;
    }

    /**
     * @generated NOT
     */
    public IParser getParser(IAdaptable hint) {
        String vid = (String) hint.getAdapter(String.class);
        if (vid != null) {
            return getParser(FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(vid));
        }
        View view = (View) hint.getAdapter(View.class);
        if (view != null) {
            return getParser(FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(view));
        }
        if (hint instanceof EObjectAdapter) {
            EObject realObject = (EObject) ((EObjectAdapter) hint)
                    .getRealObject();
            if (realObject instanceof InitialState
                    || realObject instanceof FinalState) {
                return getAnonymouseStateParser();
            } else if (realObject instanceof Flow) {
                return getFlowParser();
            }
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean provides(IOperation operation) {
        if (operation instanceof GetParserOperation) {
            IAdaptable hint = ((GetParserOperation) operation).getHint();
            if (FlowDesigner.diagram.providers.FlowDesignerElementTypes
                    .getElement(hint) == null) {
                return false;
            }
            return getParser(hint) != null;
        }
        return false;
    }

    /**
     * @generated
     */
    public static class HintAdapter extends ParserHintAdapter {

        /**
         * @generated
         */
        private final IElementType elementType;

        /**
         * @generated
         */
        public HintAdapter(IElementType type, EObject object, String parserHint) {
            super(object, parserHint);
            assert type != null;
            elementType = type;
        }

        /**
         * @generated
         */
        public Object getAdapter(Class adapter) {
            if (IElementType.class.equals(adapter)) {
                return elementType;
            }
            return super.getAdapter(adapter);
        }
    }

}
