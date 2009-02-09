/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerPackage;
import FlowDesigner.InitialState;
import FlowDesigner.NamedState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.FlowImpl#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link FlowDesigner.impl.FlowImpl#getStates <em>States</em>}</li>
 *   <li>{@link FlowDesigner.impl.FlowImpl#getFinalState <em>Final State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowImpl extends EObjectImpl implements Flow {
    /**
     * The cached value of the '{@link #getInitialState() <em>Initial State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitialState()
     * @generated
     * @ordered
     */
    protected InitialState initialState;

    /**
     * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
    protected EList<NamedState> states;

    /**
     * The cached value of the '{@link #getFinalState() <em>Final State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFinalState()
     * @generated
     * @ordered
     */
    protected FinalState finalState;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FlowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.FLOW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InitialState getInitialState() {
        return initialState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInitialState(InitialState newInitialState, NotificationChain msgs) {
        InitialState oldInitialState = initialState;
        initialState = newInitialState;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.FLOW__INITIAL_STATE, oldInitialState, newInitialState);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInitialState(InitialState newInitialState) {
        if (newInitialState != initialState) {
            NotificationChain msgs = null;
            if (initialState != null)
                msgs = ((InternalEObject)initialState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowDesignerPackage.FLOW__INITIAL_STATE, null, msgs);
            if (newInitialState != null)
                msgs = ((InternalEObject)newInitialState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowDesignerPackage.FLOW__INITIAL_STATE, null, msgs);
            msgs = basicSetInitialState(newInitialState, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.FLOW__INITIAL_STATE, newInitialState, newInitialState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<NamedState> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<NamedState>(NamedState.class, this, FlowDesignerPackage.FLOW__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FinalState getFinalState() {
        return finalState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFinalState(FinalState newFinalState, NotificationChain msgs) {
        FinalState oldFinalState = finalState;
        finalState = newFinalState;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.FLOW__FINAL_STATE, oldFinalState, newFinalState);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFinalState(FinalState newFinalState) {
        if (newFinalState != finalState) {
            NotificationChain msgs = null;
            if (finalState != null)
                msgs = ((InternalEObject)finalState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowDesignerPackage.FLOW__FINAL_STATE, null, msgs);
            if (newFinalState != null)
                msgs = ((InternalEObject)newFinalState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowDesignerPackage.FLOW__FINAL_STATE, null, msgs);
            msgs = basicSetFinalState(newFinalState, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.FLOW__FINAL_STATE, newFinalState, newFinalState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public NamedState findStateByName(String name) {
        for (NamedState state : getStates()) {
            if (state.getName() != null
                && state.getName().equals(name)
                ) {
                return state;
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean hasLastState() {
        if (finalState == null) {
            return false;
        }

        for (NamedState state: states) {
            for (Event event: state.getEvents()) {
                if (event.getNextState().equals(finalState)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FlowDesignerPackage.FLOW__INITIAL_STATE:
                return basicSetInitialState(null, msgs);
            case FlowDesignerPackage.FLOW__STATES:
                return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
            case FlowDesignerPackage.FLOW__FINAL_STATE:
                return basicSetFinalState(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FlowDesignerPackage.FLOW__INITIAL_STATE:
                return getInitialState();
            case FlowDesignerPackage.FLOW__STATES:
                return getStates();
            case FlowDesignerPackage.FLOW__FINAL_STATE:
                return getFinalState();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FlowDesignerPackage.FLOW__INITIAL_STATE:
                setInitialState((InitialState)newValue);
                return;
            case FlowDesignerPackage.FLOW__STATES:
                getStates().clear();
                getStates().addAll((Collection<? extends NamedState>)newValue);
                return;
            case FlowDesignerPackage.FLOW__FINAL_STATE:
                setFinalState((FinalState)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case FlowDesignerPackage.FLOW__INITIAL_STATE:
                setInitialState((InitialState)null);
                return;
            case FlowDesignerPackage.FLOW__STATES:
                getStates().clear();
                return;
            case FlowDesignerPackage.FLOW__FINAL_STATE:
                setFinalState((FinalState)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FlowDesignerPackage.FLOW__INITIAL_STATE:
                return initialState != null;
            case FlowDesignerPackage.FLOW__STATES:
                return states != null && !states.isEmpty();
            case FlowDesignerPackage.FLOW__FINAL_STATE:
                return finalState != null;
        }
        return super.eIsSet(featureID);
    }
} //FlowImpl
