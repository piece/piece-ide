// $Id$

package FlowDesigner.diagram.resources;

import FlowDesigner.ActionState;
import FlowDesigner.NamedState;

class ActionStateListSave extends NamedStateListSave {
    @Override
    protected String getKey() {
        return "actionState";
    }

    @Override
    protected boolean equalStateType(NamedState state) {
        return state instanceof ActionState;
    }
}
