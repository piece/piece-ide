// $Id$
package FlowDesigner.diagram.part;

import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;
import FlowDesigner.diagram.edit.parts.FlowEditPart;
import FlowDesigner.impl.FlowDesignerPackageImpl;

public class AdjustActivityEventHandlerAction implements IObjectActionDelegate {
    private FlowEditPart fFlowEditPart;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public void run(IAction action) {
        Flow flow = (Flow) ((View) fFlowEditPart.getModel()).getElement();
        CompositeTransactionalCommand transactionCommand =
            new CompositeTransactionalCommand(fFlowEditPart.getEditingDomain(),
                                              Messages.AdjustBuiltinEventHandlerAction_CommandLabel
                                              );
        for (NamedState state: flow.getStates()) {
            if (state.getActivity() != null
                && state.getActivity().startsWith(NamedState.ACTIVITY_PREFIX)
                ) {
                SetRequest request = new SetRequest(state,
                                                    FlowDesignerPackageImpl.eINSTANCE.getNamedState_Activity(),
                                                    NamedState.ACTIVITY_PREFIX + state.getName()
                                                    );
                transactionCommand.add(new SetValueCommand(request));
            }
        }
        fFlowEditPart.getViewer().getEditDomain()
            .getCommandStack().execute(new ICommandProxy(transactionCommand));
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
