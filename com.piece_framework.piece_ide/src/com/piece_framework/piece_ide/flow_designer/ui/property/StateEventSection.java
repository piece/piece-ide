// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.piece_framework.piece_ide.flow_designer.command.CreateEventCommand;
import com.piece_framework.piece_ide.flow_designer.command.DeleteEventCommand;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ステート・プロパティシートのイベントセクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class StateEventSection extends FlowDesignerPropertySection {

    private static final int LABEL_POSITION_PERCENT = 50;
    private static final int TABLE_POSITION_PERCENT = 100;
    private static final int BUTTON_POSITION_PERCENT = 100;
    
    private static final int TABLE_HEIGHT_MARGIN = 40;
    
    private static final int EVENT_COLUMN_WIDTH = 100;
    private static final int NEXT_STATE_COLUMN_WIDTH = 100;
    private static final int EVENT_HANDLER_COLUMN_WIDTH = 150;
    private static final int GUARD_COLUMN_WIDTH = 150;
    
    private static final RGB EVENT_BUILTIN_COLOR = new RGB(156, 207, 255);
    private static final RGB EVENT_TRANSITION_COLOR = new RGB(206, 255, 206);
    private static final RGB EVENT_INTERNAL_COLOR = new RGB(255, 154, 206);
    
    private CLabel fStateNameLabel;
    private Button fCreateInternalEvent;
    private Button fDeleteInternalEvent;
    private TableViewer fEventTableViewer;
    
    private Control fTab;
    
    private MouseListener deleteInternalEventListener = new MouseListener() {
        public void mouseDoubleClick(MouseEvent mouseEvent) {
        }

        public void mouseDown(MouseEvent mouseEvent) {
        }

        public void mouseUp(MouseEvent mouseEvent) {
            Table eventTable = fEventTableViewer.getTable();
            if (eventTable == null || eventTable.getSelectionCount() == 0) {
                return;
            }
            
            executeCommand(new DeleteEventCommand(
                    (State) getModel(), 
                    (Event) eventTable.getSelection()[0].getData()));
        }
    };
    
    private MouseListener createInternalEventListener = new MouseListener() {
        public void mouseDoubleClick(MouseEvent mouseEvent) {
        }

        public void mouseDown(MouseEvent mouseEvent) {
        }

        public void mouseUp(MouseEvent mouseEvent) {
            State state = (State) getModel();
            Event event = new Event(Event.INTERNAL_EVENT);
            
            executeCommand(new CreateEventCommand(state, state, event));
        }
    };
    
    /**
     * コントロールを作成する.
     * 
     * @param parent 親コンテナ
     * @param tabbedPropertySheetPage プロパティシートページ
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *        #createControls(
     *          org.eclipse.swt.widgets.Composite, 
     *          org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(
                    Composite parent, 
                    TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        Composite composite = 
            getWidgetFactory().createFlatFormComposite(parent);
        
        createStateNameLabel(composite); 
        createInternalEventButton(composite);
        createEventTable(composite);

        fTab = tabbedPropertySheetPage.getControl();
        fTab.addControlListener(new ControlListener() {
            public void controlMoved(ControlEvent e) {
            }

            public void controlResized(ControlEvent e) {
                Composite source = (Composite) e.getSource();
                resizeEventTable(source.getSize());
            }
        });
    }

    /**
     * ステート名ラベルを生成・配置する.
     * 
     * @param composite 親コンテナ
     */
    private void createStateNameLabel(Composite composite) {
        FormData data;
        fStateNameLabel = 
            getWidgetFactory().createCLabel(composite, "ステート名：");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(LABEL_POSITION_PERCENT, 0);
        data.top = new FormAttachment(0, 0);
        fStateNameLabel.setLayoutData(data);
    }

    /**
     * 内部イベント追加・削除ボタンを生成・配置する.
     * 
     * @param composite 親コンテナ
     */
    private void createInternalEventButton(Composite composite) {
        FormData data;
        fDeleteInternalEvent =
            getWidgetFactory().createButton(
                    composite, "内部イベント削除", SWT.PUSH);
        data = new FormData();
        data.right = new FormAttachment(BUTTON_POSITION_PERCENT, 0);
        data.top = new FormAttachment(0, 0);
        fDeleteInternalEvent.setLayoutData(data); 
        fDeleteInternalEvent.setEnabled(false);
        
        fCreateInternalEvent =
            getWidgetFactory().createButton(
                    composite, "内部イベント作成", SWT.PUSH);
        data = new FormData();
        data.right = new FormAttachment(fDeleteInternalEvent, 0);
        data.top = new FormAttachment(0, 0);
        fCreateInternalEvent.setLayoutData(data);
        
        fDeleteInternalEvent.addMouseListener(deleteInternalEventListener);
        fCreateInternalEvent.addMouseListener(createInternalEventListener);
    }

    /**
     * イベントテーブルを生成・配置する.
     * 
     * @param composite 親コンテナ
     */
    private void createEventTable(Composite composite) {
        fEventTableViewer = new TableViewer(composite, 
                                    SWT.HORIZONTAL | SWT.VERTICAL | SWT.VIRTUAL
                                    | SWT.FULL_SELECTION | SWT.BORDER);
        Table eventTable = fEventTableViewer.getTable();

        fEventTableViewer.setContentProvider(new ArrayContentProvider());
        fEventTableViewer.setLabelProvider(new EventTableLabelProvider());
        fEventTableViewer.setColumnProperties(new String[] {
                            "Event",
                            null,
                            "EventHandler",
                            "Guard" });
        fEventTableViewer.setCellEditors(new CellEditor[] {
                            new TextCellEditor(eventTable),
                            null,
                            new TextCellEditor(eventTable),
                            new TextCellEditor(eventTable) });
        
        eventTable.setHeaderVisible(true);
        eventTable.setLinesVisible(true);
        
        createEventTableColumn(
                eventTable, "イベント名", EVENT_COLUMN_WIDTH);
        createEventTableColumn(
                eventTable, "遷移先ステート名", NEXT_STATE_COLUMN_WIDTH);
        createEventTableColumn(
                eventTable, "イベントハンドラ", EVENT_HANDLER_COLUMN_WIDTH);
        createEventTableColumn(
                eventTable, "ガード", GUARD_COLUMN_WIDTH);
        
        eventTable.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {
            }

            public void widgetSelected(SelectionEvent selectionEvent) {
                Event event = (Event) selectionEvent.item.getData();
                
                if (event != null) {
                    fDeleteInternalEvent.setEnabled(
                            event.getType() == Event.INTERNAL_EVENT);
                }
            }
        });
    }

    /**
     * イベントテーブルに行を作成する.
     * 
     * @param eventTable イベントテーブル
     * @param columnName 行名
     * @param width 行幅
     */
    private void createEventTableColumn(
                        Table eventTable, 
                        String columnName, 
                        int width) {
        TableColumn column = new TableColumn(eventTable, SWT.NONE);
        column.setText(columnName);
        column.setWidth(width);
    }

    /**
     * インプットオブジェクトのセッターメソッド.
     * インプットオブジェクトからステートを取得する。
     * 取得したパートが GraphicalEditor の場合は、イベント表示テーブルの
     * セル・モディファイアーを生成する。
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
        if (part instanceof GraphicalEditor) {
            fEventTableViewer.setCellModifier(
                    new EventTableCellModifier(
                            (GraphicalEditor) part, 
                            fEventTableViewer, 
                            (State) getModel()));
        }
    }

    /**
     * 画面をリフレッシュする.
     * ステートから必要な情報を取得し、コントロールにセットする。
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
     *          #refresh()
     */
    @Override
    public void refresh() {
        State state = (State) getModel();
        
        if (state != null) {
            fStateNameLabel.setText("ステート名：");
            if (state.getName() != null) {
                fStateNameLabel.setText(
                        fStateNameLabel.getText() + state.getName());
            }
            
            fEventTableViewer.getTable().removeAll();
            fEventTableViewer.setInput(getItems());
            
            setEventTableBackground();
            
            resizeEventTable(fTab.getSize());
            
            fCreateInternalEvent.setVisible(true);
            fDeleteInternalEvent.setVisible(true);
            if (state.getType() == State.INITIAL_STATE
                || state.getType() == State.FINAL_STATE) {
                fCreateInternalEvent.setVisible(false);
                fDeleteInternalEvent.setVisible(false);
            }
        }
    }
    
    /**
     * イベントリストをビルトイン・遷移・内部の順番にソートして返す.
     * 
     * @return ソートされたイベントリスト
     */
    private List<Event> getItems() {
        List<Event> eventList = new ArrayList<Event>();
        List<Event> builtinEventList = new ArrayList<Event>();
        List<Event> transitionEventList = new ArrayList<Event>();
        List<Event> internalEventList = new ArrayList<Event>();
        
        State state = (State) getModel();
        
        for (Event event : state.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                builtinEventList.add(event);
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                transitionEventList.add(event);                    
            } else if (event.getType() == Event.INTERNAL_EVENT) {
                internalEventList.add(event);
            }
        }
        
        eventList.addAll(builtinEventList);
        eventList.addAll(transitionEventList);
        eventList.addAll(internalEventList);
        
        return eventList;
    }
    
    /**
     * イベントテーブルの背景色をイベントタイプに合わせて設定する.
     * 
     */
    private void setEventTableBackground() {
        Table table = fEventTableViewer.getTable();
        for (int i = 0; i < table.getItemCount(); i++) {
            TableItem item = table.getItem(i);
            // モデルを取得するためにテキストを取得を呼び出しておく。
            // こうしないと次の getData メソッドが null を返す。
            item.getText();
            
            Event event = (Event) item.getData();
            
            RGB backColor = null;
            if (event.getType() == Event.BUILTIN_EVENT) {
                backColor = EVENT_BUILTIN_COLOR;
            } else if (event.getType() == Event.TRANSITION_EVENT) {
                backColor = EVENT_TRANSITION_COLOR;                    
            } else if (event.getType() == Event.INTERNAL_EVENT) {
                backColor = EVENT_INTERNAL_COLOR;
            }
            item.setBackground(new Color(Display.getCurrent(), backColor));
        }
    }

    /**
     * プロパティーのリサイズに合わせて、イベント表示テーブルの
     * サイズを修正する.
     * 
     * @param tabSize タブプロパティーのサイズ
     */
    private void resizeEventTable(Point tabSize) {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(TABLE_POSITION_PERCENT, 0);
        data.top = new FormAttachment(fStateNameLabel, 0);
        data.bottom = new FormAttachment(0, tabSize.y - TABLE_HEIGHT_MARGIN);
        fEventTableViewer.getTable().setLayoutData(data);
    }
}
