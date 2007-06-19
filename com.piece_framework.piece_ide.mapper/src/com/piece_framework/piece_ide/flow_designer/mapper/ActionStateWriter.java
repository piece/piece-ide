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
 * @author Nonoyama yoshikazu
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
     * @return Yamlステートクラス   
     */
    public YamlState createYamlState(YamlFlow yFlow, State state) {
        
        if (state == null) {
            return null;
        }
        EventWriter evWriter = new EventWriter();

        YamlState ys = new YamlState();
        
        //Nameの設定
        ys.setName(state.getName());
        
        //Viewの設定
        ys.setView(null);
        
        
        
        //Eventの設定
        for (Event event : state.getEventList()) {
            
            if (event.isSpecialEvent()) {
                
                //スペシャルイベントの設定
                YamlService sp = evWriter.createEventHandler(event);  
                ys.setSpecialEvent(event.getName(), sp);
                
            } else {
                
                //Transitionの設定
                YamlTransition yTran = evWriter.createTransitionEvent(event);
                ys.getTransition().add(yTran); 
                
            }
        }
        
        
        
        yFlow.getActionState().add(ys);
        
        return ys;
    }
    

}
