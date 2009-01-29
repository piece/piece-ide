// $Id$

package FlowDesigner.resource;

import FlowDesigner.ActionState;
import FlowDesigner.NamedState;

class ActionStateListSave extends NamedStateListSave {
    @Override
    String getKey() {
        return "actionState";
    }

    @Override
    boolean equalStateType(NamedState state) {
        return state instanceof ActionState;
    }
}
