// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class FieldTest extends TestCase {
    
    private final class TestPropertyChangeListener implements PropertyChangeListener {
        private String fListenerName;
        private String fPropertyName;
        private Object fNewValue;
        private Object fOldValue;
        
        public TestPropertyChangeListener(final String listenerName) {
            fListenerName = listenerName;
        }
        
        public void propertyChange(PropertyChangeEvent event) {
            fPropertyName = event.getPropertyName();
            fNewValue = event.getNewValue();
            fOldValue = event.getOldValue();
        }

        public String getListenerName() {
            return fListenerName;
        }
        
        public String getPropertyName() {
            return fPropertyName;
        }
        
        public Object getNewValue() {
            return fNewValue;
        }
        
        public Object getOldValue() {
            return fOldValue;
        }
    }

    // プロパティーの値が変更されたらイベントが実行される
    public void testEventShouldBeExecutedWhenChangeFieldProperty() {
        Field field = new Field("name");
        TestPropertyChangeListener listener = 
            new TestPropertyChangeListener("Field");
        field.addPropertyChangeListener(listener);

        field.setName("first_name");
        assertEquals("Field#Name", listener.getPropertyName());
        assertEquals("first_name", listener.getNewValue());
        assertEquals("name", listener.getOldValue());

        field.setDescription("名前");
        assertEquals("Field#Description", listener.getPropertyName());
        assertEquals("名前", listener.getNewValue());
        assertEquals("", listener.getOldValue());

        field.setRequired(true);
        assertEquals("Field#Required", listener.getPropertyName());
        assertTrue(((Boolean) listener.getNewValue()).booleanValue());
        assertFalse(((Boolean) listener.getOldValue()).booleanValue());

        field.setMessage("名前は必須です。");
        assertEquals("Field#Message", listener.getPropertyName());
        assertEquals("名前は必須です。", listener.getNewValue());
        assertEquals("", listener.getOldValue());

        field.setForceValidation(true);
        assertEquals("Field#ForceValidation", listener.getPropertyName());
        assertTrue(((Boolean) listener.getNewValue()).booleanValue());
        assertFalse(((Boolean) listener.getOldValue()).booleanValue());
    }
    
    // ふたつのFieldオブジェクトを入れ替えることができる
    public void test2FieldsShouldBeSwapped() {
        Field field1 = new Field("name");
        field1.setDescription("名前");
        field1.setRequired(true);
        field1.setMessage("名前は必須です。");
        field1.setForceValidation(true);
        TestPropertyChangeListener listener1 = 
            new TestPropertyChangeListener("Field1");
        field1.addPropertyChangeListener(listener1);

        Field field2 = new Field("address");
        field2.setDescription("住所");
        field2.setRequired(false);
        field2.setMessage("住所は必須です。");
        field2.setForceValidation(false);
        TestPropertyChangeListener listener2 = 
            new TestPropertyChangeListener("Field2");
        field2.addPropertyChangeListener(listener2);
        
        Field.swap(field1, field2);
        
        assertEquals("address", field1.getName());
        assertEquals("住所", field1.getDescription());
        assertFalse(field1.isRequired());
        assertEquals("住所は必須です。", field1.getMessage());
        assertFalse(field1.isForceValidation());
        assertEquals("Field1", listener1.getListenerName());
        assertEquals("Field#Swap", listener1.getPropertyName());

        assertEquals("name", field2.getName());
        assertEquals("名前", field2.getDescription());
        assertTrue(field2.isRequired());
        assertEquals("名前は必須です。", field2.getMessage());
        assertTrue(field2.isForceValidation());
        assertEquals("Field2", listener2.getListenerName());
        assertEquals("Field#Swap", listener2.getPropertyName());
    }
    
    // 片方がnullの場合はFieldオブジェクトを入れ替えることはできない
    public void testNullFieldShouldNotBeAbleSwap() {
        Field field = new Field("name");
        
        try {
            Field.swap(field, null);
            fail();
        } catch (NullPointerException npe) {
        }

        try {
            Field.swap(null, field);
            fail();
        } catch (NullPointerException npe) {
        }
    }
    
    // フィールドにはバリデータを追加できる
    public void testFieldShouldAddValidator() {
        Field field = new Field("name");
        List<Validator> expectedValidators = new ArrayList<Validator>();

        for (UsableValidator usableValidator : UsableValidator.getList()) {
            Validator validator = usableValidator.create();
            field.addValidator(validator);
            expectedValidators.add(validator);
        }

        assertEquals(expectedValidators.size(), field.getValidators().size());
        int index = 0;
        for (Validator validator : field.getValidators()) {
            assertEquals(expectedValidators.get(index), validator);
            index++;
        }
    }

    // フィールドからバリデータを削除できる
    public void testFieldShouldDeleteValidator() {
        Field field = new Field("name");
        List<Validator> validators = new ArrayList<Validator>();

        for (UsableValidator usableValidator : UsableValidator.getList()) {
            Validator validator = usableValidator.create();
            field.addValidator(validator);
            validators.add(validator);
        }

        for (Validator validator : validators) {
            field.removeValidator(validator);
        }
        assertEquals(0, field.getValidators().size());
    }

    // バリデータの追加・削除時にイベントがされる
    public void testEventShouldBeExecutedWhenValidatorAddAndRemove() {
        Field field = new Field("name");
        TestPropertyChangeListener listener = 
            new TestPropertyChangeListener("Field");
        field.addPropertyChangeListener(listener);

        Validator expectedValidator = UsableValidator.getList().get(0).create();
        field.addValidator(expectedValidator);
        assertEquals("Field#AddValidator", listener.getPropertyName());
        assertEquals(expectedValidator, listener.getNewValue());
        assertNull(listener.getOldValue());
        
        field.removeValidator(expectedValidator);
        assertEquals("Field#RemoveValidator", listener.getPropertyName());
        assertNull(listener.getNewValue());
        assertEquals(expectedValidator, listener.getOldValue());
    }
}
