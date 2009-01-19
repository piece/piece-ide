/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.ViewState#getView <em>View</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getViewState()
 * @model
 * @generated
 */
public interface ViewState extends NamedState {
    /**
     * Returns the value of the '<em><b>View</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>View</em>' attribute.
     * @see #setView(String)
     * @see FlowDesigner.FlowDesignerPackage#getViewState_View()
     * @model
     * @generated
     */
    String getView();

    /**
     * Sets the value of the '{@link FlowDesigner.ViewState#getView <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>View</em>' attribute.
     * @see #getView()
     * @generated
     */
    void setView(String value);

} // ViewState
