package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.osgi.service.prefs.BackingStoreException;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フローライター.
 * FlowオブジェクトをYAMLファイルとシリアライズファイルに書き込む.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public final class FlowWriter {

    /**
     * コンストラクタ.
     */
    private FlowWriter() {
    }
    
    /**
     * FlowオブジェクトをYAMLファイルとシリアライズファイルに書き込む.
     * 
     * @param flow Flowオブジェクト
     * @param yamlFile YAMLファイル(.flowファイル)
     * @param monitor プログレスモニターe
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     * @throws BackingStoreException ストア例外
     */
    public static void write(
                            Flow flow, 
                            IFile yamlFile, 
                            IProgressMonitor monitor) 
                    throws CoreException, IOException, BackingStoreException {
        FlowMapper mapper = new FlowMapper();
        String yamlString = mapper.getYAML(flow);
        yamlFile.setContents(
                new ByteArrayInputStream(yamlString.getBytes()), 
                true,
                false,
                monitor);
        
        if (yamlFile.getProject() != null) {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(flow);
            objectOut.close();

            IFolder flowFolder = yamlFile.getProject().getFolder(
                                        new Path(".settings/flow"));
            if (!flowFolder.exists()) {
                flowFolder.create(true, true, monitor);
            }
            
            String[] folders = yamlFile.getFullPath().toString().split("/");
            StringBuffer serializeFolderName = new StringBuffer();
            serializeFolderName.append(".settings/flow");
            for (int i = 2; i < folders.length - 1; i++) {
                serializeFolderName.append("/" + folders[i]);
                IFolder folder = yamlFile.getProject().getFolder(
                                    new Path(serializeFolderName.toString()));
                if (!folder.exists()) {
                    folder.create(true, true, monitor);
                }
            }
            
            IFile serializeFile = yamlFile.getProject().getFile(
                    new Path(serializeFolderName.toString() + "/" 
                              + yamlFile.getName() + "_obj"));
           
            if (!serializeFile.exists()) {
                serializeFile.create(
                    new ByteArrayInputStream(byteOut.toByteArray()),
                    true, 
                    monitor);
            } else {
                serializeFile.setContents(
                    new ByteArrayInputStream(byteOut.toByteArray()), 
                    true,
                    false,
                    monitor);
            }
        }
    }
}
