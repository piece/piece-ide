// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

public class FieldsPage extends FormPage {
    private FieldsBlock fBlock;

    public class FieldsBlock extends MasterDetailsBlock {

        private static final int NAME_WIDTH = 100; 
        private static final int DESCRIPTION_WIDTH = 100; 
        
        @Override
        protected void createMasterPart(
                        final IManagedForm managedForm,
                        final Composite parent) {
            FormToolkit toolkit = managedForm.getToolkit();
            
            final Section section = toolkit.createSection(parent, 
                                    Section.TITLE_BAR | Section.DESCRIPTION);
            section.setText("フィールド一覧");
            section.setDescription("編集するフィールドを選択してください。");
            
            Composite composite = toolkit.createComposite(section);
            composite.setLayout(new GridLayout(2, false));
            final Table table = toolkit.createTable(
                                    composite, 
                                    SWT.SINGLE | SWT.FULL_SELECTION);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            
            GridData data = new GridData();
            data.horizontalAlignment = GridData.FILL;
            data.verticalAlignment = GridData.FILL;
            data.grabExcessHorizontalSpace = true;
            data.grabExcessVerticalSpace = true;
            table.setLayoutData(data);
            
            TableColumn column1 = new TableColumn(table, SWT.NULL);
            column1.setText("name");
            column1.setWidth(NAME_WIDTH);
            TableColumn column2 = new TableColumn(table, SWT.NULL);
            column2.setText("description");
            column2.setWidth(DESCRIPTION_WIDTH);
            final TableViewer viewer = new TableViewer(table);
            viewer.setContentProvider(new ArrayContentProvider());
            viewer.setLabelProvider(new ITableLabelProvider() {
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

            Composite buttons = toolkit.createComposite(composite);
            GridData gd = new GridData(GridData.FILL_VERTICAL);
            buttons.setLayoutData(gd);

            GridLayout layout = new GridLayout();
            layout.marginWidth = layout.marginHeight = 0;
            buttons.setLayout(layout);

            Button addButton = toolkit.createButton(buttons, "追加(&A)...", SWT.PUSH);
            GridData buttonData = new GridData(GridData.FILL_HORIZONTAL
                                               | GridData.VERTICAL_ALIGN_BEGINNING);
            addButton.setLayoutData(buttonData);
            addButton.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent event) {
                    // TODO:独自の入力ダイアログを作成する
                    InputDialog dialog = new InputDialog(parent.getShell(), "フィールド名入力", "フィールド名を入力してください。", null, null);
                    dialog.open();

                    Field field = new Field(dialog.getValue());
                    viewer.add(field);
                    viewer.setSelection(new StructuredSelection(field));
                }

                public void widgetDefaultSelected(SelectionEvent event) {
                }
            });

            Button delButton = toolkit.createButton(buttons, "削除(&D)...", SWT.PUSH);
            buttonData = new GridData(GridData.FILL_HORIZONTAL
                                      | GridData.VERTICAL_ALIGN_BEGINNING);
            delButton.setLayoutData(buttonData);

            Button upButton = toolkit.createButton(buttons, "上へ", SWT.PUSH);
            buttonData = new GridData(GridData.FILL_HORIZONTAL
                                      | GridData.VERTICAL_ALIGN_BEGINNING);
            upButton.setLayoutData(buttonData);

            Button downButton = toolkit.createButton(buttons, "下へ", SWT.PUSH);
            buttonData = new GridData(GridData.FILL_HORIZONTAL
                                      | GridData.VERTICAL_ALIGN_BEGINNING);
            downButton.setLayoutData(buttonData);

            final SectionPart sectionPart = new SectionPart(section);
            managedForm.addPart(sectionPart);
            viewer.addSelectionChangedListener(new ISelectionChangedListener() {
                public void selectionChanged(SelectionChangedEvent event) {
                    managedForm.fireSelectionChanged(
                                    sectionPart, 
                                    event.getSelection());
                }
            });

