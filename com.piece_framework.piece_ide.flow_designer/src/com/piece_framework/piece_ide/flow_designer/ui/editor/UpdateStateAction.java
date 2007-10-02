// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class UpdateStateAction extends EditorPartAction {

    public static final String UPDATE_STATE = "UpdateState";
    
    public UpdateStateAction(IEditorPart editor) {
        super(editor);
        
        setId(UPDATE_STATE);
        setText("ステート更新");
    }
    
    @Override
    protected boolean calculateEnabled() {
        return true;
    }
    
    @Override
    public void run() {
        Flow flow = (Flow) getEditorPart().getAdapter(Flow.class);
        
        if (flow == null) {
            return;
        }
        
        System.out.println(flow);
    }
}
