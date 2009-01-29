// $Id$

package FlowDesigner.resource;

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
