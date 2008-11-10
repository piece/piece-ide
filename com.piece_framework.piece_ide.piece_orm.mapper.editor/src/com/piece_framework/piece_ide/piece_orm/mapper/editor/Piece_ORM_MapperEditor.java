// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;

import com.piece_framework.piece_ide.piece_orm.mapper.Piece_ORM_MapperEditorPlugin;

/**
 * エディタークラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class Piece_ORM_MapperEditor extends AbstractXtextEditor {
    public Piece_ORM_MapperEditor() {
        super();
        setSourceViewerConfiguration(new MapperSourceViewerConfiguration(getPlugin(), this));
    }

    public AbstractXtextEditorPlugin getPlugin() {
        return Piece_ORM_MapperEditorPlugin.getDefault();
    }
}
