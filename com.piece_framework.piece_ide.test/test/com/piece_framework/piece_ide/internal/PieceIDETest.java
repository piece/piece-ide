// $Id$
package com.piece_framework.piece_ide.internal;

import java.lang.reflect.Method;

import junit.framework.TestCase;

/**
 * PieceIDEユーティリティテストクラス.
 * テスト対象：com.piece_framework.piece_ide.internal.PieceIDE
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 *
 */
public class PieceIDETest extends TestCase {

    /**
     * メソッド実行テスト用クラス.
     * 
     * @author MATSUFUJI Hideharu
     * @version 0.2.0
     * @since 0.1.0
     *
     */    
    class MethodTest {
        private String fValue;
        
        /**
         * コンストラクタ.
         * 
         * @param value 値
         */
        public MethodTest(String value) {
            fValue = value;
        }

        /**
         * 値がnullの場合はnullを、nullでない場合は親クラスの
         * toStringメソッドを実行する.
         * 
         * @return 文字列
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            if (fValue == null) {
                return null;
            }
            return super.toString();
        }
        
        /**
         * 値を返す.
         * 
         * @return 値
         */
        public String getValue() {
            return fValue;
        }
    }
    
    /**
     * compareメソッドテスト.
     * 同じ文字列を渡した場合にtrueが返されることをテストする。
     * 
     */
    public void testComapreShouldReturnTrueBecauseOfTheParametersAreSameStringObjects() {
        String string1 = "Test";
        String string2 = "Test";
        assertTrue(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compareメソッドテスト.
     * 異なる文字列を渡した場合にfalseが返されることをテストする。
     * 
     */
    public void testCompareShouldReturnFalseBecauseOfTheParametersAreDifferentStringObjects() {
        String string1 = "Test1";
        String string2 = "Test2";
        assertFalse(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compareメソッドテスト.
     * 比較対象のオブジェクトの両方にnullを渡した場合にtrueが返されることをテストする。
     * 
     */
    public void testCompareShouldReturnTrueBecauseOfTheParametersAreNullObjects() {
        assertTrue(PieceIDE.compare(null, null));
    }
    
    /**
     * compareメソッドテスト.
     * 比較対象のオブジェクトの片方のみnullの場合にfalseが返されることをテストする。
     * 
     */
    public void testCompareShouldReturnFalseBecauseOfTheParametersAreNullObjectAndNotNullObject() {
        String string1 = "Test1";
        String string2 = null;
        assertFalse(PieceIDE.compare(string1, string2));
        
        string1 = null;
        string2 = "Test2";
        assertFalse(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compareメソッドテスト.
     * 比較対象のオブジェクトの片方がnullでもう片方が空文字の場合にtrueが返されること
     * をテストする。
     * 
     */
    public void testCompareShouldReturnTrueBecauseOfTheParametersAreNullObjectAndEmptyStringObject() {
        String string1 = "";
        String string2 = null;
        assertTrue(PieceIDE.compare(string1, string2));
        
        string1 = null;
        string2 = "";
        assertTrue(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドの実行結果が同じ場合にfalseが返されることをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodValuesAreSame() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest("Test");
        MethodTest test2 = new MethodTest("Test");
        assertTrue(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドの実行結果が異なる場合にfalseが返されることをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnFalseBecauseOfTheMethodValuesAreDifferent() {
        Method method = createMethod(MethodTest.class, "toString");
        
        MethodTest test1 = new MethodTest("Test");
        MethodTest test2 = new MethodTest("Test");
        assertFalse(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドの実行結果の両方がnullの場合にtrueが返されることをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodValuesAreNull() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest(null);
        MethodTest test2 = new MethodTest(null);
        assertTrue(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドの実行結果の片方のみnullの場合にfalseが返されることをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnFalseBecauseOfTheMethodValuesAreNullAndNotNull() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest("Test");
        MethodTest test2 = new MethodTest(null);
        assertFalse(PieceIDE.compare(test1, test2, method));
        
        test1 = new MethodTest(null);
        test2 = new MethodTest("Test");
        assertFalse(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドの実行結果の片方がnullでもう片方が空文字の場合にtrueが返される
     * ことをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodValuesAreNullObjectAndEmptyStringObject() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest("");
        MethodTest test2 = new MethodTest(null);
        assertTrue(PieceIDE.compare(test1, test2, method));
        
        test1 = new MethodTest(null);
        test2 = new MethodTest("");
        assertTrue(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * 比較対象のオブジェクトが両方nullでメソッドを渡した場合にtrueが返される
     * ことをテストする。
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjects() {
        Method method = createMethod(MethodTest.class, "toString");
        
        assertTrue(PieceIDE.compare(null, null, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * 比較対象のオブジェクトの片方のみnullでメソッドを渡した場合にtrueが返され
     * ることをテストする。
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjectsAndNotNull() {
        Method method = createMethod(MethodTest.class, "toString");
        
        MethodTest test = new MethodTest("Test");
        assertFalse(PieceIDE.compare(test, null, method));
        
        assertFalse(PieceIDE.compare(null, test, method));
    }
    
    /**
     * compare(メソッド実行)メソッドテスト.
     * 比較対象のオブジェクトの片方がnullでもう片方が空文字でメソッドを渡した場合
     * にtrueが返されることをテストする。
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjectAndEmptyStringObject() {
        Method method = createMethod(String.class, "toString");
        
        String string1 = "";
        assertTrue(PieceIDE.compare(string1, null, method));
        
        assertTrue(PieceIDE.compare(null, string1, method));
    }

    /**
     * compare(メソッド実行)メソッドテスト.
     * メソッドオブジェクトにnullを渡した場合はオブジェクトのみのcompare
     * と同じ動きをする。
     * 同じ文字列を渡した場合にtrueが返されることをテストする。
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodParameterIsNullAndTheParametersAreSameStringObjects() {
        String string1 = "Test";
        String string2 = "Test";
        assertTrue(PieceIDE.compare(string1, string2, null));
    }
    
    /**
     * メソッドオブジェクトを作成する.
     * 
     * @param klass クラス
     * @param methodName メソッド名
     * @return メソッドオブジェクト
     */
    private Method createMethod(Class klass, String methodName) {
        Method method = null;
        try {
            method = 
                klass.getMethod(methodName, (Class[]) null);
        } catch (NoSuchMethodException nsme) {
            fail();
        }
        return method;
    }
}
