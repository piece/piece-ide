package com.piede_framework.piece_ide.flow_designer.mapper;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

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
     * YAMLファイルから読み込んだFlowオブジェトとシリアライズファイルか
     * ら読み込んだFlowオブジェクトを比較し、一致すればシリアライズファ
     * イｒから読み込んだFlowオブジェクトを返す。
     * 異なる場合はYAMLファイルから読み込んだFlowオブジェクトを返す。
     * 
     * @param yamlFile YAMLファイル(.flowファイル)
     * @return Flowオブジェクト
     * @throws CoreException コア例外
     * @throws IOException I/O例外
     * @throws ClassNotFoundException クラス未発見例外
     */
    public static Flow read(IFile yamlFile) 
                    throws CoreException, IOException, ClassNotFoundException {
        Flow yamlFlow = null;
        Flow serializeFlow = null;
        
        BufferedInputStream bufferedIn = null;
        try {
            bufferedIn = new BufferedInputStream(yamlFile.getContents());
            StringBuffer yamlBuffer = new StringBuffer();
            int read = 0;
            while ((read = bufferedIn.read()) != -1) {
                yamlBuffer.append((char) read);
            }
            FlowMapper flowMapper = new FlowMapper();
            yamlFlow = flowMapper.getFlow(yamlBuffer.toString());
            
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        IFile serializeFlowFile = 
                FlowSerializeUtility.getFlowSeirializeFile(yamlFile); 
        if (serializeFlowFile != null) {
            if (serializeFlowFile.exists()) {
                ObjectInputStream in = 
                    new ObjectInputStream(serializeFlowFile.getContents());
                serializeFlow = (Flow) in.readObject();
                in.close();
            }
        }
        
        Flow returnFlow = yamlFlow;
        if (compareFlow(yamlFlow, serializeFlow)) {
            returnFlow = serializeFlow;
        }
        
        return returnFlow;
    }
    
    public static boolean compareFlow(Flow flow1, Flow flow2) {
        if (flow1 == null || flow2 == null) {
            return false;
        }
        
        for (State state1 : flow1.getStateList()) {
            State state2 = flow2.getStateByName(state1.getName());
            if (state2 == null) {
                return false;
            }
            
            boolean compare = true;
            if (state1.getType() == State.VIEW_STATE) {
                compare = compareObject(state1.getView(),
                                        state2.getView());
            }
            if (!compare) {
                return false;
            }
            
            for (Event event1 : state1.getEventList()) {
                Event event2 = state2.getEventByName(event1.getName());
                if (event2 == null) {
                    return false;
                }
                
                if (event1.getNextState() != null 
                    && event2.getNextState() != null) {
                    compare = compareObject(event1.getNextState().getName(),
                            event2.getNextState().getName());
                } else if (event1.getNextState() != null 
                            || event2.getNextState() != null) {
                    compare = false;
                }
                if (!compare) {
                    return false;
                }
                
                if (event1.getEventHandler() != null 
                    && event2.getEventHandler() != null) {
                    compare = compareObject(event1.getEventHandler().toString(),
                            event2.getEventHandler().toString());
                } else if (event1.getEventHandler() != null 
                            || event2.getEventHandler() != null) {
                    compare = false;
                }
                if (!compare) {
                    return false;
                }
                
                if (event1.getGuardEventHandler() != null 
                    && event2.getGuardEventHandler() != null) {
                    compare = compareObject(event1.getGuardEventHandler().toString(),
                            event2.getGuardEventHandler().toString());
                } else if (event1.getGuardEventHandler() != null 
                            || event2.getGuardEventHandler() != null) {
                    compare = false;
                }
                if (!compare) {
                    return false;
                }
            }
            
            
        }
        
        return true;
    }
    
    private static boolean compareObject(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        } else if (object1 == null || object2 == null) {
            return false;
        }
        return object1.equals(object2);
    }
}
