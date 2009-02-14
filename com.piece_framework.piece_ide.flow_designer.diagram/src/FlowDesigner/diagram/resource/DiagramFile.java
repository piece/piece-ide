// $Id$
package FlowDesigner.diagram.resource;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
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
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
                    DiagramFile.this.replaceURI(oldDiagramFile,
                                                fDiagramFile);
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

    public void remove() throws CoreException {
        fDiagramFile.delete(true,
                            new NullProgressMonitor()
                            );
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

    private void replaceURI(IFile oldDiagramFile,
                            IFile newDiagramFile
                            ) {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                                                    .createEditingDomain();
        URI oldDiagramURI = URI.createPlatformResourceURI(oldDiagramFile.getFullPath().toString(),
                                                          false
                                                          );
        URI newDiagramURI = URI.createPlatformResourceURI(newDiagramFile.getFullPath().toString(),
                                                          false
                                                          );
        final URI flowURI = URI.createPlatformResourceURI(fFlowFile.getFullPath().toString(),
                                                          false
                                                          );
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
                    XMIResource resource = new XMIResourceImpl();

                    Map<Object, Object> loadOptions = new HashMap<Object, Object>();
                    loadOptions.putAll(resource.getDefaultLoadOptions());
                    loadOptions.put(XMIResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
                    loadOptions.put(XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_RECORD);

                    oldDiagramResource.load(loadOptions);
                    flowResource.load(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Diagram diagram = null;
                for (EObject eObject: oldDiagramResource.getContents()) {
                    if (eObject instanceof Diagram) {
                        diagram = (Diagram) eObject;
                        diagram.setElement(flowResource.getContents().get(0));
                        for (Object object: diagram.getPersistedChildren()) {
                            Node node = (Node) object;
                            InternalEObject element = (InternalEObject) node.getElement();
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
                    e.printStackTrace();
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
