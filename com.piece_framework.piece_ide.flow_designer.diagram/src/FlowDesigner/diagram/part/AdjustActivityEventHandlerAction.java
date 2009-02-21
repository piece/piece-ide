// $Id$
package FlowDesigner.diagram.part;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import FlowDesigner.Flow;
import FlowDesigner.diagram.edit.commands.AdjustActivityEventHandlerCommand;
import FlowDesigner.diagram.edit.parts.FlowEditPart;

public class AdjustActivityEventHandlerAction implements IObjectActionDelegate {
    private FlowEditPart fFlowEditPart;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public void run(IAction action) {
        Flow flow = (Flow) ((View) fFlowEditPart.getModel()).getElement();
        ConfigureRequest request = new ConfigureRequest(flow, null);
        AdjustActivityEventHandlerCommand command = new AdjustActivityEventHandlerCommand(Messages.AdjustBuiltinEventHandlerAction_CommandLabel, flow, request);
        try {
            command.execute(null, null);
        } catch (ExecutionException e) {
            MessageDialog.openError(Display.getCurrent().getActiveShell(),
                                    Messages.MessageDialog_ErrorTitle,
                                    e.getMessage()
                                    );
            e.printStackTrace();
        }
    }

    public void selectionChanged(IAction action, ISelection selection) {
        fFlowEditPart = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.size() == 1
                && structuredSelection.getFirstElement() instanceof FlowEditPart
                ) {
                fFlowEditPart = (FlowEditPart) structuredSelection.getFirstElement();
            }
        }
        action.setEnabled(isEnabled());
    }

    private boolean isEnabled() {
        return fFlowEditPart != null;
    }
}
