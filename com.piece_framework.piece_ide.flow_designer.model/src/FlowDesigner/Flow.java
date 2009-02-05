/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.Flow#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link FlowDesigner.Flow#getStates <em>States</em>}</li>
 *   <li>{@link FlowDesigner.Flow#getFinalState <em>Final State</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getFlow()
 * @model
 * @generated
 */
public interface Flow extends EObject {
    /**
     * Returns the value of the '<em><b>Initial State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial State</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Initial State</em>' containment reference.
     * @see #setInitialState(InitialState)
     * @see FlowDesigner.FlowDesignerPackage#getFlow_InitialState()
     * @model containment="true" required="true"
     * @generated
     */
    InitialState getInitialState();

    /**
     * Sets the value of the '{@link FlowDesigner.Flow#getInitialState <em>Initial State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial State</em>' containment reference.
     * @see #getInitialState()
     * @generated
     */
    void setInitialState(InitialState value);

    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list.
     * The list contents are of type {@link FlowDesigner.NamedState}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>States</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>States</em>' containment reference list.
     * @see FlowDesigner.FlowDesignerPackage#getFlow_States()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<NamedState> getStates();

    /**
     * Returns the value of the '<em><b>Final State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Final State</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Final State</em>' containment reference.
     * @see #setFinalState(FinalState)
     * @see FlowDesigner.FlowDesignerPackage#getFlow_FinalState()
     * @model containment="true"
     * @generated
     */
    FinalState getFinalState();

    /**
     * Sets the value of the '{@link FlowDesigner.Flow#getFinalState <em>Final State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Final State</em>' containment reference.
     * @see #getFinalState()
     * @generated
     */
    void setFinalState(FinalState value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    NamedState findStateByName(String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean hasLastState();
} // Flow
