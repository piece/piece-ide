/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.util;

import FlowDesigner.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see FlowDesigner.FlowDesignerPackage
 * @generated
 */
public class FlowDesignerSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static FlowDesignerPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowDesignerSwitch() {
        if (modelPackage == null) {
            modelPackage = FlowDesignerPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case FlowDesignerPackage.INITIAL_STATE: {
                InitialState initialState = (InitialState)theEObject;
                T result = caseInitialState(initialState);
                if (result == null) result = caseSource(initialState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.FINAL_STATE: {
                FinalState finalState = (FinalState)theEObject;
                T result = caseFinalState(finalState);
                if (result == null) result = caseTarget(finalState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.ACTION_STATE: {
                ActionState actionState = (ActionState)theEObject;
                T result = caseActionState(actionState);
                if (result == null) result = caseNamedState(actionState);
                if (result == null) result = caseSource(actionState);
                if (result == null) result = caseTarget(actionState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.VIEW_STATE: {
                ViewState viewState = (ViewState)theEObject;
                T result = caseViewState(viewState);
                if (result == null) result = caseNamedState(viewState);
                if (result == null) result = caseSource(viewState);
                if (result == null) result = caseTarget(viewState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.EVENT: {
                Event event = (Event)theEObject;
                T result = caseEvent(event);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.SOURCE: {
                Source source = (Source)theEObject;
                T result = caseSource(source);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.TARGET: {
                Target target = (Target)theEObject;
                T result = caseTarget(target);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.NAMED_STATE: {
                NamedState namedState = (NamedState)theEObject;
                T result = caseNamedState(namedState);
                if (result == null) result = caseSource(namedState);
                if (result == null) result = caseTarget(namedState);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FlowDesignerPackage.FLOW: {
                Flow flow = (Flow)theEObject;
                T result = caseFlow(flow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Initial State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Initial State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitialState(InitialState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Final State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Final State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFinalState(FinalState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Action State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Action State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActionState(ActionState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>View State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>View State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewState(ViewState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEvent(Event object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Source</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSource(Source object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Target</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTarget(Target object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named State</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamedState(NamedState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Flow</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlow(Flow object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} //FlowDesignerSwitch
