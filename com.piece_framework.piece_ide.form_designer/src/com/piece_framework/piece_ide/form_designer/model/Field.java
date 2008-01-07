// $Id$
package com.piece_framework.piece_ide.form_designer.model;

public final class Field {
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
    }

    public String getName() {
        return fName;
    }

    public void setName(final String name) {
        fName = name;
    }

    public String getDescription() {
        return fDescription;
    }

    public void setDescription(final String description) {
        fDescription = description;
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
        fMessage = message;
    }
}
