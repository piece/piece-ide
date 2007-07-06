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
    
    private String fName;
    private State fNextState;
    private boolean fBuiltinEvent;
    private boolean fInternalEvent;
    private EventHandler fEventHandler;
    private EventHandler fGuardEventHandler;
    
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
     * ビルトインイベントかどうかを返す.
     * 
     * @return ビルトインイベントの場合はTrue
     */
    public boolean isBuiltinEvent() {
        return fBuiltinEvent;
    }

    /**
     * ビルトインイベントかどうかを設定する.
     * 
     * @param builtinEvent ビルトインイベントの場合はTrue
     */
    public void setBuiltinEvent(boolean builtinEvent) {
        fBuiltinEvent = builtinEvent;
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * 遷移イベントかどうかを返す.
     * 内部イベントでなければ、遷移イベントと判断する。
     * 
     * @return 遷移イベントの場合はTrue
     */
    public boolean isTransitionEvent() {
        return !fInternalEvent;
    }

    /**
     * インターナルイベントかどうかを設定する.
     *  
     * @param internalEvent 内部イベントの場合はTrue
     */
    public void setInternalEvent(boolean internalEvent) {
        fInternalEvent = internalEvent;
        firePropertyChange("event", null, (Object) this);
    }
    
    /**
     * 内部イベントかどうかを返す.
     * 
     * @return 内部イベントの場合はTrue
     */
    public boolean isInternalEvent() {
        return fInternalEvent;
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
}
