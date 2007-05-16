// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * アクションステートモデル.
 * アクションステートモデルを管理する。
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class ActionState extends NodeElement implements IPropertySource {

    private static final long serialVersionUID = -3753390580717540401L;

    /** 名前. */
    private String fName;

    /** Activityイベント. */
    private String fActivity;

    /** Entryイベント. */
    private String fEntry;

    /** Exitイベント. */
    private String fExit;

    /**
     * コンストラクタ.
     */
    public ActionState() {
        super();
    }

    /**
     * 名前を取得.
     * 
     * @return 名前
     */
    public String getName() {
        return fName;
    }

    /**
     * 名前を設定.
     * 
     * @param name
     *           名前
     */
    public void setName(String name) {
        String old = fName;
        fName = name;
        firePropertyChange("name", old, fName);
    }

    /**
     * Activityイベントを取得.
     * 
     * @return Activityイベント
     */
    public String getActivity() {
        return fActivity;
    }

    /**
     * Activityイベントを設定.
     * 
     * @param activity
     *            Activityイベント
     */
    public void setActivity(String activity) {
        String old = fActivity;
        fActivity = activity;
        firePropertyChange("activity", old, fActivity);
    }

    /**
     * Entryイベントを取得.
     * 
     * @return Entryイベント
     */
    public String getEntry() {
        return fEntry;
    }

    /**
     * Entryイベントを設定.
     * 
     * @param entry
     *            Entryイベント
     */
    public void setEntry(String entry) {
        String old = fEntry;
        fEntry = entry;
        firePropertyChange("entry", old, fEntry);
    }

    /**
     * Exitイベントを取得.
     * 
     * @return Exitイベント
     */
    public String getExit() {
        return fExit;
    }

    /**
     * Exitイベントを設定.
     * 
     * @param exit
     *            Exitイベント
     */
    public void setExit(String exit) {
        String old = fExit;
        fExit = exit;
        firePropertyChange("exit", old, fExit);
    }
    
    /**
     * エディタブル値取得処理.
     * 
     * @return 取得したオブジェクト
     */
    public Object getEditableValue() {
        return this;
    }

    /**
     * プロパティディスクリプタ取得処理.
     * 
     * @return 取得したプロパティディスクリプタ
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
                new TextPropertyDescriptor("name", "名前"),
                new TextPropertyDescriptor("activity", "activity"),
                new TextPropertyDescriptor("entry", "entry"),
                new TextPropertyDescriptor("exit", "exit") };
    }

    /**
     * プロパティ取得時処理.
     * 
     * @param id ID
     * @return 取得したオブジェクト
     */
    public Object getPropertyValue(Object id) {
        if (id.equals("name")) {
            if (fName == null) {
                return "";
            }
            return fName;
        }

        if (id.equals("activity")) {
            if (fActivity == null) {
                return "";
            }
            return fActivity;
        }

        if (id.equals("entry")) {
            if (fEntry == null) {
                return "";
            }
            return fEntry;
        }

        if (id.equals("exit")) {
            if (fExit == null) {
                return "";
            }
            return fExit;
        }

        return null;
    }

    /**
     * プロパティ存在チェック.
     * 
     * @param id ID
     * @return 結果
     */
    public boolean isPropertySet(Object id) {
        return id.equals("name") || id.equals("activity")
                || id.equals("entry") || id.equals("exit");
    }

    /**
     * プロパティリセット時処理.
     * 
     * @param id ID
     */
    public void resetPropertyValue(Object id) {
    }

    /**
     * プロパティ設定時処理.
     * 
     * @param id ID
     * @param value 値
     */
    public void setPropertyValue(Object id, Object value) {
        if (id.equals("name")) {
            setName(String.valueOf(value));
        }

        if (id.equals("activity")) {
            setActivity(String.valueOf(value));
        }

        if (id.equals("entry")) {
            setEntry(String.valueOf(value));
        }

        if (id.equals("exit")) {
            setExit(String.valueOf(value));
        }
    }
}
