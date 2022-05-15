package com.lucasgois.admin.catalog.application.category.create;

import com.lucasgois.admin.catalog.domain.category.Category;
import com.lucasgois.admin.catalog.domain.category.CategoryID;

public record CategoryOutput(
    CategoryID id
) {
    public static CategoryOutput of(Category category) {
        return new CategoryOutput(category.getId());
    }
}
