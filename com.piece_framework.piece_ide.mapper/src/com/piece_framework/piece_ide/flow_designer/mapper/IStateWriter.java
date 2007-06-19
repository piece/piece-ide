package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.mapper.jyaml.*;

public interface IStateWriter {
        
    //String write(State state);
    
    YamlState createYamlState(YamlFlow yFlow, State state);
    
    //public void createYamlEvent(YamlFlow yFlow, State state);
    
}
