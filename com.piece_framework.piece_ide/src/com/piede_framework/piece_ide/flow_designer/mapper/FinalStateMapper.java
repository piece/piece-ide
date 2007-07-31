package com.piede_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class FinalStateMapper extends AbstractStateMapper {

    @Override
    public String getYAML(Flow flow) {
        fFlow = flow;
       
        State finalState = null;
        for (State state : flow.getStateList()) {
            if (state.getType() == State.FINAL_STATE) {
                finalState = state;
                break;
            }
        }
        
        List<Map> stateList = new ArrayList<Map>();
        for (State state : flow.getStateListToOwnState(finalState)) {
            Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
            addStateInformationToMap(state, stateMap);
            addBuiltinEventToMap(state, stateMap);
            addTransitionAndInternalEventToMap(state, stateMap);
            
            stateList.add(stateMap);
        }
        
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (stateList.size() > 1) {
            map.put("lastState", stateList);
        } else if (stateList.size() == 1) {
            Map stateMap = (Map) stateList.get(0);
            map.put("lastState", stateMap);
        }
        
        StringBuffer yamlBuffer = new StringBuffer();
        if (map.size() > 0) {
            yamlBuffer.append(Yaml.dump(map, true));
            yamlBuffer.append("\n\n");
        }
        
        return formatYAMLString(yamlBuffer.toString());
    }

}
