package com.piece_framework.piece_ide.flow_designer.mapper;

import org.ho.yaml.Yaml;
import java.util.ArrayList;
import java.util.LinkedHashMap;
//import java.util.LinkedList;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;



/**
 * FlowMapperクラス.
 * @author nonoyama
 *
 */
public class FlowMapper {
    
    /**
     * フローファイルからYamlを出力する.
     * @param flow フロー(model)
     * @return String Yaml出力結果
     */
    public String dump(Flow flow) {

        String yamlStr  = Yaml.dump(setFlow(flow));
        
        yamlStr = yamlStr.replaceAll("!java.util.LinkedHashMap", "");
        yamlStr = yamlStr.replaceAll("- \r\n *", "- ");  //\r\nはマズイ？
        yamlStr = yamlStr.replaceAll("--- ", "--- \r\n");
        
        return yamlStr;
        
    }
    
    
    /**
     * フロー情報のマッピング先となるHashMapを作成する.
     * ここで登録されている要素順にYamlへ出力される.
     * @return LinkedHashMap
     */
    private LinkedHashMap<String, Object> getFlowTemplate() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("firstState", null);
        map.put("viewState", null);
        map.put("actionState", null);
        map.put("lastState", null);  
        map.put("initial", null);        
        map.put("final", null);
        return map;
    }
    
    /**
     * ステート情報のマッピング先となるHashMapを作成する.
     * ここで登録されている要素順にYamlへ出力される.
     * @return LinkedHashMap
     */
    private LinkedHashMap<String, Object> getStateTemplate() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", null);
        map.put("view", null); 
        map.put("transition", null);
        map.put("entry", null);
        map.put("exit", null);
        map.put("activity", null);
        return map;
    }
    
    /**
     * トランジション情報のマッピング先となるHashMapを作成する.
     * ここで登録されている要素順にYamlへ出力される.
     * @return LinkedHashMap
     */
    private LinkedHashMap<String, Object> getTransitionTemplate() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("event", null);
        map.put("nextState", null); 
        map.put("action", null); 
        map.put("guard", null);
        return map;
    }
    
    /**
     * イベントハンドラ情報のマッピング先となるHashMapを作成する.
     * ここで登録されている要素順にYamlへ出力される.
     * @return LinkedHashMap
     */
    private LinkedHashMap<String, String> getEventHandlerTemplate() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("class", null);
        map.put("method", null); 
        return map;
    }
    
    
    /**
     * フロー情報のマッピングをする.
     * @param flow フロー
     * @return フロー情報のマッピング結果
     */
    private LinkedHashMap<String, Object> setFlow(Flow flow) {
        
        if (flow == null) {
            return null;
        }
        
        LinkedHashMap<String, Object> map = getFlowTemplate();
        
        
        ArrayList<LinkedHashMap<String, Object>> 
            viewList = new ArrayList<LinkedHashMap<String, Object>>();
        ArrayList<LinkedHashMap<String, Object>> 
            actionList = new ArrayList<LinkedHashMap<String, Object>>();
        
        for (State s : flow.getStates()) {
            
            switch(s.getStateType()) {
            case State.INITIAL_STATE:
                
                map.put("firstState", s.getName());                
                
                for (Event e : s.getEventList()) {
                    if ("Initialize".equals(e.getName())) {
                        map.put("initial", setEventHandler(e.getEventHandler()));
                    }                    
                }                
                break;
                
            case State.FINAL_STATE:
                
                for (Event e : s.getEventList()) {
                    if ("Finalize".equals(e.getName())) {
                        map.put("final", setEventHandler(e.getEventHandler()));
                    }                    
                }
                break;
                
            case State.VIEW_STATE:
                viewList.add(setState(s));
                break;
                
            case State.ACTION_STATE:                
                actionList.add(setState(s));                
                break;
                
            default:
                break;                    
            }
            
        }
        
        map.put("viewState", viewList);
        map.put("actionState", actionList);
        
        
        return map;
    }
    
    /**
     * ステート情報のマッピングをする.
     * @param state ステート
     * @return ステートの情報のマッピング結果
     */
    private LinkedHashMap<String, Object> setState(State state) {
        
        if (state == null) {
            return null;
        }

        LinkedHashMap<String, Object> map = getStateTemplate();
        
        switch (state.getStateType()) {
                              
        case State.FINAL_STATE:
            
            map.put("name", state.getName());
            map.put("view", state.getView());
            setStateEvents(state, map);
            
            map.remove("transition");
            
            break;
            
        case State.ACTION_STATE:
            
            map.put("name", state.getName());
            setStateEvents(state, map);

            map.remove("view");
            
            break;
            
        case State.VIEW_STATE:

            map.put("name", state.getName());
            map.put("view", state.getView());            
            setStateEvents(state, map);
            
            break;
            
        case State.INITIAL_STATE:            
        default :
            return null;
        }
        
        return map;
    }  
    
    
    /**
     * ステート内のイベント情報のマッピングをする.
     * @param state ステート(model)
     * @param map マッピング先のLinkedHashMap
     */
    private void setStateEvents(State state, LinkedHashMap<String, Object> map) {
        
        ArrayList<LinkedHashMap<String, Object>> 
            transition = new ArrayList<LinkedHashMap<String, Object>>();
        
        for (Event e : state.getEventList()) {
            
            if ("Entry".equals(e.getName())) {
                map.put("entry", setEventHandler(e.getEventHandler()));
            } else if ("Exit".equals(e.getName())) {
                map.put("exit", setEventHandler(e.getEventHandler()));
            } else if ("Activity".equals(e.getName())) {
                map.put("activity", setEventHandler(e.getEventHandler()));
            } else {
                transition.add(setTransition(e));                    
            }
        
        }
        map.put("transition", transition);
    }
    
    
    
    /**
     * トランジションのマッピングをする.
     * @param event イベント(model)
     * @return トランジションの情報のマッピング結果
     */
    private LinkedHashMap<String, Object> setTransition(Event event) {
        
        if (event == null) {
            return null;
        }
        
        LinkedHashMap<String, Object> map = getTransitionTemplate();
        
        map.put("event", event.getName());
        if (event.getNextState() != null) {
            map.put("nextState", event.getNextState().getName()); 
        }
        map.put("action", setEventHandler(event.getEventHandler())); 
        map.put("guard", setEventHandler(event.getGuardEventHandler()));
        
        return map;
    }
    
    
    
    /**
     * イベントハンドラの情報をマッピングをする.
     * @param handler イベントハンドラ(model)
     * @return イベントハンドラの情報のマッピング結果
     */
    private LinkedHashMap<String, String> setEventHandler(EventHandler handler) {
        
        if (handler == null) {
            return null;
        }
        
        LinkedHashMap<String, String> map = getEventHandlerTemplate();
        
        map.put("class", handler.getClassName());
        map.put("method", handler.getMethodName()); 

        return map;
    }
    
    
}
