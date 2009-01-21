package FlowDesigner.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FinalStateCreateCommand extends CreateElementCommand {

    /**
     * @generated
     */
    public FinalStateCreateCommand(CreateElementRequest req) {
        super(req);
    }

    /**
     * @generated
     */
    protected EObject getElementToEdit() {
        EObject container = ((CreateElementRequest) getRequest())
                .getContainer();
        if (container instanceof View) {
            container = ((View) container).getElement();
        }
        return container;
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        FlowDesigner.Flow container = (FlowDesigner.Flow) getElementToEdit();
        if (container.getFinalState() != null) {
            return false;
        }
        return true;
    }

    /**
     * @generated
     */
    protected EClass getEClassToEdit() {
        return FlowDesigner.FlowDesignerPackage.eINSTANCE.getFlow();
    }

}
