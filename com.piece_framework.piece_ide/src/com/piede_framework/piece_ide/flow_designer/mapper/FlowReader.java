package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フローリーダー.
 * YAMLファイルとシリアライズファイルからFlowオブジェクトを読み込む.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public final class FlowReader {
    
    /**
     * コンストラクタ.
     * 
     */
    private FlowReader() {
    }
    
    /**
     * YAMLファイルとシリアライズファイルからFlowオブジェクトを読み込む.
     * 
     * @param yamlFile YAMLファイル(.flowファイル)
     * @return Flowオブジェクト
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     * @throws ClassNotFoundException クラス未発見例外
     */
    public static Flow read(IFile yamlFile) 
                    throws CoreException, IOException, ClassNotFoundException {
        Flow flow = null;
        
        if (yamlFile.getProject() != null) {
            IFile serializeFile = yamlFile.getProject().getFile(
                    ".settings/flow" 
                    + yamlFile.getFullPath().toString() + "_obj");
            
            if (serializeFile.exists()) {
                ObjectInputStream in = 
                    new ObjectInputStream(serializeFile.getContents());
                flow = (Flow) in.readObject();
                in.close();
            }
        }
        
        return flow;
    }
}
