package FlowDesigner.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class FlowDesignerPaletteFactory {

    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createFlowdesigner1Group());
    }

    /**
     * Creates "flowdesigner" palette tool group
     * @generated
     */
    private PaletteContainer createFlowdesigner1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(
                FlowDesigner.diagram.part.Messages.Flowdesigner1Group_title);
        paletteContainer.add(createFinalState1CreationTool());
        paletteContainer.add(createActionState2CreationTool());
        paletteContainer.add(createViewState3CreationTool());
        paletteContainer.add(createEvent4CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createFinalState1CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.FinalState_2010);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.FinalState1CreationTool_title,
                FlowDesigner.diagram.part.Messages.FinalState1CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .findImageDescriptor("icons/FinalState.gif")); //$NON-NLS-1$
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createActionState2CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2009);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.ActionState2CreationTool_title,
                FlowDesigner.diagram.part.Messages.ActionState2CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .findImageDescriptor("icons/ActionState.gif")); //$NON-NLS-1$
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createViewState3CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2012);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.ViewState3CreationTool_title,
                FlowDesigner.diagram.part.Messages.ViewState3CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .findImageDescriptor("icons/ViewState.gif")); //$NON-NLS-1$
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createEvent4CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4004);
        LinkToolEntry entry = new LinkToolEntry(
                FlowDesigner.diagram.part.Messages.Event4CreationTool_title,
                FlowDesigner.diagram.part.Messages.Event4CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .findImageDescriptor("icons/Event.gif")); //$NON-NLS-1$
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private static class NodeToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List elementTypes;

        /**
         * @generated
         */
        private NodeToolEntry(String title, String description,
                List elementTypes) {
            super(title, description, null, null);
            this.elementTypes = elementTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }

    /**
     * @generated
     */
    private static class LinkToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List relationshipTypes;

        /**
         * @generated
         */
        private LinkToolEntry(String title, String description,
                List relationshipTypes) {
            super(title, description, null, null);
            this.relationshipTypes = relationshipTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }
}
