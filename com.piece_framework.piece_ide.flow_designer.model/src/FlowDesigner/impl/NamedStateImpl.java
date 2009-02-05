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
import FlowDesigner.NamedState;
import FlowDesigner.Source;
import FlowDesigner.Target;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.NamedStateImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link FlowDesigner.impl.NamedStateImpl#getName <em>Name</em>}</li>
 *   <li>{@link FlowDesigner.impl.NamedStateImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link FlowDesigner.impl.NamedStateImpl#getExit <em>Exit</em>}</li>
 *   <li>{@link FlowDesigner.impl.NamedStateImpl#getEntry <em>Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NamedStateImpl extends EObjectImpl implements NamedState {
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
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getActivity() <em>Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActivity()
     * @generated
     * @ordered
     */
    protected static final String ACTIVITY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getActivity() <em>Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActivity()
     * @generated
     * @ordered
     */
    protected String activity = ACTIVITY_EDEFAULT;

    /**
     * The default value of the '{@link #getExit() <em>Exit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExit()
     * @generated
     * @ordered
     */
    protected static final String EXIT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExit() <em>Exit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExit()
     * @generated
     * @ordered
     */
    protected String exit = EXIT_EDEFAULT;

    /**
     * The default value of the '{@link #getEntry() <em>Entry</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEntry()
     * @generated
     * @ordered
     */
    protected static final String ENTRY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEntry() <em>Entry</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEntry()
     * @generated
     * @ordered
     */
    protected String entry = ENTRY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NamedStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.NAMED_STATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Event> getEvents() {
        if (events == null) {
            events = new EObjectContainmentEList<Event>(Event.class, this, FlowDesignerPackage.NAMED_STATE__EVENTS);
        }
        return events;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.NAMED_STATE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getActivity() {
        return activity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActivity(String newActivity) {
        String oldActivity = activity;
        activity = newActivity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.NAMED_STATE__ACTIVITY, oldActivity, activity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExit() {
        return exit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExit(String newExit) {
        String oldExit = exit;
        exit = newExit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.NAMED_STATE__EXIT, oldExit, exit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEntry() {
        return entry;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEntry(String newEntry) {
        String oldEntry = entry;
        entry = newEntry;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.NAMED_STATE__ENTRY, oldEntry, entry));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean canBeTarget(Source source) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean canBeSource(Target target) {
        boolean isNotLastState = true;

        FinalState finalState = ((Flow) eContainer()).getFinalState();
        if (finalState != null) {
            for (Event event: getEvents()) {
                if (event.getNextState().equals(finalState)) {
                    isNotLastState = false;
                    break;
                }
            }
        }

        return isNotLastState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FlowDesignerPackage.NAMED_STATE__EVENTS:
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
            case FlowDesignerPackage.NAMED_STATE__EVENTS:
                return getEvents();
            case FlowDesignerPackage.NAMED_STATE__NAME:
                return getName();
            case FlowDesignerPackage.NAMED_STATE__ACTIVITY:
                return getActivity();
            case FlowDesignerPackage.NAMED_STATE__EXIT:
                return getExit();
            case FlowDesignerPackage.NAMED_STATE__ENTRY:
                return getEntry();
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
            case FlowDesignerPackage.NAMED_STATE__EVENTS:
                getEvents().clear();
                getEvents().addAll((Collection<? extends Event>)newValue);
                return;
            case FlowDesignerPackage.NAMED_STATE__NAME:
                setName((String)newValue);
                return;
            case FlowDesignerPackage.NAMED_STATE__ACTIVITY:
                setActivity((String)newValue);
                return;
            case FlowDesignerPackage.NAMED_STATE__EXIT:
                setExit((String)newValue);
                return;
            case FlowDesignerPackage.NAMED_STATE__ENTRY:
                setEntry((String)newValue);
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
            case FlowDesignerPackage.NAMED_STATE__EVENTS:
                getEvents().clear();
                return;
            case FlowDesignerPackage.NAMED_STATE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FlowDesignerPackage.NAMED_STATE__ACTIVITY:
                setActivity(ACTIVITY_EDEFAULT);
                return;
            case FlowDesignerPackage.NAMED_STATE__EXIT:
                setExit(EXIT_EDEFAULT);
                return;
            case FlowDesignerPackage.NAMED_STATE__ENTRY:
                setEntry(ENTRY_EDEFAULT);
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
            case FlowDesignerPackage.NAMED_STATE__EVENTS:
                return events != null && !events.isEmpty();
            case FlowDesignerPackage.NAMED_STATE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FlowDesignerPackage.NAMED_STATE__ACTIVITY:
                return ACTIVITY_EDEFAULT == null ? activity != null : !ACTIVITY_EDEFAULT.equals(activity);
            case FlowDesignerPackage.NAMED_STATE__EXIT:
                return EXIT_EDEFAULT == null ? exit != null : !EXIT_EDEFAULT.equals(exit);
            case FlowDesignerPackage.NAMED_STATE__ENTRY:
                return ENTRY_EDEFAULT == null ? entry != null : !ENTRY_EDEFAULT.equals(entry);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", activity: ");
        result.append(activity);
        result.append(", exit: ");
        result.append(exit);
        result.append(", entry: ");
        result.append(entry);
        result.append(')');
        return result.toString();
    }

} //NamedStateImpl
