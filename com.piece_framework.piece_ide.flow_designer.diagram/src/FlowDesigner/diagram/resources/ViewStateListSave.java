// $Id$

package FlowDesigner.diagram.resources;

import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

class ViewStateListSave extends NamedStateListSave {
    @Override
    protected String getKey() {
        return "viewState";
    }

    @Override
    protected boolean equalStateType(NamedState state) {
        return state instanceof ViewState;
    }
}
