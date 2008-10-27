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

public class MapperAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
    private String fIndent;

    public MapperAutoEditStrategy(IPreferenceStore store) {
        if (store.getBoolean(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_SPACES_FOR_TABS)) {
            int tabSize = store.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH);
            StringBuffer buffer = new StringBuffer();
            for (int i = 1; i <= tabSize; ++i) {
                buffer.append(" ");
            }
            fIndent = buffer.toString();
        } else {
            fIndent = "\t";
        }
    }

    public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
        if (!command.doit) {
            return;
        }

        if (command.length == 0 && command.text != null && isLineDelimiter(document, command.text)) {
            smartIndentAfterNewLine(document, command);
        }

        super.customizeDocumentCommand(document, command);
    }

    private void smartIndentAfterNewLine(IDocument document, DocumentCommand command) {
        if (command.offset == -1 || document.getLength() == 0) {
            return;
        }

        try {
            if (getBraceCount(document) > 0) {
                command.text = command.text +
                               fIndent +
                               TextUtilities.getDefaultLineDelimiter(document) +
                               "}" +
                               TextUtilities.getDefaultLineDelimiter(document);
                command.shiftsCaret = false;
                command.caretOffset = command.offset + fIndent.length() + 1;
            }
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean isLineDelimiter(IDocument document, String text) {
        String[] delimiters= document.getLegalLineDelimiters();
        if (delimiters != null) {
            return TextUtilities.equals(delimiters, text) > -1;
        }
        return false;
    }

    private int getBraceCount(IDocument document) throws BadLocationException {
        int offset = 0;
        int bracketCount= 0;
        while (offset < document.getLength()) {
            char currentChar = document.getChar(offset);
            offset++;
            switch (currentChar) {
                case '/' :
                    offset = getCommentEnd(document,
                                           offset
                                           );
                    break;
                case '{' :
                    bracketCount++;
                    break;
                case '}' :
                    bracketCount--;
                    break;
                case '"' :
                case '\'' :
                    offset = getStringEnd(document,
                                          offset,
                                          currentChar
                                          );
                    break;
                default :
            }
        }
        return bracketCount;
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
}
