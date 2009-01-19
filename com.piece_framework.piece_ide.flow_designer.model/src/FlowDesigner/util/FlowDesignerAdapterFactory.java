/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.util;

import FlowDesigner.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see FlowDesigner.FlowDesignerPackage
 * @generated
 */
public class FlowDesignerAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static FlowDesignerPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowDesignerAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = FlowDesignerPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FlowDesignerSwitch<Adapter> modelSwitch =
        new FlowDesignerSwitch<Adapter>() {
            @Override
            public Adapter caseInitialState(InitialState object) {
                return createInitialStateAdapter();
            }
            @Override
            public Adapter caseFinalState(FinalState object) {
                return createFinalStateAdapter();
            }
            @Override
            public Adapter caseActionState(ActionState object) {
                return createActionStateAdapter();
            }
            @Override
            public Adapter caseViewState(ViewState object) {
                return createViewStateAdapter();
            }
            @Override
            public Adapter caseEvent(Event object) {
                return createEventAdapter();
            }
            @Override
            public Adapter caseSource(Source object) {
                return createSourceAdapter();
            }
            @Override
            public Adapter caseTarget(Target object) {
                return createTargetAdapter();
            }
            @Override
            public Adapter caseNamedState(NamedState object) {
                return createNamedStateAdapter();
            }
            @Override
            public Adapter caseFlow(Flow object) {
                return createFlowAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.InitialState <em>Initial State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.InitialState
     * @generated
     */
    public Adapter createInitialStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.FinalState <em>Final State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.FinalState
     * @generated
     */
    public Adapter createFinalStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.ActionState <em>Action State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.ActionState
     * @generated
     */
    public Adapter createActionStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.ViewState <em>View State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.ViewState
     * @generated
     */
    public Adapter createViewStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.Event
     * @generated
     */
    public Adapter createEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.Source <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.Source
     * @generated
     */
    public Adapter createSourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.Target <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.Target
     * @generated
     */
    public Adapter createTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.NamedState <em>Named State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.NamedState
     * @generated
     */
    public Adapter createNamedStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link FlowDesigner.Flow <em>Flow</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see FlowDesigner.Flow
     * @generated
     */
    public Adapter createFlowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //FlowDesignerAdapterFactory
