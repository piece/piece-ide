package com.piece_framework.piece_ide.flow_designer.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;

/**
 * フローデザイナー・エディタクラス.
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 */
public class FlowEditor extends GraphicalEditor {

    /**
     * コンストラクタ.
     */
    public FlowEditor() {
        DefaultEditDomain domain = new DefaultEditDomain(this);
        setEditDomain(domain);
    }

    /**
     * グラフィックエディタ起動時処理.
     */
    protected void initializeGraphicalViewer() {
    }

    /**
     * 保存時処理.
     * @param monitor プログレスモニター
     */
    public void doSave(IProgressMonitor monitor) {
    }
}
