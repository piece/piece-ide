/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.NamedState#getName <em>Name</em>}</li>
 *   <li>{@link FlowDesigner.NamedState#getActivity <em>Activity</em>}</li>
 *   <li>{@link FlowDesigner.NamedState#getExit <em>Exit</em>}</li>
 *   <li>{@link FlowDesigner.NamedState#getEntry <em>Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getNamedState()
 * @model abstract="true"
 * @generated
 */
public interface NamedState extends Source, Target {
    public static final String ACTIVITY_PREFIX = "on";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see FlowDesigner.FlowDesignerPackage#getNamedState_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link FlowDesigner.NamedState#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Activity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activity</em>' attribute.
     * @see #setActivity(String)
     * @see FlowDesigner.FlowDesignerPackage#getNamedState_Activity()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getActivity();

    /**
     * Sets the value of the '{@link FlowDesigner.NamedState#getActivity <em>Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activity</em>' attribute.
     * @see #getActivity()
     * @generated
     */
    void setActivity(String value);

    /**
     * Returns the value of the '<em><b>Exit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exit</em>' attribute.
     * @see #setExit(String)
     * @see FlowDesigner.FlowDesignerPackage#getNamedState_Exit()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getExit();

    /**
     * Sets the value of the '{@link FlowDesigner.NamedState#getExit <em>Exit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exit</em>' attribute.
     * @see #getExit()
     * @generated
     */
    void setExit(String value);

    /**
     * Returns the value of the '<em><b>Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entry</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Entry</em>' attribute.
     * @see #setEntry(String)
     * @see FlowDesigner.FlowDesignerPackage#getNamedState_Entry()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getEntry();

    /**
     * Sets the value of the '{@link FlowDesigner.NamedState#getEntry <em>Entry</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Entry</em>' attribute.
     * @see #getEntry()
     * @generated
     */
    void setEntry(String value);

} // NamedState
