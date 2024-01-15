package com.task.manager.modules.validation;

import com.task.manager.modules.validation.handlers.ValidationHandler;

public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler){
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler(){
        return this.handler;
    }
}

