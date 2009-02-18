package FlowDesigner.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class FlowDesignerElementTypes extends ElementInitializers {

    /**
     * @generated
     */
    private FlowDesignerElementTypes() {
    }

    /**
     * @generated
     */
    private static Map elements;

    /**
     * @generated
     */
    private static ImageRegistry imageRegistry;

    /**
     * @generated
     */
    private static Set KNOWN_ELEMENT_TYPES;

    /**
     * @generated
     */
    public static final IElementType Flow_1000 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.Flow_1000"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType ActionState_2009 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.ActionState_2009"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType FinalState_2010 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.FinalState_2010"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType InitialState_2011 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.InitialState_2011"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType ViewState_2012 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.ViewState_2012"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType Event_4004 = getElementType("com.piece_framework.piece_ide.flow_designer.diagram.Event_4004"); //$NON-NLS-1$

    /**
     * @generated
     */
    private static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * @generated NOT
     */
    private static String getImageRegistryKey(ENamedElement element) {
        return getImageRegistryKey((EObject) element);
    }

    /**
     * @generated NOT
     */
    private static ImageDescriptor getProvidedImageDescriptor(
            ENamedElement element) {
        return getProvidedImageDescriptor((EObject) element);
    }

    /**
     * @generated NOT
     */
    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        return getImageDescriptor((EObject) element);
    }

    /**
     * @generated NOT
     */
    public static Image getImage(ENamedElement element) {
        return getImage((EObject) element);
    }

    /**
     * @generated NOT
     */
    private static String getImageRegistryKey(EObject element) {
        for (EAttribute eAttribute : element.eClass().getEAllAttributes()) {
            if (eAttribute.getName().equals("name")) { //$NON-NLS-1$
                return (String) element.eGet(eAttribute);
            }
        }
        return element.eClass().getName();
    }

    /**
     * @generated NOT
     */
    private static ImageDescriptor getProvidedImageDescriptor(EObject element) {
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = ((EStructuralFeature) element);
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            } else if (eType instanceof EClass
                    && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return FlowDesigner.diagram.part.FlowDesignerDiagramEditorPlugin
                        .getInstance().getItemImageDescriptor(
                                eClass.getEPackage().getEFactoryInstance()
                                        .create(eClass));
            }
        }
        // TODO : support structural features
        return null;
    }

    /**
     * @generated NOT
     */
    public static ImageDescriptor getImageDescriptor(EObject element) {
        String key = getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    /**
     * @generated NOT
     */
    public static Image getImage(EObject element) {
        String key = getImageRegistryKey(element);
        Image image = getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
            image = getImageRegistry().get(key);
        }
        return image;
    }

    /**
     * @generated NOT
     */
    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        EObject element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImageDescriptor(element);
    }

    /**
     * @generated NOT
     */
    public static Image getImage(IAdaptable hint) {
        EObject element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImage(element);
    }

    /**
     * Returns 'type' of the ecore object associated with the hint.
     * 
     * @generated NOT
     */
    public static EObject getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (type == null) {
            if (hint instanceof EObjectAdapter) {
                EObject realObject = (EObject) ((EObjectAdapter) hint)
                        .getRealObject();
                return realObject.eClass();
            }
        }
        if (elements == null) {
            elements = new IdentityHashMap();

            elements.put(Flow_1000, FlowDesigner.FlowDesignerPackage.eINSTANCE
                    .getFlow());

            elements
                    .put(ActionState_2009,
                            FlowDesigner.FlowDesignerPackage.eINSTANCE
                                    .getActionState());

            elements.put(ViewState_2012,
                    FlowDesigner.FlowDesignerPackage.eINSTANCE.getViewState());

            elements.put(InitialState_2011,
                    FlowDesigner.FlowDesignerPackage.eINSTANCE
                            .getInitialState());

            elements.put(FinalState_2010,
                    FlowDesigner.FlowDesignerPackage.eINSTANCE.getFinalState());

            elements.put(Event_4004, FlowDesigner.FlowDesignerPackage.eINSTANCE
                    .getEvent());
        }
        return (EObject) elements.get(type);
    }

    /**
     * @generated
     */
    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    /**
     * @generated
     */
    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet();
            KNOWN_ELEMENT_TYPES.add(Flow_1000);
            KNOWN_ELEMENT_TYPES.add(ActionState_2009);
            KNOWN_ELEMENT_TYPES.add(FinalState_2010);
            KNOWN_ELEMENT_TYPES.add(InitialState_2011);
            KNOWN_ELEMENT_TYPES.add(ViewState_2012);
            KNOWN_ELEMENT_TYPES.add(Event_4004);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

}
