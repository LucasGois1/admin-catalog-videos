package com.lucasgois.admin.catalog.domain.shared;

import com.lucasgois.admin.catalog.domain.shared.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
    protected final ID id;

    protected Entity(final ID id) {
        Objects.requireNonNull(id, "id must not be null");
        this.id = id;
    }

    public abstract void validate(ValidationHandler validationHandler);

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity<?> entity = (Entity<?>) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
