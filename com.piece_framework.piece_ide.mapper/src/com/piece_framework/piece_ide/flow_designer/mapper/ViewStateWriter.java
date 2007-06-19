package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.mapper.jyaml.YamlService;
import com.piece_framework.piece_ide.mapper.jyaml.YamlState;
import com.piece_framework.piece_ide.mapper.jyaml.YamlTransition;
import com.piece_framework.piece_ide.mapper.jyaml.YamlFlow;

public class ViewStateWriter implements IStateWriter {

        
    
    public YamlState createYamlState(YamlFlow yFlow, State state) {
        
        if (state == null) {
            return null;
        }
        
        EventWriter evWriter = new EventWriter();
        
        
        YamlState ys = new YamlState();
        
        //Nameの設定
        ys.setName(state.getName());
        
        //Viewの設定
        ys.setView(state.getView());
        
        //Eventの設定
        for (Event event : state.getEventList()) {

            
            if (event.isSpecialEvent()) {
                
                //スペシャルイベントの設定  
                YamlService sp = evWriter.createEventHandler(event);  
                ys.setSpecialEvent(event.getName(), sp);              
                
            } else {
                
                //遷移イベントの設定
                YamlTransition yTran = evWriter.createTransitionEvent(event);
                ys.getTransition().add(yTran); 
                
            }            
        }        
        
        yFlow.getViewState().add(ys);
        
        return ys;
    }
    
    

}
