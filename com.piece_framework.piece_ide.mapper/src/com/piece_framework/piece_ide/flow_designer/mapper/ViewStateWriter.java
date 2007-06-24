package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlService;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlState;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlTransition;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;

/**
 * ビューステートライタクラス.
 * @author nonoyama
 *
 */
public class ViewStateWriter implements IStateWriter {

        
    /**
     * Yaml出力用のステートクラスを作成する.
     * 
     * @param  yFlow Yamlフロークラス
     * @param  state ステート
     * @return YamlState Yamlステートクラス 
     */
    public YamlState createYamlState(YamlFlow yFlow, State state) {
        
        if (state == null) {
            return null;
        }
        
        EventWriter evWriter = new EventWriter();
        
        
        YamlState ys = new YamlState();
        ys.setName(state.getName());
        ys.setView(state.getView());
        
        for (Event event : state.getEventList()) {

            
            if (event.isSpecialEvent()) {
                
                YamlService sp = evWriter.createEventHandler(event);  
                ys.setSpecialEvent(event.getName(), sp);              
                
            } else {
                
                //Transition
                YamlTransition yTran = evWriter.createTransitionEvent(event);
                ys.getTransition().add(yTran); 
                
            }            
        }        
        
        yFlow.getViewState().add(ys);
        
        return ys;
    }
    
    

}
