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
 * A representation of the model object '<em><b>Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FlowDesigner.Source#getEvents <em>Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see FlowDesigner.FlowDesignerPackage#getSource()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Source extends EObject {
    /**
     * Returns the value of the '<em><b>Events</b></em>' containment reference list.
     * The list contents are of type {@link FlowDesigner.Event}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Events</em>' containment reference list.
     * @see FlowDesigner.FlowDesignerPackage#getSource_Events()
     * @model containment="true" keys="event"
     * @generated
     */
    EList<Event> getEvents();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean canBeSource(Target target);

} // Source
