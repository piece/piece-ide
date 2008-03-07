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
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.piece_framework.piece_ide.flow_designer.mapper.FlowSerializeUtility;

/**
 * workspace の変更通知を受けとり、YAMLファイルの変更に対する処理をシリアライズファイルにも行う.
 * 
 * @see IResourceDelta
 * @see IWorkspace#addResourceChangeListener(IResourceChangeListener, int)
 */
public class ResourceChangeListener implements IResourceChangeListener,
        IResourceDeltaVisitor {
    private ArrayList<IResourceDelta> addedList;

    private ArrayList<IResourceDelta> removedList;

    /**
     * YAMLファイルの変更に対する処理をシリアライズファイルにも行う.
     * 
     * @param event
     *            the resource change event
     * @see IResourceDelta
     */
    public void resourceChanged(IResourceChangeEvent event) {
        addedList = new ArrayList<IResourceDelta>();
        removedList = new ArrayList<IResourceDelta>();
        try {
            event.getDelta().accept(this);
            for (IResourceDelta delta : addedList) {
                IPath fromPath = delta.getMovedFromPath();
                IPath toPath = delta.getFullPath();
                boolean result = FlowSerializeUtility.moveSerializeFile(
                        fromPath, toPath);
                if (result) {
                    reuseEditor(delta);
                }
            }

            for (IResourceDelta delta : removedList) {
                FlowSerializeUtility.removeSerializeFile(delta.getFullPath());
                closeEditor(delta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 与えられた resource delta を訪れる(子をたどって処理を行う) .
     * 
     * ファイルが追加された場合はaddedListに、ファイルが削除された場合はremovedListに 引数のdeltaをaddする。
     * 
     * @param delta
     *            リソースツリーの変更内容を表すオブジェクト
     * @return <code>true</code> resource delta の子をたどって処理を行った場合;
     *         <code>false</code> resource delta の子に対する処理がスキップされた場合.
     * @exception CoreException
     *                if the visit fails for some reason.
     */
    public boolean visit(IResourceDelta delta) throws CoreException {
        if (delta.getResource().getType() != IResource.FILE) {
            return true;
        }

        switch (delta.getKind()) {
        case IResourceDelta.ADDED:
            this.addedList.add(delta);
            break;
        case IResourceDelta.REMOVED:
            this.removedList.add(delta);
            break;
        default:
            break;
        }
        return true; // visit the children
    }

    /**
     * エディタ保存先となるエディタ入力を変更する.
     * 
     * @param delta リソースツリーの変更内容を表すオブジェクト
     */
    private void reuseEditor(IResourceDelta delta) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IPath fromPath = delta.getMovedFromPath();
        IFile toFile = root.getFile(delta.getFullPath());

        IEditorInput input = new FileEditorInput(root.getFile(fromPath));

        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
        for (IWorkbenchWindow window : windows) {
            IWorkbenchPage[] pages = window.getPages();
            for (IWorkbenchPage page : pages) {
                String id = "com.piece_framework.piece_ide."
                        + "flow_designer.ui.editor.FlowDesignerEditor";
                IEditorReference[] editors = page.findEditors(input, id,
                        IWorkbenchPage.MATCH_INPUT | IWorkbenchPage.MATCH_ID);
                for (IEditorReference editorReference : editors) {
                    IReusableEditor editor = (IReusableEditor) editorReference
                            .getEditor(true);
                    editor.setInput(new FileEditorInput(toFile));
                }
            }
        }
    }

    /**
     * 削除されたYAMLファイルを入力としていたエディタを閉じる.
     * 
     * @param delta リソースツリーの変更内容を表すオブジェクト
     * @throws Exception 例外
     */
    private void closeEditor(IResourceDelta delta) throws Exception {
        EditorCloser closer = new EditorCloser(delta);
        IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.getDisplay().asyncExec(closer);
    }
    
    /**
     * エディタを閉じるためRunnable実装したクラス.
     *
     *UIを処理するスレッドにエディタを閉じる処理を移譲する。
     *
     */
    private class EditorCloser implements Runnable {
        private IResourceDelta delta;
        
        /**
         * コンストラクタ.
         * @param removed リソースツリーの変更内容を表すオブジェクト 
         */
        public EditorCloser(IResourceDelta removed) {
            this.delta = removed;
        }

        /** 
         * UIを処理するスレッドにエディタを閉じる処理を移譲する.
         * @see java.lang.Runnable#run()
         * 
         */
        public void run() {
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IEditorInput input = new FileEditorInput(root.getFile(delta
                    .getFullPath()));
            for (IWorkbenchWindow window : windows) {
                IWorkbenchPage[] pages = window.getPages();
                for (IWorkbenchPage page : pages) {
                    String id = "com.piece_framework.piece_ide."
                            + "flow_designer.ui.editor.FlowDesignerEditor";
                    IEditorReference[] editors = page.findEditors(input, id,
                            IWorkbenchPage.MATCH_INPUT
                                    | IWorkbenchPage.MATCH_ID);
                    for (IEditorReference editorReference : editors) {
                        IReusableEditor editor = 
                              (IReusableEditor) editorReference.getEditor(true);
                        page.closeEditor(editor, false);
                    }
                }
            }
        }
    }
}
