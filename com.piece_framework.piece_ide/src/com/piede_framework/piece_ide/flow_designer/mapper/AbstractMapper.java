package com.piede_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;
import com.piece_framework.piece_ide.flow_designer.model.Flow;

public abstract class AbstractMapper {
    public abstract String getYAML(Flow flow);
    
    public abstract AbstractModel getModel(String yaml);
}
