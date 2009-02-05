/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import FlowDesigner.Event;
import FlowDesigner.FinalState;
import FlowDesigner.Flow;
import FlowDesigner.FlowDesignerPackage;
import FlowDesigner.NamedState;
import FlowDesigner.Source;
import FlowDesigner.ViewState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Final State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FlowDesigner.impl.FinalStateImpl#getFinalize <em>Finalize</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FinalStateImpl extends EObjectImpl implements FinalState {
    /**
     * The default value of the '{@link #getFinalize() <em>Finalize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFinalize()
     * @generated
     * @ordered
     */
    protected static final String FINALIZE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFinalize() <em>Finalize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFinalize()
     * @generated
     * @ordered
     */
    protected String finalize = FINALIZE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FinalStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FlowDesignerPackage.Literals.FINAL_STATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFinalize() {
        return finalize;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFinalize(String newFinalize) {
        String oldFinalize = finalize;
        finalize = newFinalize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FlowDesignerPackage.FINAL_STATE__FINALIZE, oldFinalize, finalize));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean canBeTarget(Source source) {
        boolean sourceIsViewState = source instanceof ViewState;
        boolean sourceHasNoEvent = source.getEvents().size() == 0;
        Flow flow = (Flow) eContainer();
        boolean flowHasNoLastState = flow.hasLastState() == false;

        return sourceIsViewState
               && sourceHasNoEvent
               && flowHasNoLastState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FlowDesignerPackage.FINAL_STATE__FINALIZE:
                return getFinalize();
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
            case FlowDesignerPackage.FINAL_STATE__FINALIZE:
                setFinalize((String)newValue);
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
            case FlowDesignerPackage.FINAL_STATE__FINALIZE:
                setFinalize(FINALIZE_EDEFAULT);
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
            case FlowDesignerPackage.FINAL_STATE__FINALIZE:
                return FINALIZE_EDEFAULT == null ? finalize != null : !FINALIZE_EDEFAULT.equals(finalize);
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
        result.append(" (finalize: ");
        result.append(finalize);
        result.append(')');
        return result.toString();
    }

} //FinalStateImpl
