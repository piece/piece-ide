/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see FlowDesigner.FlowDesignerFactory
 * @model kind="package"
 * @generated
 */
public interface FlowDesignerPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "FlowDesigner";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://piece-framework.com/piece-ide/flowdesigner/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "FlowDesigner";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FlowDesignerPackage eINSTANCE = FlowDesigner.impl.FlowDesignerPackageImpl.init();

    /**
     * The meta object id for the '{@link FlowDesigner.Source <em>Source</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.Source
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getSource()
     * @generated
     */
    int SOURCE = 5;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE__EVENTS = 0;

    /**
     * The number of structural features of the '<em>Source</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.InitialStateImpl <em>Initial State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.InitialStateImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getInitialState()
     * @generated
     */
    int INITIAL_STATE = 0;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INITIAL_STATE__EVENTS = SOURCE__EVENTS;

    /**
     * The feature id for the '<em><b>Initialize</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INITIAL_STATE__INITIALIZE = SOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Initial State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INITIAL_STATE_FEATURE_COUNT = SOURCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link FlowDesigner.Target <em>Target</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.Target
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getTarget()
     * @generated
     */
    int TARGET = 6;

    /**
     * The number of structural features of the '<em>Target</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TARGET_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.FinalStateImpl <em>Final State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.FinalStateImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getFinalState()
     * @generated
     */
    int FINAL_STATE = 1;

    /**
     * The feature id for the '<em><b>Finalize</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FINAL_STATE__FINALIZE = TARGET_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Final State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FINAL_STATE_FEATURE_COUNT = TARGET_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.NamedStateImpl <em>Named State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.NamedStateImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getNamedState()
     * @generated
     */
    int NAMED_STATE = 7;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE__EVENTS = SOURCE__EVENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE__NAME = SOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Activity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE__ACTIVITY = SOURCE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Exit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE__EXIT = SOURCE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE__ENTRY = SOURCE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Named State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_STATE_FEATURE_COUNT = SOURCE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.ActionStateImpl <em>Action State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.ActionStateImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getActionState()
     * @generated
     */
    int ACTION_STATE = 2;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE__EVENTS = NAMED_STATE__EVENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE__NAME = NAMED_STATE__NAME;

    /**
     * The feature id for the '<em><b>Activity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE__ACTIVITY = NAMED_STATE__ACTIVITY;

    /**
     * The feature id for the '<em><b>Exit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE__EXIT = NAMED_STATE__EXIT;

    /**
     * The feature id for the '<em><b>Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE__ENTRY = NAMED_STATE__ENTRY;

    /**
     * The number of structural features of the '<em>Action State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_STATE_FEATURE_COUNT = NAMED_STATE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.ViewStateImpl <em>View State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.ViewStateImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getViewState()
     * @generated
     */
    int VIEW_STATE = 3;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__EVENTS = NAMED_STATE__EVENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__NAME = NAMED_STATE__NAME;

    /**
     * The feature id for the '<em><b>Activity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__ACTIVITY = NAMED_STATE__ACTIVITY;

    /**
     * The feature id for the '<em><b>Exit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__EXIT = NAMED_STATE__EXIT;

    /**
     * The feature id for the '<em><b>Entry</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__ENTRY = NAMED_STATE__ENTRY;

    /**
     * The feature id for the '<em><b>View</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE__VIEW = NAMED_STATE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>View State</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VIEW_STATE_FEATURE_COUNT = NAMED_STATE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.EventImpl <em>Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.EventImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getEvent()
     * @generated
     */
    int EVENT = 4;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__EVENT = 0;

    /**
     * The feature id for the '<em><b>Next State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__NEXT_STATE = 1;

    /**
     * The feature id for the '<em><b>Action</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__ACTION = 2;

    /**
     * The feature id for the '<em><b>Guard</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT__GUARD = 3;

    /**
     * The number of structural features of the '<em>Event</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVENT_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link FlowDesigner.impl.FlowImpl <em>Flow</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see FlowDesigner.impl.FlowImpl
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getFlow()
     * @generated
     */
    int FLOW = 8;

    /**
     * The feature id for the '<em><b>Initial State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW__INITIAL_STATE = 0;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW__STATES = 1;

    /**
     * The feature id for the '<em><b>Final State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW__FINAL_STATE = 2;

    /**
     * The number of structural features of the '<em>Flow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOW_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '<em>Action</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see FlowDesigner.impl.FlowDesignerPackageImpl#getAction()
     * @generated
     */
    int ACTION = 9;


    /**
     * Returns the meta object for class '{@link FlowDesigner.InitialState <em>Initial State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Initial State</em>'.
     * @see FlowDesigner.InitialState
     * @generated
     */
    EClass getInitialState();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.InitialState#getInitialize <em>Initialize</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Initialize</em>'.
     * @see FlowDesigner.InitialState#getInitialize()
     * @see #getInitialState()
     * @generated
     */
    EAttribute getInitialState_Initialize();

    /**
     * Returns the meta object for class '{@link FlowDesigner.FinalState <em>Final State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Final State</em>'.
     * @see FlowDesigner.FinalState
     * @generated
     */
    EClass getFinalState();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.FinalState#getFinalize <em>Finalize</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Finalize</em>'.
     * @see FlowDesigner.FinalState#getFinalize()
     * @see #getFinalState()
     * @generated
     */
    EAttribute getFinalState_Finalize();

    /**
     * Returns the meta object for class '{@link FlowDesigner.ActionState <em>Action State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Action State</em>'.
     * @see FlowDesigner.ActionState
     * @generated
     */
    EClass getActionState();

    /**
     * Returns the meta object for class '{@link FlowDesigner.ViewState <em>View State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>View State</em>'.
     * @see FlowDesigner.ViewState
     * @generated
     */
    EClass getViewState();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.ViewState#getView <em>View</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>View</em>'.
     * @see FlowDesigner.ViewState#getView()
     * @see #getViewState()
     * @generated
     */
    EAttribute getViewState_View();

    /**
     * Returns the meta object for class '{@link FlowDesigner.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Event</em>'.
     * @see FlowDesigner.Event
     * @generated
     */
    EClass getEvent();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.Event#getEvent <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see FlowDesigner.Event#getEvent()
     * @see #getEvent()
     * @generated
     */
    EAttribute getEvent_Event();

    /**
     * Returns the meta object for the reference '{@link FlowDesigner.Event#getNextState <em>Next State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Next State</em>'.
     * @see FlowDesigner.Event#getNextState()
     * @see #getEvent()
     * @generated
     */
    EReference getEvent_NextState();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.Event#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Action</em>'.
     * @see FlowDesigner.Event#getAction()
     * @see #getEvent()
     * @generated
     */
    EAttribute getEvent_Action();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.Event#getGuard <em>Guard</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Guard</em>'.
     * @see FlowDesigner.Event#getGuard()
     * @see #getEvent()
     * @generated
     */
    EAttribute getEvent_Guard();

    /**
     * Returns the meta object for class '{@link FlowDesigner.Source <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source</em>'.
     * @see FlowDesigner.Source
     * @generated
     */
    EClass getSource();

    /**
     * Returns the meta object for the containment reference list '{@link FlowDesigner.Source#getEvents <em>Events</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Events</em>'.
     * @see FlowDesigner.Source#getEvents()
     * @see #getSource()
     * @generated
     */
    EReference getSource_Events();

    /**
     * Returns the meta object for class '{@link FlowDesigner.Target <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target</em>'.
     * @see FlowDesigner.Target
     * @generated
     */
    EClass getTarget();

    /**
     * Returns the meta object for class '{@link FlowDesigner.NamedState <em>Named State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Named State</em>'.
     * @see FlowDesigner.NamedState
     * @generated
     */
    EClass getNamedState();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.NamedState#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see FlowDesigner.NamedState#getName()
     * @see #getNamedState()
     * @generated
     */
    EAttribute getNamedState_Name();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.NamedState#getActivity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Activity</em>'.
     * @see FlowDesigner.NamedState#getActivity()
     * @see #getNamedState()
     * @generated
     */
    EAttribute getNamedState_Activity();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.NamedState#getExit <em>Exit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exit</em>'.
     * @see FlowDesigner.NamedState#getExit()
     * @see #getNamedState()
     * @generated
     */
    EAttribute getNamedState_Exit();

    /**
     * Returns the meta object for the attribute '{@link FlowDesigner.NamedState#getEntry <em>Entry</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Entry</em>'.
     * @see FlowDesigner.NamedState#getEntry()
     * @see #getNamedState()
     * @generated
     */
    EAttribute getNamedState_Entry();

    /**
     * Returns the meta object for class '{@link FlowDesigner.Flow <em>Flow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow</em>'.
     * @see FlowDesigner.Flow
     * @generated
     */
    EClass getFlow();

    /**
     * Returns the meta object for the containment reference '{@link FlowDesigner.Flow#getInitialState <em>Initial State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Initial State</em>'.
     * @see FlowDesigner.Flow#getInitialState()
     * @see #getFlow()
     * @generated
     */
    EReference getFlow_InitialState();

    /**
     * Returns the meta object for the containment reference list '{@link FlowDesigner.Flow#getStates <em>States</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see FlowDesigner.Flow#getStates()
     * @see #getFlow()
     * @generated
     */
    EReference getFlow_States();

    /**
     * Returns the meta object for the containment reference '{@link FlowDesigner.Flow#getFinalState <em>Final State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Final State</em>'.
     * @see FlowDesigner.Flow#getFinalState()
     * @see #getFlow()
     * @generated
     */
    EReference getFlow_FinalState();

    /**
     * Returns the meta object for data type '{@link java.lang.String <em>Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Action</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     * @generated
     */
    EDataType getAction();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FlowDesignerFactory getFlowDesignerFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link FlowDesigner.impl.InitialStateImpl <em>Initial State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.InitialStateImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getInitialState()
         * @generated
         */
        EClass INITIAL_STATE = eINSTANCE.getInitialState();

        /**
         * The meta object literal for the '<em><b>Initialize</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INITIAL_STATE__INITIALIZE = eINSTANCE.getInitialState_Initialize();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.FinalStateImpl <em>Final State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.FinalStateImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getFinalState()
         * @generated
         */
        EClass FINAL_STATE = eINSTANCE.getFinalState();

        /**
         * The meta object literal for the '<em><b>Finalize</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FINAL_STATE__FINALIZE = eINSTANCE.getFinalState_Finalize();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.ActionStateImpl <em>Action State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.ActionStateImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getActionState()
         * @generated
         */
        EClass ACTION_STATE = eINSTANCE.getActionState();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.ViewStateImpl <em>View State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.ViewStateImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getViewState()
         * @generated
         */
        EClass VIEW_STATE = eINSTANCE.getViewState();

        /**
         * The meta object literal for the '<em><b>View</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VIEW_STATE__VIEW = eINSTANCE.getViewState_View();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.EventImpl <em>Event</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.EventImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getEvent()
         * @generated
         */
        EClass EVENT = eINSTANCE.getEvent();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EVENT__EVENT = eINSTANCE.getEvent_Event();

        /**
         * The meta object literal for the '<em><b>Next State</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference EVENT__NEXT_STATE = eINSTANCE.getEvent_NextState();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EVENT__ACTION = eINSTANCE.getEvent_Action();

        /**
         * The meta object literal for the '<em><b>Guard</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EVENT__GUARD = eINSTANCE.getEvent_Guard();

        /**
         * The meta object literal for the '{@link FlowDesigner.Source <em>Source</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.Source
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getSource()
         * @generated
         */
        EClass SOURCE = eINSTANCE.getSource();

        /**
         * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE__EVENTS = eINSTANCE.getSource_Events();

        /**
         * The meta object literal for the '{@link FlowDesigner.Target <em>Target</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.Target
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getTarget()
         * @generated
         */
        EClass TARGET = eINSTANCE.getTarget();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.NamedStateImpl <em>Named State</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.NamedStateImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getNamedState()
         * @generated
         */
        EClass NAMED_STATE = eINSTANCE.getNamedState();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_STATE__NAME = eINSTANCE.getNamedState_Name();

        /**
         * The meta object literal for the '<em><b>Activity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_STATE__ACTIVITY = eINSTANCE.getNamedState_Activity();

        /**
         * The meta object literal for the '<em><b>Exit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_STATE__EXIT = eINSTANCE.getNamedState_Exit();

        /**
         * The meta object literal for the '<em><b>Entry</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_STATE__ENTRY = eINSTANCE.getNamedState_Entry();

        /**
         * The meta object literal for the '{@link FlowDesigner.impl.FlowImpl <em>Flow</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see FlowDesigner.impl.FlowImpl
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getFlow()
         * @generated
         */
        EClass FLOW = eINSTANCE.getFlow();

        /**
         * The meta object literal for the '<em><b>Initial State</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FLOW__INITIAL_STATE = eINSTANCE.getFlow_InitialState();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FLOW__STATES = eINSTANCE.getFlow_States();

        /**
         * The meta object literal for the '<em><b>Final State</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FLOW__FINAL_STATE = eINSTANCE.getFlow_FinalState();

        /**
         * The meta object literal for the '<em>Action</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.String
         * @see FlowDesigner.impl.FlowDesignerPackageImpl#getAction()
         * @generated
         */
        EDataType ACTION = eINSTANCE.getAction();

    }

} //FlowDesignerPackage
