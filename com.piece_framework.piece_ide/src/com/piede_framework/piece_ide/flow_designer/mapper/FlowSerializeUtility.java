package com.piede_framework.piece_ide.flow_designer.mapper;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

/**
 * フローシリアラズユーティリティ.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public final class FlowSerializeUtility {
    
    private static final String FLOW_PATH = ".settings/flow";
    
    private static final String FLOW_SERIALIZE_EXT = "_obj";
    
    /**
     * コンストラクタ.
     * 
     */
    private FlowSerializeUtility() {
    }
    
    /**
     * フローシリアライズファイルを作成する.
     * フォルダーがなければ、フォルダーも合わせて作成する。
     * 
     * @param yamlFile YAMLファイル(.flowファイル)
     * @return フローシリアラズファイル
     * @throws CoreException コア例外
     */
    public static IFile createFlowSeirializeFile(IFile yamlFile) 
                    throws CoreException {
        if (yamlFile.getProject() == null) {
            return null;
        }
        
        IFolder flowFolder = yamlFile.getProject().getFolder(
                new Path(FLOW_PATH));
        if (!flowFolder.exists()) {
            flowFolder.create(true, true, null);
        }
        
        String[] folders = yamlFile.getFullPath().toString().split("/");
        StringBuffer serializeFolderName = new StringBuffer();
        serializeFolderName.append(FLOW_PATH);
        for (int i = 0; i < folders.length - 1; i++) {
            serializeFolderName.append("/" + folders[i]);
            IFolder folder = yamlFile.getProject().getFolder(
                                new Path(serializeFolderName.toString()));
            if (!folder.exists()) {
                folder.create(true, true, null);
            }
        }
        
        return yamlFile.getProject().getFile(
                    new Path(serializeFolderName.toString() + "/" 
                             + yamlFile.getName() + FLOW_SERIALIZE_EXT));
    }
    
    /**
     * フローシリアライズファイルを返す.
     * 
     * @param yamlFile YAMLファイル(.flowファイル)
     * @return フローシリアライズファイル
     */
    public static IFile getFlowSeirializeFile(IFile yamlFile) {
        if (yamlFile.getProject() == null) {
            return null;
        }
        
        return yamlFile.getProject().getFile(
                ".settings/flow" 
                + yamlFile.getFullPath().toString() + FLOW_SERIALIZE_EXT);
    }
}
