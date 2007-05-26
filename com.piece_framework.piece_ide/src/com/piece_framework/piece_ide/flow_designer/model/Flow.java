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
public class Flow extends AbstractModel {

    private static final long serialVersionUID = 4080106758569031141L;
    
    private String fName;
    private String fActionClassName;
    private List<State> fStates;
    
    /**
     * コンストラクタ.
     * ステートリストを初期化する。
     * 
     * @param name フロー名
     * @param actionClassName アクションクラス名
     */
    public Flow(String name, String actionClassName) {
        super();
        
        setName(name);
        setActionClassName(actionClassName);
        
        fStates = new ArrayList<State>();
    }

    /**
     * フロー名を返す.
     * 
     * @return フロー名
     */
    public String getName() {
        return fName;
    }

    /**
     * フロー名を設定する.
     * 
     * @param name フロー名
     */
    public void setName(String name) {
        fName = name;
    }
    
    /**
     * アクションクラス名を返す.
     * 
     * @return アクションクラス名
     */
    public String getActionClassName() {
        return fActionClassName;
    }

    /**
     * アクションクラス名を設定する.
     * null の場合はフロー名＋Actionにする。
     * 
     * @param actionClassName アクションクラス名
     */
    public void setActionClassName(String actionClassName) {
        if (actionClassName != null) {
            fActionClassName = actionClassName;
        } else {
            fActionClassName = fName + "Action";
        }
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
