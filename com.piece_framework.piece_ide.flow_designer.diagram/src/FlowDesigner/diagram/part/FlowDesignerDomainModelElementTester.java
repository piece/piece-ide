package FlowDesigner.diagram.part;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 */
public class FlowDesignerDomainModelElementTester extends PropertyTester {

    /**
     * @generated
     */
    public boolean test(Object receiver, String method, Object[] args,
            Object expectedValue) {
        if (false == receiver instanceof EObject) {
            return false;
        }
        EObject eObject = (EObject) receiver;
        EClass eClass = eObject.eClass();
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getInitialState()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getFinalState()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getActionState()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE.getViewState()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE.getEvent()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE.getSource()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE.getTarget()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE
                .getNamedState()) {
            return true;
        }
        if (eClass == FlowDesigner.FlowDesignerPackage.eINSTANCE.getFlow()) {
            return true;
        }
        return false;
    }

}
