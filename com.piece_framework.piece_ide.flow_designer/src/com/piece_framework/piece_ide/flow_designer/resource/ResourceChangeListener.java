// $Id$
package com.piece_framework.piece_ide.flow_designer.resource;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.piece_framework.piece_ide.flow_designer.mapper.FlowSerializeUtility;
import com.piece_framework.piece_ide.flow_designer.ui.editor.FlowDesignerEditor;

/**
 * ワークスペースの変更通知を受けとり、YAMLファイルの変更に対する
 * 処理をシリアライズファイルにも行う.
 *
 * @author TANAKA
 * @since 0.3.0
 */
public class ResourceChangeListener implements IResourceChangeListener,
        IResourceDeltaVisitor {
    private ArrayList<IResourceDelta> fAddedList;
    private ArrayList<IResourceDelta> fRemovedList;

    /**
     * YAMLファイルの変更に対する処理をシリアライズファイルにも行う.
     *
     * @param event リソース変更イベント
     */
    public void resourceChanged(IResourceChangeEvent event) {
        fAddedList = new ArrayList<IResourceDelta>();
        fRemovedList = new ArrayList<IResourceDelta>();
        try {
            event.getDelta().accept(this);
            for (IResourceDelta delta : fAddedList) {
                if (FlowSerializeUtility.hasSerializeFile(
                                        delta.getMovedFromPath())) {
                    FlowSerializeUtility.
                        moveSerializeFile(
                                delta.getMovedFromPath(),
                                delta.getFullPath()
                        );
                    reuseEditor(delta);
                }
            }

            for (IResourceDelta delta : fRemovedList) {
                if (FlowSerializeUtility.hasSerializeFile(
                                            delta.getFullPath())) {
                    FlowSerializeUtility.
                            removeSerializeFile(delta.getFullPath());
                    closeEditor(delta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 与えられた変更情報から変更対象リソースを取得する.
     * 追加、削除されたファイルをそれぞれリストに保存する。
     *
     * @param delta リソース変更情報
     * @return 常に真
     * @exception CoreException コア例外
     */
    public boolean visit(IResourceDelta delta) throws CoreException {
        if (delta.getResource().getType() != IResource.FILE) {
            return true;
        }

        switch (delta.getKind()) {
        case IResourceDelta.ADDED:
            this.fAddedList.add(delta);
            break;
        case IResourceDelta.REMOVED:
            this.fRemovedList.add(delta);
            break;
        case IResourceDelta.CHANGED:
            if ((delta.getFlags() & IResourceDelta.REPLACED) != 0) {
                fAddedList.add(delta);
            }
            break;
        default:
            break;
        }
        return true;
    }

    /**
     * エディター保存先となるエディター入力を変更する.
     *
     * @param delta リソース変更情報
     */
    private void reuseEditor(IResourceDelta delta) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IPath fromPath = delta.getMovedFromPath();
        IFile toFile = root.getFile(delta.getFullPath());

        IEditorInput input = new FileEditorInput(root.getFile(fromPath));

        ArrayList<FlowDesignerEditor> editors = getOpenedEditors(input);

        for (FlowDesignerEditor flowDesignerEditor : editors) {
            flowDesignerEditor.setInput(new FileEditorInput(toFile));
        }
    }

    /**
     * 削除されたYAMLファイルを入力としていたエディターを閉じる.
     *
     * @param delta リソース変更情報
     * @throws Exception 例外
     */
    private void closeEditor(IResourceDelta delta) throws Exception {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IEditorInput input = new FileEditorInput(
                                    root.getFile(delta.getFullPath()));
        ArrayList<FlowDesignerEditor> editors = getOpenedEditors(input);

        for (FlowDesignerEditor flowDesignerEditor : editors) {
            flowDesignerEditor.close(false);
        }
    }

    /**
     * 引数に与えられたエディター入力を使用としているエディターのリストを返す.
     *
     * @param input エディター入力
     * @return 引数に与えられた EditorInput を入力としているエディターのリスト
     */
    private ArrayList<FlowDesignerEditor> getOpenedEditors(IEditorInput input) {
        ArrayList<FlowDesignerEditor> openedEditors
                                        = new ArrayList<FlowDesignerEditor>();
        IWorkbenchWindow[] windows
                            = PlatformUI.getWorkbench().getWorkbenchWindows();
        for (IWorkbenchWindow window : windows) {
            IWorkbenchPage[] pages = window.getPages();
            for (IWorkbenchPage page : pages) {
                String id = "com.piece_framework.piece_ide."
                        + "flow_designer.ui.editor.FlowDesignerEditor";
                IEditorReference[] editors = page.findEditors(input, id,
                        IWorkbenchPage.MATCH_INPUT | IWorkbenchPage.MATCH_ID);
                for (IEditorReference editorReference : editors) {
                    FlowDesignerEditor editor =
                          (FlowDesignerEditor) editorReference.getEditor(true);
                    openedEditors.add(editor);
                }
            }
        }
        return openedEditors;
    }
}
