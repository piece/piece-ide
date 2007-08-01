package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.Event;
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
    protected List<State> getStateList() {
        List<State> stateList = new ArrayList<State>();
        if (fStateType == State.UNKNOWN_STATE) {
            return stateList;
        }
        
        for (State state : getFlow().getStateList()) {
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
        return stateList;
    }

    
    @Override
    protected Map<String, Object> getMapForYAML(List<State> stateList) {
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
        
        return map;
    }
}
