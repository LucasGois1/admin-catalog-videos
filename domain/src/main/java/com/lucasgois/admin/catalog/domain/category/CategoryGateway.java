package com.lucasgois.admin.catalog.domain.category;

import com.lucasgois.admin.catalog.domain.shared.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {
    Category create(Category category);

    Optional<Category> findById(CategoryID id);

    void deleteById(CategoryID id);

    Category update(Category category);

    Pagination<Category> findAll(CategorySearchQuery query);
}
