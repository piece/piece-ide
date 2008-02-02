// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.util.List;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.form_designer.model.Validator.Rule;

public class UsableValidatorTest extends TestCase {
    // 使用可能バリデータ一覧のリストは変更できない
    public void testUsableValidatorListShouldNotBeAbleToChange() {
        List<UsableValidator> usalbeValidators = UsableValidator.getList();

        try {
            usalbeValidators.add(usalbeValidators.get(0));
            fail();
        } catch (UnsupportedOperationException e) {
        }

        try {
            usalbeValidators.remove(0);
            fail();
        } catch (UnsupportedOperationException e) {
        }
    }

    // 使用可能バリデータはバリデータ名と説明を持つ
    public void testUsableValidatorShouldHaveNameAndDescription() {
        for (UsableValidator usableValidator : UsableValidator.getList()) {
            assertNotNull(usableValidator.getName());
            assertNotNull(usableValidator.getDescrption());
        }        
    }

    // 使用可能バリデータからバリデータを生成できる
    public void testUsableValidatorShouldCreateValidator() {
        for (UsableValidator usableValidator : UsableValidator.getList()) {
            Validator validator = usableValidator.create();
            assertNotNull(validator);
            for (Rule rule : validator.getRules()) {
                assertNotNull(rule);
            }
        }
    }
}
