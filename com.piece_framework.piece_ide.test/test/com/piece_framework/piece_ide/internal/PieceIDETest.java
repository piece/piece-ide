package com.piece_framework.piece_ide.internal;

import junit.framework.TestCase;

/**
 * PieceIDE���[�e�B���e�B�e�X�g�N���X.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.internal.PieceIDE
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class PieceIDETest extends TestCase {

    /**
     * compare���\�b�h�e�X�g.
     * �����������n�����ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreShouldReturnTrueBecauseOfSameString() {
        String string1 = "Test";
        String string2 = "Test";
        
        assertTrue(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compare���\�b�h�e�X�g.
     * �قȂ镶�����n�����ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCompareShouldReturnFalseBecauseOfDifferentString() {
        String string1 = "Test1";
        String string2 = "Test2";
        
        assertFalse(PieceIDE.compare(string1, string2));
    }
}
