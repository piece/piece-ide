// $Id: EventTableCellModifier.java 232 2007-08-12 01:24:05Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Item;

import com.piece_framework.piece_ide.flow_designer.command.SetEventAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント表示テーブルのセル・モディファイヤー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class EventTableCellModifier implements ICellModifier {

    private GraphicalEditor fEditor;
    private TableViewer fEventTableViewer;
    
    private State fState;
    
    /**
     * コンストラクタ.
     * 
     * @param editor グラフィカル・エディター
     * @param eventTableViewer イベント表示テーブル
     * @param state ステート
     */
    public EventTableCellModifier(
                GraphicalEditor editor, 
                TableViewer eventTableViewer,
                State state) {
        fEventTableViewer = eventTableViewer;
        fEditor = editor;
        fState = state;
    }
    
    /**
     * 変更できるかどうかを判定する.
     * プロパティ文字列が null でなれば、変更できると判断する。
     * 
     * @param element モデル
     * @param property プロパティ文字列
     * @return 変更できるか
     * @see org.eclipse.jface.viewers.ICellModifier
     *          #canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        if (property != null) {
            return true;
        }
        return false;
    }

    /**
     * プロパティ文字列にあったオブジェクトを返す.
     * 
     * @param element モデル
     * @param property プロパティ文字列
     * @return プロパティ文字列にあったオブジェクト
     * @see org.eclipse.jface.viewers.ICellModifier
     *          #getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property) {
        Event event = (Event) element;
        
        if (property.equals("Name")) {
            // ビルトインイベントの場合は編集不可
            if (event.getType() == Event.BUILTIN_EVENT) {
                return null;
            }
            
            return event.getName();
        } else if (property.equals("EventHandler")) {
            if (event.getEventHandler() != null) {
                return event.getEventHandler().toString();
            } else {
                if (fState.getType() == State.INITIAL_STATE
                    || fState.getType() == State.FINAL_STATE) {
                    return event.generateEventHandlerMethodName();
                } else if (fState.getType() == State.VIEW_STATE
                            || fState.getType() == State.ACTION_STATE) {
                    return event.generateEventHandlerMethodName() 
                            + "On" + fState.getName();
                }
            }
        } else if (property.equals("GuardEventHandler")) {
            // ビルトインイベントの場合は編集不可
            if (event.getType() == Event.BUILTIN_EVENT) {
                return null;
            }
            
            if (event.getGuardEventHandler() != null) {
                return event.getGuardEventHandler().toString();
            }
        }
        
        return "";
    }

    /**
     * 変更を確定する.
     * SetEventAttributeCommand を使用して Event の変更を行う。
     * 
     * @param element モデル
     * @param property プロパティ文字列
     * @param value 変更されたデータ
     * @see org.eclipse.jface.viewers.ICellModifier
     *          #modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {
        if (element instanceof Item) {
            element = ((Item) element).getData();
        }
        Event event = (Event) element;
        String attributeValue = String.valueOf(value);
        
        CommandStack commandStack = 
            (CommandStack) fEditor.getAdapter(CommandStack.class);
        SetEventAttributeCommand command =
            new SetEventAttributeCommand(
                    property, attributeValue, fState, event);
        commandStack.execute(command);

        fEventTableViewer.update(element, null);
    }
}
