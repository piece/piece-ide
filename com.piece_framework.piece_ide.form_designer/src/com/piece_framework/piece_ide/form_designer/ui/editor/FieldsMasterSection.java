// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

public class FieldsMasterSection extends SectionPart implements PropertyChangeListener {
    private static final int NAME_WIDTH = 100; 
    private static final int DESCRIPTION_WIDTH = 100; 
    
    private TableViewer fViewer;

    public FieldsMasterSection(Composite parent, IManagedForm managedForm) {
        super(parent, 
              managedForm.getToolkit(), 
              Section.TITLE_BAR | Section.DESCRIPTION);
        initialize(managedForm);
        createClient(getSection(), managedForm.getToolkit());
    }

    private void createClient(Section section, FormToolkit toolkit) {
        section.setText("フィールド一覧");
        section.setDescription("編集するフィールドを選択してください。");
        
        Composite composite = toolkit.createComposite(section);
        composite.setLayout(new GridLayout(2, false));

        createTable(composite, toolkit);
        createButtons(composite, toolkit);

        final SectionPart sectionPart = new SectionPart(section);
        final IManagedForm managedForm = getManagedForm();
        managedForm.addPart(sectionPart);
        fViewer.addSelectionChangedListener(
                new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                managedForm.fireSelectionChanged(
                                sectionPart, 
                                event.getSelection());
            }
        });

        section.setClient(composite);
    }


    private void createTable(Composite parent, FormToolkit toolkit) {
        final Table table = toolkit.createTable(
                                parent, 
                                SWT.SINGLE | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(
                new GridData(
                        GridData.HORIZONTAL_ALIGN_FILL
                        | GridData.VERTICAL_ALIGN_FILL
                        | GridData.GRAB_HORIZONTAL
                        | GridData.GRAB_VERTICAL));

        createTableColumn(table, "name", NAME_WIDTH);
        createTableColumn(table, "description", DESCRIPTION_WIDTH);

        fViewer = new TableViewer(table);
        fViewer.setContentProvider(new ArrayContentProvider());
        fViewer.setLabelProvider(new ITableLabelProvider() {
            public Image getColumnImage(Object element, int columnIndex) {
                return null;
            }

            public String getColumnText(Object element, int columnIndex) {
                Field field = (Field) element;
                if (columnIndex == 0) {
                    return field.getName();
                } else if (columnIndex == 1) {
                    return field.getDescription();
                }
                return "";
            }

            public void addListener(ILabelProviderListener listener) {
            }

            public void dispose() {
            }

            public boolean isLabelProperty(
                                Object element, 
                                String property) {
                return false;
            }

            public void removeListener(ILabelProviderListener listener) {
            }
        });
    }

    private void createTableColumn(final Table table, String text, int width) {
        TableColumn column = new TableColumn(table, SWT.NULL);
        column.setText(text);
        column.setWidth(width);
    }

    private void createButtons(final Composite parent, FormToolkit toolkit) {
        Composite buttons = toolkit.createComposite(parent);
        buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));

        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        buttons.setLayout(layout);

        Button addButton = createButton(buttons, "追加(&A)...", toolkit);
        addButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                // TODO:独自の入力ダイアログを作成する
                InputDialog dialog = new InputDialog(
                                            parent.getShell(), 
                                            "フィールド名入力", 
                                            "フィールド名を入力してください。", 
                                            null, 
                                            null);
                dialog.open();

                // TODO:名前の重複チェック

                Field field = new Field(dialog.getValue());
                field.addPropertyChangeListener(FieldsMasterSection.this);
                fViewer.add(field);
                fViewer.setSelection(new StructuredSelection(field));
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button delButton = createButton(buttons, "削除(&D)...", toolkit);
        delButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelectedイベントの発生条件を調べる
            public void widgetDefaultSelected(SelectionEvent event) {
            }

            public void widgetSelected(SelectionEvent event) {
                // TODO:確認ダイアログを表示する
                
                // Field オブジェクトにキャストしないと正しく削除されない
                Field field = 
                    (Field) ((IStructuredSelection) fViewer.getSelection())
                    .getFirstElement();
                fViewer.remove(field);
            }
        });

        Button upButton = createButton(buttons, "上へ", toolkit);
        upButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelectedイベントの発生条件を調べる
            public void widgetDefaultSelected(SelectionEvent event) {
            }

            public void widgetSelected(SelectionEvent event) {
                int index = fViewer.getTable().getSelectionIndex();
                if (index == 0) {
                    return;
                }
                
                Field.swap((Field) fViewer.getElementAt(index),
                           (Field) fViewer.getElementAt(index - 1));
                fViewer.setSelection(
                        new StructuredSelection(
                                (Field) fViewer.getElementAt(index - 1)));
            }
        });

        Button downButton = createButton(buttons, "下へ", toolkit);
        downButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelectedイベントの発生条件を調べる
            public void widgetDefaultSelected(SelectionEvent event) {
            }

            public void widgetSelected(SelectionEvent event) {
                int index = fViewer.getTable().getSelectionIndex();
                if (index >= fViewer.getTable().getItemCount()) {
                    return;
                }

                Field.swap((Field) fViewer.getElementAt(index),
                           (Field) fViewer.getElementAt(index + 1));
                fViewer.setSelection(
                        new StructuredSelection(
                                (Field) fViewer.getElementAt(index + 1)));
            }
        });
    }

    private Button createButton(Composite buttons, String text, FormToolkit toolkit) {
        Button button = toolkit.createButton(buttons, text, SWT.PUSH);
        button.setLayoutData(
                new GridData(
                        GridData.FILL_HORIZONTAL
                        | GridData.VERTICAL_ALIGN_BEGINNING));
        return button;
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (!(event.getSource() instanceof Field)) {
            return;
        }

        fViewer.refresh((Field) event.getSource());
    }
}
