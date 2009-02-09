package FlowDesigner.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class FlowDesignerEditPartFactory implements EditPartFactory {

    /**
     * @generated
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View) model;
            switch (FlowDesigner.diagram.part.FlowDesignerVisualIDRegistry
                    .getVisualID(view)) {

            case FlowDesigner.diagram.edit.parts.FlowEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.FlowEditPart(view);

            case FlowDesigner.diagram.edit.parts.ActionStateEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.ActionStateEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.ActionStateNameEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.ActionStateNameEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.FinalStateEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.FinalStateEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.InitialStateEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.InitialStateEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.ViewStateEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.ViewStateEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.ViewStateNameEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.ViewStateNameEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.ViewStateViewEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.ViewStateViewEditPart(
                        view);

            case FlowDesigner.diagram.edit.parts.EventEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.EventEditPart(view);

            case FlowDesigner.diagram.edit.parts.EventEventEditPart.VISUAL_ID:
                return new FlowDesigner.diagram.edit.parts.EventEventEditPart(
                        view);
            }
        }
        return createUnrecognizedEditPart(context, model);
    }

    /**
     * @generated
     */
    private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
        // Handle creation of unrecognized child node EditParts here
        return null;
    }

    /**
     * @generated
     */
    public static CellEditorLocator getTextCellEditorLocator(
            ITextAwareEditPart source) {
        if (source.getFigure() instanceof WrappingLabel)
            return new TextCellEditorLocator((WrappingLabel) source.getFigure());
        else {
            return new LabelCellEditorLocator((Label) source.getFigure());
        }
    }

    /**
     * @generated
     */
    static private class TextCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private WrappingLabel wrapLabel;

        /**
         * @generated
         */
        public TextCellEditorLocator(WrappingLabel wrapLabel) {
            this.wrapLabel = wrapLabel;
        }

        /**
         * @generated
         */
        public WrappingLabel getWrapLabel() {
            return wrapLabel;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getWrapLabel().getTextBounds().getCopy();
            getWrapLabel().translateToAbsolute(rect);
            if (getWrapLabel().isTextWrapOn()
                    && getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width,
                        SWT.DEFAULT)));
            } else {
                int avr = FigureUtilities.getFontMetrics(text.getFont())
                        .getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                        SWT.DEFAULT)).expand(avr * 2, 0));
            }
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    /**
     * @generated
     */
    private static class LabelCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private Label label;

        /**
         * @generated
         */
        public LabelCellEditorLocator(Label label) {
            this.label = label;
        }

        /**
         * @generated
         */
        public Label getLabel() {
            return label;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getLabel().getTextBounds().getCopy();
            getLabel().translateToAbsolute(rect);
            int avr = FigureUtilities.getFontMetrics(text.getFont())
                    .getAverageCharWidth();
            rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                    SWT.DEFAULT)).expand(avr * 2, 0));
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}
