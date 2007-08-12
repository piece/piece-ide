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
     * 以下のチェックを行う。<br>
     * ・旧データ値と同じ場合は実行不可。<br>
     * ・イベント名の変更時、重複チェックを行い、重複している場合は実行不可。<br>
     * 
     * @return コマンドが実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        if (isSameValue()) {
            return false;
        }
        
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
    private Object getOldValue() {
        Object oldValue = null;
        
        if (fAttributeName.equals("Event")) {
            oldValue = fEvent.getName();
        } else if (fAttributeName.equals("NextState")) {
            oldValue = fEvent.getNextState();
        } else if (fAttributeName.equals("EventHandler")) {
            oldValue = fEvent.getEventHandler();
        } else if (fAttributeName.equals("Guard")) {
            oldValue = fEvent.getGuardEventHandler();
        }
        
        return oldValue;
    }
    
    /**
     * 属性値を設定する.
     * 
     * @param value 属性値
     */
    private void setValue(Object value) {
        if (fAttributeName.equals("Event")) {
            fEvent.setName((String) value);
        } else if (fAttributeName.equals("NextState")) {
            fEvent.setNextState((State) value);
        } else if (fAttributeName.equals("EventHandler")) {
            fEvent.setEventHandler((EventHandler) value);
        } else if (fAttributeName.equals("Guard")) {
            fEvent.setGuardEventHandler((EventHandler) value);
        }
    }
    
    /**
     * 現在の属性値と新しい属性値が同じかを比較する.
     * 比較内容は以下のとおり。<br>
     * ・イベント名：文字列を比較<br>
     * ・遷移先ステート：ステート名を比較<br>
     * ・イベントハンドラ：クラス、メソッド名を比較<br>
     * ・ガードイベントハンドラ：クラス、メソッド名を比較<br>
     * 
     * @return 現在の属性値と新しい属性値が同じか
     */
    private boolean isSameValue() {
        Object oldValue = getOldValue();
        if (oldValue == null) {
            return false;
        }
        
        boolean isSame = false;
        if (fAttributeName.equals("Event")) {
            isSame = oldValue.equals(fAttributeValue);
        } else if (fAttributeName.equals("NextState")) {
            isSame = ((State) oldValue).getName().equals(
                            ((State) fAttributeValue).getName());
        } else if (fAttributeName.equals("EventHandler")
                    || fAttributeName.equals("Guard")) {
            isSame = oldValue.toString().equals(fAttributeValue.toString());
        }
         
        return isSame;
    }
}
