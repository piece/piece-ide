package FlowDesigner.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import FlowDesigner.diagram.resource.DiagramFile;

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
     * @generated NOT
     */
    public void addPages() {
        domainModelFilePage = new FlowDesigner.diagram.part.FlowDesignerCreationWizardPage(
                "DomainModelFile", getSelection(), "flow"); //$NON-NLS-1$ //$NON-NLS-2$
        domainModelFilePage
                .setTitle(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage
                .setDescription(FlowDesigner.diagram.part.Messages.FlowDesignerCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated NOT
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor)
                    throws CoreException, InterruptedException {
                IFile flowFile = ResourcesPlugin.getWorkspace().getRoot()
                        .getFile(domainModelFilePage.getFilePath());
                DiagramFile diagramFile = new DiagramFile(flowFile);
                URI diagramURI = URI.createPlatformResourceURI(diagramFile
                        .getFile().getFullPath().toString(), false);

                diagram = FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil
                        .createDiagram(diagramURI,
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
