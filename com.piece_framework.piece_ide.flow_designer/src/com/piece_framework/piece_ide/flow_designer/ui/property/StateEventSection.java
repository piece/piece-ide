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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * ステート・プロパティシートのイベントセクション.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class StateEventSection extends FlowDesignerPropertySection {
    private static final int EVENT_COLUMN_WIDTH = 100;
    private static final int NEXT_STATE_COLUMN_WIDTH = 100;
    private static final int EVENT_HANDLER_COLUMN_WIDTH = 150;
    private static final int GUARD_COLUMN_WIDTH = 150;

    private static final RGB EVENT_BUILTIN_COLOR = new RGB(210, 237, 221);
    private static final RGB EVENT_TRANSITION_COLOR = new RGB(238, 240, 180);
    private static final RGB EVENT_INTERNAL_COLOR = new RGB(239, 218, 188);

    private Button fCreateInternalEvent;
    private Button fDeleteInternalEvent;
    private TableViewer fEventTableViewer;

    private MouseListener fDeleteInternalEventListener = new MouseListener() {
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

    private MouseListener fCreateInternalEventListener = new MouseListener() {
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

        GridData layoutData = (GridData) parent.getLayoutData();
        layoutData.horizontalAlignment = SWT.FILL;
        layoutData.verticalAlignment = SWT.FILL;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;

        Composite composite = getWidgetFactory().createComposite(parent);
        composite.setLayout(new GridLayout(1, false));

        createInternalEventButton(composite);
        createEventTable(composite);
    }

    /**
     * 内部イベント追加・削除ボタンを生成・配置する.
     *
     * @param composite 親コンテナ
     */
    private void createInternalEventButton(Composite composite) {
        Composite buttonComposite =
            getWidgetFactory().createComposite(composite);
        buttonComposite.setLayout(new GridLayout(2, false));
        buttonComposite.setLayoutData(
            new GridData(GridData.HORIZONTAL_ALIGN_END));

        fDeleteInternalEvent = getWidgetFactory().createButton(
            buttonComposite,
            Messages.getString(
                "StateEventSection.DeleteInternalEvent"),  //$NON-NLS-1$
            SWT.PUSH);
        fDeleteInternalEvent.setLayoutData(
            new GridData(GridData.HORIZONTAL_ALIGN_END));
        fDeleteInternalEvent.addMouseListener(fDeleteInternalEventListener);
        fDeleteInternalEvent.setEnabled(false);

        fCreateInternalEvent = getWidgetFactory().createButton(
            buttonComposite,
            Messages.getString(
                "StateEventSection.CreateInternalEvent"),  //$NON-NLS-1$
            SWT.PUSH);
        fCreateInternalEvent.setLayoutData(
            new GridData(GridData.HORIZONTAL_ALIGN_END));
        fCreateInternalEvent.addMouseListener(fCreateInternalEventListener);
    }

    /**
     * イベントテーブルを生成・配置する.
     *
     * @param composite 親コンテナ
     */
    private void createEventTable(Composite composite) {
        Table eventTable = getWidgetFactory().createTable(composite,
                                                          SWT.HORIZONTAL
                                                          | SWT.VERTICAL
                                                          | SWT.VIRTUAL
                                                          | SWT.FULL_SELECTION
                                                          | SWT.BORDER
                                                          );
        eventTable.setHeaderVisible(true);
        eventTable.setLinesVisible(true);

        eventTable.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
                                              | GridData.VERTICAL_ALIGN_FILL
                                              | GridData.GRAB_HORIZONTAL
                                              | GridData.GRAB_VERTICAL
                                              ));

        createEventTableColumn(
            eventTable,
            Messages.getString("StateEventSection.EventName"),  //$NON-NLS-1$
            EVENT_COLUMN_WIDTH);
        createEventTableColumn(
            eventTable,
            Messages.getString(
                    "StateEventSection.TransitionStateName"),  //$NON-NLS-1$
            NEXT_STATE_COLUMN_WIDTH);
        createEventTableColumn(
            eventTable,
            Messages.getString("StateEventSection.EventHandler"),  //$NON-NLS-1$
            EVENT_HANDLER_COLUMN_WIDTH);
        createEventTableColumn(
            eventTable,
            Messages.getString("StateEventSection.Guard"),  //$NON-NLS-1$
            GUARD_COLUMN_WIDTH);

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

        fEventTableViewer = new TableViewer(eventTable);

        fEventTableViewer.setContentProvider(new ArrayContentProvider());
        fEventTableViewer.setLabelProvider(new EventTableLabelProvider());
        fEventTableViewer.setColumnProperties(new String[] {
                            "Name", //$NON-NLS-1$
                            null,
                            "EventHandler", //$NON-NLS-1$
                            "GuardEventHandler" }); //$NON-NLS-1$
        fEventTableViewer.setCellEditors(new CellEditor[] {
                            new TextCellEditor(eventTable),
                            null,
                            new TextCellEditor(eventTable),
                            new TextCellEditor(eventTable) });
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
            fEventTableViewer.getTable().removeAll();
            fEventTableViewer.setInput(getItems());

            setEventTableBackground();

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
}
