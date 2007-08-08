package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

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
     * @param file YAMLファイル(.flowファイル)
     * @param monitor プログレスモニターe
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     */
    public static void write(Flow flow, IFile file, IProgressMonitor monitor) 
                            throws CoreException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(flow);
        objectOut.close();
        
        file.setContents(
                new ByteArrayInputStream(byteOut.toByteArray()), 
                true,
                false,
                monitor);
    }
}
