package FlowDesigner.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 */
public class FlowDesignerCreationWizard extends Wizard implements INewWizard {

    /**
     * @generated
     */
    private IWorkbench workbench;

    /**
     * @generated
     */
    protected IStructuredSelection selection;

    /**
     * @generated
     */
    protected FlowDesigner.diagram.part.FlowDesignerCreationWizardPage diagramModelFilePage;

    /**
     * @generated
     */
    protected FlowDesigner.diagram.part.FlowDesignerCreationWizardPage domainModelFilePage;

    /**
     * @generated
     */
    protected Resource diagram;

    /**
     * @generated
     */
    private boolean openNewlyCreatedDiagramEditor = true;

    /**
     * @generated
     */
    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * @generated
     */
    public IStructuredSelection getSelection() {
        return selection;
    }

    /**
     * @generated
     */
    public final Resource getDiagram() {
        return diagram;
    }

    /**
     * @generated
     */
    public final boolean isOpenNewlyCreatedDiagramEditor() {
        return openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void setOpenNewlyCreatedDiagramEditor(
            boolean openNewlyCreatedDiagramEditor) {
        this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizardTitle);
        setDefaultPageImageDescriptor(FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getBundledImageDescriptor("icons/wizban/NewFlowDesignerWizard.gif")); //$NON-NLS-1$
        setNeedsProgressMonitor(true);
    }

    /**
     * @generated
     */
    public void addPages() {
        diagramModelFilePage = new FlowDesigner.diagram.part.FlowDesignerCreationWizardPage(
                "DiagramModelFile", getSelection(), "flow_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
        diagramModelFilePage
                .setTitle(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DiagramModelFilePageTitle);
        diagramModelFilePage
                .setDescription(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DiagramModelFilePageDescription);
        addPage(diagramModelFilePage);

        domainModelFilePage = new FlowDesigner.diagram.part.FlowDesignerCreationWizardPage(
                "DomainModelFile", getSelection(), "flow") { //$NON-NLS-1$ //$NON-NLS-2$

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length()
                            - ".flow_diagram".length()); //$NON-NLS-1$
                    setFileName(FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil
                            .getUniqueFileName(getContainerFullPath(),
                                    fileName, "flow")); //$NON-NLS-1$
                }
                super.setVisible(visible);
            }
        };
        domainModelFilePage
                .setTitle(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage
                .setDescription(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor)
                    throws CoreException, InterruptedException {
                diagram = FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil
                        .createDiagram(diagramModelFilePage.getURI(),
                                domainModelFilePage.getURI(), monitor);
                if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
                    try {
                        FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil
                                .openDiagram(diagram);
                    } catch (PartInitException e) {
                        ErrorDialog
                                .openError(
                                        getContainer().getShell(),
                                        FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizardOpenEditorError,
                                        null, e.getStatus());
                    }
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog
                        .openError(
                                getContainer().getShell(),
                                FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizardCreationError,
                                null, ((CoreException) e.getTargetException())
                                        .getStatus());
            } else {
                FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .getInstance()
                        .logError(
                                "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
            }
            return false;
        }
        return diagram != null;
    }
}
