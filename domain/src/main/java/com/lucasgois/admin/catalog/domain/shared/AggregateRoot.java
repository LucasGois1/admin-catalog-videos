package com.lucasgois.admin.catalog.domain.shared;

import com.lucasgois.admin.catalog.domain.shared.exceptions.DomainException;
import com.lucasgois.admin.catalog.domain.shared.validation.DomainError;
import com.lucasgois.admin.catalog.domain.shared.validation.ValidationHandler;

import java.util.List;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {
    protected AggregateRoot(final ID id) {
        super(id);
    }

    @Override
    public void validate(ValidationHandler validationHandler) {
        throw DomainException.with(List.of(new DomainError("AggregateRoot.validate() is not implemented")));
    }
}
