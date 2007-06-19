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
    public List<State> getStates() {
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
