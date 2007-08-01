package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.State;

public class FinalStateMapper extends AbstractStateMapper {

    @Override
    protected List<State> getStateList() {
        List<State> stateList = new ArrayList<State>();
        for (State state : getFlow().getStateList()) {
            if (state.getType() == State.FINAL_STATE) {
                stateList.add(state);
                break;
            }
        }
        return stateList;
    }
    
    @Override
    protected Map<String, Object> getMapForYAML(List<State> stateList) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (stateList.size() != 1) {
            return map;
        }
        
        State finalState = stateList.get(0);
        List<Map> stateListForYaml = new ArrayList<Map>();
        for (State state : getFlow().getStateListToOwnState(finalState)) {
            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            addStateInformationToMap(state, stateMap);
            addBuiltinEventToMap(state, stateMap);
            addTransitionAndInternalEventToMap(state, stateMap);
            
            stateListForYaml.add(stateMap);
        }
        
        if (stateListForYaml.size() > 1) {
            map.put("lastState", stateListForYaml);
        } else if (stateListForYaml.size() == 1) {
            Map stateMap = (Map) stateListForYaml.get(0);
            map.put("lastState", stateMap);
        }

        return map;
    }
}
