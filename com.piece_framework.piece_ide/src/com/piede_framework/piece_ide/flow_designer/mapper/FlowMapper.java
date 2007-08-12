// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローマッパー.
 * FlowオブジェクトとYAMLファイル・シリアライズファイルと
 * のマッピング機能を提供する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class FlowMapper extends AbstractMapper {

    /**
     * フローモデルを返す.
     * 
     * @param yaml YAML文字列
     * @return フローモデル
     * @see com.piede_framework.piece_ide.flow_designer.mapper.AbstractMapper
     *          #getModel(java.lang.String)
     */
    @Override
    public AbstractModel getModel(String yaml) {
        Object object = Yaml.load(yaml);
        if (object == null || !(object instanceof Map)) {
            return null;
        }
        
        Flow flow = new Flow(null, null);
        Object initialValue = getValueIgnoreCase((Map) object, "firstState");
        Object lastValue = getValueIgnoreCase((Map) object, "lastState");
        Object viewValue = getValueIgnoreCase((Map) object, "viewState");
        Object actionValue = getValueIgnoreCase((Map) object, "actionState");
        
        State initialState = null;
        State finalState = null;
        List<State> viewStateList = new ArrayList<State>();
        List<State> actionStateList = new ArrayList<State>();
        
        if (viewValue != null && viewValue instanceof Map) {
            
        }
        if (actionValue != null && actionValue instanceof Map) {
            
        }
        if (lastValue != null && lastValue instanceof Map) {
            finalState = new State(State.FINAL_STATE);
            finalState.setName("Final");
            
            State state = null;
            Object view = getValueIgnoreCase((Map) lastValue, "view");
            if (view != null) {
                state = new State(State.VIEW_STATE);
                state.setView((String) view);
            } else {
                state = new State(State.ACTION_STATE);
            }
            state.setName(
                    (String) getValueIgnoreCase((Map) lastValue, "name"));
            
            Object activityValue = 
                        getValueIgnoreCase((Map) lastValue, "activity");
            if (activityValue != null && activityValue instanceof Map) {
                Object methodValue = 
                    getValueIgnoreCase((Map) activityValue, "method");
                if (methodValue != null && methodValue instanceof String) {
                    Event event = state.getEventByName("Activity");
                    event.setEventHandler(
                            new EventHandler((String) methodValue));
                }
            }
            Object entryValue = 
                        getValueIgnoreCase((Map) lastValue, "entry");
            if (entryValue != null && entryValue instanceof Map) {
                Object methodValue = 
                    getValueIgnoreCase((Map) entryValue, "method");
                if (methodValue != null && methodValue instanceof String) {
                    Event event = state.getEventByName("Entry");
                    event.setEventHandler(
                            new EventHandler((String) methodValue));
                }
            }
            Object exitValue = 
                        getValueIgnoreCase((Map) lastValue, "exit");
            if (exitValue != null && exitValue instanceof Map) {
                Object methodValue = 
                    getValueIgnoreCase((Map) exitValue, "method");
                if (methodValue != null && methodValue instanceof String) {
                    Event event = state.getEventByName("Exit");
                    event.setEventHandler(
                            new EventHandler((String) methodValue));
                }
            }
            
            Event transitionEvent = new Event(Event.TRANSITION_EVENT);
            transitionEvent.setName(
                    state.generateEventName(finalState.getName()));
            transitionEvent.setNextState(finalState);
            state.addEvent(transitionEvent);
            
            if (state.getType() == State.VIEW_STATE) {
                viewStateList.add(state);
            } else if (state.getType() == State.ACTION_STATE) {
                actionStateList.add(state);
            }
            
            Iterator iterator = ((Map) lastValue).keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = ((Map) lastValue).get(key);
                System.out.println(key + ":" + value);
            }
        }
        if (initialValue != null && initialValue instanceof String) {
            initialState = new State(State.INITIAL_STATE);
            initialState.setName("Initial");
            Event transitionEvent = null;
            for (State state : viewStateList) {
                if (state.getName().equals((String) initialValue)) {
                    transitionEvent = new Event(Event.TRANSITION_EVENT);
                    transitionEvent.setName(
                            initialState.generateEventName(state.getName()));
                    transitionEvent.setNextState(state);
                }
            }
            if (transitionEvent == null) {
                for (State state : actionStateList) {
                    if (state.getName().equals((String) initialValue)) {
                        transitionEvent = new Event(Event.TRANSITION_EVENT);
                        transitionEvent.setName(
                            initialState.generateEventName(state.getName()));
                        transitionEvent.setNextState(state);
                    }
                }
            }
            if (transitionEvent != null) {
                initialState.addEvent(transitionEvent);
            }
        }
        
        if (initialState != null) {
            flow.addState(initialState);
        }
        if (finalState != null) {
            flow.addState(finalState);
        }
        for (State state : viewStateList) {
            flow.addState(state);
        }
        for (State state : actionStateList) {
            flow.addState(state);
        }
        
//        Iterator iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            AbstractStateMapper stateMapper = 
//                    createStateMapper((String) iterator.next());
//            if (stateMapper == null) {
//                continue;
//            }
//            
//            State state = (State) stateMapper.getModel(yaml);
//            if (state != null) {
//                flow.addState(state);
//            }
//        }
        
        return flow;
    }
    
    private Object getValueIgnoreCase(Map map, String key) {
        if (map == null) {
            return null;
        }
        
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String mapKey = (String) iterator.next();
            if (key.equalsIgnoreCase(mapKey)) {
                 return map.get(mapKey);
            }
        }
        return null;
    }
    
    /**
     * 指定されたFlowをYAMLで出力する.
     * 
     * @param flow フロー
     * @return YAML
     */
    public String getYAML(Flow flow) {        
        StringBuffer yamlBuffer = new StringBuffer();
        yamlBuffer.append(createStateMapper(State.INITIAL_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.FINAL_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.VIEW_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.ACTION_STATE).getYAML(flow));
        
        return formatYAMLString(yamlBuffer.toString());
    }
    
    /**
     * ステートタイプにあったマッパーを生成する.
     * 
     * @param stateType ステートタイプ
     * @return マッパー
     */
    private AbstractStateMapper createStateMapper(int stateType) {
        AbstractStateMapper stateMapper = null;
        if (stateType == State.INITIAL_STATE) {
            stateMapper = new InitialStateMapper();
        } else if (stateType == State.FINAL_STATE) {
            stateMapper = new FinalStateMapper();
        } else if (stateType == State.VIEW_STATE) {
            stateMapper = new NormalStateMapper(State.VIEW_STATE);
        } else if (stateType == State.ACTION_STATE) {
            stateMapper = new NormalStateMapper(State.ACTION_STATE);
        }
        return stateMapper;
    }
    
    /**
     * ステートタイプにあったマッパーを生成する.
     * 
     * @param stateType ステートタイプ
     * @return マッパー
     */
    private AbstractStateMapper createStateMapper(String stateType) {
        AbstractStateMapper stateMapper = null;
        if (stateType.equalsIgnoreCase("firstState")) {
            stateMapper = createStateMapper(State.INITIAL_STATE);
        } else if (stateType.equalsIgnoreCase("lastState")) {
            stateMapper = createStateMapper(State.FINAL_STATE);
        } else if (stateType.equalsIgnoreCase("viewState")) {
            stateMapper = createStateMapper(State.VIEW_STATE);
        } else if (stateType.equalsIgnoreCase("actionState")) {
            stateMapper = createStateMapper(State.ACTION_STATE);
        }
        return stateMapper;
    }
}
