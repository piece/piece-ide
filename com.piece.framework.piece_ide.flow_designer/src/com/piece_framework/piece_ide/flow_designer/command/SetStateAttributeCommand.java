// $Id: SetStateAttributeCommand.java 211 2007-08-07 01:26:43Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.command;

import java.lang.reflect.Method;

import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * ステート属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
/**
 * @author matsu
 *
 */
public class SetStateAttributeCommand extends AbstractSetAttributeCommand {
    private String fAttributeName;
    private Flow fFlow;
    
    /**
     * コンストラクタ.
     * 
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @param flow フロー
     * @param state ステート
     */
    public SetStateAttributeCommand(
                String attributeName, 
                String attributeValue, 
                Flow flow,
                State state) {
        super();
        fAttributeName = attributeName;
        fFlow = flow;
        
        setModel(state);
        Method setterMethod = createMethod(State.class, 
                                           "set" + attributeName, 
                                           new Class[]{String.class});
        setSetterMethod(setterMethod);
        setAttributeValue(attributeValue);
        Method getterMethod = createMethod(State.class, 
                                           "get" + attributeName, 
                                           null);  
        Object oldValue = (String) executeMethod(getterMethod, state, null);
        setOldValue(oldValue);
    }

    /**
     * コマンド実行の可否を決める個別の条件を判断する.
     * ・ステート名が重複している場合は実行不可とする。<br>
     * 
     * @return コマンドが実行できる場合はtrueを返す。
     * @see com.piece_framework.piece_ide.flow_designer.command
     *          .AbstractSetAttributeCommand#canExecuteSpecialCase()
     */
    @Override
    boolean canExecuteSpecialCase() {
        if (fAttributeName.equals("Name")) {
            if (!fFlow.checkUsableStateName((String) getAttributeValue())) {
                MessageDialog.openError(
                        null, 
                        Messages.getString(
                            "SetStateAttributeCommand.StateNameErrorTitle"),
                        Messages.getString(
                             "SetStateAttributeCommand.StateNameError"));
                return false;
            }
        }
        return true;
    }
}
