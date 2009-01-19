/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Final State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.FinalState#getFinalize <em>Finalize</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getFinalState()
 * @model
 * @generated
 */
public interface FinalState extends Target {
    /**
     * Returns the value of the '<em><b>Finalize</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finalize</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Finalize</em>' attribute.
     * @see #setFinalize(String)
     * @see FlowDesigner.FlowDesignerPackage#getFinalState_Finalize()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getFinalize();

    /**
     * Sets the value of the '{@link FlowDesigner.FinalState#getFinalize <em>Finalize</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Finalize</em>' attribute.
     * @see #getFinalize()
     * @generated
     */
    void setFinalize(String value);

} // FinalState
