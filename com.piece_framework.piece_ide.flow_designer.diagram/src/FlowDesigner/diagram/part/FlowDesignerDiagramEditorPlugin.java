package FlowDesigner.diagram.part;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import FlowDesigner.diagram.resource.DiagramFile;

/**
 * @generated
 */
public class FlowDesignerDiagramEditorPlugin extends AbstractUIPlugin implements IStartup {

    /**
     * @generated
     */
    public static final String ID = "com.piece_framework.piece_ide.flow_designer.diagram"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint(
            ID);

    /**
     * @generated
     */
    private static FlowDesignerDiagramEditorPlugin instance;

    /**
     * @generated
     */
    private ComposedAdapterFactory adapterFactory;

    /**
     * @generated
     */
    private FlowDesigner.diagram.part.FlowDesignerDocumentProvider documentProvider;

    /**
     * @generated
     */
    public FlowDesignerDiagramEditorPlugin() {
    }

    /**
     * @generated
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        PreferencesHint.registerPreferenceStore(DIAGRAM_PREFERENCES_HINT,
                getPreferenceStore());
        adapterFactory = createAdapterFactory();
    }

    /**
     * @generated
     */
    public void stop(BundleContext context) throws Exception {
        adapterFactory.dispose();
        adapterFactory = null;
        instance = null;
        super.stop(context);
    }

    /**
     * @generated
     */
    public static FlowDesignerDiagramEditorPlugin getInstance() {
        return instance;
    }

    /**
     * @generated
     */
    protected ComposedAdapterFactory createAdapterFactory() {
        List factories = new ArrayList();
        fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    /**
     * @generated
     */
    protected void fillItemProviderFactories(List factories) {
        factories
                .add(new FlowDesigner.provider.FlowDesignerItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
    }

    /**
     * @generated
     */
    public AdapterFactory getItemProvidersAdapterFactory() {
        return adapterFactory;
    }

    /**
     * @generated
     */
    public ImageDescriptor getItemImageDescriptor(Object item) {
        IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory
                .adapt(item, IItemLabelProvider.class);
        if (labelProvider != null) {
            return ExtendedImageRegistry.getInstance().getImageDescriptor(
                    labelProvider.getImage(item));
        }
        return null;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path.
     *
     * @generated
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getBundledImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
    }

    /**
     * Respects images residing in any plug-in. If path is relative,
     * then this bundle is looked up for the image, otherwise, for absolute 
     * path, first segment is taken as id of plug-in with image
     *
     * @generated
     * @param path the path to image, either absolute (with plug-in id as first segment), or relative for bundled images
     * @return the image descriptor
     */
    public static ImageDescriptor findImageDescriptor(String path) {
        final IPath p = new Path(path);
        if (p.isAbsolute() && p.segmentCount() > 1) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(p.segment(0), p
                    .removeFirstSegments(1).makeAbsolute().toString());
        } else {
            return getBundledImageDescriptor(p.makeAbsolute().toString());
        }
    }

    /**
     * Returns an image for the image file at the given plug-in relative path.
     * Client do not need to dispose this image. Images will be disposed automatically.
     *
     * @generated
     * @param path the path
     * @return image instance
     */
    public Image getBundledImage(String path) {
        Image image = getImageRegistry().get(path);
        if (image == null) {
            getImageRegistry().put(path, getBundledImageDescriptor(path));
            image = getImageRegistry().get(path);
        }
        return image;
    }

    /**
     * Returns string from plug-in's resource bundle
     *
     * @generated
     */
    public static String getString(String key) {
        return Platform.getResourceString(getInstance().getBundle(), "%" + key); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public FlowDesigner.diagram.part.FlowDesignerDocumentProvider getDocumentProvider() {
        if (documentProvider == null) {
            documentProvider = new FlowDesigner.diagram.part.FlowDesignerDocumentProvider();
        }
        return documentProvider;
    }

    /**
     * @generated
     */
    public void logError(String error) {
        logError(error, null);
    }

    /**
     * @generated
     */
    public void logError(String error, Throwable throwable) {
        if (error == null && throwable != null) {
            error = throwable.getMessage();
        }
        getLog().log(
                new Status(IStatus.ERROR, FlowDesignerDiagramEditorPlugin.ID,
                        IStatus.OK, error, throwable));
        debug(error, throwable);
    }

    /**
     * @generated
     */
    public void logInfo(String message) {
        logInfo(message, null);
    }

    /**
     * @generated
     */
    public void logInfo(String message, Throwable throwable) {
        if (message == null && throwable != null) {
            message = throwable.getMessage();
        }
        getLog().log(
                new Status(IStatus.INFO, FlowDesignerDiagramEditorPlugin.ID,
                        IStatus.OK, message, throwable));
        debug(message, throwable);
    }

    /**
     * @generated
     */
    private void debug(String message, Throwable throwable) {
        if (!isDebugging()) {
            return;
        }
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

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
