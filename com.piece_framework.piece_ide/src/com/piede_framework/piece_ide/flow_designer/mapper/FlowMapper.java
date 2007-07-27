// $Id$
package com.piede_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

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
public class FlowMapper {

    /**
     * 指定されたFlowをYAMLで出力する.
     * 
     * @param flow フロー
     * @return YAML
     */
    public static String getYAML(Flow flow) {
        return 
            "firstState: DisplayForm1\n\n"
          + "lastState:\n"
          + "  name: DisplayForm1\n"
          + "  view: Form1\n"
          + "  activity:\n"
          + "    method: ActionClass:doActivityOnDisplayForm1\n"
          + "  entry:\n"
          + "    method: ActionClass:doEntryOnDisplayForm1\n"
          + "  exit:\n"
          + "    method: ActionClass:doExitOnDisplayForm1\n";
    }
}
