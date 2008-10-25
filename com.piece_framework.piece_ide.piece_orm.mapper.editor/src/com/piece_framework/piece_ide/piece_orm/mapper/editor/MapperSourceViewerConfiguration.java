// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.XtextSourceViewerConfiguration;

public class MapperSourceViewerConfiguration extends XtextSourceViewerConfiguration {
    public MapperSourceViewerConfiguration(AbstractXtextEditorPlugin plugin,
                                           AbstractXtextEditor editor
                                           ) {
        super(plugin, editor);
    }

    @Override
    public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer,
                                                     String contentType
                                                     ) {
        IAutoEditStrategy[] parentStrategies = super.getAutoEditStrategies(sourceViewer, contentType);
        return parentStrategies;
    }
}
