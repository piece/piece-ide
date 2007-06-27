// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * ステート・ファクトリー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class StateFactory implements CreationFactory {

    private int fStateType;
    
    /**
     * コンストラクタ.
     * 
     * @param stateType ステートタイプ
     */
    public StateFactory(int stateType) {
        if (State.INITIAL_STATE == stateType 
            || State.FINAL_STATE == stateType
            || State.ACTION_STATE == stateType
            || State.VIEW_STATE == stateType) {
            fStateType = stateType;
        }
    }
    
    /**
     * ステートのインスタンスを返す.
     * ステートタイプにあったステートを生成して返す。
     * 
     * @return ステートのインスタンス
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public Object getNewObject() {
        if (State.INITIAL_STATE != fStateType 
            && State.FINAL_STATE != fStateType
            && State.ACTION_STATE != fStateType
            && State.VIEW_STATE != fStateType) {
            return null;
        }
        
        // TODO: 一時的なイベント生成
        State state = new State(fStateType);
        
        // 遷移イベント1
        Event event = new Event(state);
        event.setName("TransitionEvent1");
        State nextState = new State(fStateType);
        nextState.setName("next_state1");
        event.setNextState(nextState);
        event.setEventHandler("class1", "method1");
        event.setGuardEventHandler("guard_class1", "guard_method1");
        state.addEvent(event);
        
        // 内部イベント1
        event = new Event(state);
        event.setName("InnerEvent1");
        event.setNextState(state);
        event.setEventHandler("class1", "method1");
        event.setGuardEventHandler("guard_class1", "guard_method1");
        state.addEvent(event);

        // 遷移イベント2
        event = new Event(state);
        event.setName("TransitionEvent2");
        nextState = new State(fStateType);
        nextState.setName("next_state2");
        event.setNextState(nextState);
        event.setEventHandler("class2", "method2");
        event.setGuardEventHandler("guard_class2", "guard_method2");
        state.addEvent(event);
        
        // 内部イベント2
        event = new Event(state);
        event.setName("InnerEvent2");
        event.setNextState(state);
        event.setEventHandler("class2", "method2");
        event.setGuardEventHandler("guard_class2", "guard_method2");
        state.addEvent(event);
        
        return state;
        //return new State(fStateType);
    }

    /**
     * ステートクラスを返す.
     * 
     * @return ステートクラス
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return State.class;
    }

}
