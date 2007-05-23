// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * フロークラス.
 * フロークラスはステートを保持する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Flow extends AbstractModel implements IFlow {

    private static final long serialVersionUID = 4080106758569031141L;
    
    private List<State> fStates;
    
    /**
     * コンストラクタ.
     * ステートリストを初期化する。
     */
    public Flow() {
        super();
        fStates = new ArrayList<State>();
    }
    
    /**
     * ステートリストを返す.
     * 
     * @return ステートリスト
     * @see com.piece_framework.piece_ide.flow_designer.model.IFlow#getStates()
     */
    public List getStates() {
        return fStates;
    }

    /**
     * ステートを追加する.
     * 
     * @param state ステート
     * @see com.piece_framework.piece_ide.flow_designer.model.IFlow#addState(
     *          com.piece_framework.piece_ide.flow_designer.model.State)
     */
    public void addState(State state) {
        fStates.add(state);
        state.setParent(this);
        firePropertyChange("state", null, null);
    }

    /**
     * ステートを削除する.
     * 
     * @param state ステート
     * @see com.piece_framework.piece_ide.flow_designer.model.IFlow#removeState(
     *          com.piece_framework.piece_ide.flow_designer.model.State)
     */
    public void removeState(State state) {
        fStates.remove(state);
        state.setParent(null);
        firePropertyChange("state", null, null);
    }
}
