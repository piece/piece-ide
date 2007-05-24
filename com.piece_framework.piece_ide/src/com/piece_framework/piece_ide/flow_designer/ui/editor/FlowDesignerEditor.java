// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.StateFactory;
import com.piece_framework.piece_ide.flow_designer.model.Transition;
import com.piece_framework.piece_ide.flow_designer.
            ui.editpart.FlowDesignerEditFactory;
import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;

//import piece_ide.flow_designer.ui.editpart.FlowDesignerEditFactory;
//import piece_ide.PieceIDEPlugin;

/**
 * フローデザイナー・エディター.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowDesignerEditor extends GraphicalEditorWithFlyoutPalette {
    
    private static final int INITAL_STATE_X = 50;
    private static final int INITAL_STATE_Y = 50;
    
    private Flow fFlow;
    
    /**
     * コンストラクタ.
     * 
     */
    public FlowDesignerEditor() {
        DefaultEditDomain domain = new DefaultEditDomain(this);
        setEditDomain(domain);
    }
    
    /**
     * グラフィカル・ビューアを設定する.
     * グラフィカル・ビューアを作成し、エディットパート・ファクトリと
     * メニュー・プロバイダを設定する。
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
     */
    @Override
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        
        GraphicalViewer viewer = getGraphicalViewer();
        viewer.setEditPartFactory(new FlowDesignerEditFactory());
        
        FlowDesignerContextMenuProvider menuProvider
            = new FlowDesignerContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(menuProvider);
        getSite().registerContextMenu(menuProvider, viewer);
    }

    /**
     * グラフィカル・ビューアを初期化する.
     * ファイルからフローを読み込んで、表示する。ファイルにフローの情報が
     * ない場合はイニシャルステートのみを表示する。
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette
     *          #initializeGraphicalViewer()
     */
    @Override
    protected void initializeGraphicalViewer() {
        
        boolean needInit = true;
        
        try {
            IFile file = ((IFileEditorInput) getEditorInput()).getFile();
            setPartName(file.getName());
            ObjectInputStream in = new ObjectInputStream(file.getContents());
            fFlow = (Flow) in.readObject();
            in.close();
            
            needInit = false;
            
        } catch (CoreException ce) {
            // TODO: オブジェクト読み込み時の例外処理
            ce.printStackTrace();
            
        } catch (EOFException eofe) {
            // TODO: オブジェクト読み込み時の例外処理
            eofe.printStackTrace();
            
        } catch (IOException ioe) {
            // TODO: オブジェクト読み込み時の例外処理
            ioe.printStackTrace();
            
        } catch (ClassNotFoundException cnfe) {
            // TODO: オブジェクト読み込み時の例外処理
            cnfe.printStackTrace();
            
        }
        
        if (needInit) {
            fFlow = new Flow();
            
            State initialState = new State(State.INITIAL_STATE);
            
            initialState.setX(INITAL_STATE_X);
            initialState.setY(INITAL_STATE_Y);
            
            fFlow.addState(initialState);
        }
        
        GraphicalViewer viewer = getGraphicalViewer();
        viewer.setContents(fFlow);
    }
    
    /**
     * フローを保存する.
     * 
     * @param monitor プログレスモニター
     * @see org.eclipse.ui.part.EditorPart
     *          #doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(fFlow);
            objectOut.close();
            
            IFile file = ((IFileEditorInput) getEditorInput()).getFile();
            file.setContents(
                    new ByteArrayInputStream(byteOut.toByteArray()), 
                    true,
                    false,
                    monitor);
            getCommandStack().markSaveLocation();
            
        } catch (CoreException ce) {
            // TODO: オブジェクト読み込み時の例外処理
            ce.printStackTrace();
            
        } catch (IOException ioe) {
            // TODO: オブジェクト読み込み時の例外処理
            ioe.printStackTrace();
            
        }
    }
    
    /**
     * コマンドスタックに変更があった場合のイベント.
     * 編集中の場合、エディターのタイトルに"*"を表示する。
     * 
     * @param event イベント
     * @see org.eclipse.gef.ui.parts.GraphicalEditor
     *          #commandStackChanged(java.util.EventObject)
     */
    @Override
    public void commandStackChanged(EventObject event) {
        firePropertyChange(IEditorPart.PROP_DIRTY);
        super.commandStackChanged(event);
    }

    /**
     * パレット・ルートを返す.
     * パレットを生成して返す。
     * 
     * @return 作成したパレット・ルート
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette
     *          #getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        PaletteRoot root = new PaletteRoot();
        
        PaletteGroup group = new PaletteGroup("選択ツール");
        group.add(new PanningSelectionToolEntry());
        group.add(new MarqueeToolEntry());
        root.add(group);
        
        PaletteDrawer drawer = new PaletteDrawer("ステート");
        drawer.add(new CreationToolEntry(
                "ビューステート", 
                "ビューステートを作成",
                new StateFactory(State.VIEW_STATE),
                PieceIDEPlugin.getImageDescriptor("icons/ViewState.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/ViewState.gif")));
        drawer.add(new CreationToolEntry(
                "アクションステート", 
                "アクションステートを作成",
                new StateFactory(State.ACTION_STATE),
                PieceIDEPlugin.getImageDescriptor("icons/ActionState.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/ActionState.gif")));
        drawer.add(new CreationToolEntry(
                "ファイナルステート", 
                "ファイナルステートを作成",
                new StateFactory(State.FINAL_STATE),
                PieceIDEPlugin.getImageDescriptor("icons/FinalState.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/FinalState.gif")));
        root.add(drawer);
        
        drawer = new PaletteDrawer("コネクタ");
        drawer.add(new ConnectionCreationToolEntry(
                "遷移", 
                "遷移を設定",
                new SimpleFactory(Transition.class),
                PieceIDEPlugin.getImageDescriptor("icons/Transition.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/Transition.gif")));
        root.add(drawer);
        
        return root;
    }
}
