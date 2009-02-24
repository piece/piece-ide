// $Id$
package FlowDesigner.diagram.part;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerPackageImpl;

public class Refactoring {
    public static final String UPDATE_ACTIVITY_EVENT_HANDLER = "updateActivityEventHandler"; //$NON-NLS-1$
    private IPersistentPreferenceStore fStore;

    public Refactoring(IFile file) {
        if (file == null) {
            throw new NullPointerException();
        }

        fStore = new ScopedPreferenceStore(new ProjectScope(file.getProject()),
                                           FlowDesignerDiagramEditorPlugin.ID
                                           );
    }

    public void addSetValueCommandForActivity(EditPart editPart,
                                              Request request,
                                              Command addedCommand
                                              ) {
        if (fStore.getBoolean(UPDATE_ACTIVITY_EVENT_HANDLER) == false) {
            return;
        }

        if (addedCommand == null
            || (addedCommand instanceof ICommandProxy) == false
            ) {
            return;
        }
        ICommandProxy proxy = (ICommandProxy) addedCommand;
        if ((proxy.getICommand() instanceof CompositeTransactionalCommand) == false) {
            return;
        }
        CompositeTransactionalCommand realAddedCommand = (CompositeTransactionalCommand) proxy.getICommand();

        if (request == null
            || (request instanceof DirectEditRequest) == false
            ) {
            return;
        }
        DirectEditRequest directEditRequest = (DirectEditRequest) request;

        if (editPart == null
            || (editPart.getModel() instanceof View) == false
            || (((Node) editPart.getModel()).getElement() instanceof NamedState) == false
            ) {
            return;
        }
        NamedState state = (NamedState) ((Node) editPart.getModel()).getElement();

        if (state.getActivity() == null
            || state.getActivity().startsWith(NamedState.ACTIVITY_PREFIX) == false
            ) {
            return;
        }

        String activity = NamedState.ACTIVITY_PREFIX
                          + (String) directEditRequest.getCellEditor().getValue();
        SetValueCommand setCommand = new SetValueCommand(new SetRequest(state,
                                                                        FlowDesignerPackageImpl.eINSTANCE.getNamedState_Activity(),
                                                                        activity
                                                                        ));
        realAddedCommand.add(setCommand);
    }
}
