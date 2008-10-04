package com.piece_framework.piece_ide.piece_orm.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.openarchitectureware.xtext.AbstractLanguageUtilities;
import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.IXtextParser;

import com.piece_framework.piece_ide.piece_orm.mapper.parser.XtextParser;

public class Piece_ORM_MapperUtilities extends AbstractLanguageUtilities {

	@Override
	protected IXtextParser internalParse(InputStream inputStream) {
		return new XtextParser(inputStream);
	}

	public String getFileExtension() {
		return "mapper";
	}

	public EPackage getEPackage() {
		return com.piece_framework.piece_ide.piece_orm.mapper.MetaModelRegistration.getEPackage();
	}

	List<String> r = new ArrayList<String>();
	{
		r.add("table");
		r.add("referencedColumn");
		r.add("OneToOne");
		r.add("type");
		r.add("ManyToMany");
		r.add("column");
		r.add("OneToMany");
		r.add("orderBy");
		r.add("through");
		r.add("ManyToOne");
		r.add("query");
		r.add("inverseColumn");
		r.add("associations");
	}
	public List<String> allKeywords() {
		return r;
	}

	protected ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
	}

	public IPartitionTokenScanner getPartitionScanner() {
		return new GeneratedPartitionScanner();
	}

	@Override
	public AbstractXtextEditorPlugin getXtextEditorPlugin() {
		return Piece_ORM_MapperEditorPlugin.getDefault();
	}
	
	@Override
	public String getPackageForExtensions() {
		return "com::piece_framework::piece_ide::piece_orm::mapper";
	}
	
	public XtextFile getXtextFile() {
		return MetaModelRegistration.getXtextFile();
	}
}
