package FlowDesigner.diagram.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;

public class DiagramGeneralPropertyPage extends FieldEditorPreferencePage implements IWorkbenchPropertyPage {
    @Override
    protected void createFieldEditors() {
    }

    public IAdaptable getElement() {
        return null;
    }

    public void setElement(IAdaptable element) {
    }
}
