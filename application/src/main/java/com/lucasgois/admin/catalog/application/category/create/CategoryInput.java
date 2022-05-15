package com.lucasgois.admin.catalog.application.category.create;

import com.lucasgois.admin.catalog.domain.category.Category;

public record CategoryInput(
    String name,
    String description,
    boolean active
) {
    public static Category with(
        String name,
        String description,
        boolean active
    ) {
        return Category.newCategory(name, description, active);
    }
}
