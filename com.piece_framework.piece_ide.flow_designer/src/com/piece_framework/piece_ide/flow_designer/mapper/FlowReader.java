// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;
import com.piece_framework.piece_ide.internal.PieceIDE;

/**
 * フローリーダー.
 * YAMLファイルとシリアライズファイルからFlowオブジェクトを読み込む.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public final class FlowReader {

    /**
     * コンストラクタ.
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
     * @param yamlFile YAMLファイル
     * @return Flowオブジェクト
     * @throws CoreException コア例外
     */
    public static Flow read(IFile yamlFile) throws CoreException {
        Flow yamlFlow = null;
        Flow serializeFlow = null;

        yamlFlow = getFlowFromYAMLFile(yamlFile);
        if (yamlFlow == null) {
            boolean dataExists = false;
            BufferedInputStream bufferedIn = null;
            try {
                bufferedIn =
                    new BufferedInputStream(yamlFile.getContents(true));
                if (bufferedIn.read() != -1) {
                    dataExists = true;
                }

            } catch (IOException ioe) {
            } finally {
                try {
                    bufferedIn.close();
                } catch (IOException ioe) {
                }
            }

            if (dataExists) {
                MessageDialog.openError(
                        null,
                        Messages.getString(
                            "FlowReader.ReadErrorTitle"), //$NON-NLS-1$
                        Messages.getString(
                            "FlowReader.ReadError")); //$NON-NLS-1$
            }
            return null;
        }

        serializeFlow = getFlowFromSeirializeFile(yamlFile);

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
                returnFlow.setActionClassName(
                        flowName + "Action"); //$NON-NLS-1$
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
     * YAMLファイルからFlowオブジェクトを取得する.
     *
     * @param yamlFile YAMLファイル
     * @return Flowオブジェクト
     * @throws CoreException コア例外
     */
    private static Flow getFlowFromYAMLFile(IFile yamlFile)
                                throws CoreException {
        Flow yamlFlow = null;
        BufferedInputStream bufferedIn = null;
        ByteArrayOutputStream byteOut = null;
        try {
            bufferedIn = new BufferedInputStream(yamlFile.getContents(true));
            byteOut = new ByteArrayOutputStream();
            int read = 0;
            while ((read = bufferedIn.read()) != -1) {
                byteOut.write(read);
            }

            FlowMapper flowMapper = new FlowMapper();
            yamlFlow =
                flowMapper.getFlow(byteOut.toString("UTF-8")); //$NON-NLS-1$

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bufferedIn != null) {
                    bufferedIn.close();
                }
            } catch (IOException ioe) {
            }
            try {
                if (byteOut != null) {
                    byteOut.close();
                }
            } catch (IOException ioe) {
            }
        }

        return yamlFlow;
    }

    /**
     * シリアライズファイルからFlowオブジェクトを取得する.
     * シリアライズファイルのパスはYAMLファイルのパスを元に導出される
     * ので、YAMLファイルを引数として受け取る。
     *
     * @param yamlFile YAMLファイル
     * @return Flowオブジェクト
     * @throws CoreException コア例外
     */
    private static Flow getFlowFromSeirializeFile(IFile yamlFile)
                            throws CoreException {
        Flow serializeFlow = null;
        IFile serializeFlowFile =
                FlowSerializeUtility.getFlowSeirializeFile(yamlFile);

        if (serializeFlowFile != null) {
            if (serializeFlowFile.exists()) {
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(
                                serializeFlowFile.getContents(true));
                    serializeFlow = (Flow) in.readObject();
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException ioe) {
                    }
                }
            }
        }

        return serializeFlow;
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
            if (!PieceIDE.compare(state1.getView(),
                                  state2.getView())) {
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
        try {
            getNameMethod =
                State.class.getMethod("getName", (Class[]) null); //$NON-NLS-1$
        } catch (NoSuchMethodException nsme) {
        }

        if (!PieceIDE.compare(event1.getNextState(),
                              event2.getNextState(),
                              getNameMethod)) {
            return false;
        }
        if (!PieceIDE.compare(event1.getEventHandler(),
                              event2.getEventHandler())) {
            return false;
        }
        if (!PieceIDE.compare(event1.getGuardEventHandler(),
                              event2.getGuardEventHandler())) {
            return false;
        }
        return true;
    }
}
