package FlowDesigner.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

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
     * @generated
     */
    protected IParser createActionStateName_5003Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getNamedState_Name(), };
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features);
        return parser;
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
     * @generated
     */
    protected IParser createViewStateName_5004Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getNamedState_Name(), };
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features);
        return parser;
    }

    /**
     * @generated
     */
    private IParser eventName_6002Parser;

    /**
     * @generated
     */
    private IParser getEventName_6002Parser() {
        if (eventName_6002Parser == null) {
            eventName_6002Parser = createEventName_6002Parser();
        }
        return eventName_6002Parser;
    }

    /**
     * @generated
     */
    protected IParser createEventName_6002Parser() {
        EAttribute[] features = new EAttribute[] { FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getEvent_Name(), };
        FlowDesigner.diagram.parsers.MessageFormatParser parser = new FlowDesigner.diagram.parsers.MessageFormatParser(
                features);
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
        case FlowDesigner.diagram.edit.parts.EventNameEditPart.VISUAL_ID:
            return getEventName_6002Parser();
        }
        return null;
    }

    /**
     * @generated
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
