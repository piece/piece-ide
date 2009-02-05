/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import FlowDesigner.Event;
import FlowDesigner.FlowDesignerPackage;
import FlowDesigner.InitialState;

import FlowDesigner.Target;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Initial State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.InitialStateImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link FlowDesigner.impl.InitialStateImpl#getInitialize <em>Initialize</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InitialStateImpl extends EObjectImpl implements InitialState {
    /**
     * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvents()
     * @generated
     * @ordered
     */
    protected EList<Event> events;

    /**
     * The default value of the '{@link #getInitialize() <em>Initialize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitialize()
     * @generated
     * @ordered
     */
    protected static final String INITIALIZE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInitialize() <em>Initialize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitialize()
     * @generated
     * @ordered
     */
    protected String initialize = INITIALIZE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InitialStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.INITIAL_STATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Event> getEvents() {
        if (events == null) {
            events = new EObjectContainmentEList<Event>(Event.class, this, FlowDesignerPackage.INITIAL_STATE__EVENTS);
        }
        return events;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInitialize() {
        return initialize;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInitialize(String newInitialize) {
        String oldInitialize = initialize;
        initialize = newInitialize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.INITIAL_STATE__INITIALIZE, oldInitialize, initialize));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean canBeSource(Target target) {
        return getEvents().size() == 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FlowDesignerPackage.INITIAL_STATE__EVENTS:
                return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
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
            case FlowDesignerPackage.INITIAL_STATE__EVENTS:
                return getEvents();
            case FlowDesignerPackage.INITIAL_STATE__INITIALIZE:
                return getInitialize();
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
            case FlowDesignerPackage.INITIAL_STATE__EVENTS:
                getEvents().clear();
                getEvents().addAll((Collection<? extends Event>)newValue);
                return;
            case FlowDesignerPackage.INITIAL_STATE__INITIALIZE:
                setInitialize((String)newValue);
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
            case FlowDesignerPackage.INITIAL_STATE__EVENTS:
                getEvents().clear();
                return;
            case FlowDesignerPackage.INITIAL_STATE__INITIALIZE:
                setInitialize(INITIALIZE_EDEFAULT);
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
            case FlowDesignerPackage.INITIAL_STATE__EVENTS:
                return events != null && !events.isEmpty();
            case FlowDesignerPackage.INITIAL_STATE__INITIALIZE:
                return INITIALIZE_EDEFAULT == null ? initialize != null : !INITIALIZE_EDEFAULT.equals(initialize);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (initialize: ");
        result.append(initialize);
        result.append(')');
        return result.toString();
    }

} //InitialStateImpl
