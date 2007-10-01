// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.lang.reflect.Method;

import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * イベント属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SetEventAttributeCommand extends AbstractSetAttributeCommand {
    private String fAttributeName;
    private State fState;

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
                String attributeValue, 
                State state,
                Event event) {
        super();
        fAttributeName = attributeName;
        fState = state;
        
        setModel(event);
        Method setterMethod = createMethod(Event.class, 
                                           "set" + attributeName,  
                                           new Class[]{String.class});
        setSetterMethod(setterMethod);
        setAttributeValue(attributeValue);
        Method getterMethod = createMethod(Event.class, 
                                           "get" + attributeName,  
                                           null);  
        Object oldValue = (String) executeMethod(getterMethod, event, null);
        setOldValue(oldValue);
    }

    /**
     * コマンド実行の可否を決める個別の条件を判断する.
     * ・イベント名が重複している場合は実行不可とする。<br>
     * 
     * @return コマンドが実行できる場合はtrueを返す。
     * @see com.piece_framework.piece_ide.flow_designer.command
     *          .AbstractSetAttributeCommand#canExecuteSpecialCase()
     */
    @Override
    boolean canExecuteSpecialCase() {
        Event event = (Event) getModel();
        if (event.getType() == Event.BUILTIN_EVENT) {
            if (fAttributeName.equals("Name")  
                || fAttributeName.equals("GuardEventHandler")) { 
                return false;
            }
        }
        if (fAttributeName.equals("Name")) { 
            if (!fState.checkUsableEventName((String) getAttributeValue())) {
                MessageDialog.openError(
                        null, 
                        Messages.getString(
                            "SetEventAttributeCommand.EventNameErrorTitle"), 
                        Messages.getString(
                            "SetEventAttributeCommand.EventNameError"));  
                return false;
            }
        }
        return true;
    }
}
