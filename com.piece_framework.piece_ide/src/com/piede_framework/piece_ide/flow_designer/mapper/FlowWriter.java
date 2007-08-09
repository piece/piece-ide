package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;

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
     * @param file YAMLファイル(.flowファイル)
     * @param monitor プログレスモニターe
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     * @throws BackingStoreException ストア例外
     */
    public static void write(Flow flow, IFile file, IProgressMonitor monitor) 
                            throws CoreException, IOException, BackingStoreException {
        FlowMapper mapper = new FlowMapper();
        String yamlString = mapper.getYAML(flow);
        file.setContents(
                new ByteArrayInputStream(yamlString.getBytes()), 
                true,
                false,
                monitor);
        
        if (file.getProject() != null) {
            IProject project = file.getProject();
            IScopeContext projectScope = new ProjectScope(project);
            Preferences projectNode = projectScope.getNode(
                                        PieceIDEPlugin.PLUGIN_ID);
            
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(flow);
            objectOut.close();
            
            projectNode.putByteArray(file.getName(), byteOut.toByteArray());
            
            projectNode.flush();
            
            System.out.println(project);
            System.out.println(projectScope);
            System.out.println(projectNode);
        
        }
        
        
        
//        file.setContents(
//                new ByteArrayInputStream(byteOut.toByteArray()), 
//                true,
//                false,
//                monitor);
    }
}
