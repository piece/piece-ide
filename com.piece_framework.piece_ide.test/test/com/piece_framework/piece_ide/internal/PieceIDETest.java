package com.piece_framework.piece_ide.internal;

import junit.framework.TestCase;

/**
 * PieceIDEユーティリティテストクラス.
 * テスト対象：com.piece_framework.piece_ide.internal.PieceIDE
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class PieceIDETest extends TestCase {

    /**
     * compareメソッドテスト.
     * 同じ文字列を渡した場合にtrueが返されることをテストする。
     * 
     */
    public void testComapreShouldReturnTrueBecauseOfSameString() {
        String string1 = "Test";
        String string2 = "Test";
        
        assertTrue(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compareメソッドテスト.
     * 異なる文字列を渡した場合にfalseが返されることをテストする。
     * 
     */
    public void testCompareShouldReturnFalseBecauseOfDifferentString() {
        String string1 = "Test1";
        String string2 = "Test2";
        
        assertFalse(PieceIDE.compare(string1, string2));
    }
}
