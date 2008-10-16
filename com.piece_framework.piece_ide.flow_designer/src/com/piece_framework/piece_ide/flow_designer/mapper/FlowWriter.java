// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.service.prefs.BackingStoreException;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * フローライター.
 * FlowオブジェクトをYAMLファイルとシリアライズファイルに書き込む.
 *
 * @author MATSUFUJI Hideharu
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
        writeYAML(flow, yamlFile, monitor);

        writeSerializeFile(flow, yamlFile, monitor);
    }

    /**
     * FlowオブジェクトをYAMLファイルに書き込む.
     *
     * @param flow Flowオブジェクト
     * @param yamlFile YAMLファイル(.flowファイル)
     * @param monitor プログレスモニターe
     * @throws CoreException コア例外
     */
    private static void writeYAML(
                                Flow flow,
                                IFile yamlFile,
                                IProgressMonitor monitor)
                     throws CoreException {
        FlowMapper mapper = new FlowMapper();
        String yamlString = mapper.getYAML(flow);
        try {
            yamlFile.setContents(
                    new ByteArrayInputStream(
                            yamlString.getBytes("UTF-8")),  //$NON-NLS-1$
                    true,
                    false,
                    monitor);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
    }

    /**
     * Flowオブジェクトをシリアライズファイルに書き込む.
     *
     * @param flow Flowオブジェクト
     * @param yamlFile YAMLファイル(.flowファイル)
     * @param monitor プログレスモニターe
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     */
    private static void writeSerializeFile(
                                Flow flow,
                                IFile yamlFile,
                                IProgressMonitor monitor)
                     throws CoreException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(flow);
        objectOut.close();

        IFile serializeFlowFile =
            FlowSerializeUtility.createFlowSeirializeFile(yamlFile);

        if (serializeFlowFile == null) {
            return;
        }
        if (!serializeFlowFile.exists()) {
            serializeFlowFile.create(
                new ByteArrayInputStream(byteOut.toByteArray()),
                true,
                monitor);
        } else {
            serializeFlowFile.setContents(
                new ByteArrayInputStream(byteOut.toByteArray()),
                true,
                false,
                monitor);
        }
    }
}
