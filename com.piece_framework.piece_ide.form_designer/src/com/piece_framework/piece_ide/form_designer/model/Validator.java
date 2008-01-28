// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Validator {
    public static final Validator COMPARE;
    public static final Validator DATE;
    public static final Validator EMAIL;

    private static final List<Validator> fValidators;

    static {
        fValidators = new ArrayList<Validator>();

        COMPARE = new Validator("Compare", "2 つの値を比較");
        COMPARE.setRule(COMPARE.new Rule("to", String.class));
        fValidators.add(COMPARE);

        DATE = new Validator("Date", "日付");
        DATE.setRule(DATE.new Rule("pattern", String.class));
        DATE.setRule(DATE.new Rule("patternYearPosition", Integer.class));
        DATE.setRule(DATE.new Rule("patternMonthPosition", Integer.class));
        DATE.setRule(DATE.new Rule("patternDayPostion", Integer.class));
        fValidators.add(DATE);

        EMAIL = new Validator("Email", "メールアドレス");
        EMAIL.setRule(EMAIL.new Rule("allowDotBeforeAtmark", Boolean.class));
        fValidators.add(EMAIL);
    }

    public static final List<Validator> getValidators() {
        return Collections.unmodifiableList(fValidators);
    }

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
        
        private Rule(final Rule rule) {
            if (rule == null) {
                throw new NullPointerException();
            }
            
            fKey = rule.fKey;
            fValueClass = rule.fValueClass;
            fValue = rule.fValue;
        }
        
        public String getKey() {
            return fKey;
        }
        
        public Object getValue() {
            return fValue;
        }

        public void setValue(Object value) {
            if (value == null) {
                throw new NullPointerException();
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

    private final String fName;
    private final String fDescription;
    private final List<Rule> fRules;

    private Validator(final String name, final String description) {
        if (name == null) {
            throw new NullPointerException();
        }
        
        fName = name;
        if (description != null) {
            fDescription = description;
        } else {
            fDescription = "";
        }
        fRules = new ArrayList<Rule>();
    }

    Validator(final Validator validator) {
        if (validator == null) {
            throw new NullPointerException();
        }
        fName = validator.fName;
        fDescription = validator.fDescription;
        fRules = new ArrayList<Rule>();
        for (Rule rule : validator.fRules) {
            fRules.add(new Rule(rule));
        }
    }
    
    public String getName() {
        return fName;
    }

    public String getDescription() {
        return fDescription;
    }

    public List<Rule> getRules() {
        return Collections.unmodifiableList(fRules);
    }

    private void setRule(final Rule rule) {
        if (rule == null) {
            throw new NullPointerException();
        }

        fRules.add(rule);
    }
}
