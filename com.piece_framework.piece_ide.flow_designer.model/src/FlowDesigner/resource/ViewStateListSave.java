// $Id$

package FlowDesigner.resource;

import FlowDesigner.NamedState;
import FlowDesigner.ViewState;

class ViewStateListSave extends NamedStateListSave {
    @Override
    String getKey() {
        return "viewState";
    }

    @Override
    boolean equalStateType(NamedState state) {
        return state instanceof ViewState;
    }
}
