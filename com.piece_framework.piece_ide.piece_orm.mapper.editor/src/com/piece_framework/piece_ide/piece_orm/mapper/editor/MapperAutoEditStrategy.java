// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

/**
 * 自動編集クラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class MapperAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
    private IPreferenceStore fPreferenceStore;

    public MapperAutoEditStrategy(IPreferenceStore preferenceStore) {
        fPreferenceStore = preferenceStore;
    }

    public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
        if (!command.doit) {
            return;
        }

        if (isLineDelimiter(document, command)) {
            smartIndentAfterNewLine(document, command);
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
}
