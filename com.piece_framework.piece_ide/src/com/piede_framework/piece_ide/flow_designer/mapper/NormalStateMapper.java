package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class NormalStateMapper extends AbstractStateMapper {

    private int fStateType;
    
    public NormalStateMapper(int stateType) {
        fStateType = State.UNKNOWN_STATE;
        if (stateType == State.VIEW_STATE
            || stateType == State.ACTION_STATE) {
            fStateType = stateType;
        }
    }
    
    @Override
    public String getYAML(Flow flow) {
        fFlow = flow;
        
        if (fStateType == State.UNKNOWN_STATE) {
            return "";
        }
        
        List<State> stateList = new ArrayList<State>();
        for (State state : flow.getStateList()) {
            if (state.getType() == fStateType) {
                // ファイナルステートへの遷移がある場合はなにもしない
                boolean finalStateFlag = false;
                for (Event event : state.getTransitionEventList()) {
                    if (event.getNextState().getType() == State.FINAL_STATE) {
                        finalStateFlag = true;
                        break;
                    }
                }
                if (!finalStateFlag) {
                    stateList.add(state);
                }
            }
        }
        
        List<Map> stateListForYAML = new ArrayList<Map>();
        for (State state : stateList) {
            Map<String, Object> stateMap = 
                    new LinkedHashMap<String, Object>();
            addStateInformationToMap(state, stateMap);
            addBuiltinEventToMap(state, stateMap);
            addTransitionAndInternalEventToMap(state, stateMap);
            
            stateListForYAML.add(stateMap);
        }
        
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (stateListForYAML.size() > 0) {
            if (fStateType == State.VIEW_STATE) {
                map.put("viewState", stateListForYAML);
            } else if (fStateType == State.ACTION_STATE) {
                map.put("actionState", stateListForYAML);
            }
        }
        
        StringBuffer yamlBuffer = new StringBuffer();
        if (map.size() > 0) {
            yamlBuffer.append(Yaml.dump(map, true));
            yamlBuffer.append("\n\n");
        }
        
        return formatYAMLString(yamlBuffer.toString());
    }

}
