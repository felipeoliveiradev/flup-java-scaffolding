package com.task.manager.modules.validation;

import com.task.manager.modules.Category.application.create.CreateCategoryOutput;
import com.task.manager.modules.validation.Exceptions.DomainException;
import com.task.manager.modules.validation.handlers.Notification;
import com.task.manager.modules.validation.handlers.ValidationHandler;
import com.task.manager.modules.validation.utils.CpfCnpjValidate;
import io.vavr.control.Either;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationField {

    private String field;
    private String input;
    private ArrayList<Error> validation;

    public ValidationField(
            final String field,
            final String input
    ) {
        this.field = field;
        this.input = input;
        this.validation = new ArrayList<>();
    }

    public ValidationField Null() throws Exception {
        if (this.input == null) {
            throw new Exception("'%s' should not be null".formatted(this.field));
        }
        return this;
    }

    public ValidationField Empty() {
        if (this.input != null && this.input.isBlank()) {
            this.validation.add(new Error("'%s' should not be empty".formatted(this.field)));
        }
        return this;
    }

    public ValidationField Custom(final String regex, final String message) {
        if (!this.input.matches(regex)) {
            this.validation.add(new Error(message));
        }
        return this;
    }

    public ValidationField Name() {
        if (!this.input.matches("[A-Z]([a-z]+|\\s[a-z]+)")) {
            this.validation.add(new Error("Please fill the %s with Name valid".formatted(this.field)));
        }
        return this;
    }

    public ValidationField Url() {
        if (!this.input.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            this.validation.add(new Error("Please fill the %s with Url valid".formatted(this.field)));
        }
        return this;
    }

    public ValidationField Email() {
        if (!this.input.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            this.validation.add(new Error("Please fill the %s with Email valid".formatted(this.field)));
        }
        return this;
    }


    public ValidationField Document(final String type) {
        if(type == "CPF"){
            if (!CpfCnpjValidate.isCpfValid(this.input)) {
                this.validation.add(new Error("Please fill the %s with CPF valid".formatted(this.field)));
            }
        }

        if(type == "CNPJ"){
            if (!CpfCnpjValidate.isCnpjValid(this.input)) {
                this.validation.add(new Error("Please fill the %s with CNPJ valid".formatted(this.field)));
            }
        }

        if(type == "CPF&CNPJ"){
            if (!CpfCnpjValidate.isCnpjCpfValid(this.input)) {
                this.validation.add(new Error("Please fill the %s with CPF or CNPJ valid".formatted(this.field)));
            }
        }
        return this;
    }

    public ValidationField Max(final int maxLength) {
        final var length = this.input.trim().length();
        if (length > maxLength) {
            this.validation.add(new Error("'%s' should be max %s ".formatted(this.field, maxLength)));
        }
        return this;
    }

    public ValidationField Min(final int minLength) {
        final var length = this.input.trim().length();
        if (length < minLength) {
            this.validation.add(new Error("'%s' should be min %s ".formatted(this.field, minLength)));
        }
        return this;
    }

    public List<Error> validate() {
        return this.validation;
    }
}
