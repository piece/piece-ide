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

/**
 * �t�B�[���hMaster�Z�N�V����.
 * 
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FieldsMasterSection extends SectionPart 
                                 implements PropertyChangeListener {
    private static final int NAME_WIDTH = 100; 
    private static final int DESCRIPTION_WIDTH = 100; 
    
    private TableViewer fViewer;

    /**
     * �R���X�g���N�^.
     * 
     * @param parent �e�R���|�W�b�g
     * @param managedForm �Ǘ��t�H�[��
     */
    public FieldsMasterSection(Composite parent, IManagedForm managedForm) {
        super(parent, 
              managedForm.getToolkit(), 
              Section.TITLE_BAR | Section.DESCRIPTION);
        initialize(managedForm);
        createClient(getSection(), managedForm.getToolkit());
    }

    /**
     * �R���g���[���𐶐�����.
     * 
     * @param section �Z�N�V����
     * @param toolkit �c�[���L�b�g
     */
    private void createClient(Section section, FormToolkit toolkit) {
        section.setText("�t�B�[���h�ꗗ");
        section.setDescription("�ҏW����t�B�[���h��I�����Ă��������B");
        
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


    /**
     * Table�R���g���[���𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @param toolkit �c�[���L�b�g
     */
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

    /**
     * TableColumn�I�u�W�F�N�g�𐶐�����.
     * 
     * @param table Table�I�u�W�F�N�g
     * @param caption �J�����̃L���v�V����
     * @param width �J������
     */
    private void createTableColumn(final Table table, 
                                   String caption, 
                                   int width) {
        TableColumn column = new TableColumn(table, SWT.NULL);
        column.setText(caption);
        column.setWidth(width);
    }

    /**
     * �ǉ��A�폜�A��ցA���ւ̊e�{�^���𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @param toolkit �c�[���L�b�g
     */
    private void createButtons(final Composite parent, FormToolkit toolkit) {
        Composite buttons = toolkit.createComposite(parent);
        buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));

        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        buttons.setLayout(layout);

        Button addButton = createButton(buttons, "�ǉ�(&A)...", toolkit);
        addButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                // TODO:�Ǝ��̓��̓_�C�A���O���쐬����
                InputDialog dialog = new InputDialog(
                                            parent.getShell(), 
                                            "�t�B�[���h������", 
                                            "�t�B�[���h������͂��Ă��������B", 
                                            null, 
                                            null);
                dialog.open();

                // TODO:���O�̏d���`�F�b�N

                Field field = new Field(dialog.getValue());
                field.addPropertyChangeListener(FieldsMasterSection.this);
                fViewer.add(field);
                fViewer.setSelection(new StructuredSelection(field));
            }

            public void widgetDefaultSelected(SelectionEvent event) {
            }
        });

        Button delButton = createButton(buttons, "�폜(&D)...", toolkit);
        delButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelected�C�x���g�̔��������𒲂ׂ�
            public void widgetDefaultSelected(SelectionEvent event) {
            }

            public void widgetSelected(SelectionEvent event) {
                // TODO:�m�F�_�C�A���O��\������
                
                // Field �I�u�W�F�N�g�ɃL���X�g���Ȃ��Ɛ������폜����Ȃ�
                Field field = 
                    (Field) ((IStructuredSelection) fViewer.getSelection())
                    .getFirstElement();
                fViewer.remove(field);
            }
        });

        Button upButton = createButton(buttons, "���", toolkit);
        upButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelected�C�x���g�̔��������𒲂ׂ�
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

        Button downButton = createButton(buttons, "����", toolkit);
        downButton.addSelectionListener(new SelectionListener() {
            // TODO:widgetDefaultSelected�C�x���g�̔��������𒲂ׂ�
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

    /**
     * Button�I�u�W�F�N�g�𐶐�����.
     * 
     * @param parent �e�R���|�W�b�g
     * @param caption �{�^���̃L���v�V����
     * @param toolkit �c�[���L�b�g
     * @return ��������Button�I�u�W�F�N�g
     */
    private Button createButton(Composite parent, 
                                String caption, 
                                FormToolkit toolkit) {
        Button button = toolkit.createButton(parent, caption, SWT.PUSH);
        button.setLayoutData(
                new GridData(
                        GridData.FILL_HORIZONTAL
                        | GridData.VERTICAL_ALIGN_BEGINNING));
        return button;
    }

    /**
     * �ݒ�ύX�ʒm���󂯎��C�x���g.
     * 
     * @param event �ύX�ʒm�C�x���g
     * @see java.beans.PropertyChangeListener#propertyChange(
     *          java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (!(event.getSource() instanceof Field)) {
            return;
        }

        fViewer.refresh((Field) event.getSource());
    }
}
