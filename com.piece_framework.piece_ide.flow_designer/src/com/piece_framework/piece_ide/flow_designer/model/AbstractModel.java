// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * モデル抽象クラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public abstract class AbstractModel implements Serializable {
    private static final long serialVersionUID = -298583418873971664L;
    private PropertyChangeSupport fSupport;

    /**
     * コンストラクタ.
     */
    public AbstractModel() {
        fSupport = new PropertyChangeSupport(this);
    }

    /**
     * オブジェクトの変更通知イベント.
     *
     * @param name プロパティ名
     * @param oldValue 変更前オブジェクト
     * @param newValue 変更後オブジェクト
     */
    protected void firePropertyChange(String name,
                                        Object oldValue,
                                        Object newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * 整数の変更通知イベント.
     *
     * @param name プロパティ名
     * @param oldValue 変更前整数値
     * @param newValue 変更後整数値
     */
    protected void firePropertyChagen(String name,
                                        int oldValue,
                                        int newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * ブール型の変更通知イベント.
     *
     * @param name プロパティ名
     * @param oldValue 変更前ブール値
     * @param newValue 変更後ブール値
     */
    protected void firePropertyChagen(String name,
                                        boolean oldValue,
                                        boolean newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * プロパティ変更リスナーを追加する.
     *
     * @param listener リスナー
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        fSupport.addPropertyChangeListener(listener);
    }

    /**
     * プロパティ変更リスナーを削除する.
     *
     * @param listener リスナー
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        fSupport.removePropertyChangeListener(listener);
    }

    /**
     * プロパティ変更リスナーを取得する.
     *
     * @return リスナー
     */
    public PropertyChangeListener[] getPropertyChangeListener() {
        return fSupport.getPropertyChangeListeners();
    }
}

