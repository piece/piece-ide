// $Id: Flow.java 237 2007-08-12 15:42:35Z matsufuji $
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
    
    private int fActionStateSequenceNo;
    private int fViewStateSequenceNo;
    
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
        if (getActionClassName() == null && getName() != null) {
            setActionClassName(getName() + "Action");
        }
        
        fActionStateSequenceNo = 1;
        fViewStateSequenceNo = 1;
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
        String oldValue = fName;
        if (name != null && name.length() > 0) {
            fName = name;
        } else {
            fName = null;
        }
        firePropertyChange("Flow#Name", oldValue, fName);
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
     * 
     * @param actionClassName アクションクラス名
     */
    public void setActionClassName(String actionClassName) {
        String oldValue = fActionClassName;
        if (actionClassName != null && actionClassName.length() > 0) {
            fActionClassName = actionClassName;
        } else {
            fActionClassName = null;
        }
        firePropertyChange("Flow#ActionClassName", oldValue, fActionClassName);
    }

    /**
     * ステートリストを返す.
     * 
     * @return ステートリスト
     */
    public List<State> getStateList() {
        return fStates;
    }
    
    /**
     * 指定されたステートへ遷移しているステートを返す.
     * 
     * @param ownState ステート
     * @return 指定されたステートへ遷移しているステート
     */
    public List<State> getStateListToOwnState(State ownState) {
        List<State> stateList = new ArrayList<State>();
        for (State state : getStateList()) {
            for (Event event : state.getEventList()) {
                if (event.getNextState() == ownState
                    && ownState != null) {
                    stateList.add(state);
                    break;
                }
            }
        }
        return stateList;
    }
    
    /**
     * 指定されたステート名に合致するステートを返す.
     * 該当するステートがない場合はnull を返す。
     * 
     * @param stateName ステート名
     * @return ステート
     */
    public State getStateByName(String stateName) {
        for (State state : getStateList()) {
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        return null;
    }
    
    /**
     * 指定されたステートが遷移先となるイベントの一覧を返す.
     * 
     * @param ownState ステート
     * @return 遷移イベント
     */
    public List<Event> getTransitionEventListToOwnState(State ownState) {
        List<Event> transitionEventList = new ArrayList<Event>();
        for (State state : getStateList()) {
            if (state == ownState) {
                continue;
            }
            for (Event event : state.getTransitionEventList()) {
                if (event.getNextState() == ownState) {
                    transitionEventList.add(event);
                }
            }
        }
        return transitionEventList;
    }
    
    /**
     * ステートを追加する.
     * 
     * @param state ステート
     */
    public void addState(State state) {
        fStates.add(state);
        firePropertyChange("Flow#State", null, state);
    }

    /**
     * ステートを削除する.
     * 
     * @param state ステート
     */
    public void removeState(State state) {
        fStates.remove(state);
        firePropertyChange("Flow#State", state, null);
    }

    /**
     * ステートタイプにあった新しいステート名を返す.
     * Piece_IDEではステート生成時、自動的に名前が生成される。<br>
     * 生成規則は以下のとおり。<br>
     * ・イニシャルステート："InitialState"固定<br>
     * ・ファイナルステート："FinalState"固定<br>
     * ・ビューステート："DisplayForm" + 連番<br>
     * ・アクションステート："Process" + 連番<br>
     * ビューステートとアクションステートに関しては、手動で入力された
     * 名前が生成した名前と重複する可能性を考慮して、重複チェックをと
     * おるまで連番をインクリメントする。
     * 
     * @param stateType ステートタイプ
     * @return 新しいステート名
     */
    public String generateStateName(int stateType) {
        String stateName = null;
        while (!checkUsableStateName(stateName)) {
            if (stateType == State.INITIAL_STATE) {
                stateName = "InitialState";
            } else if (stateType == State.VIEW_STATE) {
                stateName = "DisplayForm" + fViewStateSequenceNo;
                fViewStateSequenceNo++;
            } else if (stateType == State.ACTION_STATE) {
                stateName = "Process" + fActionStateSequenceNo;
                fActionStateSequenceNo++;
            } else if (stateType == State.FINAL_STATE) {
                stateName = "FinalState";
            }
        }
        return stateName;
    }
    
    /**
     * 指定されたステート名が使用可能かチェックする.
     * 
     * @param stateName ステート名
     * @return 使用可能なステート名の場合はtrue
     */
    public boolean checkUsableStateName(String stateName) {
        if (stateName == null) {
            return false;
        }
        for (State state : getStateList()) {
            if (state.getName() == null) {
                continue;
            }
            if (state.getName().equals(stateName)) {
                return false;
            }
        }
        return true;
    }
}
