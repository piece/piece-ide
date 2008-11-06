// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.editor.contentassist.XTextModelContentAssist;
import org.openarchitectureware.xtext.parser.model.NodeUtil;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class MapperContentAssistProcessor extends XTextModelContentAssist {
    private static String[] containerRules = {"Mapper",
                                              "Method",
                                              "Association",
                                              "InnerAssociation",
                                              "LinkTable"
                                              };
    {
        Arrays.sort(containerRules);
    }

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

        Node containerNode = getContainerNode(offset, node);
        if (containerNode != null) {
            proposals = removeExistedProposal(proposals,
                                              wholetext,
                                              containerNode
                                              );
        }

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
                                                        String wholetext,
                                                        Node containerNode
                                                        ) {
        List<ICompletionProposal> removeProposals = new ArrayList<ICompletionProposal>();
        for (Object childNode : containerNode.getChildren()) {
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
