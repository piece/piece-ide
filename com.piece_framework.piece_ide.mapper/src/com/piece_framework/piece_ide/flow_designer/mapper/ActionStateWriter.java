package com.piece_framework.piece_ide.flow_designer.mapper;


import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlState;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlService;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlTransition;


/**
 * アクションステートライタクラス.
 * 
 * @author nonoyama 
 * @version 
 * @since 
 *
 */
public class ActionStateWriter implements IStateWriter {


    
    /**
     * Yaml出力用のステートクラスを作成する.
     *
     * @param  yFlow Yamlフロークラス
     * @param  state モデルステートクラス
     * @return YamlState Yamlステートクラス   
     */
    public YamlState createYamlState(YamlFlow yFlow, State state) {
        
        if (state == null) {
            return null;
        }
        
        EventWriter evWriter = new EventWriter();
        YamlState ys = new YamlState();        
        ys.setName(state.getName());        
        ys.setView(null);
                
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
        
        
        
        yFlow.getActionState().add(ys);
        
        return ys;
    }
    

}
