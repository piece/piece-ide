// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * イベント・ファクトリー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class EventFactory implements CreationFactory {

    private int fEventType;

    /**
     * コンストラクタ.
     *
     * @param eventType イベントタイプ
     */
    public EventFactory(int eventType) {
        if (eventType == Event.BUILTIN_EVENT
            || eventType == Event.INTERNAL_EVENT
            || eventType == Event.TRANSITION_EVENT) {
            fEventType = eventType;
        } else {
            fEventType = Event.UNKNOWN_EVENT;
        }
    }

    /**
     * イベントのインスタンスを返す.
     * イベントタイプにあったイベントを生成して返す。
     *
     * @return イベントのインスタンス
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        return new Event(fEventType);
    }

    /**
     * イベントクラスを返す.
     *
     * @return イベントクラス
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return Event.class;
    }
}
