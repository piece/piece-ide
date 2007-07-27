package com.piede_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class FlowMapper {

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
