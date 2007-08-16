package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
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
     * イルから読み込んだFlowオブジェクトを返す。
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
        
        if (returnFlow != null) {
            String fileName = yamlFile.getName();
            String flowName = fileName.substring(0, fileName.indexOf('.'));
            if (returnFlow.getName() == null) {
                returnFlow.setName(flowName);
            }
            if (returnFlow.getActionClassName() == null) {
                returnFlow.setActionClassName(flowName + "Action");
            }
        }
        
        return returnFlow;
    }
    
    /**
     * フローを比較する.
     * 
     * @param flow1 フロー1
     * @param flow2 フロー2
     * @return ふたつのフローが一致しているか
     */
    public static boolean compareFlow(Flow flow1, Flow flow2) {
        if (flow1 == null || flow2 == null) {
            return false;
        }
        
        for (State state1 : flow1.getStateList()) {
            State state2 = flow2.getStateByName(state1.getName());
            if (!compareState(state1, state2)) {
                return false;
            }
            
            for (Event event1 : state1.getEventList()) {
                if (!compareEvent(event1,
                                  state2.getEventByName(event1.getName()))) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * ステートを比較する.
     * 比較する内容は下記のとおり。<br>
     * ・ステート名が同じか(フロー2からの呼び出し時にチェック)<br>
     * ・ビュー名が同じか(ビューステートの場合のみ)<br>
     * 
     * @param state1 ステート1
     * @param state2 ステート2
     * @return ふたつのステートが一致しているか
     */
    private static boolean compareState(State state1, State state2) {
        if (state2 == null) {
            return false;
        }
        
        if (state1.getType() == State.VIEW_STATE) {
            if (!compareObject(state1.getView(),
                               state2.getView(), 
                               null)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * イベントを比較する.
     * 比較する内容は下記のとおり。<br>
     * ・次ステートが同じか<br>
     * ・イベントハンドラが同じか<br>
     * ・ガードイベントハンドラが同じか<br>
     * 
     * @param event1 イベント1
     * @param event2 イベント2
     * @return ふたつのイベントが一致しているか
     */
    private static boolean compareEvent(Event event1, Event event2) {
        if (event2 == null) {
            return false;
        }
        
        Method getNameMethod = null;
        Method toStringMethod = null;
        try {
            getNameMethod = 
                State.class.getMethod("getName", (Class[]) null);
            toStringMethod = 
                EventHandler.class.getMethod("toString", (Class[]) null);
        } catch (NoSuchMethodException nsme) {
        }
        
        if (!compareObject(event1.getNextState(),
                           event2.getNextState(), 
                           getNameMethod)) {
            return false;
        }
        if (!compareObject(event1.getEventHandler(),
                           event2.getEventHandler(), 
                           toStringMethod)) {
            return false;
        }
        if (!compareObject(event1.getGuardEventHandler(),
                           event2.getGuardEventHandler(), 
                           toStringMethod)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * オブジェクトを比較する.
     * 最初に指定されたオブジェクト自体のnullチェックを行い、ふたつとも
     * 有効であれば、メソッド呼び出しを行い戻り値の比較を行う。
     * 
     * @param object1 オブジェクト1
     * @param object2 オブジェクト2
     * @param method メソッド
     * @return ふたつのオブジェクトが一致しているか
     */
    private static boolean compareObject(
                                Object object1, 
                                Object object2, 
                                Method method) {
        if (object1 == null && object2 == null) {
            return true;
        } else if (object1 == null || object2 == null) {
            return false;
        }
        if (method == null) {
            return object1.equals(object2);
        }
        
        Object returnValue1 = null;
        Object returnValue2 = null;
        try {
            returnValue1 = method.invoke(object1, (Object[]) null);
            returnValue2 = method.invoke(object2, (Object[]) null);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return false;
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
            return false;
        }
        if (returnValue1 == null && returnValue2 == null) {
            return true;
        } else if (returnValue1 == null || returnValue2 == null) {
            return false;
        }
        return returnValue1.equals(returnValue2);
    }
}
