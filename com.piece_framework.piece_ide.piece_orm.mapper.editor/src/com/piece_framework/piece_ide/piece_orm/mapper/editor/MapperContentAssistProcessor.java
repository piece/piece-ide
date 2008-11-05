// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;
import org.openarchitectureware.xtext.Assignment;
import org.openarchitectureware.xtext.CrossReference;
import org.openarchitectureware.xtext.Element;
import org.openarchitectureware.xtext.EnumLiteral;
import org.openarchitectureware.xtext.Keyword;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.StringRule;
import org.openarchitectureware.xtext.XtextLog;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.contentassist.XTextModelContentAssist;
import org.openarchitectureware.xtext.editor.contentassist.XtextCompletionProposal;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.Proposal;
import org.openarchitectureware.xtext.parser.model.NodeUtil;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class MapperContentAssistProcessor extends XTextModelContentAssist {
    private static final String CONTENT_ASSIST_EXTENSIONS = "ContentAssist";
    private LanguageUtilities fLangUtil;

    private class Grammar {
        private Node fNode;
        public Grammar(Node node) {
            fNode = node;
        }

        public boolean hasCrossReference() {
            if (fNode.getGrammarElement() instanceof Assignment) {
                Assignment assignment = (Assignment) fNode.getGrammarElement();
                return assignment.getToken() instanceof CrossReference;
            }
            return false;
        }

        public boolean hasStringRule() {
            for (EObject element = fNode.getGrammarElement();
                 element != null;
                 element = element.eContainer()
                 ) {
                if (element instanceof StringRule) {
                    return true;
                }
            }
            return false;
        }
    }

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
        if (node == null) {
            return null;
        }

        NodeForContentAssist lastComplete = new NodeForContentAssist(node, offset);
        if (lastComplete.hasParent()) {
            Grammar grammar = new Grammar(lastComplete.getParent());
            if (grammar.hasCrossReference() && grammar.hasStringRule()) {
                lastComplete = new NodeForContentAssist(lastComplete.getParent());
            }
        }

        ProposalParameter parameter = ProposalParameter.create(wholetext,
                                                               offset,
                                                               lastComplete
                                                               );
        List<Proposal> proposals = new ArrayList<Proposal>(createProposals(parameter));

        // sort proposals using an extension (located in an external .ext file):
        Object tmpProposals = fLangUtil.invokeExtension(CONTENT_ASSIST_EXTENSIONS, "sortProposals",
                proposals);
        if (tmpProposals instanceof Collection) {
            if (tmpProposals == proposals) {
                Collections.sort(proposals, new Comparator<Proposal>() {
                    public int compare(Proposal o1, Proposal o2) {
                        if (o1.isApplyPrefixFilter() != o2.isApplyPrefixFilter()) {
                            if (o1.isApplyPrefixFilter())
                                return -1;
                            else
                                return 1;
                        }
                        return o1.getLabel().compareTo(o2.getLabel());
                    }
                });
            } else {
                proposals = (List<Proposal>) tmpProposals;
            }
        }

        List<ICompletionProposal> cp = new ArrayList<ICompletionProposal>();
        for (Proposal pi : proposals) {
            if (!pi.isApplyPrefixFilter()
                    || parameter.getPrefix() == null
                    || (pi.getToInsert().toLowerCase().startsWith(parameter.getPrefix()
                            .toLowerCase()))) {
                cp.add(createCompletionProposal(pi));
            }
        }
        return cp.toArray(new ICompletionProposal[cp.size()]);
    }

    private List<Proposal> createProposals(ProposalParameter parameter) {
        Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                                                             parameter.getLastComplete()
                                                             );
        List<Proposal> proposals = new ArrayList<Proposal>();
        for (Element element : followUps) {
            try {
                proposals.addAll(handleElement(element,
                                               parameter.getLastComplete(),
                                               parameter.getPrefix()
                                               ));
            } catch (RuntimeException e) {
                if (e.getMessage() == null) {
                    XtextLog.logInfo(e.getClass().getSimpleName() + " occurred",
                                     e
                                     );
                } else {
                    XtextLog.logInfo(e.getMessage());
                }
            }
        }

        for (Proposal proposal : proposals) {
            proposal.setStartReplace(parameter.getStartReplace());
            proposal.setEndReplace(parameter.getEndReplace());
            proposal.setApplyPrefixFilter(parameter.isApplyPrefixFilter());
        }

        return proposals;
    }

    private ICompletionProposal createCompletionProposal(Proposal p) {
        Image img = fLangUtil.getImage(p.getImage());
        return new XtextCompletionProposal(p, img);
    }

    private List<Proposal> handleElement(Element element,
                                         Node lastComplete,
                                         String prefix
                                         ) {
        List<Proposal> proposals = null;
        if (element instanceof Assignment) {
            proposals = handleAssignment((Assignment) element, lastComplete, prefix);
        } else if (element instanceof Keyword) {
            proposals = handleKeyword((Keyword) element, lastComplete, prefix);
        } else if (element instanceof EnumLiteral) {
            proposals = handleEnumLiteral((EnumLiteral) element, lastComplete, prefix);
        } else {
            proposals = handleOther(element, lastComplete, prefix);
        }

        if (proposals == null) {
            proposals = Collections.emptyList();
        }

        return proposals;
    }
}
