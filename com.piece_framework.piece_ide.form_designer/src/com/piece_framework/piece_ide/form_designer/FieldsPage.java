// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
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

        @Override
        protected void createMasterPart(
                        IManagedForm managedForm,
                        Composite parent) {
            FormToolkit toolkit = managedForm.getToolkit();
            
            Section section = toolkit.createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
            section.setText("フィールド一覧");
            section.setDescription("編集するフィールドを選択してください。");
            
            Composite composite = toolkit.createComposite(section);
            composite.setLayout(new FillLayout());
            Tree tree = toolkit.createTree(composite, SWT.NONE);
            
            section.setClient(composite);
            
//            SectionPart part = new SectionPart(section);
//            managedForm.addPart(part);
//            section.setLayout(new FormLayout());
//            section.setLayoutData(new GridData(GridData.FILL_BOTH));
            
//            Composite container = managedForm.getToolkit().createComposite(parent);
//            container.setLayout(FormLayoutFactory.createMasterGridLayout(false, 1));
//            container.setLayoutData(new GridData(GridData.FILL_BOTH));
//            fSection = createMasterSection(managedForm, container);
//            managedForm.addPart(fSection);
//            Section section = fSection.getSection();
//            section.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
//            section.setLayoutData(new GridData(GridData.FILL_BOTH));
        }

        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
            // TODO 自動生成されたメソッド・スタブ
            
        }

        @Override
        protected void registerPages(DetailsPart detailsPart) {
            // TODO 自動生成されたメソッド・スタブ
            
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
