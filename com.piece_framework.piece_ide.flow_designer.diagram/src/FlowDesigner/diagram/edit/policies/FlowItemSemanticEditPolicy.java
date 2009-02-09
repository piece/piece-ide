package FlowDesigner.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class FlowItemSemanticEditPolicy
        extends
        FlowDesigner.diagram.edit.policies.FlowDesignerBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (FlowDesigner.diagram.providers.FlowDesignerElementTypes.ActionState_2009 == req
                .getElementType()) {
            if (req.getContainmentFeature() == null) {
                req
                        .setContainmentFeature(FlowDesigner.FlowDesignerPackage.eINSTANCE
                                .getFlow_States());
            }
            return getGEFWrapper(new FlowDesigner.diagram.edit.commands.ActionStateCreateCommand(
                    req));
        }
        if (FlowDesigner.diagram.providers.FlowDesignerElementTypes.FinalState_2010 == req
                .getElementType()) {
            if (req.getContainmentFeature() == null) {
                req
                        .setContainmentFeature(FlowDesigner.FlowDesignerPackage.eINSTANCE
                                .getFlow_FinalState());
            }
            return getGEFWrapper(new FlowDesigner.diagram.edit.commands.FinalStateCreateCommand(
                    req));
        }
        if (FlowDesigner.diagram.providers.FlowDesignerElementTypes.InitialState_2011 == req
                .getElementType()) {
            if (req.getContainmentFeature() == null) {
                req
                        .setContainmentFeature(FlowDesigner.FlowDesignerPackage.eINSTANCE
                                .getFlow_InitialState());
            }
            return getGEFWrapper(new FlowDesigner.diagram.edit.commands.InitialStateCreateCommand(
                    req));
        }
        if (FlowDesigner.diagram.providers.FlowDesignerElementTypes.ViewState_2012 == req
                .getElementType()) {
            if (req.getContainmentFeature() == null) {
                req
                        .setContainmentFeature(FlowDesigner.FlowDesignerPackage.eINSTANCE
                                .getFlow_States());
            }
            return getGEFWrapper(new FlowDesigner.diagram.edit.commands.ViewStateCreateCommand(
                    req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @generated
     */
    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
                .getEditingDomain();
        return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
    }

    /**
     * @generated
     */
    private static class DuplicateAnythingCommand extends
            DuplicateEObjectsCommand {

        /**
         * @generated
         */
        public DuplicateAnythingCommand(
                TransactionalEditingDomain editingDomain,
                DuplicateElementsRequest req) {
            super(editingDomain, req.getLabel(), req
                    .getElementsToBeDuplicated(), req
                    .getAllDuplicatedElementsMap());
        }

    }

}
