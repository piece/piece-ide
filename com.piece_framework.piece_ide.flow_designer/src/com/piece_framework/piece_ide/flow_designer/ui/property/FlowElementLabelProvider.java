// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;

import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.ActionStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FinalStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FlowEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.InitialStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.NormalStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.ViewStateEditPart;

/**
 * フローのラベルプロバイダー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class FlowElementLabelProvider extends LabelProvider {

    /**
     * コンストラクタ.
     * マッパーを生成する。
     *
     */
    public FlowElementLabelProvider() {
        super();
    }

    /**
     * セクションのタイトルとなる文字列を返す.
     *
     * @param elements 選択されたオブジェクト
     * @return セクションのタイトル文字列
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object elements) {
        String text = null;

        if (elements == null || elements.equals(StructuredSelection.EMPTY)) {
            return Messages.getString(
                    "FlowElementLabelProvider.Flow"); //$NON-NLS-1$
        }

        Object object = getSelectionObject(elements);

        if (object != null) {
            String stateName = null;
            if (object instanceof NormalStateEditPart) {
                stateName =
                    ((State) ((NormalStateEditPart) object).getModel())
                        .getName();
            }
            if (object instanceof ActionStateEditPart) {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.ActionState")  //$NON-NLS-1$
                    + " : " + stateName;
            } else if (object instanceof ViewStateEditPart) {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.ViewState")    //$NON-NLS-1$
                    + " : " + stateName;
            } else if (object instanceof InitialStateEditPart) {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.InitialState"); //$NON-NLS-1$
            } else if (object instanceof FinalStateEditPart) {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.FinalState");  //$NON-NLS-1$
            } else if (object instanceof FlowEditPart) {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.Flow"); //$NON-NLS-1$
            } else {
                text =
                    Messages.getString(
                        "FlowElementLabelProvider.Unknown"); //$NON-NLS-1$
            }
        }

        return text;
    }

    /**
     * セレクションオブジェクトからエディットパートを取得する.
     * 複数選択されている場合は、最初に選択したオブジェクトの
     * エディットパートを返す。
     *
     * @param objects セレクションオブジェクト
     * @return エディットパート
     */
    private Object getSelectionObject(Object objects) {
        Object object = null;
        if (objects instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) objects;
            object = selection.getFirstElement();
        }
        return object;
    }
}
