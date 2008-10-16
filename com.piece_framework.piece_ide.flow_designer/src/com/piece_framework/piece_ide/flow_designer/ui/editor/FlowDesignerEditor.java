// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.osgi.service.prefs.BackingStoreException;

import com.piece_framework.piece_ide.flow_designer.mapper.FlowReader;
import com.piece_framework.piece_ide.flow_designer.mapper.FlowWriter;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventFactory;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.StateFactory;
import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FlowDesignerEditFactory;

/**
 * フローデザイナー・エディター.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class FlowDesignerEditor extends GraphicalEditorWithFlyoutPalette
           implements ITabbedPropertySheetPageContributor, IReusableEditor {
    private static final int INITAL_STATE_X = 50;
    private static final int INITAL_STATE_Y = 50;

    private Flow fFlow;

    /**
     * コンストラクタ.
     */
    public FlowDesignerEditor() {
        DefaultEditDomain domain = new DefaultEditDomain(this) {
            private boolean fIsShowPropertySheet = false;

            @Override
            public void focusGained(FocusEvent event, EditPartViewer viewer) {
                super.focusGained(event, viewer);

                if (!fIsShowPropertySheet) {
                    try {
                        IWorkbenchPage workbenchPage =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                                .getActivePage();
                        workbenchPage.showView(
                            "org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
                        fIsShowPropertySheet = true;
                    } catch (PartInitException pie) {
                        pie.printStackTrace();
                    }
                }
            }
        };
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

        List<String> zoomLevels = new ArrayList<String>();
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        IAction zoomIn = new ZoomInAction(root.getZoomManager());
        IAction zoomOut = new ZoomOutAction(root.getZoomManager());
        getActionRegistry().registerAction(zoomIn);
        getActionRegistry().registerAction(zoomOut);
        IHandlerService service =
            (IHandlerService) getSite().getService(IHandlerService.class);
        service.activateHandler(GEFActionConstants.ZOOM_IN,
                                new ActionHandler(zoomIn)
                                );
        service.activateHandler(GEFActionConstants.ZOOM_OUT,
                                new ActionHandler(zoomOut)
                                );

        ScrollingGraphicalViewer viewer =
            (ScrollingGraphicalViewer) getGraphicalViewer();
        viewer.setRootEditPart(root);
        viewer.setEditPartFactory(new FlowDesignerEditFactory());

        getActionRegistry().registerAction(new ToggleGridAction(viewer));
        getActionRegistry().registerAction(
                new ToggleSnapToGeometryAction(viewer));

        FlowDesignerContextMenuProvider menuProvider
            = new FlowDesignerContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(menuProvider);
        getSite().registerContextMenu(menuProvider, viewer);

        viewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED,
                           new Boolean(true));
    }

    /**
     * アクションを生成する.
     *
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    @Override
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    protected void createActions() {
        super.createActions();
        ActionRegistry registry = getActionRegistry();

        IAction adjustEventAction = new AdjustEventsAction(this);
        registry.registerAction(adjustEventAction);
        getSelectionActions().add(adjustEventAction.getId());

        IAction showPropertyAction = new ShowPropertySheetAction(this);
        registry.registerAction(showPropertyAction);
        getSelectionActions().add(showPropertyAction.getId());
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

            fFlow = FlowReader.read(file);
            if (fFlow != null) {
                needInit = false;
            }
        } catch (CoreException ce) {
            // TODO: オブジェクト読み込み時の例外処理
            ce.printStackTrace();
        }

        if (needInit) {
            String flowName = null;
            String actionClassName = null;
            // ファイル名からフロー名・アクションクラス名を生成
            if (getPartName() != null) {
                String fileName = getPartName();

                flowName = fileName.substring(0, fileName.indexOf('.'));
                actionClassName = flowName + "Action";   //$NON-NLS-1$
            }

            fFlow = new Flow(flowName, actionClassName);

            State initialState = new State(State.INITIAL_STATE);
            initialState.setName(fFlow.generateStateName(State.INITIAL_STATE));
            initialState.setX(INITAL_STATE_X);
            initialState.setY(INITAL_STATE_Y);

            fFlow.addState(initialState);
        }

        getGraphicalViewer().setContents(fFlow);
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

        PaletteGroup group = new PaletteGroup(
                Messages.getString(
                        "FlowDesignerEditor.PalleteGroup")); //$NON-NLS-1$
        group.add(new PanningSelectionToolEntry());
        group.add(new MarqueeToolEntry());
        root.add(group);

        PaletteDrawer drawer = new PaletteDrawer(
                Messages.getString(
                    "FlowDesignerEditor.PalletteDrawerState")); //$NON-NLS-1$
        drawer.add(new CreationToolEntry(
            Messages.getString(
                "FlowDesignerEditor.PalleteViewState"), //$NON-NLS-1$
            Messages.getString(
                "FlowDesignerEditor.PalleteViewStateComment"), //$NON-NLS-1$
            new StateFactory(State.VIEW_STATE),
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/ViewState.gif"),  //$NON-NLS-1$
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/ViewState.gif")));  //$NON-NLS-1$
        drawer.add(new CreationToolEntry(
            Messages.getString(
                "FlowDesignerEditor.PalleteActionState"), //$NON-NLS-1$
            Messages.getString(
                "FlowDesignerEditor.PalleteActionStateComment"), //$NON-NLS-1$
            new StateFactory(State.ACTION_STATE),
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/ActionState.gif"),  //$NON-NLS-1$
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/ActionState.gif")));  //$NON-NLS-1$
        drawer.add(new CreationToolEntry(
            Messages.getString(
                "FlowDesignerEditor.PalleteFinalState"), //$NON-NLS-1$
            Messages.getString(
                "FlowDesignerEditor.PalleteFinalStateComment"), //$NON-NLS-1$
            new StateFactory(State.FINAL_STATE),
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/FinalState.gif"),  //$NON-NLS-1$
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/FinalState.gif")));  //$NON-NLS-1$
        root.add(drawer);

        drawer = new PaletteDrawer(
            Messages.getString(
                "FlowDesignerEditor.PalletteDrawerTransition")); //$NON-NLS-1$
        drawer.add(new ConnectionCreationToolEntry(
            Messages.getString(
                "FlowDesignerEditor.PalletteTransition"), //$NON-NLS-1$
            Messages.getString(
                "FlowDesignerEditor.PalleteTransitionComment"), //$NON-NLS-1$
            new EventFactory(Event.TRANSITION_EVENT),
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/Transition.gif"),  //$NON-NLS-1$
            FlowDesignerPlugin.getImageDescriptor(
                    "icons/Transition.gif")));  //$NON-NLS-1$
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
        } else if (type == Flow.class) {
            return fFlow;
        } else if (type == ZoomManager.class) {
            return getGraphicalViewer().getProperty(
                        ZoomManager.class.toString());
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
        return getSite().getId();
    }

    /**
     * エディタ入力をセットする.
     *
     * @param input エディタ入力
     */
    public final void setInput(IEditorInput input) {
        setInputWithNotify(input);
    }

    /**
     * エディターを閉じる.
     *
     * @param save 保存するか
     */
    public void close(final boolean save) {
        Display display = getSite().getShell().getDisplay();
        display.asyncExec(new Runnable() {
            public void run() {
                getSite().getPage().closeEditor(FlowDesignerEditor.this, save);
            }
        });
    }
}
