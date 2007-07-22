// $Id$
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
        fName = name;
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
    public EventHandler getEventHandler() {
        return fEventHandler;
    }

    /**
     * イベントハンドラを設定する.
     * 
     * @param eventHandler イベントハンドラ
     */
    public void setEventHandler(EventHandler eventHandler) {
        fEventHandler = eventHandler;
        firePropertyChange("event", null, (Object) this);
    }

    /**
     * イベントハンドラを設定する.
     * 
     * @param className クラス名
     * @param methodName メソッド名
     */
    public void setEventHandler(String className, String methodName) {
        setEventHandler(new EventHandler(className, methodName));
    }
    
    /**
     * ガード用イベントハンドラを返す.
     * 
     * @return ガード用イベントハンドラ
     */
    public EventHandler getGuardEventHandler() {
        return fGuardEventHandler;
    }
    
    /**
     * ガード用イベントハンドラを設定する.
     * 
     * @param guardEventHandler ガード用イベントハンドラ
     */
    public void setGuardEventHandler(EventHandler guardEventHandler) {
        fGuardEventHandler = guardEventHandler;
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * ガード用イベントハンドラを設定する.
     * 
     * @param className クラス名
     * @param methodName メソッド名
     */
    public void setGuardEventHandler(String className, String methodName) {
        setGuardEventHandler(new EventHandler(className, methodName));
    }
    
    /**
     * イベントハンドラのメソッド名を生成する.
     * イベントハンドラのメソッド名は、以下の規則で生成される。<br>
     * "do" + イベント名 + "On" + ステート名 
     * 
     * @param stateName ステート名
     * @return イベントハンドラのメソッド名
     */
    public String generateEventHandlerMethodName(String stateName) {
        if (getName() == null) {
            return null;
        }
        if (stateName == null) {
            return null;
        }
        return "do" + getName() + "On" + stateName;
    }
}
