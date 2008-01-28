// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

public class FieldTest extends TestCase {
    
    private final class TestPropertyChangeListener implements PropertyChangeListener {
        private String fListenerName;
        private String fPropertyName;
        
        public TestPropertyChangeListener(final String listenerName) {
            fListenerName = listenerName;
        }
        
        public void propertyChange(PropertyChangeEvent event) {
            fPropertyName = event.getPropertyName();
        }
        
        public String getListenerName() {
            return fListenerName;
        }
        
        public String getPropertyName() {
            return fPropertyName;
        }
    }

    public void testEventShouldBeExecutedWhenChangeFieldProperty() {
        Field field = new Field("name");
        TestPropertyChangeListener listener = 
            new TestPropertyChangeListener("Field");
        field.addPropertyChangeListener(listener);

        field.setName("first_name");
        assertEquals("Field#Name", listener.getPropertyName());
        
        field.setDescription("名前");
        assertEquals("Field#Description", listener.getPropertyName());

        field.setRequired(true);
        assertEquals("Field#Required", listener.getPropertyName());

        field.setMessage("名前は必須です。");
        assertEquals("Field#Message", listener.getPropertyName());

        field.setForceValidation(true);
        assertEquals("Field#ForceValidation", listener.getPropertyName());
    }
    
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
}
