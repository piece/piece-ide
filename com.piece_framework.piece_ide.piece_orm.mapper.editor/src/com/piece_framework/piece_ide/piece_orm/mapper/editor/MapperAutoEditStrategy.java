// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;

public class MapperAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
    public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
        super.customizeDocumentCommand(document, command);
    }
}
