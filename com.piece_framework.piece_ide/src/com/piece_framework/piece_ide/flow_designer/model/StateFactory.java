// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * ステート・ファクトリー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
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
        if (State.INITIAL_STATE != fStateType 
            && State.FINAL_STATE != fStateType
            && State.ACTION_STATE != fStateType
            && State.VIEW_STATE != fStateType) {
            return null;
        }
        
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
