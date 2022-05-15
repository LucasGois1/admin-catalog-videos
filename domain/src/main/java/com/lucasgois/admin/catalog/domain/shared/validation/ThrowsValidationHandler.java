package com.lucasgois.admin.catalog.domain.shared.validation;

import com.lucasgois.admin.catalog.domain.shared.exceptions.DomainException;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final DomainError error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(final ValidationHandler validationHandler) {
        throw DomainException.with(validationHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        try {
            validation.validate();
        } catch (final Exception e) {
            throw DomainException.with(new DomainError(e.getMessage()));
        }

        return this;
    }

    @Override
    public List<DomainError> getErrors() {
        return List.of();
    }
}
