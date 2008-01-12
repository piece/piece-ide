// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public final class Field {
    private PropertyChangeSupport fSupport;

    private String fName;
    private String fDescription;
    private boolean fRequired;
    private boolean fForceValidation;
    private String fMessage;

    public Field(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        fName = name;
        fDescription = "";
        fRequired = false;
        fForceValidation = false;
        fMessage = "";
        fSupport = new PropertyChangeSupport(this);
    }

    public String getName() {
        return fName;
    }

    public void setName(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        String oldValue = fName;
        fName = name;
        firePropertyChange("Field#Name", oldValue, fName); //$NON-NLS-1$
    }

    public String getDescription() {
        return fDescription;
    }

    public void setDescription(final String description) {
        if (description == null) {
            throw new NullPointerException();
        }
        String oldValue = fDescription;
        fDescription = description;
        firePropertyChange("Field#Description", oldValue, fDescription); //$NON-NLS-1$
    }

    public boolean isRequired() {
        return fRequired;
    }

    public void setRequired(final boolean required) {
        fRequired = required;
    }

    public boolean isForceValidation() {
        return fForceValidation;
    }

    public void setForceValidation(final boolean forceValidation) {
        fForceValidation = forceValidation;
    }

    public String getMessage() {
        return fMessage;
    }

    public void setMessage(final String message) {
        if (fMessage == null) {
            throw new NullPointerException();
        }
        String oldValue = fMessage;
        fMessage = message;
        firePropertyChange("Field#Message", oldValue, fMessage); //$NON-NLS-1$
    }

    protected void firePropertyChange(String name, 
                                    Object oldValue, 
                                    Object newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    protected void firePropertyChange(String name, 
                                    boolean oldValue, 
                                    boolean newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        fSupport.addPropertyChangeListener(listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return fSupport.getPropertyChangeListeners();
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        fSupport.removePropertyChangeListener(listener);
    }
    
    public static final void swap(Field field1, Field field2) {
        Field tmp = new Field(field1.getName());
        copy(field1, tmp);
        copy(field2, field1);
        copy(tmp, field2);

        field1.firePropertyChange("Field#swap", null, null);
        field2.firePropertyChange("Field#swap", null, null);
    }

    private static final void copy(Field source, Field destnation) {
        destnation.fName = source.fName;
        destnation.fDescription = source.fDescription;
        destnation.fRequired = source.fRequired;
        destnation.fForceValidation = source.fForceValidation;
        destnation.fMessage = source.fMessage;
        destnation.fSupport = new PropertyChangeSupport(destnation);
        for (PropertyChangeListener listener : source.getPropertyChangeListeners()) {
            destnation.addPropertyChangeListener(listener);
        }
    }
}
