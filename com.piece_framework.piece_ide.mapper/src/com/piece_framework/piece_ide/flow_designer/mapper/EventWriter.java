package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlService;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlTransition;

public class EventWriter {
        
    
    public YamlService createEventHandler(Event event) {
        
        YamlService ysrv = new YamlService();
        if (event.getEventHandler() != null) {
            ysrv.setClass_(event.getEventHandler().getClassName());
            ysrv.setMethod_(event.getEventHandler().getMethodName()); 
        }
        
        return ysrv;
    }
    
    
    public YamlService createGuardEventHandler(Event event) {
        
        YamlService ysrv = new YamlService();
        if (event.getGuardEventHandler() != null) {
            ysrv.setClass_(event.getGuardEventHandler().getClassName());
            ysrv.setMethod_(event.getGuardEventHandler().getMethodName()); 
        }
        
        return ysrv;
    }
    
    
    
    public YamlTransition createTransitionEvent(Event event) {

        YamlTransition yTran = new YamlTransition();
        
        yTran.setEvent(event.getName());
        
        yTran.setNextState(event.getNextState().getName());
        
        yTran.setAction(createEventHandler(event));
        
        yTran.setGuard(createGuardEventHandler(event));        
        
        return yTran;
    }
    

}
