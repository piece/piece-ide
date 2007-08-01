package com.piede_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.model.Flow;

public abstract class AbstractMapper {
    public abstract String getYAML(Flow flow);
    
    public abstract AbstractModel getModel(String yaml);
    
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
    protected String formatYAMLString(String yamlString) {
        String result = yamlString;
        
        result = result.replace("\r\n", "\n");
        result = result.replace("--- \n", "");
        result = result.replace("\"", "");
        result = result.replace(" !java.util.LinkedHashMap", "");
        result = result.replaceAll("-\n *", "- ");
        result = result.replace(": \n", ":\n");
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
}
