// $Id$
package com.piece_framework.piece_ide.internal;

import java.lang.reflect.Method;

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
     * ���\�b�h���s�e�X�g�p�N���X.
     * 
     * @author MATSUFUJI Hideharu
     * @version 0.1.0
     * @since 0.1.0
     *
     */    
    class MethodTest {
        private String fValue;
        
        /**
         * �R���X�g���N�^.
         * 
         * @param value �l
         */
        public MethodTest(String value) {
            fValue = value;
        }

        /**
         * �l��null�̏ꍇ��null���Anull�łȂ��ꍇ�͐e�N���X��toString���\�b�h�����s����.
         * 
         * @return ������
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
         * �l��Ԃ�.
         * 
         * @return �l
         */
        public String getValue() {
            return fValue;
        }
    }
    
    /**
     * compare���\�b�h�e�X�g.
     * �����������n�����ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreShouldReturnTrueBecauseOfTheParametersAreSameStringObjects() {
        String string1 = "Test";
        String string2 = "Test";
        assertTrue(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compare���\�b�h�e�X�g.
     * �قȂ镶�����n�����ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCompareShouldReturnFalseBecauseOfTheParametersAreDifferentStringObjects() {
        String string1 = "Test1";
        String string2 = "Test2";
        assertFalse(PieceIDE.compare(string1, string2));
    }
    
    /**
     * compare���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g�̗�����null��n�����ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCompareShouldReturnTrueBecauseOfTheParametersAreNullObjects() {
        assertTrue(PieceIDE.compare(null, null));
    }
    
    /**
     * compare���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g�̕Е��̂�null�̏ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
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
     * compare���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g�̕Е���null�ł����Е����󕶎��̏ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
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
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�̎��s���ʂ������ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodValuesAreSame() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest("Test");
        MethodTest test2 = new MethodTest("Test");
        assertTrue(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�̎��s���ʂ��قȂ�ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreWithMethodShouldReturnFalseBecauseOfTheMethodValuesAreDifferent() {
        Method method = createMethod(MethodTest.class, "toString");
        
        MethodTest test1 = new MethodTest("Test");
        MethodTest test2 = new MethodTest("Test");
        assertFalse(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�̎��s���ʂ̗�����null�̏ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodValuesAreNull() {
        Method method = createMethod(MethodTest.class, "getValue");
        
        MethodTest test1 = new MethodTest(null);
        MethodTest test2 = new MethodTest(null);
        assertTrue(PieceIDE.compare(test1, test2, method));
    }
    
    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�̎��s���ʂ̕Е��̂�null�̏ꍇ��false���Ԃ���邱�Ƃ��e�X�g����B
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
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�̎��s���ʂ̕Е���null�ł����Е����󕶎��̏ꍇ��true���Ԃ����
     * ���Ƃ��e�X�g����B
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
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g������null�Ń��\�b�h��n�����ꍇ��true���Ԃ����
     * ���Ƃ��e�X�g����B
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjects() {
        Method method = createMethod(MethodTest.class, "toString");
        
        assertTrue(PieceIDE.compare(null, null, method));
    }
    
    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g�̕Е��̂�null�Ń��\�b�h��n�����ꍇ��true���Ԃ���
     * �邱�Ƃ��e�X�g����B
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjectsAndNotNull() {
        Method method = createMethod(MethodTest.class, "toString");
        
        MethodTest test = new MethodTest("Test");
        assertFalse(PieceIDE.compare(test, null, method));
        
        assertFalse(PieceIDE.compare(null, test, method));
    }
    
    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ��r�Ώۂ̃I�u�W�F�N�g�̕Е���null�ł����Е����󕶎��Ń��\�b�h��n�����ꍇ
     * ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testCompareWithMethodShouldReturnTrueBecauseOfTheParametersAreNullObjectAndEmptyStringObject() {
        Method method = createMethod(String.class, "toString");
        
        String string1 = "";
        assertTrue(PieceIDE.compare(string1, null, method));
        
        assertTrue(PieceIDE.compare(null, string1, method));
    }

    /**
     * compare(���\�b�h���s)���\�b�h�e�X�g.
     * ���\�b�h�I�u�W�F�N�g��null��n�����ꍇ�̓I�u�W�F�N�g�݂̂�compare
     * �Ɠ�������������B
     * �����������n�����ꍇ��true���Ԃ���邱�Ƃ��e�X�g����B
     * 
     */
    public void testComapreWithMethodShouldReturnTrueBecauseOfTheMethodParameterIsNullAndTheParametersAreSameStringObjects() {
        String string1 = "Test";
        String string2 = "Test";
        assertTrue(PieceIDE.compare(string1, string2, null));
    }
    
    /**
     * ���\�b�h�I�u�W�F�N�g���쐬����.
     * 
     * @param klass �N���X
     * @param methodName ���\�b�h��
     * @return ���\�b�h�I�u�W�F�N�g
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
