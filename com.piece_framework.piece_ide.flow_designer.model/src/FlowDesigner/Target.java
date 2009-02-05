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
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see FlowDesigner.FlowDesignerPackage#getTarget()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Target extends EObject {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean canBeTarget(Source source);
} // Target
