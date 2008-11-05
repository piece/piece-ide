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

        String text = wholetext.substring(0, offset);
        List<Proposal> proposals = new ArrayList<Proposal>();
        String prefix = null;
        if (offset > 0) {
            if (!insideNode(lastComplete, offset)) {
                int startReplace = (prefix == null) ? offset : offset - prefix.length();
                int endReplace = offset;
                prefix = getPrefix(text, lastComplete);

                Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                                                                     lastComplete
                                                                     );
                proposals.addAll(createProposals(lastComplete,
                                                 prefix,
                                                 followUps,
                                                 startReplace,
                                                 endReplace,
                                                 false
                                                 ));
            } else {
                int startReplace = lastComplete.getStart();
                int endReplace = lastComplete.getEnd();

                NodeForContentAssist previous = new NodeForContentAssist(lastComplete.fNode,
                                                                         lastComplete.getStart()
                                                                         );
                prefix = getPrefix(text, previous);
                Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                                                                     previous
                                                                     );
                proposals.addAll(createProposals(previous,
                                                 prefix,
                                                 followUps,
                                                 startReplace,
                                                 endReplace,
                                                 true)
                                                 );
            }
        } else {
            if (wholetext.length() > 0) {
                int startReplace = 0;
                int endReplace = (prefix == null) ? 0 : prefix.length();
                prefix = getPrefix(text, lastComplete);

                Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                                                                     lastComplete
                                                                     );
                proposals.addAll(createProposals(lastComplete,
                                                 prefix,
                                                 followUps,
                                                 startReplace,
                                                 endReplace,
                                                 false
                                                 ));
            } else {
                int startReplace = lastComplete.getEnd() - 1;
                int endReplace = lastComplete.getEnd() - 1;

                Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                                                                     lastComplete);
                proposals.addAll(createProposals(lastComplete,
                                                 null,
                                                 followUps,
                                                 startReplace,
                                                 endReplace,
                                                 true
                                                 ));
            }
        }

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
                    || prefix == null
                    || (pi.getToInsert().toLowerCase().startsWith(prefix
                            .toLowerCase()))) {
                cp.add(createCompletionProposal(pi));
            }
        }
        return cp.toArray(new ICompletionProposal[cp.size()]);
    }

    private List<Proposal> createProposals(Node lastComplete,
                                           String prefix,
                                           Set<Element> followUps,
                                           int startReplace,
                                           int endReplace,
                                           boolean applyPrefixFilter
                                           ) {
        List<Proposal> proposals = new ArrayList<Proposal>();
        for (Element element : followUps) {
            try {
                proposals.addAll(handleElement(element, lastComplete, prefix));
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
            proposal.setStartReplace(startReplace);
            proposal.setEndReplace(endReplace);
            proposal.setApplyPrefixFilter(applyPrefixFilter);
        }

        return proposals;
    }

    private ICompletionProposal createCompletionProposal(Proposal p) {
        Image img = fLangUtil.getImage(p.getImage());
        return new XtextCompletionProposal(p, img);
    }

    private String getPrefix(String text, Node lastComplete) {
        String prefix = text;
        if (lastComplete != null && lastComplete.getEnd() > 0
                && lastComplete.getEnd() <= text.length())
            prefix = text.substring(lastComplete.getEnd());
        // prefix (in almost all cases) does not end with whitespaces
        return prefix.trim().length() == 0
                || prefix.substring(prefix.length() - 1, prefix.length()).matches("\\s") ? null
                : prefix.trim();
    }

    private boolean insideNode(Node node, int offset) {
        return node.getEnd() >= offset;
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
