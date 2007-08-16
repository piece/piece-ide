// $Id: SetStateAttributeCommand.java 211 2007-08-07 01:26:43Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
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
    
    private Flow fFlow;
    private State fState;

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
        fAttributeValue = attributeValue;
        fFlow = flow;
        fState = state;
    }

    /**
     * コマンドが実行できるか判断する.
     * 以下のチェックを行う。<br>
     * ・旧データ値と同じ場合は実行不可。<br>
     * ・ステート名の変更時、重複チェックを行い、重複している場合は実行不可。<br>
     * 
     * @return コマンドが実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        if (getOldValue() != null && getOldValue().equals(fAttributeValue)) {
            return false;
        }
        
        if (fAttributeName.equals("name")) {
            if (!fFlow.checkUsableStateName(fAttributeValue)) {
                MessageDialog.openError(
                        null, "エラー", "ステート名が重複しています。");
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
        fOldValue = getOldValue();
        setValue(fAttributeValue);
    }

    /**
     * 待避してあった前回値を使って、設定を元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        setValue(fOldValue);
    }
    
    /**
     * 属性名に該当する現在の属性値を返す.
     * 
     * @return 属性値
     */
    private String getOldValue() {
        String oldValue = null;
        
        if (fAttributeName.equals("name")) {
            oldValue = fState.getName();
        } else if (fAttributeName.equals("summary")) {
            oldValue = fState.getSummary();
        } else if (fState.getType() == State.VIEW_STATE
                    && fAttributeName.equals("view")) {
            oldValue = fState.getView();            
        }
        
        return oldValue;
    }
    
    /**
     * 属性値を設定する.
     * 
     * @param value 属性値
     */
    private void setValue(String value) {
        if (fAttributeName.equals("name")) {
            fState.setName(value);
        } else if (fAttributeName.equals("summary")) {
            fState.setSummary(value);
        } else if (fState.getType() == State.VIEW_STATE
                    && fAttributeName.equals("view")) {
            fState.setView(value);
        }
    }
}
