// $Id$

package FlowDesigner.diagram.part;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.ui.IStartup;

import FlowDesigner.diagram.resource.DiagramFile;

public class FlowDesignerDiagramStartup implements IStartup {
    public void earlyStartup() {
        IFileObserver fileObserver = new IFileObserver() {
            public void handleFileChanged(IFile file) {
            }

            public void handleFileDeleted(IFile file) {
                DiagramFile diagramFile = new DiagramFile(file);
                diagramFile.remove();
            }

            public void handleFileMoved(IFile oldFile, IFile newFile) {
                DiagramFile diagramFile = new DiagramFile(oldFile);
                diagramFile.moveToFlowFile(newFile);
            }

            public void handleFileRenamed(IFile oldFile, IFile newFile) {
                DiagramFile diagramFile = new DiagramFile(oldFile);
                diagramFile.moveToFlowFile(newFile);
            }

            public void handleMarkerAdded(IMarker marker) {
            }

            public void handleMarkerChanged(IMarker marker) {
            }

            public void handleMarkerDeleted(IMarker marker, Map attributes) {
            }
        };
        FileChangeManager.getInstance().addFileObserver(fileObserver,
                new String[] { FlowDesignerDiagramEditorUtil.FLOW_EXTENSION });
    }
}
