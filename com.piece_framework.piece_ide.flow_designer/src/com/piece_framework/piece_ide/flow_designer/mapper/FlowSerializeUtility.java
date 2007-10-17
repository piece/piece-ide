package com.piece_framework.piece_ide.flow_designer.mapper;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

/**
 * フローシリアラズユーティリティ.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 *
 */
public final class FlowSerializeUtility {
    
    private static final String FLOW_PATH = ".settings/flow"; //$NON-NLS-1$
    
    private static final String FLOW_SERIALIZE_EXT = "_obj"; //$NON-NLS-1$
    
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
        
        IFile serializeFile = yamlFile.getProject().getFile(
                                new Path(FLOW_PATH
                                         + yamlFile.getFullPath().toString()
                                         + FLOW_SERIALIZE_EXT));
        if (!serializeFile.exists()) {
            createFolder(serializeFile);
        }
        
        return serializeFile;
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
                ".settings/flow"  //$NON-NLS-1$
                + yamlFile.getFullPath().toString() + FLOW_SERIALIZE_EXT);
    }
    
    /**
     * 指定されたファイルに到達するためのフォルダーを作成する.
     * 
     * @param file ファイル
     * @throws CoreException コア例外
     */
    private static void createFolder(IFile file) throws CoreException {
        String[] folders = 
            file.getFullPath().toString().split("/"); //$NON-NLS-1$
        StringBuffer folderPath = new StringBuffer();
        
        // 最初の2要素には空文字、プロジェクト名が入るので2からはじめる
        int startIndex = 2;
        // 最後の要素にはファイル名が入っているので除く
        int endIndex = folders.length - 1;
        
        for (int i = startIndex; i < endIndex; i++) {
            folderPath.append("/" + folders[i]); //$NON-NLS-1$
            IFolder folder = file.getProject().getFolder(
                                new Path(folderPath.toString()));
            if (!folder.exists()) {
                folder.create(true, true, null);
            }
        }
    }
}
