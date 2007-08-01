package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class InitialStateMapper extends AbstractStateMapper {

    @Override
    protected List<State> getStateList() {
        List<State> stateList = new ArrayList<State>();
        for (State state : getFlow().getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
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
        
        State initialState = stateList.get(0);
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                map.put("firstState", event.getNextState().getName());
                break;
            }
        }
        
        return map;
    }
}
