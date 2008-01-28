// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * フィールドクラス.
 * 
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public final class Field {
    private PropertyChangeSupport fSupport;

    private String fName;
    private String fDescription;
    private boolean fRequired;
    private boolean fForceValidation;
    private String fMessage;

    private List<Validator> fValidators;

    /**
     * コンストラクタ.
     * 
     * @param name フィールド名
     */
    public Field(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        fName = name;
        fDescription = "";
        fRequired = false;
        fForceValidation = false;
        fMessage = "";
        
        fValidators = new ArrayList<Validator>();

        fSupport = new PropertyChangeSupport(this);
    }

    /**
     * フィールド名を返す.
     * 
     * @return フィールド名
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド名を設定する.
     * 
     * @param name フィールド名
     */
    public void setName(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        String oldValue = fName;
        fName = name;
        firePropertyChange("Field#Name", oldValue, fName); //$NON-NLS-1$
    }

    /**
     * 説明を返す.
     * 
     * @return 説明
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * 説明を設定する.
     * 
     * @param description 説明
     */
    public void setDescription(final String description) {
        if (description == null) {
            throw new NullPointerException();
        }
        String oldValue = fDescription;
        fDescription = description;
        firePropertyChange("Field#Description", 
                           oldValue, 
                           fDescription);
    }

    /**
     * 必須かどうかを返す.
     * 
     * @return 必須の場合はtrue
     */
    public boolean isRequired() {
        return fRequired;
    }

    /**
     * 必須かどうかを設定する.
     * 
     * @param required 必須の場合はtrue
     */
    public void setRequired(final boolean required) {
        boolean oldValue = fRequired;
        fRequired = required;
        firePropertyChange("Field#Required", 
                oldValue, 
                fRequired);
    }

    /**
     * フィールドの状態にかかわらずバリデートを行うかを返す.
     * 
     * @return フィールドの状態にかかわらずバリデートを行う場合はtrue
     */
    public boolean isForceValidation() {
        return fForceValidation;
    }

    /**
     * フィールドの状態にかかわらずバリデートを行うかを設定する.
     * 
     * @param forceValidation フィールドの状態にかかわらずバリデートを行う場合はtrue
     */
    public void setForceValidation(final boolean forceValidation) {
        boolean oldValue = fForceValidation;
        fForceValidation = forceValidation;
        firePropertyChange("Field#ForceValidation", 
                oldValue, 
                fForceValidation);
    }

    /**
     * 必須チェック時のエラーメッセージを返す.
     * 
     * @return 必須チェック時のエラーメッセージ
     */
    public String getMessage() {
        return fMessage;
    }

    /**
     * 必須チェック時のエラーメッセージを設定する.
     * 
     * @param message 必須チェック時のエラーメッセージ
     */
    public void setMessage(final String message) {
        if (fMessage == null) {
            throw new NullPointerException();
        }
        String oldValue = fMessage;
        fMessage = message;
        firePropertyChange("Field#Message", oldValue, fMessage); //$NON-NLS-1$
    }

    public List<Validator> getValidators() {
        if (fValidators == null) {
            return null;
        }
        return Collections.unmodifiableList(fValidators);
    }

    public void addValidator(Validator validator) {
        if (validator == null) {
            throw new NullPointerException();
        }
        fValidators.add(new Validator(validator));
    }

    /**
     * オブジェクトデータの設定変更通知を行う.
     * 
     * @param name 変更箇所名
     * @param oldValue 変更前文字列
     * @param newValue 変更後文字列
     */
    protected void firePropertyChange(String name, 
                                    Object oldValue, 
                                    Object newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * boolean型データの設定変更通知を行う.
     * 
     * @param name 変更箇所名
     * @param oldValue 変更前値
     * @param newValue 変更後値
     */
    protected void firePropertyChange(String name, 
                                    boolean oldValue, 
                                    boolean newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * 設定変更通知リスナーを追加する.
     * 
     * @param listener 設定変更通知リスナー
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        fSupport.addPropertyChangeListener(listener);
    }

    /**
     * 設定変更通知リスナーを削除する.
     * 
     * @param listener 設定変更通知リスナー
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        fSupport.removePropertyChangeListener(listener);
    }
    
    /**
     * ふたつのFieldオブジェクトを入れ替える.
     * 入れ替えられるオブジェクトの参照は変更せず、プロパティーの値のみを入れ替えます。
     * 
     * @param field1 Fieldオブジェクト
     * @param field2 Fieldオブジェクト
     */
    public static void swap(final Field field1, final Field field2) {
        Field tmp = new Field(field1.getName());
        copy(field1, tmp);
        copy(field2, field1);
        copy(tmp, field2);

        field1.firePropertyChange("Field#Swap", null, null);
        field2.firePropertyChange("Field#Swap", null, null);
    }

    /**
     * Fieldオブジェクトのプロパティーの値をコピーする.
     * 
     * @param source コピー元Fieldオブジェクト
     * @param destnation コピー先Fieldオブジェクト
     */
    private static void copy(final Field source, final Field destnation) {
        destnation.fName = source.fName;
        destnation.fDescription = source.fDescription;
        destnation.fRequired = source.fRequired;
        destnation.fForceValidation = source.fForceValidation;
        destnation.fMessage = source.fMessage;
        destnation.fSupport = new PropertyChangeSupport(destnation);
        for (PropertyChangeListener listener
                : source.fSupport.getPropertyChangeListeners()) {
            destnation.addPropertyChangeListener(listener);
        }
    }
}
