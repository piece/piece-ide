// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SetEventAttributeCommand extends Command {

    private String fAttributeName;
    private Object fAttributeValue;
    private Object fOldValue;
    
    private State fState;
    private Event fEvent;

    /**
     * コンストラクタ.
     * 
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @param state ステート
     * @param event イベント
     */
    public SetEventAttributeCommand(
                String attributeName, 
                Object attributeValue, 
                State state,
                Event event) {
        super();
        fAttributeName = attributeName;
        fAttributeValue = attributeValue;
        fState = state;
        fEvent = event;
    }

    /**
     * コマンドが実行できるか判断する.
     * ステート名の変更時、重複チェックを行う。
     * 
     * @return コマンドが実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        if (fAttributeName.equals("Event")) {
            if (!fState.checkUsableEventName((String) fAttributeValue)) {
                MessageDialog.openError(
                        null, "エラー", "イベント名が重複しています。");
                return false;
            }
        }
        return true;
    }

    /**
     * 属性名に対応する属性値を設定する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (fAttributeName.equals("Event")) {
            fOldValue = fEvent.getName();
            fEvent.setName((String) fAttributeValue);
        } else if (fAttributeName.equals("NextState")) {
            fOldValue = fEvent.getNextState();
            fEvent.setNextState((State) fAttributeValue);
        } else if (fAttributeName.equals("EventHandler")) {
            fOldValue = fEvent.getEventHandler();
            fEvent.setEventHandler((EventHandler) fAttributeValue);
        } else if (fAttributeName.equals("Guard")) {
            fOldValue = fEvent.getGuardEventHandler();
            fEvent.setGuardEventHandler((EventHandler) fAttributeValue);
        }
    }

    /**
     * 待避してあった前回値を使って、設定を元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        if (fAttributeName.equals("Event")) {
            fEvent.setName((String) fOldValue);
        } else if (fAttributeName.equals("NextState")) {
            fEvent.setNextState((State) fOldValue);
        } else if (fAttributeName.equals("EventHandler")) {
            fEvent.setEventHandler((EventHandler) fOldValue);
        } else if (fAttributeName.equals("Guard")) {
            fEvent.setGuardEventHandler((EventHandler) fOldValue);
        }
    }
}
