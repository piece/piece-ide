package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public final class FlowReader {
    private FlowReader() {
    }
    
    public static Flow read(IFile yamlFile) 
                            throws CoreException, IOException, ClassNotFoundException {
        Flow flow = null;
        
        if (yamlFile.getProject() != null) {
            IFile serializeFile = yamlFile.getProject().getFile(
                    ".settings/flow" + yamlFile.getFullPath().toString() + "_obj");
            
            if (serializeFile.exists()) {
                ObjectInputStream in = new ObjectInputStream(serializeFile.getContents());
                flow = (Flow) in.readObject();
                in.close();
            }
        }
        
        return flow;
    }
}
