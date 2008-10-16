// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UsableValidator {
    private static final UsableValidator COMPARE;
    private static final UsableValidator DATE;
    private static final UsableValidator EMAIL;

    private static final List<UsableValidator> fUsableValidators;

    static {
        fUsableValidators = new ArrayList<UsableValidator>();

        COMPARE = new UsableValidator("Compare", "2 つの値を比較");
        COMPARE.fRules.put("to", String.class);
        fUsableValidators.add(COMPARE);

        DATE = new UsableValidator("Date", "日付");
        DATE.fRules.put("pattern", String.class);
        DATE.fRules.put("patternYearPosition", Integer.class);
        DATE.fRules.put("patternMonthPosition", Integer.class);
        DATE.fRules.put("patternDayPostion", Integer.class);
        fUsableValidators.add(DATE);

        EMAIL = new UsableValidator("Email", "メールアドレス");
        EMAIL.fRules.put("allowDotBeforeAtmark", Boolean.class);
        fUsableValidators.add(EMAIL);
    }

    public static final List<UsableValidator> getList() {
        return Collections.unmodifiableList(fUsableValidators);
    }
    
    private String fName;
    private String fDescrption;
    private Map<String, Class> fRules;
    
    private UsableValidator(final String name, final String descrption) {
        fName = name;
        fDescrption = descrption;
        fRules = new HashMap<String, Class>();
    }
    
    public String getName() {
        return fName;
    }

    public String getDescrption() {
        return fDescrption;
    }
    
    public Validator create() {
        Validator validator = new Validator(fName, fDescrption);
        for (String key : fRules.keySet()) {
            validator.addRule(key, fRules.get(key));
        }
        return validator;
    }
}
