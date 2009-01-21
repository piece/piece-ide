package FlowDesigner.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * @generated
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = getPreferenceStore();
        FlowDesigner.diagram.preferences.DiagramPrintingPreferencePage
                .initDefaults(store);
        FlowDesigner.diagram.preferences.DiagramGeneralPreferencePage
                .initDefaults(store);
        FlowDesigner.diagram.preferences.DiagramAppearancePreferencePage
                .initDefaults(store);
        FlowDesigner.diagram.preferences.DiagramConnectionsPreferencePage
                .initDefaults(store);
        FlowDesigner.diagram.preferences.DiagramRulersAndGridPreferencePage
                .initDefaults(store);
    }

    /**
     * @generated
     */
    protected IPreferenceStore getPreferenceStore() {
        return FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                .getInstance().getPreferenceStore();
    }
}
