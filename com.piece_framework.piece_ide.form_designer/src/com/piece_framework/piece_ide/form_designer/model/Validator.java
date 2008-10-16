// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Validator {
    public final class Rule {
        private String fKey;
        private Class fValueClass;
        private Object fValue;

        private Rule(final String key, final Class valueClass) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (valueClass == null) {
                throw new NullPointerException();
            }

            fKey = key;
            fValueClass = valueClass;
        }
        
        public String getKey() {
            return fKey;
        }

        public Class getValueClass() {
            return fValueClass;
        }

        public Object getValue() {
            return fValue;
        }

        public void setValue(Object value) {
            if (value == null){
                fValue = null;
                return;
            }

            try {
                Constructor constructor = fValueClass.getConstructor(String.class);
                fValue = constructor.newInstance(value.toString());
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private String fName;
    private String fDescription;
    private String fMessage;
    private List<Rule> fRules;

    Validator(final String name, final String description) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (description == null) {
            throw new NullPointerException();
        }

        fName = name;
        fDescription = description;
        fMessage = "";
        fRules = new ArrayList<Rule>();
    }

    public String getName() {
        return fName;
    }

    public String getDescription() {
        return fDescription;
    }

    public String getMessage() {
        return fMessage;
    }

    public void setMessage(final String message) {
        if (message == null) {
            throw new NullPointerException();
        }
        fMessage = message;
    }

    public List<Rule> getRules() {
        return Collections.unmodifiableList(fRules);
    }

    void addRule(final String key, final Class valueClass) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (valueClass == null) {
            throw new NullPointerException();
        }

        fRules.add(new Rule(key, valueClass));
    }
}
