// $Id$
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
 * @since 0.1.0
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
     * 以下の項目は変更不可とする。<br>
     * ・ビルトインイベントのイベントハンドラ以外<br>
     * ・イニシャルステートの遷移イベントのすべての項目<br>
     *
     * @param element モデル
     * @param property プロパティ文字列
     * @return 変更できるか
     * @see org.eclipse.jface.viewers.ICellModifier
     *          #canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        Event event = (Event) element;

        if (event.getType() == Event.BUILTIN_EVENT) {
            if (!property.equals("EventHandler")) { //$NON-NLS-1$
                return false;
            }
        }

        if (fState.getType() == State.INITIAL_STATE
            && event.getType() == Event.TRANSITION_EVENT) {
            return false;
        }

        return true;
    }

    /**
     * プロパティ文字列にあったオブジェクトを返す.
     * 編集不可の場合は
     *
     * @param element モデル
     * @param property プロパティ文字列
     * @return プロパティ文字列にあったオブジェクト
     * @see org.eclipse.jface.viewers.ICellModifier
     *          #getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property) {
        Event event = (Event) element;

        if (property.equals("Name")) { //$NON-NLS-1$
            return event.getName();
        } else if (property.equals("EventHandler")) { //$NON-NLS-1$
            if (event.getEventHandler() != null) {
                return event.getEventHandler();
            } else {
                boolean isNormalState =
                            fState.getType() == State.VIEW_STATE
                            || fState.getType() == State.ACTION_STATE;
                boolean isBuildinEvent = event.getType() == Event.BUILTIN_EVENT;
                String eventName = event.generateEventHandlerMethodName();
                if (isNormalState && isBuildinEvent) {
                    eventName += "On" + fState.getName(); //$NON-NLS-1$
                }
                return eventName;
            }
        } else if (property.equals("GuardEventHandler")) { //$NON-NLS-1$
            if (event.getGuardEventHandler() != null) {
                return event.getGuardEventHandler();
            }
        }

        return ""; //$NON-NLS-1$
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
