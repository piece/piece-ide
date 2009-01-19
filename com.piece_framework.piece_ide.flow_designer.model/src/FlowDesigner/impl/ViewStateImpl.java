/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import FlowDesigner.FlowDesignerPackage;
import FlowDesigner.ViewState;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.ViewStateImpl#getView <em>View</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewStateImpl extends NamedStateImpl implements ViewState {
    /**
     * The default value of the '{@link #getView() <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getView()
     * @generated
     * @ordered
     */
    protected static final String VIEW_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getView() <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getView()
     * @generated
     * @ordered
     */
    protected String view = VIEW_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ViewStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.VIEW_STATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getView() {
        return view;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setView(String newView) {
        String oldView = view;
        view = newView;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.VIEW_STATE__VIEW, oldView, view));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FlowDesignerPackage.VIEW_STATE__VIEW:
                return getView();
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
            case FlowDesignerPackage.VIEW_STATE__VIEW:
                setView((String)newValue);
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
            case FlowDesignerPackage.VIEW_STATE__VIEW:
                setView(VIEW_EDEFAULT);
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
            case FlowDesignerPackage.VIEW_STATE__VIEW:
                return VIEW_EDEFAULT == null ? view != null : !VIEW_EDEFAULT.equals(view);
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
        result.append(" (view: ");
        result.append(view);
        result.append(')');
        return result.toString();
    }

} //ViewStateImpl
