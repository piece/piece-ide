// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.LinkedHashMap;
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
public final class FlowMapper {

    /**
     * コンストラクタ.
     * 
     */
    private FlowMapper() {
    }
    
    /**
     * 指定されたFlowをYAMLで出力する.
     * 
     * @param flow フロー
     * @return YAML
     */
    public static String getYAML(Flow flow) {
        StringBuffer yamlBuffer = new StringBuffer();
        
        Yaml.config.setMinimalOutput(true);
        
        for (State state : flow.getStateList()) {
            Map stateMap = new LinkedHashMap();
            if (state.getType() == State.INITIAL_STATE) {
                for (Event event : state.getEventList()) {
                    if (event.getType() == Event.TRANSITION_EVENT) {
                        stateMap.put("firstState", event.getNextState().getName());
                        break;
                    }
                }
            } else if (state.getType() == State.FINAL_STATE) {
                for (State sourceState : flow.getStateListToOwnState(state)) {
                    Map sourceStateMap = new LinkedHashMap();
                    sourceStateMap.put("name", sourceState.getName());
                    if (sourceState.getType() == State.VIEW_STATE) {
                        sourceStateMap.put("view", sourceState.getView());
                    }
                    for (Event event : sourceState.getEventList()) {
                        EventHandler eventHandler = event.getEventHandler();
                        if (eventHandler == null) {
                            continue;
                        }
                        
                        Map eventMap = new LinkedHashMap();
                        String methodName = "";
                        if (eventHandler.getClassName() == null) {
                            methodName = flow.getActionClassName() + ":" +
                                         eventHandler.getMethodName();
                        } else {
                            methodName = eventHandler.toString();
                        }
                        eventMap.put("method", methodName);
                        
                        if (event.getName().equals("Activity")) {
                            sourceStateMap.put("activity", eventMap);
                        } else if (event.getName().equals("Entry")) {
                            sourceStateMap.put("entry", eventMap);
                        } else if (event.getName().equals("Exit")) {
                            sourceStateMap.put("exit", eventMap);
                        }
                    }
                    stateMap.put("lastState", sourceStateMap);
                }
            } else {
                continue;
            }
            
            yamlBuffer.append(Yaml.dump(stateMap, true));
            yamlBuffer.append("\n");
        }
        
        return formatYAMLString(yamlBuffer.toString());
    }
    
    /**
     * YAML文字列を整形する.
     * 以下の規則に従って整形する。<br>
     * ・"\r\n" → \n<br>
     * ・"--- \n" → 空文字列<br>
     * ・"\"" → 空文字列<br>
     * ・" !java.util.LinkedHashMap" → 空文字列<br>
     * ・最後の2連続改行 → ひとつの改行のみ<br>
     * 
     * @param yamlString YAML文字列
     * @return 整形したYAML文字列
     */
    private static String formatYAMLString(String yamlString) {
        String result = yamlString;
        
        result = result.replace("\r\n", "\n");
        result = result.replace("--- \n", "");
        result = result.replace("\"", "");
        result = result.replace(" !java.util.LinkedHashMap", "");
        
        result = result.substring(0, result.length() - 1);
        
        return result;
    }
}
