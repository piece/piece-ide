package FlowDesigner.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EventEditPart extends ConnectionNodeEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4004;

    /**
     * @generated
     */
    public EventEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(
                EditPolicyRoles.SEMANTIC_ROLE,
                new FlowDesigner.diagram.edit.policies.EventItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof FlowDesigner.diagram.edit.parts.EventEventEditPart) {
            ((FlowDesigner.diagram.edit.parts.EventEventEditPart) childEditPart)
                    .setLabel(getPrimaryShape().getFigureEventEventFigure());
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */

    protected Connection createConnectionFigure() {
        return new EventFigure();
    }

    /**
     * @generated
     */
    public EventFigure getPrimaryShape() {
        return (EventFigure) getFigure();
    }

    /**
     * @generated
     */
    public class EventFigure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureEventEventFigure;

        /**
         * @generated
         */
        public EventFigure() {

            createContents();
            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated
         */
        private void createContents() {

            fFigureEventEventFigure = new WrappingLabel();
            fFigureEventEventFigure.setText("<...>");

            this.add(fFigureEventEventFigure);

        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureEventEventFigure() {
            return fFigureEventEventFigure;
        }

    }

}
