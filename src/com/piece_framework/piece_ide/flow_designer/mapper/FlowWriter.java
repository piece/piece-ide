// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class FlowWriter {

    public static void write(Flow flow) {
        FileOutputStream fileOut;
        
        try {
            fileOut = new FileOutputStream("c:\\test.flow");
            
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(flow);
            objectOut.close();
            
            fileOut.close();
            
        } catch (IOException ioe) {
            // TODO: オブジェクト読み込み時の例外処理
            ioe.printStackTrace();
            
        }
    }
}
