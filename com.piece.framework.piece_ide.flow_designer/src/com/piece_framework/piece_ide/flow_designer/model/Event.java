// $Id: Event.java 165 2007-07-23 02:06:53Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.model;

import java.io.Serializable;

/**
 * イベントクラス.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Event extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 2259605742197882832L;
    
    /** イベントタイプ定数：ビルトインイベント. */
    public static final int BUILTIN_EVENT = 1;
    /** イベントタイプ定数：内部イベント. */
    public static final int INTERNAL_EVENT = 2;
    /** イベントタイプ定数：遷移イベント. */
    public static final int TRANSITION_EVENT = 3;
    /** イベントタイプ定数：不明. */
    public static final int UNKNOWN_EVENT = 0;
    
    private String fName;
    private State fNextState;
    private int fType;
    private EventHandler fEventHandler;
    private EventHandler fGuardEventHandler;
    
    /**
     * イベントハンドラクラス.
     * 
     * @author MATSUFUJI Hideharu
     * @version 0.1.0
     * @since 0.1.0
     *
     */
    private final class EventHandler implements Serializable {

        private static final long serialVersionUID = 8627376257429314590L;
        
        private String fClassName;
        private String fMethodName;
        
        /**
         * コンストラクタ.
         * クラス名とメソッド名が ":" で接続された文字列からクラス名
         * とメソッド名を分割して、設定する。
         * 
         * @param classAndMethodName クラス名 + ":" + メソッド名
         */
        private EventHandler(String classAndMethodName) {
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
                if (classAndMethodName.endsWith(":")) {
                    className = arrayValue[0];
                } else {
                    methodName = arrayValue[0];
                }
            }
            
            setClassName(className);
            setMethodName(methodName);
        }
        
        /**
         * クラス名を返す.
         * 
         * @return クラス名
         */
        private String getClassName() {
            return fClassName;
        }
        
        /**
         * クラス名を設定する.
         * 
         * @param className クラス名
         */
        private void setClassName(String className) {
            if (className != null && className.length() > 0) {
                fClassName = className;
            } else {
                fClassName = null;
            }
        }

        /**
         * メソッド名を返す.
         * 
         * @return メソッド名
         */
        private String getMethodName() {
            return fMethodName;
        }
        
        /**
         * メソッド名を設定する.
         * 
         * @param methodName メソッド名
         */
        private void setMethodName(String methodName) {
            if (methodName != null && methodName.length() > 0) {
                fMethodName = methodName;
            } else {
                fMethodName = null;
            }
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
            
            if (className != null && className.length() > 0 
                && methodName != null && methodName.length() > 0) {
                return className + ":" + methodName;
            } else if (methodName != null && methodName.length() > 0) {
                return methodName;
            }
            return null;
        }   
    }
    
    /**
     * コンストラクタ.
     * イベントタイプは以下の定数のいずれかである必要があります。<br>
     * ビルトインイベント：BUILTIN_EVENT<br>
     * 内部イベント：INTERNAL_EVENT<br>
     * 遷移イベント：TRANSITION_EVENT<br>
     * これら以外のイベントタイプが指定された場合は、UNKNOWN_EVENTが
     * セットされます。
     * 
     * @param type イベントタイプ
     */
    public Event(int type) {
        if (type == BUILTIN_EVENT
            || type == INTERNAL_EVENT
            || type == TRANSITION_EVENT) {
            fType = type;
        } else {
            fType = UNKNOWN_EVENT;
        }
    }
    
    /**
     * イベント名を返す.
     * 
     * @return イベント名
     */
    public String getName() {
        return fName;
    }
    
    /**
     * イベント名を設定する.
     * 
     * @param name イベント名
     */
    public void setName(String name) {
        if (name != null && name.length() > 0) {
            fName = name;
        } else {
            fName = null;
        }
        firePropertyChange("event", null, (Object) this);
    }

    /**
     * 遷移先ステートを返す.
     * 
     * @return 遷移先ステート 
     */
    public State getNextState() {
        return fNextState;
    }

    /**
     * 遷移先ステートを設定する.
     * 
     * @param nextState 遷移先ステート
     */
    public void setNextState(State nextState) {
        fNextState = nextState;
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * イベントタイプを返す.
     * イベントタイプは以下の定数のいずれかで返される。<br>
     * ビルトインイベント：BUILTIN_EVENT<br>
     * 内部イベント：INTERNAL_EVENT<br>
     * 遷移イベント：TRANSITION_EVENT<br>
     * 不明：UNKNOWN_EVENT<br>
     * 
     * @return イベントタイプ
     */
    public int getType() {
        return fType;
    }
    
    /**
     * イベントハンドラを返す.
     * 
     * @return イベントハンドラ
     */
    public String getEventHandler() {
        if (fEventHandler == null) {
            return null;
        }
        if (fEventHandler.getMethodName() == null) {
            return null;
        }
        return fEventHandler.toString();
    }

    /**
     * イベントハンドラのクラス名を返す.
     * 
     * @return イベントハンドラのクラス名.
     */
    public String getEventHandlerClassName() {
        if (fEventHandler == null) {
            return null;
        }
        return fEventHandler.getClassName();
    }
    
    /**
     * イベントハンドラのメソッド名を返す.
     * 
     * @return イベントハンドラのメソッド名.
     */
    public String getEventHandlerMethodName() {
        if (fEventHandler == null) {
            return null;
        }
        return fEventHandler.getMethodName();
    }
    
    /**
     * イベントハンドラを設定する.
     * 
     * @param classAndMethodName クラス名 + ":" + メソッド名
     */
    public void setEventHandler(String classAndMethodName) {
        if (classAndMethodName != null && classAndMethodName.length() > 0) {
            fEventHandler = new EventHandler(classAndMethodName);
        } else {
            fEventHandler = null;
        }
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * ガード用イベントハンドラを返す.
     * 
     * @return ガード用イベントハンドラ
     */
    public String getGuardEventHandler() {
        if (fGuardEventHandler == null) {
            return null;
        }
        if (fGuardEventHandler.getMethodName() == null) {
            return null;
        }
        return fGuardEventHandler.toString();
    }
    
    /**
     * ガード要イベントハンドラのクラス名を返す.
     * 
     * @return ガード要イベントハンドラのクラス名
     */
    public String getGuardEventHandlerClassName() {
        if (fGuardEventHandler == null) {
            return null;
        }
        return fGuardEventHandler.getClassName();
    }
    
    /**
     * ガード要イベントハンドラのメソッド名を返す.
     * 
     * @return ガード要イベントハンドラのメソッド名
     */
    public String getGuardEventHandlerMethodName() {
        if (fGuardEventHandler == null) {
            return null;
        }
        return fGuardEventHandler.getMethodName();
    }
    
    /**
     * ガード用イベントハンドラを設定する.
     * 
     * @param classAndMethodName クラス名 + ":" + メソッド名
     */
    public void setGuardEventHandler(String classAndMethodName) {
        if (classAndMethodName != null && classAndMethodName.length() > 0) {
            fGuardEventHandler = new EventHandler(classAndMethodName);
        } else {
            fGuardEventHandler = null;
        }
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * イベントハンドラのメソッド名を生成する.
     * イベントハンドラのメソッド名は、以下の規則で生成される。<br>
     * "do" + イベント名 
     * 
     * @return イベントハンドラのメソッド名
     */
    public String generateEventHandlerMethodName() {
        if (getName() == null) {
            return null;
        }
        return "do" + getName();
    }
}
