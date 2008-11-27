// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;
import org.openarchitectureware.xtext.impl.AssignmentImpl;
import org.openarchitectureware.xtext.parser.parsetree.Node;

/**
 * 自動編集クラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class MapperAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
    private IPreferenceStore fPreferenceStore;
    private AbstractXtextEditor fEditor;

    public MapperAutoEditStrategy(IPreferenceStore preferenceStore,
                                  AbstractXtextEditor editor) {
        fPreferenceStore = preferenceStore;
        fEditor = editor;
    }

    public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
        if (!command.doit) {
            return;
        }

        if (isLineDelimiter(document, command)) {
            smartIndentAfterNewLine(document, command);
        } else if (isAssociationAssignmentNode()) {
            smartAssociationName(document, command);
        } else {
            super.customizeDocumentCommand(document, command);
        }
    }

    private void smartIndentAfterNewLine(IDocument document, DocumentCommand command) {
        if (command.offset == -1 || document.getLength() == 0) {
            return;
        }

        try {
            String prefix = getPrefixString(document, command);

            String indent = getIndentString();

            StringBuffer commandText = new StringBuffer(command.text);
            int caretOffset = 0;
            if (getBraceCount(document) > 0) {
                commandText.append(prefix);
                commandText.append(indent);
                commandText.append(TextUtilities.getDefaultLineDelimiter(document));
                commandText.append(prefix);
                commandText.append("}");
                if (command.offset == document.getLength()) {
                    commandText.append(TextUtilities.getDefaultLineDelimiter(document));
                }

                caretOffset = TextUtilities.getDefaultLineDelimiter(document).length() +
                              prefix.length() +
                              indent.length();
            } else {
                commandText.append(prefix);

                caretOffset = TextUtilities.getDefaultLineDelimiter(document).length() +
                              prefix.length();
            }

            command.text = commandText.toString();
            command.shiftsCaret = false;
            command.caretOffset = command.offset + caretOffset;
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean isLineDelimiter(IDocument document, DocumentCommand command) {
        if (command.length != 0 || command.text == null) {
            return false;
        }

        String[] delimiters= document.getLegalLineDelimiters();
        if (delimiters != null) {
            return TextUtilities.equals(delimiters, command.text) > -1;
        }
        return false;
    }

    private int getBraceCount(IDocument document) throws BadLocationException {
        int offset = 0;
        int braceCount= 0;
        while (offset < document.getLength()) {
            char currentChar = document.getChar(offset);
            offset++;
            switch (currentChar) {
                case '/':
                    offset = getCommentEnd(document,
                                           offset
                                           );
                    break;
                case '{':
                    braceCount++;
                    break;
                case '}':
                    braceCount--;
                    break;
                case '"':
                case '\'':
                    offset = getStringEnd(document,
                                          offset,
                                          currentChar
                                          );
                    break;
                default :
            }
        }
        return braceCount;
    }

    private int getCommentEnd(IDocument document,
                              int offset
                              ) throws BadLocationException {
        int commentEnd = document.getLength();
        if (offset < document.getLength()) {
            char next = document.getChar(offset);
            if (next == '*') {
                commentEnd = getMultiLineCommentEnd(document,
                                                    offset + 1
                                                    );
            } else if (next == '/') {
                IRegion region = document.getLineInformation(document.getLineOfOffset(offset));
                commentEnd = region.getOffset() + region.getLength();
            }
        }
        return commentEnd;
    }

    private int getMultiLineCommentEnd(final IDocument document,
                                       final int startOffset
                                       ) throws BadLocationException {
        int offset = startOffset;
        while (offset < document.getLength()) {
            char currentChar = document.getChar(offset);
            offset++;
            if (currentChar == '*') {
                if (offset < document.getLength()
                    && document.getChar(offset) == '/'
                    ) {
                    return offset + 1;
                }
            }
        }
        return document.getLength();
    }

    private int getStringEnd(final IDocument document,
                             final int startOffset,
                             final char quotationChar
                             ) throws BadLocationException {
        int offset = startOffset;
        while (offset < document.getLength()) {
            char currentChar = document.getChar(offset);
            offset++;
            if (currentChar == '\\') {
                offset++;
            } else if (currentChar == quotationChar) {
                return offset;
            }
        }
        return document.getLength();
    }

    private String getIndentString() {
        StringBuffer indent = new StringBuffer();
        if (fPreferenceStore.getBoolean(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_SPACES_FOR_TABS)) {
            int tabSize = fPreferenceStore.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH);
            for (int i = 1; i <= tabSize; ++i) {
                indent.append(" ");
            }
        } else {
            indent.append("\t");
        }
        return indent.toString();
    }

    private String getPrefixString(IDocument document,
                                   DocumentCommand command
                                   ) throws BadLocationException {
        int offsetForLine = command.offset == document.getLength() ? command.offset - 1
                                                                   : command.offset;
        IRegion line =
            document.getLineInformation(document.getLineOfOffset(offsetForLine));
        int contentOffset = findEndOfWhiteSpace(document,
                                                line.getOffset(),
                                                line.getOffset() + line.getLength()
                                                );
        return document.get(line.getOffset(),
                            contentOffset - line.getOffset()
                            );
    }

    private boolean isAssociationAssignmentNode() {
        Node currentNode = fEditor.getCurrentNode();
        if (currentNode == null) {
            return false;
        }
        if (currentNode.getParent() == null) {
            return false;
        }
        if (currentNode.getParent().getModelElement() == null) {
            return false;
        }

        return currentNode.getParent().getModelElement().eClass().getName().equals("Association")
               && currentNode.getGrammarElement() instanceof AssignmentImpl;
    }

    private boolean isMethodNode(Node node) {
        if (node.getModelElement() == null) {
            return false;
        }

        return node.getModelElement().eClass().getName().equals("Method");
    }

    private boolean isAssociationReferentNode(Node node) {
        if (node.getModelElement() == null) {
            return false;
        }

        return node.getModelElement().eClass().getName().equals("AssociationReference");
    }

    private void smartAssociationName(IDocument document,
                                      DocumentCommand command
                                      ) {
        EObject association = fEditor.getCurrentNode().getParent().getModelElement();
        String associationName = getName(association);
        if (associationName == null) {
            return;
        }

        Node associationAssignmentNode = fEditor.getCurrentNode();
        int offsetOfWord = command.offset - associationAssignmentNode.getStart();

        Node rootNode = fEditor.getRootNode();
        if (rootNode == null) {
            return;
        }

        int targetNodeCountBeforeOffset = 0;
        for (EObject mapperNode : fEditor.getRootNode().eContents()) {
            if (!isMethodNode((Node) mapperNode)) {
                continue;
            }

            for (EObject methodNode : ((Node) mapperNode).eContents()) {
                if (!isAssociationReferentNode((Node) methodNode)) {
                    continue;
                }

                Node associationReferenceNode = (Node) methodNode;
                EObject associationReference = associationReferenceNode.getModelElement();
                if (associationName.equals(getName(associationReference))) {
                    Node targetNode = (Node) associationReferenceNode.getChildren().get(1);
                    try {
                        command.addCommand(targetNode.getStart() + offsetOfWord,
                                           command.length,
                                           command.text,
                                           command.owner
                                           );
                    } catch (BadLocationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (targetNode.getEnd() < command.offset) {
                        targetNodeCountBeforeOffset++;
                    }
                }
            }
        }

        if (command.getCommandCount() > 1) {
            boolean delete = command.length > 0;
            command.caretOffset = command.offset + (targetNodeCountBeforeOffset + 1) * command.text.length();
            if (delete) {
                command.caretOffset -= targetNodeCountBeforeOffset * command.length;
            }
            command.shiftsCaret = false;
            command.doit = false;
        }
    }

    private String getName(EObject eObject) {
        for (EAttribute attribute : eObject.eClass().getEAllAttributes()) {
            if (attribute.getName().equals("name")
                && eObject.eGet(attribute) instanceof String
                ) {
                return (String) eObject.eGet(attribute);
            }
        }

        return null;
    }
}
