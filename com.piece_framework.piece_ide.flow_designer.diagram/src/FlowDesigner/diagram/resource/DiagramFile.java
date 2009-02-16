// $Id$
package FlowDesigner.diagram.resource;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.ui.PlatformUI;

import FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin;
import FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil;

public class DiagramFile {
    private static final String DIAGRAM_PATH =
        ".settings/" + FlowDesignerDiagramEditorPlugin.ID + "/";
    private static final String DIAGRAM_EXTENSION = "_diagram";
    private IFile fDiagramFile;
    private IFile fFlowFile;

    public DiagramFile(IFile flowFile) {
        initialize(flowFile);
    }

    public boolean exists() {
        return fDiagramFile.exists();
    }

    public void createFromFlow() {
        URI diagramURI = URI.createPlatformResourceURI(fDiagramFile.getFullPath().toString(),
                                                       false
                                                       );
        URI flowURI = URI.createPlatformResourceURI(fFlowFile.getFullPath().toString(),
                                                    false
                                                    );

        FlowDesignerDiagramEditorUtil.createDiagramFromModel(diagramURI,
                                                             flowURI
                                                             );
    }

    public IFile getFile() {
        return fDiagramFile;
    }

    public void moveToFlowFile(IFile newFlowFile) {
        final IFile oldDiagramFile = fDiagramFile;
        initialize(newFlowFile);

        final URI oldDiagramURI = URI.createPlatformResourceURI(oldDiagramFile.getFullPath().toString(),
                                                                false
                                                                );
        final URI newDiagramURI = URI.createPlatformResourceURI(fDiagramFile.getFullPath().toString(),
                                                                false
                                                                );
        final URI flowURI = URI.createPlatformResourceURI(fFlowFile.getFullPath().toString(),
                                                          false
                                                          );

        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
                    DiagramFile.this.replaceURI(oldDiagramURI,
                                                newDiagramURI,
                                                flowURI
                                                );
                    oldDiagramFile.delete(true, new NullProgressMonitor());
                } catch (CoreException e) {
                    FlowDesignerDiagramEditorPlugin.getInstance().logError(
                            "Unable to move diagram",
                            e
                            );
                }
            }
        });
    }

    public void remove() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
                    fDiagramFile.delete(true,
                                        new NullProgressMonitor()
                                        );
                } catch (CoreException e) {
                    FlowDesignerDiagramEditorPlugin.getInstance().logError(
                            "Unable to delete diagram",
                            e
                            );
                }
            }
        });
    }

    private IFile getDiagramFile() {
        return fFlowFile.getProject().getFile(getDiagramPath());
    }

    private Path getDiagramPath() {
        return new Path(DIAGRAM_PATH
                        + fFlowFile.getFullPath().removeFirstSegments(1).toString()
                        + DIAGRAM_EXTENSION
                        );
    }

    private void initialize(IFile flowFile) {
        if (flowFile == null) {
            throw new NullPointerException();
        }
        fFlowFile = flowFile;

        fDiagramFile = getDiagramFile();
    }

    private void replaceURI(URI oldDiagramURI,
                            URI newDiagramURI,
                            URI flowURI
                            ) {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                                                    .createEditingDomain();
        final Resource oldDiagramResource = editingDomain.getResourceSet()
                                                .createResource(oldDiagramURI);
        final Resource newDiagramResource = editingDomain.getResourceSet()
                                                .createResource(newDiagramURI);
        final Resource flowResource = editingDomain.getResourceSet()
                                                .createResource(flowURI);

        AbstractTransactionalCommand command = new AbstractTransactionalCommand(
                                        editingDomain,
                                        FlowDesigner.diagram.part.Messages.FlowDesignerDiagramEditorUtil_CreateDiagramCommandLabel,
                                        Collections.EMPTY_LIST
                                        ) {
            protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
                                                        IAdaptable info
                                                        ) throws ExecutionException {
                try {
                    oldDiagramResource.load(null);
                    flowResource.load(null);
                } catch (IOException e) {
                    return CommandResult.newErrorCommandResult(e);
                }

                Diagram diagram = null;
                for (EObject eObject: oldDiagramResource.getContents()) {
                    if (eObject instanceof Diagram) {
                        diagram = (Diagram) eObject;
                        diagram.setElement(flowResource.getContents().get(0));
                        for (Object object: diagram.getPersistedChildren()) {
                            InternalEObject element = (InternalEObject) ((Node) object).getElement();
                            URI newURI = URI.createURI(element.eProxyURI().scheme() +
                                                       ":" +
                                                       flowResource.getURI().devicePath() +
                                                       "#" +
                                                       element.eProxyURI().fragment());
                            element.eSetProxyURI(newURI);
                        }
                    }
                }

                newDiagramResource.getContents().add(diagram);
                try {
                    newDiagramResource.save(null);
                } catch (IOException e) {
                    return CommandResult.newErrorCommandResult(e);
                }

                return CommandResult.newOKCommandResult();
            }
        };

        try {
            OperationHistoryFactory.getOperationHistory().execute(command,
                    new SubProgressMonitor(new NullProgressMonitor(), 1), null);
        } catch (ExecutionException e) {
            FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
            .getInstance().logError(
                    "Unable to create model and diagram", e); //$NON-NLS-1$
        }
        FlowDesignerDiagramEditorUtil.setCharset(WorkspaceSynchronizer.getFile(oldDiagramResource));
        FlowDesignerDiagramEditorUtil.setCharset(WorkspaceSynchronizer.getFile(newDiagramResource));
    }
}
