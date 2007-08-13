// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;

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

    private Flow fFlow;
    private Map fYAMLMap;
    private List<State> fNormalStateList;
    private Map<State, List> fTransitionMap;
    
    /**
     * フローモデルを返す.
     * 
     * @param yaml YAML文字列
     * @return フローモデル
     */
    public Flow getFlow(String yaml) {
        fYAMLMap = getYAMLMap(yaml);
        if (fYAMLMap == null) {
            return null;
        }
        
        Object initialValue = getValueIgnoreCase(fYAMLMap, "firstState");
        Object lastValue = getValueIgnoreCase(fYAMLMap, "lastState");
        
        fFlow = new Flow(null, null);
        State initialState = null;
        State finalState = null;
        fNormalStateList = new ArrayList<State>();
        fTransitionMap = new HashMap<State, List>();
        
        createNormalStateList();
        
        if (lastValue != null 
            && (lastValue instanceof Map || lastValue instanceof List)) {
            List<Map> lastList = new ArrayList<Map>();
            if (lastValue instanceof Map) {
                lastList.add((Map) lastValue);
            } else if (lastValue instanceof List) {
                Iterator iterator = ((List) lastValue).iterator();
                while (iterator.hasNext()) {
                    lastList.add((Map) iterator.next());
                }
            }
            
            finalState = new State(State.FINAL_STATE);
            finalState.setName("Final");
            
            Iterator iterator = lastList.iterator();
            while (iterator.hasNext()) {
                Map lastMap = (Map) iterator.next();
                
                State state = createNormalState(lastMap);
                
                Event transitionEvent = new Event(Event.TRANSITION_EVENT);
                transitionEvent.setName(
                        state.generateEventName(finalState.getName()));
                transitionEvent.setNextState(finalState);
                state.addEvent(transitionEvent);
                
                fNormalStateList.add(state);
            }
        }
        
        initialState = new State(State.INITIAL_STATE);
        initialState.setName("Initial");
        if (initialValue != null && initialValue instanceof String) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("event", 
                    ((String) initialValue) + "From" + initialState.getName());
            map.put("nextState", (String) initialValue);
            List<Object> list = new ArrayList<Object>();
            list.add(map);
            fTransitionMap.put(initialState, list);
            
//            Event transitionEvent = null;
//            for (State state : fNormalStateList) {
//                if (state.getName().equals((String) initialValue)) {
//                    transitionEvent = new Event(Event.TRANSITION_EVENT);
//                    transitionEvent.setName(
//                            initialState.generateEventName(state.getName()));
//                    transitionEvent.setNextState(state);
//                }
//            }
//            if (transitionEvent != null) {
//                initialState.addEvent(transitionEvent);
//            }
        }
        
        if (initialState != null) {
            fFlow.addState(initialState);
        }
        if (finalState != null) {
            fFlow.addState(finalState);
        }
        for (State state : fNormalStateList) {
            fFlow.addState(state);
        }
        
        addTransitionEvent();
        
        return fFlow;
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

    private Map getYAMLMap(String yaml) {
        Object yamlObject = null;
        try {
            yamlObject = Yaml.load(yaml);
        } catch (YamlException ye) {
            ye.printStackTrace();
        }
        if (yamlObject == null || !(yamlObject instanceof Map)) {
            return null;
        }
        
        return (Map) yamlObject;
    }

    private void createNormalStateList() {
        String[] stateNameList = {"viewState", "actionState"};
        for (String stateName : stateNameList) {
            Object value = getValueIgnoreCase(fYAMLMap, stateName);
            if (value == null || !(value instanceof List)) {
                continue;
            }
            
            Iterator iterator = ((List) value).iterator();
            while (iterator.hasNext()) {
                Object map = iterator.next();
                if (!(map instanceof Map)) {
                    continue;
                }
                
                fNormalStateList.add(
                        createNormalState((Map) map));
            }
        }
    }
    
    /**
     * ノーマルステート(ビューステート、アクションステート)を生成する.
     * ビューステートかアクションステートかの判断はMapオブジェクトがviewキーを
     * 保持しているかで判断する。
     * 
     * @param stateMap ステートMapオブジェクト
     * @return ノーマルステート(ビューステート、アクションステート)
     */
    private State createNormalState(Map stateMap) {
        State state = null;
        Object view = getValueIgnoreCase(stateMap, "view");
        if (view != null) {
            state = new State(State.VIEW_STATE);
            state.setView((String) view);
        } else {
            state = new State(State.ACTION_STATE);
        }
        state.setName(
                (String) getValueIgnoreCase(stateMap, "name"));
        
        setBuiltinEventHandler(stateMap, state, "Activity");
        setBuiltinEventHandler(stateMap, state, "Entry");
        setBuiltinEventHandler(stateMap, state, "Exit");

        Object transitionValue = 
            getValueIgnoreCase(stateMap, "transition");
        if (transitionValue != null
            && transitionValue instanceof List) {
            fTransitionMap.put(state, (List) transitionValue);
        }
        
        return state;
    }
    
    /**
     * 遷移イベントを各ステートに追加する.
     * 
     */
    private void addTransitionEvent() {
        for (State state : fFlow.getStateList()) {
            List transitionList = fTransitionMap.get(state);
            if (transitionList == null) {
                continue;
            }
            
            Iterator iterator = transitionList.iterator();
            while (iterator.hasNext()) {
                Object map = iterator.next();
                if (!(map instanceof Map)) {
                    continue;
                }
                
                State nextState = fFlow.getStateByName(
                        (String) getValueIgnoreCase((Map) map, "nextState"));
                if (nextState != null) {
                    Event event = new Event(Event.TRANSITION_EVENT);
                    event.setName(
                        (String) getValueIgnoreCase((Map) map, "event"));
                    event.setNextState(nextState);
                    state.addEvent(event);
                }
            }
        }
    }
    
    /**
     * ビルトインイベントをステートにセットする.
     * 
     * @param map Mapオブジェクト
     * @param state ステート
     * @param eventName ビルトインイベント名
     */
    private void setBuiltinEventHandler(
                        Map map, 
                        State state, 
                        String eventName) {
        Object value = 
                    getValueIgnoreCase(map, eventName);
        if (value != null && value instanceof Map) {
            Object methodValue = 
                getValueIgnoreCase((Map) value, "method");
            if (methodValue != null && methodValue instanceof String) {
                Event event = state.getEventByName(eventName);
                event.setEventHandler(
                        new EventHandler((String) methodValue));
            }
        }
    }
    
    /**
     * 文字列をキーに持つMapオブジェクトから値を取得する.
     * キーとなる文字列の大文字・小文字は無視する。
     * 
     * @param map Mapオブジェクト
     * @param key キー
     * @return 値
     */
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
}
