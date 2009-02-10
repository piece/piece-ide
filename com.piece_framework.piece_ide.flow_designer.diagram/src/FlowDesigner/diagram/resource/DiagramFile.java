// $Id$
package FlowDesigner.diagram.resource;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;

import FlowDesigner.diagram.edit.parts.FlowEditPart;
import FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin;
import FlowDesigner.diagram.part.FlowDesignerDiagramEditorUtil;
import FlowDesigner.diagram.part.Messages;

public class DiagramFile {
    private static final String DIAGRAM_PATH =
        ".settings/" + FlowDesignerDiagramEditorPlugin.ID + "/";
    private static final String DIAGRAM_EXTENSION = "_diagram";
    private IFile fDiagramFile;
    private IFile fFlowFile;

    public DiagramFile(IFile flowFile) {
        if (flowFile == null) {
            throw new NullPointerException();
        }
        fFlowFile = flowFile;

        fDiagramFile = getDiagramFile();
    }

    public boolean exists() {
        return fDiagramFile.exists();
    }

    public void createFromFlow() {
        FlowDesignerDiagramEditorUtil.createDiagramFromFlow(fFlowFile, fDiagramFile);
    }

    public IFile getFile() {
        return fDiagramFile;
    }

    private IFile getDiagramFile() {
        String diagramPath = DIAGRAM_PATH
                             + fFlowFile.getFullPath().removeFirstSegments(1).toString()
                             + DIAGRAM_EXTENSION;
        return fFlowFile.getProject().getFile(new Path(diagramPath));
    }
}
