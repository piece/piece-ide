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

public class AdjustBuiltinEventHandlerCommand extends EditElementCommand {
    public AdjustBuiltinEventHandlerCommand(String label,
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
            if (state.getEntry() != null) {
                state.setEntry("doEntryOn" + state.getName());
            }
            if (state.getActivity() != null) {
                state.setActivity("doActivtyOn" + state.getName());
            }
            if (state.getExit() != null) {
                state.setExit("doExitOn" + state.getName());
            }
        }

        return CommandResult.newOKCommandResult();
    }
}
