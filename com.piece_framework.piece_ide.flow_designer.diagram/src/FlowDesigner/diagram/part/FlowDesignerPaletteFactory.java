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
        paletteContainer.add(createInitialState1CreationTool());
        paletteContainer.add(createFinalState2CreationTool());
        paletteContainer.add(createActionState3CreationTool());
        paletteContainer.add(createViewState4CreationTool());
        paletteContainer.add(createEvent5CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createInitialState1CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.InitialState_2007);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.InitialState1CreationTool_title,
                FlowDesigner.diagram.part.Messages.InitialState1CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .getImageDescriptor(FlowDesigner.diagram.providers.FlowDesignerElementTypes.InitialState_2007));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createFinalState2CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.FinalState_2008);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.FinalState2CreationTool_title,
                FlowDesigner.diagram.part.Messages.FinalState2CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .getImageDescriptor(FlowDesigner.diagram.providers.FlowDesignerElementTypes.FinalState_2008));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createActionState3CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2005);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.ActionState3CreationTool_title,
                FlowDesigner.diagram.part.Messages.ActionState3CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .getImageDescriptor(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2005));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createViewState4CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2006);
        NodeToolEntry entry = new NodeToolEntry(
                FlowDesigner.diagram.part.Messages.ViewState4CreationTool_title,
                FlowDesigner.diagram.part.Messages.ViewState4CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .getImageDescriptor(FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2006));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createEvent5CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types
                .add(FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4003);
        LinkToolEntry entry = new LinkToolEntry(
                FlowDesigner.diagram.part.Messages.Event5CreationTool_title,
                FlowDesigner.diagram.part.Messages.Event5CreationTool_desc,
                types);
        entry
                .setSmallIcon(FlowDesigner.diagram.providers.FlowDesignerElementTypes
                        .getImageDescriptor(FlowDesigner.diagram.providers.FlowDesignerElementTypes.Event_4003));
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
