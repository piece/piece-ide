package FlowDesigner.diagram.properties;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin;
import FlowDesigner.diagram.part.Messages;
import FlowDesigner.diagram.part.Refactoring;

public class RefactorPropertyPage extends FieldEditorPreferencePage implements IWorkbenchPropertyPage {
    private BooleanFieldEditor fUpdateActivityEventHandler;
    private IProject fProject;

    @Override
    protected void createFieldEditors() {
        fUpdateActivityEventHandler =
            new BooleanFieldEditor(Refactoring.UPDATE_ACTIVITY_EVENT_HANDLER,
                                   Messages.RefactorPropertyPage_UpdateActivityEventHandler,
                                   getFieldEditorParent()
                                   );
        addField(fUpdateActivityEventHandler);
    }

    @Override
    public boolean performOk() {
        IPersistentPreferenceStore store = (IPersistentPreferenceStore) getPreferenceStore();
        if (store == null) {
            return false;
        }

        store.setValue(getClass().getName(),
                       System.currentTimeMillis()
                       );
        store.setValue(Refactoring.UPDATE_ACTIVITY_EVENT_HANDLER,
                       fUpdateActivityEventHandler.getBooleanValue()
                       );
        try {
            store.save();
        } catch (IOException e) {
            FlowDesignerDiagramEditorPlugin.getInstance().logError("Unable to save property", //$NON-NLS-1$
                                                                   e
                                                                   );
            return false;
        }

        return true;
    }

    public IAdaptable getElement() {
        return fProject;
    }

    public void setElement(IAdaptable element) {
        if (element.getAdapter(IProject.class) != null) {
            fProject = (IProject) element.getAdapter(IProject.class);
            ScopedPreferenceStore store =
                new ScopedPreferenceStore(new ProjectScope(fProject),
                                          FlowDesignerDiagramEditorPlugin.ID
                                          );
            store.setDefault(Refactoring.UPDATE_ACTIVITY_EVENT_HANDLER,
                             false
                             );
            setPreferenceStore(store);
        }
    }
}
