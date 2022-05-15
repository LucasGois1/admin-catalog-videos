package com.lucasgois.admin.catalog.domain.category;

import com.lucasgois.admin.catalog.domain.shared.validation.DomainError;
import com.lucasgois.admin.catalog.domain.shared.validation.ValidationHandler;
import com.lucasgois.admin.catalog.domain.shared.validation.Validator;

public class CategoryValidator extends Validator {
    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler validationHandler) {
        super(validationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraint();
    }

    private void checkNameConstraint() {
        if(category.getName() == null) {
            this.handler.append(new DomainError("Category Name cannot be null"));
        }

        if(category.getName().isBlank()) {
            this.handler.append(new DomainError("Category Name cannot be empty"));
        }

        if(category.getName().trim().length() < 3) {
            this.handler.append(new DomainError("Category Name must have at least 3 characters"));
        }

        if(category.getName().trim().length() > 255) {
            this.handler.append(new DomainError("Category Name must have less than 255 characters"));
        }
    }
}
