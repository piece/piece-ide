package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Item;

import com.piece_framework.piece_ide.flow_designer.command.SetEventAttributeCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;

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
    
    /**
     * コンストラクタ.
     * 
     * @param editor グラフィカル・エディター
     * @param eventTableViewer イベント表示テーブル
     */
    public EventTableCellModifier(
                GraphicalEditor editor, 
                TableViewer eventTableViewer) {
        fEventTableViewer = eventTableViewer;
        fEditor = editor;
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
        
        if (property.equals("Event")) {
            // ビルトインイベントの場合は編集不可
            if (event.isBuiltinEvent()) {
                return null;
            }
            
            return event.getName();
        } else if (property.equals("EventHandler")) {
            if (event.getEventHandler() != null) {
                return event.getEventHandler().toString();
            }
        } else if (property.equals("Guard")) {
            // ビルトインイベントの場合は編集不可
            if (event.isBuiltinEvent()) {
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
        
        Object attributeValue = null;
        
        if (property.equals("Event")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isBuiltinEvent()) {
                attributeValue = value;
            }
        } else if (property.equals("EventHandler")) {
            String eventHandlerString = String.valueOf(value);
            if (eventHandlerString != null) {
                attributeValue = new EventHandler(eventHandlerString);
            }
        } else if (property.equals("Guard")) {
            // ビルトインイベントの場合は編集不可
            if (!event.isBuiltinEvent()) {
                String guardEventHandlerString = String.valueOf(value);
                if (guardEventHandlerString != null) {
                    attributeValue = new EventHandler(guardEventHandlerString);
                }
            }
        }

        if (attributeValue != null) {
            CommandStack commandStack = 
                (CommandStack) fEditor.getAdapter(CommandStack.class);
            SetEventAttributeCommand command =
                new SetEventAttributeCommand(
                        property, attributeValue, event);
            commandStack.execute(command);
        }
        fEventTableViewer.update(element, null);
    }
}
