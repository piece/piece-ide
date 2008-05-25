// $Id$ 
package com.piece_framework.piece_ide.flow_designer.resource;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
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
 * workspace の変更通知を受けとり、YAMLファイルの変更に対する処理をシリアライズファイルにも行う.
 * 
 * @see IResourceDelta
 * @see IWorkspace#addResourceChangeListener(IResourceChangeListener, int)
 */
public class ResourceChangeListener implements IResourceChangeListener,
        IResourceDeltaVisitor {
    private ArrayList<IResourceDelta> fAddedList;

    private ArrayList<IResourceDelta> fRemovedList;

    /**
     * YAMLファイルの変更に対する処理をシリアライズファイルにも行う.
     * 
     * @param event
     *            the resource change event
     * @see IResourceDelta
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
            this.fAddedList.add(delta);
            break;
        case IResourceDelta.REMOVED:
            this.fRemovedList.add(delta);
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
        
        ArrayList<FlowDesignerEditor> editors = getOpenedEditors(input);
        
        for (FlowDesignerEditor flowDesignerEditor : editors) {
            flowDesignerEditor.setInput(new FileEditorInput(toFile));
        }
    }

    /**
     * 削除されたYAMLファイルを入力としていたエディタを閉じる.
     * 
     * @param delta リソースツリーの変更内容を表すオブジェクト
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
     * 引数に与えられた EditorInput を入力としているエディタのリストを返す.
     * @param input エディタ入力
     * @return 引数に与えられた EditorInput を入力としているエディタのリスト
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
