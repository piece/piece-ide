package FlowDesigner.diagram.resources;

import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

public class ActionStateLoad extends NamedStateLoad {
    @Override
    NamedState createNamedState() {
        return FlowDesignerFactoryImpl.eINSTANCE.createActionState();
    }

    @Override
    String getStateType() {
        return "actionState";
    }
}
