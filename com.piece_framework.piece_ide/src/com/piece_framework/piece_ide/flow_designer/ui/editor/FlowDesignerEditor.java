// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import java.io.EOFException;
import java.io.IOException;
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
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.osgi.service.prefs.BackingStoreException;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventFactory;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.StateFactory;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FlowDesignerEditFactory;
import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowReader;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowWriter;

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
public class FlowDesignerEditor extends GraphicalEditorWithFlyoutPalette 
                                implements ITabbedPropertySheetPageContributor {
    
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
//            ObjectInputStream in = new ObjectInputStream(file.getContents());
//            fFlow = (Flow) in.readObject();
//            in.close();
            
            fFlow = FlowReader.read(file);
            if (fFlow != null) {
                needInit = false;
            }
            
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
            String flowName = null;
            String actionClassName = null;
            // ファイル名からフロー名・アクションクラス名を生成
            if (getPartName() != null) {
                String fileName = getPartName();
                
                flowName = fileName.substring(0, fileName.indexOf('.'));
                actionClassName = flowName + "Action"; 
            }
            
            fFlow = new Flow(flowName, actionClassName);
            
            State initialState = new State(State.INITIAL_STATE);
            initialState.setName(fFlow.generateStateName(State.INITIAL_STATE));
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
        try {
            FlowWriter.write(fFlow,
                             ((IFileEditorInput) getEditorInput()).getFile(),
                             monitor);
            getCommandStack().markSaveLocation();
            
        } catch (CoreException ce) {
            // TODO: オブジェクト読み込み時の例外処理
            ce.printStackTrace();
            
        } catch (IOException ioe) {
            // TODO: オブジェクト読み込み時の例外処理
            ioe.printStackTrace();
            
        } catch (BackingStoreException bse) {
            bse.printStackTrace();
        }
    }
    
    /**
     * コマンドスタックに変更があった場合のイベント.
     * 編集中にする(エディターのタイトルに"*"を表示する)。
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
                new EventFactory(Event.TRANSITION_EVENT),
                PieceIDEPlugin.getImageDescriptor("icons/Transition.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/Transition.gif")));
        root.add(drawer);
        
        return root;
    }

    /**
     * クラスの型に合ったオブジェクトを返す.
     * 
     * @param type クラス
     * @return 指定されたクラスの型にあったオブジェクト
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette
     *          #getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class type) {
        if (type == IPropertySheetPage.class) {
            return new TabbedPropertySheetPage(this);
        }
        return super.getAdapter(type);
    }

    /**
     * タブ・プロパティが使用するコントリビュートIDを返す.
     * 
     * @return コントリビュートID
     * @see org.eclipse.ui.views.properties.tabbed
     *          .ITabbedPropertySheetPageContributor#getContributorId()
     */
    public String getContributorId() {
        System.out.println(getSite().getId());
        return getSite().getId();
    }
}
