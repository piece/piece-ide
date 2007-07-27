// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.io.Serializable;

/**
 * イベントハンドラクラス.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class EventHandler implements Serializable {

    private static final long serialVersionUID = 8627376257429314590L;
    
    private String fClassName;
    private String fMethodName;
    
    /**
     * コンストラクタ.
     * 
     * @param className クラス名
     * @param methodName メソッド名
     */
    public EventHandler(String className, String methodName) {
        setClassName(className);
        setMethodName(methodName);
    }
    
    /**
     * コンストラクタ.
     * クラス名とメソッド名が ":" で接続された文字列からクラス名
     * とメソッド名を分割して、設定する。
     * 
     * @param classAndMethodName クラス名 + ":" + メソッド名
     */
    public EventHandler(String classAndMethodName) {
        if (classAndMethodName == null) {
            return;
        }
        String[] arrayValue = classAndMethodName.split(":");
        
        String className = null;
        String methodName = null;
        
        if (arrayValue.length == 2) {
            className = arrayValue[0];
            methodName = arrayValue[1];
        } else if (arrayValue.length == 1) {
            methodName = arrayValue[0];
        }
        
        setClassName(className);
        setMethodName(methodName);
    }
    
    /**
     * クラス名を返す.
     * 
     * @return クラス名
     */
    public String getClassName() {
        return fClassName;
    }
    
    /**
     * クラス名を設定する.
     * 
     * @param className クラス名
     */
    public void setClassName(String className) {
        fClassName = className;
    }

    /**
     * メソッド名を返す.
     * 
     * @return メソッド名
     */
    public String getMethodName() {
        return fMethodName;
    }
    
    /**
     * メソッド名を設定する.
     * 
     * @param methodName メソッド名
     */
    public void setMethodName(String methodName) {
        fMethodName = methodName;
    }

    /**
     * クラス名・メソッド名を ":" で接続した文字列を返す.
     * 
     * @return クラス名・メソッド名を ":" で接続した文字列 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String className = getClassName();
        String methodName = getMethodName();
        
        if (className != null && methodName != null) {
            return className + ":" + methodName;
        } else if (methodName != null) {
            return ":" + methodName;
        }
        return "";
    }   
}
