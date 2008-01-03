// $Id$
package com.piece_framework.piece_ide.form_designer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class FieldsPage extends FormPage {
    
    private FieldsBlock fBlock;
    
    public class FieldsBlock extends MasterDetailsBlock {

        private static final int NAME_WIDTH = 100; 
        private static final int DESCRIPTION_WIDTH = 100; 
        
        @Override
        protected void createMasterPart(
                        IManagedForm managedForm,
                        Composite parent) {
            FormToolkit toolkit = managedForm.getToolkit();
            
            Section section = toolkit.createSection(parent, 
                                    Section.TITLE_BAR | Section.DESCRIPTION);
            section.setText("フィールド一覧");
            section.setDescription("編集するフィールドを選択してください。");
            
            Composite composite = toolkit.createComposite(section);
            composite.setLayout(new FillLayout());
            Table table = toolkit.createTable(
                                    composite, 
                                    SWT.SINGLE | SWT.FULL_SELECTION);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            TableColumn column1 = new TableColumn(table, SWT.NULL);
            column1.setText("名前");
            column1.setWidth(NAME_WIDTH);
            TableColumn column2 = new TableColumn(table, SWT.NULL);
            column2.setText("説明");
            column2.setWidth(DESCRIPTION_WIDTH);
            TableViewer viewer = new TableViewer(table);
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
            viewer.setInput(createSampleData());
            
            section.setClient(composite);
        }

        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
            // TODO 自動生成されたメソッド・スタブ
            
        }

        @Override
        protected void registerPages(DetailsPart detailsPart) {
            // TODO 自動生成されたメソッド・スタブ
            
        }
        
        private List createSampleData() {
            List<Field> fields = new ArrayList<Field>();

            Field field1 = new Field();
            field1.setName("name");
            field1.setDescription("氏名");
            field1.setRequired(true);
            field1.setForceValidation(true);
            field1.setMessage("%description%は必須項目です。");
            fields.add(field1);
 
            Field field2 = new Field();
            field2.setName("age");
            field2.setDescription("年齢");
            field2.setRequired(false);
            field2.setForceValidation(false);
            field2.setMessage("%description%は必須項目です。");
            fields.add(field2);

            return fields;
        }
    }
    
    public FieldsPage(FormEditor editor) {
        super(editor, null, "フィールド");
        fBlock = new FieldsBlock();
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        fBlock.createContent(managedForm);
    }
}
