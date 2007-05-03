// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class FlowDesignerContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry fRegistry;
    
    public FlowDesignerContextMenuProvider(EditPartViewer viewer, 
                                            ActionRegistry registry) {
        super(viewer);
        fRegistry = registry;
    }

    @Override
    public void buildContextMenu(IMenuManager menu) {
        GEFActionConstants.addStandardActionGroups(menu);
        
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, 
                           fRegistry.getAction(ActionFactory.UNDO.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, 
                           fRegistry.getAction(ActionFactory.REDO.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_EDIT, 
                           fRegistry.getAction(ActionFactory.DELETE.getId()));
        
    }

}
