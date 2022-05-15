package com.lucasgois.admin.catalog.domain.shared.exceptions;

import com.lucasgois.admin.catalog.domain.shared.validation.DomainError;

import java.util.List;

public class DomainException extends NoStackTraceException {

    private final List<DomainError> errors;

    private DomainException(final String message, final List<DomainError> errors) {
        super(message);
        this.errors = errors;
    }

    public static DomainException with(final DomainError error) {
        return new DomainException(error.message(), List.of(error));
    }

    public static DomainException with(final List<DomainError> errors) {
        return new DomainException("", errors);
    }

    public List<DomainError> getErrors() {
        return errors;
    }
}
