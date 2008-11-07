// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.XtextSourceViewerConfiguration;

import com.piece_framework.piece_ide.piece_orm.mapper.editor.contentassist.MapperContentAssistProcessor;

public class MapperSourceViewerConfiguration extends XtextSourceViewerConfiguration {
    private AbstractXtextEditorPlugin fPlugin;
    private AbstractXtextEditor fEditor;

    public MapperSourceViewerConfiguration(AbstractXtextEditorPlugin plugin,
                                           AbstractXtextEditor editor
                                           ) {
        super(plugin, editor);
        fPlugin = plugin;
        fEditor = editor;
    }

    @Override
    public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer,
                                                     String contentType
                                                     ) {
        if (contentType.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
            return new IAutoEditStrategy[] { new MapperAutoEditStrategy(fPreferenceStore) };
        }

        return super.getAutoEditStrategies(sourceViewer, contentType);
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        MapperContentAssistProcessor processor = new MapperContentAssistProcessor(fPlugin.getUtilities(),
                                                                                  fEditor
                                                                                  );
        ContentAssistant assistant = new ContentAssistant();
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
        return assistant;
    }
}
