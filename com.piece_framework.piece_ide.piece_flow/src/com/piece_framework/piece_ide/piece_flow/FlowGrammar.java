
/*******************************************************************************
 * generated with "/org.eclipse.xtext.xtext.ui/src/org/eclipse/xtext/xtext/ui/wizard/project/GrammarGenerator.xpt"
 *******************************************************************************/
package com.piece_framework.piece_ide.piece_flow;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.GeneratorFacade;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.resource.ClassloaderClasspathUriResolver;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * Run this class in order to generate the  Flow grammar.
 */
public class  FlowGrammar {
	private Logger log = Logger.getLogger( FlowGrammar.class);

	private static final String PATH = ".";
	private static final String UI_PATH = "../com.piece_framework.piece_ide.piece_flow.ui";

	public void generate() throws IOException {
		XtextStandaloneSetup.doSetup();

		GeneratorFacade.cleanFolder(PATH + "/src-gen");
		GeneratorFacade.cleanFolder(UI_PATH + "/src-gen");

		String classpathUri = "classpath:/com/piece_framework/piece_ide/piece_flow/Flow.xtext";
		log.info("loading " + classpathUri);
		ResourceSet rs = new XtextResourceSet();
		Resource resource = rs
				.createResource(new ClassloaderClasspathUriResolver().resolve(
						null, URI.createURI(classpathUri)));
		resource.load(null);
		Grammar grammarModel = (Grammar) resource.getContents().get(0);

		GeneratorFacade.generate(grammarModel, PATH, UI_PATH, "flow");
		log.info("Done.");
	}

	public static void main(String[] args) throws IOException {
		 FlowGrammar generator = new FlowGrammar();
		generator.generate();
	}

}

