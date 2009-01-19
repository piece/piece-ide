/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Initial State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.InitialState#getInitialize <em>Initialize</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getInitialState()
 * @model
 * @generated
 */
public interface InitialState extends Source {
    /**
     * Returns the value of the '<em><b>Initialize</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initialize</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Initialize</em>' attribute.
     * @see #setInitialize(String)
     * @see FlowDesigner.FlowDesignerPackage#getInitialState_Initialize()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getInitialize();

    /**
     * Sets the value of the '{@link FlowDesigner.InitialState#getInitialize <em>Initialize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initialize</em>' attribute.
     * @see #getInitialize()
     * @generated
     */
    void setInitialize(String value);

} // InitialState
