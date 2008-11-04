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

        String text = wholetext.substring(0, offset);
        NodeForContentAssist lastComplete = new NodeForContentAssist(node, offset);
        if ((lastComplete.fNode != null) && !lastComplete.getErrors().isEmpty()
                && (lastComplete.getStart() == 0))
            lastComplete = null;
        if (lastComplete.hasParent()) {
            NodeForContentAssist parent = new NodeForContentAssist(lastComplete.getParent());
            if (parent.getGrammarElement() instanceof Assignment) {
                Assignment assignment = (Assignment) parent.getGrammarElement();
                if (assignment.getToken() instanceof CrossReference) {
                    for (EObject it = lastComplete.getGrammarElement(); it != null; it = it
                            .eContainer()) {
                        if (it instanceof StringRule) {
                            lastComplete = parent;
                            break;
                        }
                    }
                }
            }
        }

        List<Proposal> proposals = new ArrayList<Proposal>();
        String prefix = null;
        // if lastComplete is under the cursor we look up proposals for
        // completing this node
        // and proposals for possible completions of the preceeding node.
        if (lastComplete.fNode != null && lastComplete.getEnd() >= offset) {
            int startReplace;
            int endReplace;

            // get the preceeding node
            NodeForContentAssist previous = new NodeForContentAssist(lastComplete.fNode,
                                                                     lastComplete.getStart()
                                                                     );
            if (previous.fNode == lastComplete.fNode) {
                // This case only occurrs in an empty document.
                // Since ANTLR specifies the end of an empty document to be at position 1 (NOT zero),
                // we subtract 1 at this place:
                startReplace = lastComplete.getEnd() - 1;
                endReplace = lastComplete.getEnd() - 1;

                Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                        lastComplete);
                proposals.addAll(createProposals(lastComplete, null, followUps, startReplace,
                        endReplace, true));
            } else {
                Set<Element> followUps;

                // 2008-04-10 Peter: 
                //      Do NOT take the last complete node into consideration,
                //      as this will produce superfluous proposals!
//              // Last Complete Node
//              startReplace = lastComplete.getEnd();
//              endReplace   = lastComplete.getEnd();
//
//              followUps = NodeUtil.getPossibleFollows(langUtil.getXtextFile(), lastComplete);
//              proposals.addAll(createProposals(lastComplete, null, followUps, startReplace, endReplace, false, true));
                //--- end deactivated code

                // Previous Node
                startReplace = lastComplete.getStart();
                endReplace = lastComplete.getEnd();

                prefix = getPrefix(text, previous);
                followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(), previous);
                proposals.addAll(createProposals(previous, prefix, followUps, startReplace,
                        endReplace, true));
            }
        } else {
            int startReplace;
            int endReplace;
            prefix = getPrefix(text, lastComplete);

            if (lastComplete.fNode == null) {
                startReplace = 0;
                endReplace = (prefix == null) ? 0 : prefix.length();
            } else {
                startReplace = (prefix == null) ? offset : offset - prefix.length();
                endReplace = offset;
            }

            Set<Element> followUps = NodeUtil.getPossibleFollows(fLangUtil.getXtextFile(),
                    lastComplete);
            proposals.addAll(createProposals(lastComplete, prefix, followUps, startReplace,
                    endReplace, false));
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

    private List<Proposal> createProposals(Node lastComplete, String prefix,
            Set<Element> followUps, int startReplace, int endReplace, boolean applyPrefixFilter) {
        List<Proposal> proposals = new ArrayList<Proposal>();
        for (Element ele : followUps) {
            List<Proposal> tempResult = null;
            if (ele instanceof Assignment) {
                Assignment ass = (Assignment) ele;

                try {
                    tempResult = handleAssignment(ass, lastComplete, prefix);
                    proposals.addAll(tempResult != null ? tempResult
                            : Collections.EMPTY_LIST);
                } catch (RuntimeException re) {
                    XtextLog.logInfo(re.getMessage());
                }
            } else if (ele instanceof Keyword) {
                try {
                    tempResult = handleKeyword((Keyword) ele, lastComplete,
                            prefix);
                    proposals.addAll(tempResult != null ? tempResult
                            : Collections.EMPTY_LIST);
                } catch (RuntimeException re) {
                    XtextLog.logInfo(re.getMessage());
                }
            } else if (ele instanceof EnumLiteral) {
                try {
                    tempResult = handleEnumLiteral((EnumLiteral) ele,
                            lastComplete, prefix);
                    proposals.addAll(tempResult != null ? tempResult
                            : Collections.EMPTY_LIST);
                } catch (RuntimeException re) {
                    XtextLog.logInfo(re.getMessage());
                }
            } else {
                try {
                    tempResult = handleOther(ele, lastComplete, prefix);
                    proposals.addAll(tempResult != null ? tempResult
                            : Collections.EMPTY_LIST);
                } catch (RuntimeException re) {
                    String message = re.getMessage();
                    if (message == null) {
                        // Message can be null, so more info is needed here
                        //TODO what about above logs and re.getMessage==null? 
                        XtextLog.logInfo(re.getClass().getSimpleName()
                                + " occurred", re);
                    } else
                        XtextLog.logInfo(re.getMessage());
                }
            }
        }

        for (Proposal p : proposals) {
            p.setStartReplace(startReplace);
            p.setEndReplace(endReplace);
            p.setApplyPrefixFilter(applyPrefixFilter);
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
}
