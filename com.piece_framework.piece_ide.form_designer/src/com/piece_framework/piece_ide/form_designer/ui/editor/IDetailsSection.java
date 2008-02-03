// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.piece_framework.piece_ide.form_designer.model.Field;

public interface IDetailsSection {
    public void createContents(
                    final Section parentSection, 
                    final FormToolkit toolkit);
    
    public void selectionChanged(final Field field);
}
