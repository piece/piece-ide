// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.form_designer.model.Validator.Rule;

public class ValidatorTest extends TestCase {
    private Validator fValidator;
    
    @Override
    protected void setUp() throws Exception {
        fValidator = UsableValidator.getList().get(0).create();
        super.setUp();
    }

    // バリデータはバリデータ名、説明を保持する
    public void testValidatorShouldHaveNameAndDescriptionAndMessage() {
        assertNotNull(fValidator.getName());
        assertNotNull(fValidator.getDescription());
    }
    
    // バリデータは読み書き可能なメッセージを保持する
    public void testValidatorShouldHaveReadableAndWritableMessage() {
        assertEquals("", fValidator.getMessage());
        fValidator.setMessage("メッセージ");
        assertEquals("メッセージ", fValidator.getMessage());
    }

    // バリデータのメッセージにnullをセットすると例外が発生する
    public void testValidatorMessageShouldThrowExceptionWhenSetNull() {
        try {
            fValidator.setMessage(null);
            fail();
        } catch(NullPointerException e) {
        }
    }
    
    // バリデータはひとつ以上のルールを保持する
    public void testValidatorShouldHaveRulesOver1() {
        assertTrue(0 < fValidator.getRules().size());
    }

    // ルールはキーと値クラスを保持する
    public void testRuleShouldHaveKeyAndValuClass() {
        for (Rule rule : fValidator.getRules()) {
            assertNotNull(rule.getKey());
            assertNotNull(rule.getValueClass());
            assertNull(rule.getValue());
        }
    }
    
    // ルールは値クラスにあった読み書き可能な値を保持する
    public void testRuleShouldHaveReadableAndWritableValueThatSetValueClass() {
        for (Rule rule : fValidator.getRules()) {
            assertNull(rule.getValue());

            if (rule.getValueClass() == String.class) {
                rule.setValue("value");
                assertTrue(rule.getValue() instanceof String);
                assertEquals("value", rule.getValue());
            } else if (rule.getValueClass() == Integer.class) {
                rule.setValue("3");
                assertTrue(rule.getValue() instanceof Integer);
                assertEquals(3, ((Integer) rule.getValue()).intValue());
            } else if (rule.getValueClass() == Boolean.class) {
                rule.setValue("true");
                assertTrue(rule.getValue() instanceof Boolean);
                assertTrue(((Boolean) rule.getValue()).booleanValue());
            } else {
                fail();
            }
        }
    }

    // ルールの値にnullをセットできる
    public void testRuleValueShouldBeAbleToSetNull() {
        for (Rule rule : fValidator.getRules()) {
            rule.setValue(null);
            assertNull(rule.getValue());
        }
    }
}
