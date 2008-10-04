package com.piece_framework.piece_ide.piece_orm.mapper.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewProjectWizard;

import com.piece_framework.piece_ide.piece_orm.mapper.Piece_ORM_MapperEditorPlugin;

public class NewPiece_ORM_MapperProjectWizard extends AbstractNewProjectWizard {

	public NewPiece_ORM_MapperProjectWizard() {
		super();
		setLangName("Piece_ORM_Mapper");
		setGeneratorProjectName("com.piece_framework.piece_ide.piece_orm.mapper.generator");
		setDslProjectName("com.piece_framework.piece_ide.piece_orm.mapper");
		setFileExtension("mapper");
		setPackageName("com/piece_framework/piece_ide/piece_orm/mapper/");
	}
	
	@Override
	protected LanguageUtilities getUtilities() {
		return Piece_ORM_MapperEditorPlugin.getDefault().getUtilities();
	}
}

