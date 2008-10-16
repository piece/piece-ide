// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.lang.reflect.Method;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フロー属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 *
 * @author MATSUFUJI Hideharu
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
                                           "set" + attributeName,  //$NON-NLS-1$
                                           new Class[]{String.class});
        setSetterMethod(setterMethod);
        setAttributeValue(attributeValue);
        Method getterMethod = createMethod(Flow.class,
                                           "get" + attributeName,  //$NON-NLS-1$
                                           null);
        Object oldValue = (String) executeMethod(getterMethod, flow, null);
        setOldValue(oldValue);
    }

    /**
     * コマンド実行の可否を決める個別の条件を判断する.
     *
     * @return コマンドが実行できる場合はtrueを返す。
     * @see com.piece_framework.piece_ide.flow_designer.command
     *          .AbstractSetAttributeCommand#canExecuteSpecialCase()
     */
    @Override
    boolean canExecuteSpecialCase() {
        return true;
    }
}
