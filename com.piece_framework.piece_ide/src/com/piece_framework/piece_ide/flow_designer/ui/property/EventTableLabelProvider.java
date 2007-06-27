package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント表示テーブルのラベルプロバイダー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class EventTableLabelProvider extends LabelProvider implements
        ITableLabelProvider {

    private static final int EVENT_COLUMN = 0;
    private static final int NEXT_STATE_COLUMN = 1;
    private static final int EVENT_HANDLER_COLUMN = 2;
    private static final int GUARD_COLUMN = 3;
    
    /**
     * 行に対応するイメージを返す.
     * 
     * @param element モデル
     * @param columnIndex 行インデックス
     * @return 行に対応するイメージ
     * @see org.eclipse.jface.viewers.ITableLabelProvider
     *          #getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }
    
    /**
     * 行に対応する文字列を返す.
     * 
     * @param element モデル
     * @param columnIndex 行インデックス
     * @return 行に対応する文字列
     * @see org.eclipse.jface.viewers.ITableLabelProvider
     *          #getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {
        Event event = (Event) element;
        
        switch (columnIndex) {
            case EVENT_COLUMN:
                if (event.getName() != null) {
                    return event.getName();
                }
                break;
            case NEXT_STATE_COLUMN:
                State nextState = event.getNextState();
                if (nextState != null && nextState.getName() != null) {
                    return nextState.getName();
                }
                break;
            case EVENT_HANDLER_COLUMN:
                return getDisplayEvent(event.getEventHandler());
            case GUARD_COLUMN:
                return getDisplayEvent(event.getGuardEventHandler());
            default:
                break;
        }
        
        return "";
    }

    /**
     * イベントハンドラーの表示用文字列として、
     * クラス名とメソッド名を ":" でつないで返す.
     * 
     * @param eventHandler イベントハンドラー
     * @return 表示用文字列
     */
    private String getDisplayEvent(EventHandler eventHandler) {
        if (eventHandler == null) {
            return "";
        }
        
        String className = eventHandler.getClassName();
        String methodName = eventHandler.getMethodName();
        
        if (className != null && methodName != null) {
            return className + ":" + methodName;
        } else if (methodName != null) {
            return ":" + methodName;
        }
        
        return "";
    }
}
