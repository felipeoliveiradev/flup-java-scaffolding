package com.task.manager.modules.category.domain;

import com.task.manager.modules.validation.Error;
import com.task.manager.modules.validation.Validator;
import com.task.manager.modules.validation.handlers.ValidationHandler;

public class CategoryValidator  extends Validator {
    private final Category category;
    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name =  this.category.getName();
        if(name == null){
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }
        if(name.isBlank()){
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }
        final var length = name.trim().length();
        if(length < 3 || length > 255){
            this.validationHandler().append(new Error("'name' should must be between 3 and 255 characters"));
            return;
        }

    }
}
