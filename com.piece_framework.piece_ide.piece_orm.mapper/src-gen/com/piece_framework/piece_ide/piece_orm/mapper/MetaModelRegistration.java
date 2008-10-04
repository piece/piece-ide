
package com.piece_framework.piece_ide.piece_orm.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.openarchitectureware.workflow.util.ResourceLoader;
import org.openarchitectureware.workflow.util.ResourceLoaderImpl;
import org.openarchitectureware.workflow.util.ResourceLoaderFactory;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.TypeResolver;

public class MetaModelRegistration {
	
    static {
    	
			register();
    	
		loadXtextFile();
	}
	
		@SuppressWarnings("unchecked")
		public static void register() {
			String nsURI = "http://trac.piece-framework.com/piece-orm/";
			if (EPackage.Registry.INSTANCE.get(nsURI) == null) {
				InputStream in = MetaModelRegistration.class.getResourceAsStream("piece_orm_mapper.ecore");
				Resource r = TypeResolver.getResourceSet().createResource(URI.createURI("com/piece_framework/piece_ide/piece_orm/mapper/piece_orm_mapper.ecore"));
				try {
					r.load(in, null);
					EPackage pack = (EPackage) r.getContents().get(0);
					EPackage.Registry.INSTANCE.put(nsURI,pack);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
   	
	
	public static EPackage getEPackage() {
		return EPackage.Registry.INSTANCE.getEPackage("http://trac.piece-framework.com/piece-orm/");
	}
	
	public static XtextFile xtextfile;
	
	public static XtextFile getXtextFile() {
		return xtextfile;
	}
	
	private static void loadXtextFile(){
		if (xtextfile == null) {
		    ResourceLoader rl = ResourceLoaderFactory.createResourceLoader();
			try {
			    //intialize xtext mm
			    org.openarchitectureware.xtext.XtextPackage.eINSTANCE.getEFactoryInstance();
				ResourceLoaderFactory
						.setCurrentThreadResourceLoader(new ResourceLoaderImpl(
								MetaModelRegistration.class.getClassLoader()));

				final URL url = ResourceLoaderFactory.createResourceLoader()
						.getResource("com/piece_framework/piece_ide/piece_orm/mapper/Piece_ORM_Mapper.xmi");

				// Get the URI of the model file.
				URI fileURI = URI.createURI(url.toExternalForm());

				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());
				Resource resource = resourceSet.getResource(fileURI, true);

				resource.load(null);
				xtextfile = (XtextFile) resource.getContents().get(0);
			} catch (Exception e) {
				System.out.println("Couldn't load xmi file (com/piece_framework/piece_ide/piece_orm/mapper/Piece_ORM_Mapper.xmi)");
			} finally {
				ResourceLoaderFactory
						.setCurrentThreadResourceLoader(rl);
			}
		}
	}
}