            section.setClient(composite);
        }

        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
            // TODO 自動生成されたメソッド・スタブ
            
        }

        @Override
        protected void registerPages(DetailsPart detailsPart) {
            detailsPart.registerPage(
                Field.class, 
                new IDetailsPage() {
                    private IManagedForm fForm;
                    private Text fNameText;
                    private Text fDescriptionText;
                    private Text fMessageText;
                    private Button fRequired;
                    private Button fForceValidation;

                    public void createContents(Composite parent) {
                        parent.setLayout(new FillLayout());
                        FormToolkit toolkit = fForm.getToolkit();
                        Section section = toolkit.createSection(
                                    parent, 
                                    Section.TITLE_BAR | Section.DESCRIPTION);
                        section.setText("フィールド詳細");

                        Composite composite = toolkit.createComposite(section);
                        composite.setLayout(new GridLayout(3, false));

                        toolkit.createLabel(composite, "name");
                        toolkit.createLabel(composite, "：");
                        fNameText = toolkit.createText(composite, "");
                        fNameText.setLayoutData(
                                new GridData(GridData.FILL_HORIZONTAL));

                        toolkit.createLabel(composite, "description");
                        toolkit.createLabel(composite, "：");
                        fDescriptionText = toolkit.createText(composite, "");
                        fDescriptionText.setLayoutData(
                                new GridData(GridData.FILL_HORIZONTAL));

                        toolkit.createLabel(composite, "required");
                        toolkit.createLabel(composite, "：");
                        fRequired = toolkit.createButton(composite, "", SWT.CHECK);

                        toolkit.createLabel(composite, "message");
                        toolkit.createLabel(composite, "：");
                        fMessageText = toolkit.createText(composite, "");
                        fMessageText.setLayoutData(
                                new GridData(GridData.FILL_HORIZONTAL));

                        toolkit.createLabel(composite, "force validation");
                        toolkit.createLabel(composite, "：");
                        fForceValidation = toolkit.createButton(composite, "", SWT.CHECK);

                        section.setClient(composite);
                    }

                    public void commit(boolean onSave) {
                    }

                    public void dispose() {
                    }

                    public void initialize(IManagedForm form) {
                        fForm = form;
                    }

                    public boolean isDirty() {
                        return false;
                    }

                    public boolean isStale() {
                        return false;
                    }

                    public void refresh() {
                    }

                    public void setFocus() {
                    }

                    public boolean setFormInput(Object input) {
                        return false;
                    }

                    public void selectionChanged(
                                    IFormPart part,
                                    ISelection selection) {
                        fNameText.setText("");
                        fDescriptionText.setText("");
                        fRequired.setSelection(false);
                        fMessageText.setText("");
                        fForceValidation.setSelection(false);

                        Field field = (Field) ((IStructuredSelection) selection).getFirstElement();
                        if (field != null) {
                            fNameText.setText(field.getName());
                            if (field.getDescription() != null) {
                                fDescriptionText.setText(field.getDescription());
                            }
                            fRequired.setSelection(field.isRequired());
                            if (field.getMessage() != null) {
                                fMessageText.setText(field.getMessage());
                            }
                            fForceValidation.setSelection(field.isForceValidation());
                        }
                    }
                });
        }
        
    }
    
    public FieldsPage(FormEditor editor) {
        super(editor, null, "フィールド");

        // TODO:ダミーデータ作成
//        createSampleData();

        fBlock = new FieldsBlock();
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        fBlock.createContent(managedForm);
    }

//    private void createSampleData() {
//        fFields = new ArrayList<Field>();
//
//        Field field1 = new Field();
//        field1.setName("name");
//        field1.setDescription("氏名");
//        field1.setRequired(true);
//        field1.setForceValidation(true);
//        field1.setMessage("%description%は必須項目です。");
//        fFields.add(field1);
//
//        Field field2 = new Field();
//        field2.setName("age");
//        field2.setDescription("年齢");
//        field2.setRequired(false);
//        field2.setForceValidation(false);
//        field2.setMessage("%description%は必須項目です。");
//        fFields.add(field2);
//    }
}
