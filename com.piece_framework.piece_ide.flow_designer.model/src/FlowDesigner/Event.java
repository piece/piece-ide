/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.Event#getName <em>Name</em>}</li>
 *   <li>{@link FlowDesigner.Event#getNextState <em>Next State</em>}</li>
 *   <li>{@link FlowDesigner.Event#getAction <em>Action</em>}</li>
 *   <li>{@link FlowDesigner.Event#getGuard <em>Guard</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends EObject {
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
     * @see FlowDesigner.FlowDesignerPackage#getEvent_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link FlowDesigner.Event#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Next State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Next State</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Next State</em>' reference.
     * @see #setNextState(Target)
     * @see FlowDesigner.FlowDesignerPackage#getEvent_NextState()
     * @model required="true"
     * @generated
     */
    Target getNextState();

    /**
     * Sets the value of the '{@link FlowDesigner.Event#getNextState <em>Next State</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Next State</em>' reference.
     * @see #getNextState()
     * @generated
     */
    void setNextState(Target value);

    /**
     * Returns the value of the '<em><b>Action</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Action</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' attribute.
     * @see #setAction(String)
     * @see FlowDesigner.FlowDesignerPackage#getEvent_Action()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getAction();

    /**
     * Sets the value of the '{@link FlowDesigner.Event#getAction <em>Action</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' attribute.
     * @see #getAction()
     * @generated
     */
    void setAction(String value);

    /**
     * Returns the value of the '<em><b>Guard</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Guard</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Guard</em>' attribute.
     * @see #setGuard(String)
     * @see FlowDesigner.FlowDesignerPackage#getEvent_Guard()
     * @model dataType="FlowDesigner.Action"
     * @generated
     */
    String getGuard();

    /**
     * Sets the value of the '{@link FlowDesigner.Event#getGuard <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Guard</em>' attribute.
     * @see #getGuard()
     * @generated
     */
    void setGuard(String value);

} // Event
