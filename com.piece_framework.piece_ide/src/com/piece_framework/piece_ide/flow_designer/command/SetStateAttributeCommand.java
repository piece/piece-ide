package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート属性設定コマンド.
 * 指定された属性名に対応する属性値を設定する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SetStateAttributeCommand extends Command {

    private String fAttributeName;
    private String fAttributeValue;
    private String fOldValue;
    
    private State fState;

    /**
     * コンストラクタ.
     * 
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @param state ステート
     */
    public SetStateAttributeCommand(
                String attributeName, 
                String attributeValue, 
                State state) {
        super();
        fAttributeName = attributeName;
        fAttributeValue = attributeValue;
        fState = state;
    }
    
    /**
     * 属性名に対応する属性値を設定する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (fAttributeName.equals("name")) {
            fOldValue = fState.getName();
            fState.setName(fAttributeValue);
        } else if (fAttributeName.equals("summary")) {
            fOldValue = fState.getSummary();
            fState.setSummary(fAttributeValue);
        } else if (fState.getStateType() == State.VIEW_STATE
                    && fAttributeName.equals("view")) {
            fOldValue = fState.getView();
            fState.setView(fAttributeValue);
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
            fState.setName(fOldValue);
        } else if (fAttributeName.equals("summary")) {
            fState.setSummary(fOldValue);
        } else if (fState.getStateType() == State.VIEW_STATE
                    && fAttributeName.equals("view")) {
            fState.setView(fOldValue);
        }
    }
}
