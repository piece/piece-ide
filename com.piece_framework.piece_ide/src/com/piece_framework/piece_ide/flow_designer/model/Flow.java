// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * フロークラス.
 * フロークラスはステートを保持する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Flow extends AbstractModel implements IPropertySource {

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
                if (event.getNextState() == ownState) {
                    stateList.add(state);
                    break;
                }
            }
        }
        return stateList;
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
        firePropertyChange("state", null, null);
    }

    /**
     * ステートを削除する.
     * 
     * @param state ステート
     */
    public void removeState(State state) {
        fStates.remove(state);
        firePropertyChange("state", null, null);
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
    
    /**
     * エディット可能なオブジェクトを返す.
     * 
     * @return 自身 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return this;
    }

    /**
     * プロパティ・ディスクリプタ一覧を返す.
     * 
     * @return 編集可能なプロパティ・ディスクリプタ一覧
     * @see org.eclipse.ui.views.properties.IPropertySource
     *          #getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        List<IPropertyDescriptor> list = new ArrayList<IPropertyDescriptor>();
        
        list.add(new TextPropertyDescriptor("name", "フロー名"));
        list.add(new TextPropertyDescriptor("actionClassName", 
                                             "アクションクラス名"));
        
        return list.toArray(new IPropertyDescriptor[0]);
    }

    /**
     * プロパティの値を返す.
     * 
     * @param id プロパティ名
     * @return プロパティ
     * @see org.eclipse.ui.views.properties.IPropertySource
     *          #getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        Object ret = null;
        
        if (id.equals("name")) {
            if (fName != null) {
                ret = fName;
            } else {
                ret = "";
            }
        }
        
        if (id.equals("actionClassName")) {
            if (fActionClassName != null) {
                ret = fActionClassName;
            } else {
                ret = "";
            }
        }
        
        return ret;
    }

    /**
     * プロパティの存在チェック.
     * 
     * @param id プロパティ名
     * @return チェック結果
     * @see org.eclipse.ui.views.properties.IPropertySource
     *          #isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        return id.equals("name") 
                || id.equals("actionClassName");
    }

    /**
     * プロパティの値をリセットする.
     * 
     * @param id プロパティ名
     * @see org.eclipse.ui.views.properties.IPropertySource
     *          #resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
    }

    /**
     * プロパティの値を設定する.
     * 
     * @param id プロパティ名
     * @param value プロパティ値
     * @see org.eclipse.ui.views.properties.IPropertySource
     *          #setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        if (id.equals("name")) {
            setName(String.valueOf(value));
        }
        
        if (id.equals("actionClassName")) {
            setActionClassName(String.valueOf(value));
        }
    }
}
