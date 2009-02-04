/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import FlowDesigner.Event;
import FlowDesigner.FlowDesignerPackage;
import FlowDesigner.Target;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.EventImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link FlowDesigner.impl.EventImpl#getNextState <em>Next State</em>}</li>
 *   <li>{@link FlowDesigner.impl.EventImpl#getAction <em>Action</em>}</li>
 *   <li>{@link FlowDesigner.impl.EventImpl#getGuard <em>Guard</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventImpl extends EObjectImpl implements Event {
    /**
     * The default value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected static final String EVENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected String event = EVENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getNextState() <em>Next State</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNextState()
     * @generated
     * @ordered
     */
    protected Target nextState;

    /**
     * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
    protected static final String ACTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
    protected String action = ACTION_EDEFAULT;

    /**
     * The default value of the '{@link #getGuard() <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGuard()
     * @generated
     * @ordered
     */
    protected static final String GUARD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getGuard() <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGuard()
     * @generated
     * @ordered
     */
    protected String guard = GUARD_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEvent() {
        return event;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvent(String newEvent) {
        String oldEvent = event;
        event = newEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.EVENT__EVENT, oldEvent, event));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Target getNextState() {
        if (nextState != null && nextState.eIsProxy()) {
            InternalEObject oldNextState = (InternalEObject)nextState;
            nextState = (Target)eResolveProxy(oldNextState);
            if (nextState != oldNextState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FlowDesignerPackage.EVENT__NEXT_STATE, oldNextState, nextState));
            }
        }
        return nextState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Target basicGetNextState() {
        return nextState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNextState(Target newNextState) {
        Target oldNextState = nextState;
        nextState = newNextState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.EVENT__NEXT_STATE, oldNextState, nextState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAction() {
        return action;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAction(String newAction) {
        String oldAction = action;
        action = newAction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.EVENT__ACTION, oldAction, action));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getGuard() {
        return guard;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGuard(String newGuard) {
        String oldGuard = guard;
        guard = newGuard;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.EVENT__GUARD, oldGuard, guard));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FlowDesignerPackage.EVENT__EVENT:
                return getEvent();
            case FlowDesignerPackage.EVENT__NEXT_STATE:
                if (resolve) return getNextState();
                return basicGetNextState();
            case FlowDesignerPackage.EVENT__ACTION:
                return getAction();
            case FlowDesignerPackage.EVENT__GUARD:
                return getGuard();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FlowDesignerPackage.EVENT__EVENT:
                setEvent((String)newValue);
                return;
            case FlowDesignerPackage.EVENT__NEXT_STATE:
                setNextState((Target)newValue);
                return;
            case FlowDesignerPackage.EVENT__ACTION:
                setAction((String)newValue);
                return;
            case FlowDesignerPackage.EVENT__GUARD:
                setGuard((String)newValue);
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
            case FlowDesignerPackage.EVENT__EVENT:
                setEvent(EVENT_EDEFAULT);
                return;
            case FlowDesignerPackage.EVENT__NEXT_STATE:
                setNextState((Target)null);
                return;
            case FlowDesignerPackage.EVENT__ACTION:
                setAction(ACTION_EDEFAULT);
                return;
            case FlowDesignerPackage.EVENT__GUARD:
                setGuard(GUARD_EDEFAULT);
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
            case FlowDesignerPackage.EVENT__EVENT:
                return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
            case FlowDesignerPackage.EVENT__NEXT_STATE:
                return nextState != null;
            case FlowDesignerPackage.EVENT__ACTION:
                return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
            case FlowDesignerPackage.EVENT__GUARD:
                return GUARD_EDEFAULT == null ? guard != null : !GUARD_EDEFAULT.equals(guard);
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
        result.append(" (event: ");
        result.append(event);
        result.append(", action: ");
        result.append(action);
        result.append(", guard: ");
        result.append(guard);
        result.append(')');
        return result.toString();
    }

} //EventImpl
