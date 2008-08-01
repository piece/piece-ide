// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import org.eclipse.ui.actions.RetargetAction;

import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * イベント調整リターゲットアクション.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.2.0
 *
 */
public class AdjustEventsRetargetAction extends RetargetAction {

    /**
     * コンストラクタ.
     *
     */
    public AdjustEventsRetargetAction() {
        super(AdjustEventsAction.ADJUST_EVENTS,
                Messages.getString("AdjustEvents.Label")); //$NON-NLS-1$
        setToolTipText(
                Messages.getString("AdjustEvents.ToolTipText")); //$NON-NLS-1$
        setImageDescriptor(
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/AdjustEvents.gif")); //$NON-NLS-1$
    }
}
