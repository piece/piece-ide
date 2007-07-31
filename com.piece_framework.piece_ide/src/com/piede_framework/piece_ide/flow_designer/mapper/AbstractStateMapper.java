package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

public abstract class AbstractStateMapper extends AbstractMapper {

    private Flow fFlow;
    
    @Override
    public AbstractModel getModel(String yaml) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
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
            eventMap.put("method", getMethodName(eventHandler));
            
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
        eventHandlerMap.put("method", getMethodName(eventHandler));
        map.put(key, eventHandlerMap);
    }
    
    /**
     * イベントハンドラからメソッド名を取得する.
     * イベントハンドラのメソッド名は以下の規則で決定する。<br>
     * ・イベントハンドラにクラス名が指定されていない場合<br>
     * 　　[フローに指定されているアクションクラス]＋":"＋<br>
     * 　　　　[イベントハンドラのメソッド名]<br>
     * ・イベントハンドラにクラス名が指定されている場合<br>
     * 　　[イベントハンドラのクラス名]＋":"＋<br>
     * 　　　　[イベントハンドラのメソッド名]<br>
     * 　(この場合はEventHandlerクラスのtoStringメソッドで取得できる。)
     * 
     * @param eventHandler イベントハンドラ
     * @return メソッド名
     */
    protected String getMethodName(EventHandler eventHandler) {
        String methodName = "";
        if (eventHandler.getClassName() == null) {
            methodName = fFlow.getActionClassName() + ":"
                       + eventHandler.getMethodName();
        } else {
            methodName = eventHandler.toString();
        }
        return methodName;
    }
    
    /**
     * YAML文字列を整形する.
     * 以下の規則に従って整形する。<br>
     * ・"\r\n" → \n<br>
     * ・"--- \n" → 空文字列<br>
     * ・"\"" → 空文字列<br>
     * ・" !java.util.LinkedHashMap" → 空文字列<br>
     * ・"-\n *" → "- "<br>
     * ・": \n" → ":\n"<br>
     * ・最後の2連続改行 → ひとつの改行のみ<br>
     * 
     * @param yamlString YAML文字列
     * @return 整形したYAML文字列
     */
    protected String formatYAMLString(String yamlString) {
        String result = yamlString;
        
        result = result.replace("\r\n", "\n");
        result = result.replace("--- \n", "");
        result = result.replace("\"", "");
        result = result.replace(" !java.util.LinkedHashMap", "");
        result = result.replaceAll("-\n *", "- ");
        result = result.replace(": \n", ":\n");
        if (result.length() >= 1) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
}
