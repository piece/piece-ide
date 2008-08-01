// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * ステート・ファクトリー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class StateFactory implements CreationFactory {

    private int fStateType;

    /**
     * コンストラクタ.
     *
     * @param stateType ステートタイプ
     */
    public StateFactory(int stateType) {
        if (State.INITIAL_STATE == stateType
            || State.FINAL_STATE == stateType
            || State.ACTION_STATE == stateType
            || State.VIEW_STATE == stateType) {
            fStateType = stateType;
        } else {
            fStateType = State.UNKNOWN_STATE;
        }
    }

    /**
     * ステートのインスタンスを返す.
     * ステートタイプにあったステートを生成して返す。
     *
     * @return ステートのインスタンス
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        return new State(fStateType);
    }

    /**
     * ステートクラスを返す.
     *
     * @return ステートクラス
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return State.class;
    }

}
