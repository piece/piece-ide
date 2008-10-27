// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.contentassist.XTextModelContentAssist;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class MapperContentAssistProcessor extends XTextModelContentAssist {
    private LanguageUtilities fLangUtil;

    public MapperContentAssistProcessor(LanguageUtilities langUtil,
                                        AbstractXtextEditor editor
                                        ) {
        super(langUtil, editor);
        fLangUtil = langUtil;
    }

    @Override
    public ICompletionProposal[] internalComputeProposals(String wholetext,
                                                          int offset,
                                                          Node node
                                                          ) {
        ICompletionProposal[] proposals = super.internalComputeProposals(wholetext, offset, node);
        for (ICompletionProposal proposal : proposals) {
            System.out.println(proposal);
        }
        return proposals;
    }
}
