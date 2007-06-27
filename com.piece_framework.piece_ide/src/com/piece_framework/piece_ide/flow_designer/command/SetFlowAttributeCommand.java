package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フロー属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SetFlowAttributeCommand extends Command {

    private String fAttributeName;
    private String fAttributeValue;
    private String fOldValue;
    
    private Flow fFlow;

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
        fAttributeName = attributeName;
        fAttributeValue = attributeValue;
        fFlow = flow;
    }
    
    /**
     * 属性名に対応する属性値を設定する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (fAttributeName.equals("name")) {
            fOldValue = fFlow.getName();
            fFlow.setName(fAttributeValue);
        } else if (fAttributeName.equals("actionClassName")) {
            fOldValue = fFlow.getActionClassName();
            fFlow.setActionClassName(fAttributeValue);
        }
    }

    /**
     * 待避してあった前回値を使って、設定を元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        if (fAttributeName.equals("name")) {
            fFlow.setName(fOldValue);
        } else if (fAttributeName.equals("actionClassName")) {
            fFlow.setActionClassName(fOldValue);
        }
    }
}
