// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * フローマッパー.
 * FlowオブジェクトとYAMLファイル・シリアライズファイルと
 * のマッピング機能を提供する。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class FlowMapper extends AbstractMapper {

    /**
     * 指定されたFlowをYAMLで出力する.
     * 
     * @param flow フロー
     * @return YAML
     */
    public String getYAML(Flow flow) {        
        StringBuffer yamlBuffer = new StringBuffer();
        yamlBuffer.append(createStateMapper(State.INITIAL_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.FINAL_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.VIEW_STATE).getYAML(flow));
        yamlBuffer.append(createStateMapper(State.ACTION_STATE).getYAML(flow));
        
        return formatYAMLString(yamlBuffer.toString());
    }
    
    /**
     * ステートタイプにあったマッパーを生成する.
     * 
     * @param stateType ステートタイプ
     * @return マッパー
     */
    private AbstractStateMapper createStateMapper(int stateType) {
        AbstractStateMapper stateMapper = null;
        if (stateType == State.INITIAL_STATE) {
            stateMapper = new InitialStateMapper();
        } else if (stateType == State.FINAL_STATE) {
            stateMapper = new FinalStateMapper();
        } else if (stateType == State.VIEW_STATE) {
            stateMapper = new NormalStateMapper(State.VIEW_STATE);
        } else if (stateType == State.ACTION_STATE) {
            stateMapper = new NormalStateMapper(State.ACTION_STATE);
        }
        return stateMapper;
    }

    /**
     * YAML文字列を整形する.
     * 以下の規則に従って整形する。<br>
     * ・"\r\n" → \n<br>
     * ・"--- \n" → 空文字列<br>
     * ・"\"" → 空文字列<br>
     * ・" !java.util.LinkedHashMap" → 空文字列<br>
     * ・"-\n *" → "- "<br>
     * ・": \n" → ":\n"<br>
     * ・最後の2連続改行 → ひとつの改行のみ<br>
     * 
     * @param yamlString YAML文字列
     * @return 整形したYAML文字列
     */
    private String formatYAMLString(String yamlString) {
        String result = yamlString;
        
        result = result.replace("\r\n", "\n");
        result = result.replace("--- \n", "");
        result = result.replace("\"", "");
        result = result.replace(" !java.util.LinkedHashMap", "");
        result = result.replaceAll("-\n *", "- ");
        result = result.replace(": \n", ":\n");
        result = result.substring(0, result.length() - 1);
        
        return result;
    }

    /**
     * モデルを取得する.
     * 
     * @param yaml YAML 文字列
     * @return モデル
     * @see com.piede_framework.piece_ide.flow_designer.mapper.AbstractMapper
     *          #getModel(java.lang.String)
     */
    @Override
    public AbstractModel getModel(String yaml) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
}
