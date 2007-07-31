package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ho.yaml.Yaml;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class InitialStateMapper extends AbstractStateMapper {

    @Override
    public String getYAML(Flow flow) {
        State initialState = null;
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
                break;
            }
        }
        
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                map.put("firstState", event.getNextState().getName());
                break;
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
