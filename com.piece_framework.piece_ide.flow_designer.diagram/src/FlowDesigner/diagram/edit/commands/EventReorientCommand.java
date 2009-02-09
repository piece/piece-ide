package FlowDesigner.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class EventReorientCommand extends EditElementCommand {

    /**
     * @generated
     */
    private final int reorientDirection;

    /**
     * @generated
     */
    private final EObject oldEnd;

    /**
     * @generated
     */
    private final EObject newEnd;

    /**
     * @generated
     */
    public EventReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), request);
        reorientDirection = request.getDirection();
        oldEnd = request.getOldRelationshipEnd();
        newEnd = request.getNewRelationshipEnd();
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        if (false == getElementToEdit() instanceof FlowDesigner.Event) {
            return false;
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return canReorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return canReorientTarget();
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean canReorientSource() {
        if (!(oldEnd instanceof FlowDesigner.Source && newEnd instanceof FlowDesigner.Source)) {
            return false;
        }
        FlowDesigner.Target target = getLink().getNextState();
        return FlowDesigner.diagram.edit.policies.FlowDesignerBaseItemSemanticEditPolicy.LinkConstraints
                .canExistEvent_4004(getNewSource(), target);
    }

    /**
     * @generated
     */
    protected boolean canReorientTarget() {
        if (!(oldEnd instanceof FlowDesigner.Target && newEnd instanceof FlowDesigner.Target)) {
            return false;
        }
        if (!(getLink().eContainer() instanceof FlowDesigner.Source)) {
            return false;
        }
        FlowDesigner.Source source = (FlowDesigner.Source) getLink()
                .eContainer();
        return FlowDesigner.diagram.edit.policies.FlowDesignerBaseItemSemanticEditPolicy.LinkConstraints
                .canExistEvent_4004(source, getNewTarget());
    }

    /**
     * @generated
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException(
                    "Invalid arguments in reorient link command"); //$NON-NLS-1$
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return reorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return reorientTarget();
        }
        throw new IllegalStateException();
    }

    /**
     * @generated
     */
    protected CommandResult reorientSource() throws ExecutionException {
        getOldSource().getEvents().remove(getLink());
        getNewSource().getEvents().add(getLink());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected CommandResult reorientTarget() throws ExecutionException {
        getLink().setNextState(getNewTarget());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected FlowDesigner.Event getLink() {
        return (FlowDesigner.Event) getElementToEdit();
    }

    /**
     * @generated
     */
    protected FlowDesigner.Source getOldSource() {
        return (FlowDesigner.Source) oldEnd;
    }

    /**
     * @generated
     */
    protected FlowDesigner.Source getNewSource() {
        return (FlowDesigner.Source) newEnd;
    }

    /**
     * @generated
     */
    protected FlowDesigner.Target getOldTarget() {
        return (FlowDesigner.Target) oldEnd;
    }

    /**
     * @generated
     */
    protected FlowDesigner.Target getNewTarget() {
        return (FlowDesigner.Target) newEnd;
    }
}
