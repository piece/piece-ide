// $Id: SetFlowAttributeCommand.java 180 2007-07-27 00:57:08Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.command;

import java.lang.reflect.Method;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.internal.PieceIDE;

/**
 * フロー属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SetFlowAttributeCommand extends AbstractSetAttributeCommand {

    /**
     * コンストラクタ.
     * 
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @param flow フロー
     */
    public SetFlowAttributeCommand(
                String attributeName, 
                String attributeValue, 
                Flow flow) {
        super();
        setModel(flow);
        Method setterMethod = createMethod(Flow.class, 
                                           "set" + attributeName, 
                                           new Class[]{String.class});
        setSetterMethod(setterMethod);
        setAttributeValue(attributeValue);
        Method getterMethod = createMethod(Flow.class, 
                                           "get" + attributeName, 
                                           null);  
        Object oldValue = (String) executeMethod(getterMethod, flow, null);
        setOldValue(oldValue);
    }
    
    /**
     * コマンドが実行できるか判断する.
     * 以下のチェックを行う。<br>
     * ・旧データ値と同じ場合は実行不可。<br>
     * 
     * @return コマンドが実行できる場合はtrueを返す。
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        return !PieceIDE.compare(getOldValue(), getAttributeValue());
    }    
}
