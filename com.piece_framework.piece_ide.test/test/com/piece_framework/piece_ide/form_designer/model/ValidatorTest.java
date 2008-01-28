// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import junit.framework.TestCase;

public class ValidatorTest extends TestCase {

    // バリデータはフィールドに追加される。
    public void testValidatorShouldBeAddedField() {
        Field field = new Field("name");

        field.addValidator(Validator.COMPARE);

        assertEquals(1, field.getValidators().size());
        Validator validator = field.getValidators().get(0);
        assertEquals(1, validator.getRules().size());
        Validator.Rule rule = validator.getRules().get(0);
        assertEquals("to", rule.getKey());
        assertNull(rule.getValue());
    }

    // バリデータは新しいインスタンスとしてフィールドに追加される。
    public void testValidatorShouldBeAddedFieldAsNewInstance() {
        Field field = new Field("email");
        
        field.addValidator(Validator.COMPARE);

        assertFalse(Validator.COMPARE == field.getValidators().get(0));
        
        field.getValidators().get(0).getRules().get(0).setValue("email2");
        assertNull(Validator.COMPARE.getRules().get(0).getValue());
    }

    // ルールの値を変更するとフィールドが保持しているバリデータのルールの値も変更される。
    public void testValidatorRuleThatFiledHasShouldChangeWhenRuleValueChange() {
        Field field = new Field("email");
        
        field.addValidator(Validator.COMPARE);

        field.getValidators().get(0).getRules().get(0).setValue("email2");

        assertEquals("email2", field.getValidators().get(0).getRules().get(0).getValue());
    }
}
