// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント表示テーブルのラベル・プロバイダー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
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
                if (event.getEventHandler() != null) {
                    return event.getEventHandler().toString();
                }
                break;
            case GUARD_COLUMN:
                if (event.getGuardEventHandler() != null) {
                    return event.getGuardEventHandler().toString();
                }
                break;
            default:
                break;
        }

        return ""; //$NON-NLS-1$
    }
}
