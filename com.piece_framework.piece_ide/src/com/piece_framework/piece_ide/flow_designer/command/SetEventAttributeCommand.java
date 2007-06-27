package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

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
    
    private Event fEvent;

    /**
     * コンストラクタ.
     * 
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @param event イベント
     */
    public SetEventAttributeCommand(
                String attributeName, 
                Object attributeValue, 
                Event event) {
        super();
        fAttributeName = attributeName;
        fAttributeValue = attributeValue;
        fEvent = event;
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
