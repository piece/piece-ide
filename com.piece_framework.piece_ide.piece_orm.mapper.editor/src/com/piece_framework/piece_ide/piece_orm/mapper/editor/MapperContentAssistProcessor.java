// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.contentassist.XTextModelContentAssist;
import org.openarchitectureware.xtext.parser.model.NodeUtil;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class MapperContentAssistProcessor extends XTextModelContentAssist {
    public MapperContentAssistProcessor(LanguageUtilities langUtil,
                                        AbstractXtextEditor editor
                                        ) {
        super(langUtil, editor);
    }

    @Override
    public ICompletionProposal[] internalComputeProposals(String wholetext,
                                                          int offset,
                                                          Node node
                                                          ) {
        ICompletionProposal[] proposals = super.internalComputeProposals(wholetext, offset, node);
        if (proposals == null) {
            return null;
        }

        Node lastComplete = NodeUtil.getNodeBeforeOffset(node, offset);
        if (lastComplete == null) {
            return proposals;
        }
        boolean insideElement = lastComplete.getEnd() >= offset;
        if (insideElement) {
            return proposals;
        }

        Map<String, String> parentRules = new HashMap<String, String>();
        parentRules.put("Mapper", "Mapper");
        parentRules.put("Method", "Method");
        parentRules.put("Association", "Association");
        parentRules.put("InnerAssociation", "InnerAssociation");
        parentRules.put("LinkTable", "LinkTable");

        Node parent = lastComplete.getParent();
        while (parent != null
               && (!parentRules.containsKey(parent.getModelElement().eClass().getName())
                   || parent.getStart() > offset
                   || offset > parent.getEnd()
                   )) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return proposals;
        }

        List<ICompletionProposal> removeProposals = new ArrayList<ICompletionProposal>();
        for (Object childNode : parent.getChildren()) {
            String nodeText = NodeUtil.getText(wholetext, (Node) childNode).trim();
            if (nodeText.startsWith("association") || nodeText.startsWith("method")) {
                continue;
            }
            for (ICompletionProposal proposal : proposals) {
                if (nodeText.startsWith(proposal.getDisplayString())) {
                    removeProposals.add(proposal);
                }
            }
        }

        List<ICompletionProposal> newProposals = new ArrayList<ICompletionProposal>();
        for (ICompletionProposal proposal : proposals) {
            boolean add = true;
            for (ICompletionProposal removeProposal : removeProposals) {
                if (proposal == removeProposal) {
                    add = false;
                    break;
                }
            }
            if (add) {
                newProposals.add(proposal);
            }
        }

        return newProposals.toArray(new ICompletionProposal[0]);
    }
}
