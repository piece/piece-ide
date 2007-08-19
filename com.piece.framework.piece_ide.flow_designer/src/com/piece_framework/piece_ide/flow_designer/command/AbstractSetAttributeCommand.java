package com.piece_framework.piece_ide.flow_designer.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;

/**
 * 属性設定コマンド抽象クラス.
 * 継承したクラスが作成したsetterメソッドでコマンドを実行する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class AbstractSetAttributeCommand extends Command {
    private Object fAttributeValue;
    private Object fOldValue;
    
    private Method fSetterMethod;
    
    private AbstractModel fModel;

    /**
     * 属性名に対応する属性値を設定する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        executeMethod(fSetterMethod, fModel, new Object[]{fAttributeValue});
    }

    /**
     * 待避してあった前回値を使って、設定を元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        executeMethod(fSetterMethod, fModel, new Object[]{fOldValue});
    }
    
    /**
     * メソッドオブジェクトを生成する.
     * 
     * @param klass クラス
     * @param methodName メソッド名
     * @param parameters パラメータ型
     * @return メソッドオブジェクト
     */
    protected Method createMethod(
                            Class klass, 
                            String methodName, 
                            Class[] parameters) {
        Method method = null;
        try {
            method = 
                klass.getMethod(methodName, parameters);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }
        return method;
    }

    /**
     * メソッドを実行する.
     * 
     * @param method メソッドオブジェクト
     * @param object 実行対象オブジェクト
     * @param parameters パラメータ
     * @return メソッドの戻り値
     */
    protected Object executeMethod(
                            Method method,
                            Object object, 
                            Object[] parameters) {
        Object returnObject = null;
        try {
            returnObject = method.invoke(object, parameters);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }
        return returnObject;
    }

    /**
     * 属性値を返す.
     * 
     * @return 属性値
     */
    protected Object getAttributeValue() {
        return fAttributeValue;
    }

    /**
     * 属性値を設定する.
     * 
     * @param attributeValue 属性値
     */
    protected void setAttributeValue(Object attributeValue) {
        fAttributeValue = attributeValue;
    }

    /**
     * モデルを返す.
     * 
     * @return モデル
     */
    protected AbstractModel getModel() {
        return fModel;
    }

    /**
     * モデルを設定する.
     * 
     * @param model モデル
     */
    protected void setModel(AbstractModel model) {
        fModel = model;
    }

    /**
     * 旧属性値を返す.
     * 
     * @return 旧属性値
     */
    protected Object getOldValue() {
        return fOldValue;
    }

    /**
     * 旧属性値を設定する.
     * 
     * @param oldValue 旧属性値
     */
    protected void setOldValue(Object oldValue) {
        fOldValue = oldValue;
    }

    /**
     * setterメソッドを返す.
     * 
     * @return setterメソッド
     */
    protected Method getSetterMethod() {
        return fSetterMethod;
    }

    /**
     * setterメソッドを設定する.
     * 
     * @param setterMethod setterメソッド
     */
    protected void setSetterMethod(Method setterMethod) {
        fSetterMethod = setterMethod;
    }
}
