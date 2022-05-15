package com.lucasgois.admin.catalog.domain.shared.validation;

public abstract class Validator {
    protected final ValidationHandler handler;

    protected Validator(final ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();
}
