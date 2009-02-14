// $Id$
package FlowDesigner.diagram.resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
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

    public void moveToFlowFile(IFile newFlowFile) throws CoreException {
        final IFile oldDiagramFile = fDiagramFile;
        initialize(newFlowFile);
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                try {
                    oldDiagramFile.copy(fDiagramFile.getFullPath(), true, new NullProgressMonitor());
                    oldDiagramFile.delete(true, new NullProgressMonitor());
                } catch (CoreException e) {
                    e.printStackTrace();
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
}
