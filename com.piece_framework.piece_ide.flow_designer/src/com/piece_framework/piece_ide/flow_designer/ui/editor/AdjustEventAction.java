// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;

import com.piece_framework.piece_ide.flow_designer.command.AdjustEventCommand;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * イベント調整アクション.
 * イベント名・イベントハンドラ名をステート名に合わせて一括更新するアクション。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.2.0
 *
 */
public class AdjustEventAction extends EditorPartAction {
    /** イベント調整アクションID. */
    public static final String ADJUST_EVENTS = "AdjustEvents";
    
    /**
     * コンストラクタ.
     * 
     * @param editor エディター
     */
    public AdjustEventAction(IEditorPart editor) {
        super(editor);
        
        setId(ADJUST_EVENTS);
        setText("イベントの調整");
        setImageDescriptor(FlowDesignerPlugin.getImageDescriptor(
                                    "icons/AdjustEvents.gif"));
    }
    
    /**
     * 実行可能状態を返す.
     * 常にtrueを返す。
     * 
     * @return 実行可能状態
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }
    
    /**
     * フローを取得し、ステート更新コマンドを実行する.
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        Flow flow = (Flow) getEditorPart().getAdapter(Flow.class);
        
        if (flow == null) {
            return;
        }
        
        CommandStack commandStack = 
            (CommandStack) getEditorPart().getAdapter(CommandStack.class);
        
        if (commandStack != null) {
            AdjustEventCommand command = new AdjustEventCommand(flow);
            commandStack.execute(command);
        }
    }
}
