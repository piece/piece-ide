/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import FlowDesigner.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowDesignerFactoryImpl extends EFactoryImpl implements FlowDesignerFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FlowDesignerFactory init() {
        try {
            FlowDesignerFactory theFlowDesignerFactory = (FlowDesignerFactory)EPackage.Registry.INSTANCE.getEFactory("http://piece-framework.com/piece-ide/flowdesigner/1.0"); 
            if (theFlowDesignerFactory != null) {
                return theFlowDesignerFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FlowDesignerFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowDesignerFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case FlowDesignerPackage.INITIAL_STATE: return createInitialState();
            case FlowDesignerPackage.FINAL_STATE: return createFinalState();
            case FlowDesignerPackage.ACTION_STATE: return createActionState();
            case FlowDesignerPackage.VIEW_STATE: return createViewState();
            case FlowDesignerPackage.EVENT: return createEvent();
            case FlowDesignerPackage.FLOW: return createFlow();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case FlowDesignerPackage.ACTION:
                return createActionFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case FlowDesignerPackage.ACTION:
                return convertActionToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InitialState createInitialState() {
        InitialStateImpl initialState = new InitialStateImpl();
        return initialState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FinalState createFinalState() {
        FinalStateImpl finalState = new FinalStateImpl();
        return finalState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionState createActionState() {
        ActionStateImpl actionState = new ActionStateImpl();
        return actionState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ViewState createViewState() {
        ViewStateImpl viewState = new ViewStateImpl();
        return viewState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Event createEvent() {
        EventImpl event = new EventImpl();
        return event;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Flow createFlow() {
        FlowImpl flow = new FlowImpl();
        flow.setInitialState(createInitialState());
        return flow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createActionFromString(EDataType eDataType, String initialValue) {
        return (String)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertActionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowDesignerPackage getFlowDesignerPackage() {
        return (FlowDesignerPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FlowDesignerPackage getPackage() {
        return FlowDesignerPackage.eINSTANCE;
    }

} //FlowDesignerFactoryImpl
