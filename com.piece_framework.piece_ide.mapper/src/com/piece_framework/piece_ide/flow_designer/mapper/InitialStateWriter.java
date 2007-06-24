package com.piece_framework.piece_ide.flow_designer.mapper;


import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlService;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlState;

/**
 * イニシャルステートライタクラス.
 * @author nonoyama
 *
 */
public class InitialStateWriter implements IStateWriter {



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
        
        yFlow.setFirstState(state.getName());
        
        EventWriter evWriter = new EventWriter();
        
        for (Event event : state.getEventList()) {
            
            if (event.isSpecialEvent()) {                
                if ("Initialize".equals(event.getName())) {  
                    
                    YamlService ysrv = evWriter.createEventHandler(event);  
                    yFlow.setInitial(ysrv);        
                }
            }
        }        
        
        return null;
    }
    
    
    
}
