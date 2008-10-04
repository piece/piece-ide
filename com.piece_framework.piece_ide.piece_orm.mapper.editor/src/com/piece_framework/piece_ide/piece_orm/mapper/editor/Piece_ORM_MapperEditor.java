package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;

import com.piece_framework.piece_ide.piece_orm.mapper.Piece_ORM_MapperEditorPlugin;

public class Piece_ORM_MapperEditor extends AbstractXtextEditor {

   public AbstractXtextEditorPlugin getPlugin() {
      return Piece_ORM_MapperEditorPlugin.getDefault();
   }
}
