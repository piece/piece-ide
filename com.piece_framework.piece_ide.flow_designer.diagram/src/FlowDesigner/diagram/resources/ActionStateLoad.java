// $Id$

package FlowDesigner.diagram.resources;

import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

class ActionStateLoad extends NamedStateLoad {
    @Override
    NamedState createNamedState() {
        return FlowDesignerFactoryImpl.eINSTANCE.createActionState();
    }

    @Override
    String getStateType() {
        return "actionState";
    }
}
