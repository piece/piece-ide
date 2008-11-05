package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.openarchitectureware.xtext.parser.parsetree.Node;

public class ProposalParameter {
    private Node fLastComplete;
    private String fPrefix;
    private int fStartReplace;
    private int fEndReplace;
    private boolean fApplyPrefixFilter;

    private ProposalParameter() {}

    public static ProposalParameter create(String wholetext,
                                           int offset,
                                           NodeForContentAssist lastComplete
                                           ) {
        String beforeText = wholetext.substring(0, offset);
        ProposalParameter parameter = new ProposalParameter();
        if (offset > 0) {
            if (!insideNode(lastComplete, offset)) {
                parameter.fLastComplete = lastComplete;
                parameter.fPrefix = getPrefix(beforeText, lastComplete);
                parameter.fStartReplace = parameter.fPrefix == null ? offset
                                                                    : offset - parameter.fPrefix.length();
                parameter.fEndReplace = offset;
                parameter.fApplyPrefixFilter = false;
            } else {
                NodeForContentAssist previous = new NodeForContentAssist(lastComplete.fNode,
                                                                         lastComplete.getStart()
                                                                         );
                parameter.fLastComplete = previous;
                parameter.fPrefix = getPrefix(beforeText, previous);
                parameter.fStartReplace = lastComplete.getStart();
                parameter.fEndReplace = lastComplete.getEnd();
                parameter.fApplyPrefixFilter = true;
            }
        } else {
            if (wholetext.length() > 0) {
                parameter.fLastComplete = lastComplete;
                parameter.fPrefix = getPrefix(beforeText, lastComplete);
                parameter.fStartReplace = 0;
                parameter.fEndReplace = parameter.fPrefix == null ? 0 : parameter.fPrefix.length();
                parameter.fApplyPrefixFilter = false;
            } else {
                parameter.fLastComplete = lastComplete;
                parameter.fPrefix = null;
                parameter.fStartReplace = lastComplete.getEnd() - 1;
                parameter.fEndReplace = lastComplete.getEnd() - 1;
                parameter.fApplyPrefixFilter = true;
            }
        }

        return parameter;
    }

    private static String getPrefix(String text, Node lastComplete) {
        String prefix = new String(text);
        if (lastComplete != null
            && lastComplete.getEnd() > 0
            && lastComplete.getEnd() <= text.length()
            ) {
            prefix = text.substring(lastComplete.getEnd());
        }

        if (prefix.trim().length() == 0
            || prefix.substring(prefix.length() - 1, prefix.length()).matches("\\s")
            ) {
            prefix = null;
        }

        return prefix;
    }

    private static boolean insideNode(Node node, int offset) {
        return node.getEnd() >= offset;
    }

    public Node getLastComplete() {
        return fLastComplete;
    }

    public String getPrefix() {
        return fPrefix;
    }

    public int getStartReplace() {
        return fStartReplace;
    }

    public int getEndReplace() {
        return fEndReplace;
    }

    public boolean isApplyPrefixFilter() {
        return fApplyPrefixFilter;
    }
}
