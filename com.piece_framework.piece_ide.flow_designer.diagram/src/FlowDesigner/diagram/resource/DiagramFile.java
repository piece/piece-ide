// $Id$
package FlowDesigner.diagram.resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import FlowDesigner.diagram.part.FlowDesignerDiagramEditor;
import FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin;
import FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil;

public class DiagramFile {
    private static final String DIAGRAM_PATH =
        ".settings/" + FlowDesignerDiagramEditorPlugin.ID + "/"; //$NON-NLS-1$ //$NON-NLS-2$
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
                    FlowDesignerDiagramEditorUtil.replaceURI(oldDiagramURI,
                                                             newDiagramURI,
                                                             flowURI
                                                             );
                    DiagramFile.this.refreshEditorInput(oldDiagramFile,
                                                        fDiagramFile
                                                        );
                    oldDiagramFile.delete(true, new NullProgressMonitor());
                } catch (CoreException e) {
                    FlowDesignerDiagramEditorPlugin.getInstance().logError(
                            "Unable to move diagram", //$NON-NLS-1$
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
                            "Unable to delete diagram", //$NON-NLS-1$
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
                        + FlowDesignerDiagramEditorUtil.DIAGRAM_EXTENSION
                        );
    }

    private void initialize(IFile flowFile) {
        if (flowFile == null) {
            throw new NullPointerException();
        }
        fFlowFile = flowFile;

        fDiagramFile = getDiagramFile();
    }

    private void refreshEditorInput(IFile oldDiagramFile,
                                    IFile newDiagramFile
                                    ) throws PartInitException {
        IEditorInput oldEditorInput = new FileEditorInput(oldDiagramFile);
        IEditorInput newEditorInput = new FileEditorInput(newDiagramFile);

        for (IWorkbenchWindow window: PlatformUI.getWorkbench().getWorkbenchWindows()) {
            for (IWorkbenchPage page: window.getPages()) {
                IEditorReference[] editorReferences = page.findEditors(oldEditorInput,
                                                                       null,
                                                                       IWorkbenchPage.MATCH_INPUT
                                                                       );
                for (IEditorReference editorReference: editorReferences) {
                    if (editorReference.getEditor(true) instanceof FlowDesignerDiagramEditor) {
                        FlowDesignerDiagramEditor editor = (FlowDesignerDiagramEditor) editorReference.getEditor(true);
                        editor.init(editor.getEditorSite(),
                                    newEditorInput
                                    );
                    }
                }
            }
        }
    }
}
