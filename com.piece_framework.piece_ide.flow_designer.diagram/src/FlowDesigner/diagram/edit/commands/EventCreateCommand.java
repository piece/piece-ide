package FlowDesigner.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.InitialState;
import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

/**
 * @generated
 */
public class EventCreateCommand extends CreateElementCommand {

    /**
     * @generated
     */
    private final EObject source;

    /**
     * @generated
     */
    private final EObject target;

    /**
     * @generated
     */
    public EventCreateCommand(CreateRelationshipRequest request,
            EObject source, EObject target) {
        super(request);
        this.source = source;
        this.target = target;
        if (request.getContainmentFeature() == null) {
            setContainmentFeature(FlowDesigner.FlowDesignerPackage.eINSTANCE
                    .getSource_Events());
        }

        super.setElementToEdit(source);
    }

    /**
     * @generated NOT
     */
    public boolean canExecute() {
        if (source == null && target == null) {
            return false;
        }
        if (source != null && false == source instanceof FlowDesigner.Source) {
            return false;
        }
        if (target != null && false == target instanceof FlowDesigner.Target) {
            return false;
        }
        if (getSource() == null) {
            return true; // link creation is in progress; source is not defined yet
        }

        if (getSource() != null && getTarget() != null) {
            boolean canBeSource = getSource().canBeSource(getTarget());
            boolean canBeTarget = getTarget().canBeTarget(getSource());
            if (canBeSource == false || canBeTarget == false) {
                return false;
            }
        }

        // target may be null here but it's possible to check constraint
        return FlowDesigner.diagram.edit.policies.FlowDesignerBaseItemSemanticEditPolicy.LinkConstraints
                .canCreateEvent_4004(getSource(), getTarget());
    }

    /**
     * @generated
     */
    protected EObject doDefaultElementCreation() {
        FlowDesigner.Event newElement = FlowDesigner.FlowDesignerFactory.eINSTANCE
                .createEvent();
        getSource().getEvents().add(newElement);
        newElement.setNextState(getTarget());
        return newElement;
    }

    /**
     * @generated
     */
    protected EClass getEClassToEdit() {
        return FlowDesigner.FlowDesignerPackage.eINSTANCE.getSource();
    }

    /**
     * @generated
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException(
                    "Invalid arguments in create link command"); //$NON-NLS-1$
        }
        return super.doExecuteWithResult(monitor, info);
    }

    /**
     * @generated
     */
    protected ConfigureRequest createConfigureRequest() {
        ConfigureRequest request = super.createConfigureRequest();
        request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
        request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
        return request;
    }

    /**
     * @generated
     */
    protected void setElementToEdit(EObject element) {
        throw new UnsupportedOperationException();
    }

    /**
     * @generated
     */
    protected FlowDesigner.Source getSource() {
        return (FlowDesigner.Source) source;
    }

    /**
     * @generated
     */
    protected FlowDesigner.Target getTarget() {
        return (FlowDesigner.Target) target;
    }
}
