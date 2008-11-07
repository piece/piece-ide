// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.openarchitectureware.xtext.Assignment;
import org.openarchitectureware.xtext.Element;
import org.openarchitectureware.xtext.EnumLiteral;
import org.openarchitectureware.xtext.Keyword;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.contentassist.XTextModelContentAssist;
import org.openarchitectureware.xtext.editor.contentassist.codeassist.Proposal;
import org.openarchitectureware.xtext.parser.model.NodeUtil;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class MapperContentAssistProcessor extends XTextModelContentAssist {
    private static String[] containerRules = {"Mapper",
                                              "Method",
                                              "Association",
                                              "InnerAssociation",
                                              "LinkTable"
                                              };
    private static String[] unnecessaryWords = {"^(.*) \\{$"};
    private static Map<String, Map<String, String>> proposalIcons;

    {
        Arrays.sort(containerRules);

        proposalIcons = new HashMap<String, Map<String, String>>();

        Map<String, String> mapperStatements = new HashMap<String, String>();
        mapperStatements.put("method", "method.gif");
        mapperStatements.put("association", "association.gif");

        Map<String, String> methodStatements = new HashMap<String, String>();
        methodStatements.put("query", "method_statement.gif");
        methodStatements.put("orderBy", "method_statement.gif");
        methodStatements.put("association", "association.gif");

        Map<String, String> associationStatements = new HashMap<String, String>();
        associationStatements.put("table", "association_required_statement.gif");
        associationStatements.put("type", "association_required_statement.gif");
        associationStatements.put("property", "association_required_statement.gif");
        associationStatements.put("column", "association_statement.gif");
        associationStatements.put("referencedColumn", "association_statement.gif");
        associationStatements.put("orderBy", "association_statement.gif");
        associationStatements.put("linkTable", "link_table.gif");

        Map<String, String> linkTableStatements = new HashMap<String, String>();
        linkTableStatements.put("table", "link_table_required_statement.gif");
        linkTableStatements.put("column", "link_table_statement.gif");
        linkTableStatements.put("referencedColumn", "link_table_statement.gif");
        linkTableStatements.put("inverseColumn", "link_table_statement.gif");

        proposalIcons.put("Mapper", mapperStatements);
        proposalIcons.put("Method", methodStatements);
        proposalIcons.put("Association", associationStatements);
        proposalIcons.put("InnerAssociation", associationStatements);
        proposalIcons.put("LinkTable", linkTableStatements);
    }

    private Node fContainerNode;

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
        fContainerNode = getContainerNode(offset, node);

        ICompletionProposal[] proposals = super.internalComputeProposals(wholetext,
                                                                         offset,
                                                                         node
                                                                         );
        proposals = removeExistedProposal(proposals, wholetext);

        return proposals;
    }

    @Override
    protected List<Proposal> handleAssignment(Assignment element,
                                              Node lastComplete,
                                              String prefix
                                              ) {
        List<Proposal> proposals = super.handleAssignment(element, lastComplete, prefix);
        cutOutUnnecessaryWords(proposals);
        setImage(proposals);
        return proposals;
    }

    @Override
    protected List<Proposal> handleEnumLiteral(EnumLiteral element,
                                               Node lastComplete,
                                               String prefix
                                               ) {
        List<Proposal> proposals = super.handleEnumLiteral(element, lastComplete, prefix);
        cutOutUnnecessaryWords(proposals);
        setImage(proposals);
        return proposals;
    }

    @Override
    protected List<Proposal> handleKeyword(Keyword element,
                                           Node lastComplete,
                                           String prefix
                                           ) {
        List<Proposal> proposals = super.handleKeyword(element, lastComplete, prefix);
        cutOutUnnecessaryWords(proposals);
        setImage(proposals);
        return proposals;
    }

    @Override
    protected List<Proposal> handleOther(Element element,
                                         Node lastComplete,
                                         String prefix
                                         ) {
        List<Proposal> proposals = super.handleOther(element, lastComplete, prefix);
        cutOutUnnecessaryWords(proposals);
        setImage(proposals);
        return proposals;
    }

    private Node getContainerNode(int offset,
                                  Node node
                                  ) {
        Node lastComplete = NodeUtil.getNodeBeforeOffset(node, offset);
        if (lastComplete == null) {
            return null;
        }
        boolean insideNode = lastComplete.getEnd() >= offset;
        if (insideNode) {
            return null;
        }

        Node parent = lastComplete.getParent();
        try {
            while (!checkContainerNode(offset, parent)) {
                parent = parent.getParent();
            }
        } catch (NullPointerException e) {
            parent = null;
        }

        return parent;
    }

    private boolean checkContainerNode(int offset,
                                       Node node
                                       ) {
        boolean isContainerRule = Arrays.binarySearch(containerRules,
                                                      node.getModelElement().eClass().getName()
                                                      ) >= 0;
        boolean insideNode = node.getStart() <= offset && offset <= node.getEnd();

        return isContainerRule && insideNode;
    }

    private ICompletionProposal[] removeExistedProposal(ICompletionProposal[] proposals,
                                                        String wholetext
                                                        ) {
        if (fContainerNode == null) {
            return proposals;
        }
        if (proposals == null) {
            return null;
        }

        List<ICompletionProposal> removeProposals = new ArrayList<ICompletionProposal>();
        for (Object childNode : fContainerNode.getChildren()) {
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

    private void setImage(List<Proposal> proposals) {
        String containerRuleName = fContainerNode != null ? fContainerNode.getModelElement().eClass().getName()
                                                          : "Mapper";

        Map<String, String> icons = proposalIcons.get(containerRuleName);
        if (icons == null) {
            return;
        }
        for (Proposal proposal : proposals) {
            if (icons.containsKey(proposal.getLabel())) {
                proposal.setImage(icons.get(proposal.getLabel()));
            }
        }
    }

    private void cutOutUnnecessaryWords(List<Proposal> proposals) {
        for (Proposal proposal : proposals) {
            for (String unnecessaryWord : unnecessaryWords) {
                if (proposal.getLabel().matches(unnecessaryWord)) {
                    proposal.setLabel(proposal.getLabel().replaceAll(unnecessaryWord, "$1"));
                }
            }
        }
    }
}
