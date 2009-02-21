// $Id$
package FlowDesigner.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

public class AdjustActivityEventHandlerCommand extends EditElementCommand {
    public AdjustActivityEventHandlerCommand(String label,
                                            EObject elementToEdit,
                                            IEditCommandRequest request
                                            ) {
        super(label, elementToEdit, request);
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
                                                IAdaptable info
                                                ) throws ExecutionException {
        Flow flow = (Flow) getElementToEdit();
        for (NamedState state: flow.getStates()) {
            if (state.getActivity() != null
                && state.getActivity().startsWith("on")
                ) {
                state.setActivity("on" + state.getName()); //$NON-NLS-1$
            }
        }

        return CommandResult.newOKCommandResult();
    }
}
