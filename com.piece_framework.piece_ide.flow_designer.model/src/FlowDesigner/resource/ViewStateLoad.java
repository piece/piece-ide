// $Id$

package FlowDesigner.resource;

import FlowDesigner.NamedState;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

class ViewStateLoad extends NamedStateLoad {
    @Override
    NamedState createNamedState() {
        return FlowDesignerFactoryImpl.eINSTANCE.createViewState();
    }

    @Override
    String getStateType() {
        return "viewState";
    }
}
