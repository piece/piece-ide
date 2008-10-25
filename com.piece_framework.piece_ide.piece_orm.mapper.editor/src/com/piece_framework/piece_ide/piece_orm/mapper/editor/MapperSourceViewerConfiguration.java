// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<IAutoEditStrategy> strategies = new ArrayList<IAutoEditStrategy>();
        strategies.addAll(Arrays.asList(super.getAutoEditStrategies(sourceViewer, contentType)));
        strategies.add(new MapperAutoEditStrategy());
        return strategies.toArray(new IAutoEditStrategy[0]);
    }
}
