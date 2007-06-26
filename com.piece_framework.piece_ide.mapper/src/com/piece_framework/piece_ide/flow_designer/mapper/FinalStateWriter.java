package com.piece_framework.piece_ide.flow_designer.mapper;


import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlService;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlState;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;

/**
 * ファイナルステートライタークラス.
 * 
 * @author nonoyama
 * @version 
 * @since 
 *
 */
public class FinalStateWriter implements IStateWriter {

    
    /**
     * Yaml出力用のステートクラスを作成する.
     * 
     * @param  yFlow Yamlフロークラス
     * @param  state ステート情報
     * @return YamlState Yamlステートクラス 
     */
    public YamlState createYamlState(YamlFlow yFlow, State state) {
        
        if (state == null) {
            return null;
        }
        
        YamlState ys = new YamlState();
        ys.setName(state.getName());
        
        EventWriter evWriter = new EventWriter();
        
        for (Event event : state.getEventList()) {

            if (event.isSpecialEvent()) {                
                if ("Finalize".equals(event.getName())) {                    
                    
                    YamlService ysrv = evWriter.createEventHandler(event);
                    yFlow.setFinal_(ysrv);        
                }
            }
        }
        
        yFlow.setLastState(ys);
        
        return ys;
    }
    

    
    
}