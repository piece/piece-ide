package com.piece_framework.piece_ide.piece_orm.mapper.resource;

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.openarchitectureware.workflow.util.ResourceLoaderImpl;
import org.openarchitectureware.xtext.parser.IXtextParser;
import org.openarchitectureware.xtext.resource.AbstractXtextResource;

import com.piece_framework.piece_ide.piece_orm.mapper.parser.XtextParser;

public class Piece_ORM_MapperResource extends AbstractXtextResource {
	public Piece_ORM_MapperResource(URI uri) {
		super(uri);
		setFormattingExtension("com::piece_framework::piece_ide::piece_orm::mapper::Formatting");
		setResourceLoader(new ResourceLoaderImpl(XtextParser.class.getClassLoader()));
	}

	@Override
	protected IXtextParser createParser(InputStream inputStream) {
		return new XtextParser(inputStream);
	}

}

