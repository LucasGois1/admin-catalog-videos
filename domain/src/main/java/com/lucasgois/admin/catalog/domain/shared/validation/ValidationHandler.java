package com.lucasgois.admin.catalog.domain.shared.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(DomainError error);

    ValidationHandler append(ValidationHandler validationHandler);

    ValidationHandler validate(Validation validation);

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    List<DomainError> getErrors();

    interface Validation {
        void validate();
    }
}
