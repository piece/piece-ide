// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.List;

import org.openarchitectureware.xtext.editor.contentassist.codeassist.CodeassistFactory;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.Proposal;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.impl.CodeassistFactoryImpl;

public class MethodNameContentAssist {
    private static List<Proposal> fProposals;

    public static List<Proposal> getProposalList() {
        if (fProposals == null) {
            String[] labels = {"findById",
                               "findByName",
                               "insert",
                               "update",
                               "delete"
                               };

            fProposals = new ArrayList<Proposal>();
            CodeassistFactory factory = CodeassistFactoryImpl.init();
            for (String label : labels) {
                Proposal proposal = factory.createProposal();
                proposal.setLabel(label);
                proposal.setToInsert(label);
                if (label.startsWith("find")) {
                    proposal.setImage("default.gif");
                } else {
                    proposal.setImage("keyword.gif");
                }
                fProposals.add(proposal);
            }
        }

        return fProposals;
    }
}
