package com.piece_framework.piece_ide.flow_designer.ui.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

import com.piece_framework.piece_ide.flow_designer.model.AbstractModel;

/**
 * フローデザイナーのプロパティシートの抽象セクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public abstract class FlowDesignerPropertySection extends
        AbstractPropertySection {

    private AbstractModel fModel;
    
    /**
     * インプットオブジェクトのセッターメソッド.
     * インプットオブジェクトからモデルを取得し、リスナーを登録する.
     * 
     * @param part ワークベンチパート
     * @param selection セレクトオブジェクト
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *          #setInput(
     *              org.eclipse.ui.IWorkbenchPart, 
     *              org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        if (selection instanceof IStructuredSelection) {
            Object input = ((IStructuredSelection) selection).getFirstElement();
            if (input instanceof EditPart) {
                fModel = (AbstractModel) ((EditPart) input).getModel();
                fModel.addPropertyChangeListener(new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent arg0) {
                        refresh();
                    }
                });
            }
        }
    }
    
    /**
     * コマンドを実行する.
     * 
     * @param command コマンド
     */
    protected void executeCommand(Command command) {
        CommandStack commandStack = 
            (CommandStack) getPart().getAdapter(CommandStack.class);
        
        if (commandStack != null) {
            commandStack.execute(command);
        }
    }
    
    /**
     * モデルを返す.
     * 
     * @return モデル
     */
    protected AbstractModel getModel() {
        return fModel;
    }
}
