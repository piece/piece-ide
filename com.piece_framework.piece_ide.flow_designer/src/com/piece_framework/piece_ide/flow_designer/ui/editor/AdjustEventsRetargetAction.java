// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.ui.actions.RetargetAction;

import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * イベント調整リターゲットアクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.2.0
 *
 */
public class AdjustEventsRetargetAction extends RetargetAction {
    
    /**
     * コンストラクタ.
     * 
     */
    public AdjustEventsRetargetAction() {
        super(AdjustEventsAction.ADJUST_EVENTS, "イベントの調整");
        setToolTipText("ステート名に合わせて、イベントを調整します。");
        setImageDescriptor(
            FlowDesignerPlugin.getImageDescriptor("icons/AdjustEvents.gif"));
    }
}
