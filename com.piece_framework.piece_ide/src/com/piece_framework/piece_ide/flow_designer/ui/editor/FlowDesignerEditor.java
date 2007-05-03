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

import com.piece_framework.piece_ide.flow_designer.model.ActionState;
import com.piece_framework.piece_ide.flow_designer.model.Diagram;
import com.piece_framework.piece_ide.flow_designer.model.FinalState;
import com.piece_framework.piece_ide.flow_designer.model.InitialState;
import com.piece_framework.piece_ide.flow_designer.model.Transition;
import com.piece_framework.piece_ide.flow_designer.model.ViewState;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FlowDesignerEditFactory;
import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;

//import piece_ide.flow_designer.ui.editpart.FlowDesignerEditFactory;
//import piece_ide.PieceIDEPlugin;

public class FlowDesignerEditor extends GraphicalEditorWithFlyoutPalette {
    
    private Diagram fDiagram;
    
    public FlowDesignerEditor() {
        DefaultEditDomain domain = new DefaultEditDomain(this);
        setEditDomain(domain);
    }
    
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

    @Override
    protected void initializeGraphicalViewer() {
        
        boolean needInit = true;
        
        try {
            IFile file = ((IFileEditorInput) getEditorInput()).getFile();
            setPartName(file.getName());
            ObjectInputStream in = new ObjectInputStream(file.getContents());
            fDiagram = (Diagram) in.readObject();
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
            fDiagram = new Diagram();
            
            InitialState initialState = new InitialState();
            FinalState finalState = new FinalState();
            
            initialState.setX(50);
            initialState.setY(10);
            
            finalState.setX(50);
            finalState.setY(200);
            
            fDiagram.addContents(initialState);
            fDiagram.addContents(finalState);
        }
        
        GraphicalViewer viewer = getGraphicalViewer();
        viewer.setContents(fDiagram);
    }
    
    @Override
    public void doSave(IProgressMonitor monitor) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(fDiagram);
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
    
    @Override
    public void commandStackChanged(EventObject event) {
        firePropertyChange(IEditorPart.PROP_DIRTY);
        super.commandStackChanged(event);
    }

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
                new SimpleFactory(ViewState.class),
                PieceIDEPlugin.getImageDescriptor("icons/ViewState.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/ViewState.gif")));
        drawer.add(new CreationToolEntry(
                "アクションテート", 
                "アクションテートを作成",
                new SimpleFactory(ActionState.class),
                PieceIDEPlugin.getImageDescriptor("icons/ActionState.gif"),
                PieceIDEPlugin.getImageDescriptor("icons/ActionState.gif")));
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
