// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;

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
public class FlowMapper {

    private Flow fFlow;
    
    /**
     * 指定されたFlowをYAMLで出力する.
     * 
     * @param flow フロー
     * @return YAML
     */
    public String getYAML(Flow flow) {
        fFlow = flow;
        
        Map<String, Object> firstStateMap = new LinkedHashMap<String, Object>();
        Map<String, Object> finalStateMap = new LinkedHashMap<String, Object>();
        Map<String, Object> viewStateMap = new LinkedHashMap<String, Object>();
        List<Map> viewStateList = new ArrayList<Map>();
        Map<String, Object> actionStateMap = 
                new LinkedHashMap<String, Object>();
        List<Map> actionStateList = new ArrayList<Map>();
        
        for (State state : fFlow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                for (Event event : state.getEventList()) {
                    if (event.getType() == Event.TRANSITION_EVENT) {
                        firstStateMap.put("firstState", 
                                event.getNextState().getName());
                        break;
                    }
                }
            } else if (state.getType() == State.FINAL_STATE) {
                for (State sourceState : fFlow.getStateListToOwnState(state)) {
                    Map<String, Object> sourceStateMap = 
                            new LinkedHashMap<String, Object>();
                    sourceStateMap.put("name", sourceState.getName());
                    if (sourceState.getType() == State.VIEW_STATE) {
                        sourceStateMap.put("view", sourceState.getView());
                    }
                    addBuiltinEventToMap(sourceState, sourceStateMap);
                    finalStateMap.put("lastState", sourceStateMap);
                }
            } else if (state.getType() == State.VIEW_STATE
                     || state.getType() == State.ACTION_STATE) {
                // ファイナルステートへの遷移がある場合はなにもしない
                boolean finalStateFlag = false;
                for (Event event : state.getTransitionEventList()) {
                    if (event.getNextState().getType() == State.FINAL_STATE) {
                        finalStateFlag = true;
                        break;
                    }
                }
                if (finalStateFlag) {
                    continue;
                }
                
                Map<String, Object> stateMap = 
                        new LinkedHashMap<String, Object>();
                stateMap.put("name", state.getName());
                if (state.getType() == State.VIEW_STATE) {
                    stateMap.put("view", state.getView());
                }
                addBuiltinEventToMap(state, stateMap);
                addTransitionAndInternalEventToMap(state, stateMap);
                
                if (state.getType() == State.VIEW_STATE) {
                    viewStateList.add(stateMap);
                } else if (state.getType() == State.ACTION_STATE) {
                    actionStateList.add(stateMap);
                }
            } else {
                continue;
            }
        }
        
        StringBuffer yamlBuffer = new StringBuffer();
        if (firstStateMap.size() > 0) {
            yamlBuffer.append(Yaml.dump(firstStateMap, true));
            yamlBuffer.append("\n");
        }
        if (finalStateMap.size() > 0) {
            yamlBuffer.append(Yaml.dump(finalStateMap, true));
            yamlBuffer.append("\n");
        }
        if (viewStateList.size() > 0) {
            viewStateMap.put("viewState", viewStateList);
            yamlBuffer.append(Yaml.dump(viewStateMap, true));
            yamlBuffer.append("\n");
        }
        if (actionStateList.size() > 0) {
            actionStateMap.put("actionState", actionStateList);
            yamlBuffer.append(Yaml.dump(actionStateMap, true));
            yamlBuffer.append("\n");
        }
        
        return formatYAMLString(yamlBuffer.toString());
    }

    /**
     * ステートが保持するビルトインイベントをMapオブジェクトに追加する.
     * 
     * @param state ステート
     * @param map Mapオブジェクト
     */
    private void addBuiltinEventToMap(State state, Map<String, Object> map) {
        for (Event event : state.getEventList()) {
            if (event.getType() != Event.BUILTIN_EVENT) {
                continue;
            }
            EventHandler eventHandler = event.getEventHandler();
            if (eventHandler == null) {
                continue;
            }
            
            Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
            String methodName = "";
            if (eventHandler.getClassName() == null) {
                methodName = fFlow.getActionClassName() + ":"
                           + eventHandler.getMethodName();
            } else {
                methodName = eventHandler.toString();
            }
            eventMap.put("method", methodName);
            
            if (event.getName().equals("Activity")) {
                map.put("activity", eventMap);
            } else if (event.getName().equals("Entry")) {
                map.put("entry", eventMap);
            } else if (event.getName().equals("Exit")) {
                map.put("exit", eventMap);
            }
        }
    }

    /**
     * ステートが保持する遷移イベント・内部イベントをMapオブジェクトに追加する.
     * 
     * @param state ステート
     * @param map Mapオブジェクト
     */
    private void addTransitionAndInternalEventToMap(
                        State state, 
                        Map<String, Object> map) {
        List<Map> eventList = new ArrayList<Map>();
        for (Event event : state.getEventList()) {
            if (event.getType() != Event.TRANSITION_EVENT
                && event.getType() != Event.INTERNAL_EVENT) {
                continue;
            }
            
            Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
            eventMap.put("event", event.getName());
            eventMap.put("nextState", event.getNextState().getName());
            EventHandler eventHandler = event.getEventHandler();
            Map<String, Object> eventHandlerMap = 
                    new LinkedHashMap<String, Object>();
            if (eventHandler != null) {
                String methodName = "";
                if (eventHandler.getClassName() == null) {
                    methodName = fFlow.getActionClassName() + ":"
                               + eventHandler.getMethodName();
                } else {
                    methodName = eventHandler.toString();
                }
                eventHandlerMap.put("method", methodName);
                eventMap.put("action", eventHandlerMap);
            }
            
            eventList.add(eventMap);
        }
        map.put("transition", eventList);
    }
    
    /**
     * YAML文字列を整形する.
     * 以下の規則に従って整形する。<br>
     * ・"\r\n" → \n<br>
     * ・"--- \n" → 空文字列<br>
     * ・"\"" → 空文字列<br>
     * ・" !java.util.LinkedHashMap" → 空文字列<br>
     * ・"-\n    " → "- "<br>
     * ・": \n" → ":\n"<br>
     * ・最後の2連続改行 → ひとつの改行のみ<br>
     * 
     * @param yamlString YAML文字列
     * @return 整形したYAML文字列
     */
    private String formatYAMLString(String yamlString) {
        String result = yamlString;
        
        result = result.replace("\r\n", "\n");
        result = result.replace("--- \n", "");
        result = result.replace("\"", "");
        result = result.replace(" !java.util.LinkedHashMap", "");
        result = result.replaceAll("-\n *", "- ");
        result = result.replace(": \n", ":\n");
        result = result.substring(0, result.length() - 1);
        
        return result;
    }
}
