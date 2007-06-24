package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlState;

/**
 * インターフェイス.
 * @author nonoyama
 *
 */
public interface IStateWriter {
     

    /**
     * Yaml出力用のステートクラスを作成する.
     * 
     * @param  yFlow Yamlフロークラス
     * @param  state ステート
     * @return YamlState Yamlステートクラス 
     */
    YamlState createYamlState(YamlFlow yFlow, State state);
    
}
