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
 * ステートマッパー抽象クラス.
 * すべてのステートマッパーのスーパークラスとなる。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public abstract class AbstractStateMapper extends AbstractMapper {

    private Flow fFlow;

    /**
     * YAMLを返す.
     * ステートリストを取得し、必要なMapオブジェクトを生成し、そこから
     * YAML文字列を抽出する。
     * 
     * @param flow フロー
     * @return YAML文字列
     * @see com.piede_framework.piece_ide.flow_designer.mapper.AbstractMapper
     *          #getYAML(com.piece_framework.piece_ide.flow_designer.model.Flow)
     */
    public String getYAML(Flow flow) {
        fFlow = flow;
        
        List<State> stateList = getStateList();
        Map<String, Object> map = getMapForYAML(stateList);
        return convertMapToYAML(map);
    }
    
    /**
     * 処理対象となるステートの一覧を返す.
     * 
     * @return 処理対象ステートリスト
     */
    protected abstract List<State> getStateList();
    
    
    /**
     * YAML出力のためのMapオブジェクトを返す.
     * 
     * @param stateList 処理対象ステートリスト
     * @return YAML文字列
     */
    protected abstract Map<String, Object> getMapForYAML(
                            List<State> stateList);
    
    /**
     * MapオブジェクトをYAMLに変換する.
     * 
     * @param map Mapオブジェクト
     * @return YAML文字列
     */
    protected String convertMapToYAML(Map<String, Object> map) {
        StringBuffer yamlBuffer = new StringBuffer();
        if (map.size() > 0) {
            yamlBuffer.append(Yaml.dump(map, true));
            yamlBuffer.append("\n\n");
        }
        return formatYAMLString(yamlBuffer.toString());
    }
    
    /**
     * フローを返す.
     * 
     * @return フロー
     */
    protected Flow getFlow() {
        return fFlow;
    }
    
    /**
     * ステート名・ビュー名(ビューステートの場合のみ)をMapオブジェクトに
     * 追加する.
     * 
     * @param state ステート
     * @param map Mapオブジェクト
     */
    protected void addStateInformationToMap(
                        State state, 
                        Map<String, Object> map) {
        map.put("name", state.getName());
        if (state.getType() == State.VIEW_STATE) {
            map.put("view", state.getView());
        }
    }
    
    /**
     * ステートが保持するビルトインイベントをMapオブジェクトに追加する.
     * 
     * @param state ステート
     * @param map Mapオブジェクト
     */
    protected void addBuiltinEventToMap(State state, Map<String, Object> map) {
        for (Event event : state.getEventList()) {
            if (event.getType() != Event.BUILTIN_EVENT) {
                continue;
            }
            EventHandler eventHandler = event.getEventHandler();
            if (eventHandler == null) {
                continue;
            }
            
            Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
            eventMap.put("method", eventHandler.toString());
            
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
    protected void addTransitionAndInternalEventToMap(
                        State state, 
                        Map<String, Object> map) {
        List<Map> eventList = new ArrayList<Map>();
        for (Event event : state.getEventList()) {
            if (event.getType() != Event.TRANSITION_EVENT
                && event.getType() != Event.INTERNAL_EVENT) {
                continue;
            }
            if (event.getNextState().getType() == State.FINAL_STATE) {
                continue;
            }
            Map<String, Object> eventMap = new LinkedHashMap<String, Object>();
            eventMap.put("event", event.getName());
            eventMap.put("nextState", event.getNextState().getName());
            
            addEventHandlerToMap(
                    event.getEventHandler(), "action", eventMap);
            addEventHandlerToMap(
                    event.getGuardEventHandler(), "guard", eventMap);
            
            eventList.add(eventMap);
        }
        if (eventList.size() > 0) {
            map.put("transition", eventList);
        }
    }
    
    /**
     * イベントハンドラをMapオブジェクトに追加する.
     * 
     * @param eventHandler イベントハンドラ
     * @param key キー
     * @param map Mapオブジェクト
     */
    protected void addEventHandlerToMap(
                        EventHandler eventHandler, 
                        String key, 
                        Map<String, Object> map) {
        if (eventHandler == null) {
            return;
        }
        
        Map<String, Object> eventHandlerMap = 
                    new LinkedHashMap<String, Object>();
        eventHandlerMap.put("method", eventHandler.toString());
        map.put(key, eventHandlerMap);
    }
}
