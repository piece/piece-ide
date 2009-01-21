package FlowDesigner.diagram.part;

import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class FlowDesignerLoadResourceAction implements IObjectActionDelegate {

    /**
     * @generated
     */
    private FlowDesigner.diagram.edit.parts.FlowEditPart mySelectedElement;

    /**
     * @generated
     */
    private Shell myShell;

    /**
     * @generated
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        myShell = targetPart.getSite().getShell();
    }

    /**
     * @generated
     */
    public void run(IAction action) {
        LoadResourceAction.LoadResourceDialog loadResourceDialog = new LoadResourceAction.LoadResourceDialog(
                myShell, mySelectedElement.getEditingDomain());
        loadResourceDialog.open();
    }

    /**
     * @generated
     */
    public void selectionChanged(IAction action, ISelection selection) {
        mySelectedElement = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.size() == 1
                    && structuredSelection.getFirstElement() instanceof FlowDesigner.diagram.edit.parts.FlowEditPart) {
                mySelectedElement = (FlowDesigner.diagram.edit.parts.FlowEditPart) structuredSelection
                        .getFirstElement();
            }
        }
        action.setEnabled(isEnabled());
    }

    /**
     * @generated
     */
    private boolean isEnabled() {
        return mySelectedElement != null;
    }

}
