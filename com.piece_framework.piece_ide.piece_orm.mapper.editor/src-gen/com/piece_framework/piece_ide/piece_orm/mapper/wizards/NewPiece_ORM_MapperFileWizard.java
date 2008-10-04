
package com.piece_framework.piece_ide.piece_orm.mapper.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewFileWizard;

import com.piece_framework.piece_ide.piece_orm.mapper.Piece_ORM_MapperEditorPlugin;

public class NewPiece_ORM_MapperFileWizard extends AbstractNewFileWizard {

	@Override
	protected LanguageUtilities getUtilities() {
		return Piece_ORM_MapperEditorPlugin.getDefault().getUtilities();
	}
}
