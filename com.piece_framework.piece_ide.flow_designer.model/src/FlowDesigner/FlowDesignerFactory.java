/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FlowDesigner.FlowDesignerPackage
 * @generated
 */
public interface FlowDesignerFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FlowDesignerFactory eINSTANCE = FlowDesigner.impl.FlowDesignerFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Initial State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Initial State</em>'.
     * @generated
     */
    InitialState createInitialState();

    /**
     * Returns a new object of class '<em>Final State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Final State</em>'.
     * @generated
     */
    FinalState createFinalState();

    /**
     * Returns a new object of class '<em>Action State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Action State</em>'.
     * @generated
     */
    ActionState createActionState();

    /**
     * Returns a new object of class '<em>View State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>View State</em>'.
     * @generated
     */
    ViewState createViewState();

    /**
     * Returns a new object of class '<em>Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Event</em>'.
     * @generated
     */
    Event createEvent();

    /**
     * Returns a new object of class '<em>Flow</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Flow</em>'.
     * @generated
     */
    Flow createFlow();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FlowDesignerPackage getFlowDesignerPackage();

} //FlowDesignerFactory
