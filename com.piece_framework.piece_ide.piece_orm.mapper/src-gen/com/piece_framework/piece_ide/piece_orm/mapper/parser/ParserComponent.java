package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.xtext.parser.impl.AbstractParserComponent;
import org.openarchitectureware.xtext.resource.IXtextResource;

import com.piece_framework.piece_ide.piece_orm.mapper.resource.Piece_ORM_MapperResourceFactory;

public class ParserComponent extends AbstractParserComponent {
	static {
		Piece_ORM_MapperResourceFactory.register();
	}

	protected String getFileExtension() {
		return "mapper";
	}

}
