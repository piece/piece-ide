// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.CodeassistFactory;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.Proposal;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.impl.CodeassistFactoryImpl;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import com.piece_framework.piece_ide.piece_orm.mapper.Piece_ORM_MapperEditorPlugin;

public class AssociationNameContentAssist {
    public static List<Proposal> getProposalList() {
        Piece_ORM_MapperEditorPlugin plugin = Piece_ORM_MapperEditorPlugin.getDefault();
        IEditorPart editor = plugin.getWorkbench()
                                   .getActiveWorkbenchWindow()
                                   .getActivePage()
                                   .getActiveEditor();
        if (!(editor instanceof AbstractTextEditor)) {
            return null;
        }
        IDocument document =
            ((AbstractTextEditor) editor).getDocumentProvider().getDocument(editor.getEditorInput());

        Node root = plugin.getRootNode(document.get());
        List<Proposal> proposals = new ArrayList<Proposal>();
        CodeassistFactory factory = CodeassistFactoryImpl.init();
        for (Object childNode : root.getChildren()) {
            EObject model = ((Node) childNode).getModelElement();
            if (model == null) {
                continue;
            }
            if (!model.eClass().getName().equals("Association")) {
                continue;
            }

            String associationName = null;
            for (EAttribute attribute : model.eClass().getEAllAttributes()) {
                if (attribute.getName().equals("name")) {
                    associationName = (String) model.eGet(attribute);
                    break;
                }
            }
            if (associationName == null) {
                continue;
            }

            Proposal proposal = factory.createProposal();
            proposal.setLabel(associationName);
            proposal.setToInsert(associationName);
            proposal.setImage("association.gif");
            proposals.add(proposal);
        }

        return proposals;
    }
}
