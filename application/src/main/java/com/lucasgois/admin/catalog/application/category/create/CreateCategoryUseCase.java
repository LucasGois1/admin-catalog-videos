package com.lucasgois.admin.catalog.application.category.create;

import com.lucasgois.admin.catalog.application.UseCase;
import com.lucasgois.admin.catalog.domain.category.Category;
import com.lucasgois.admin.catalog.domain.category.CategoryGateway;
import com.lucasgois.admin.catalog.domain.shared.validation.ThrowsValidationHandler;

import java.util.Objects;

public class CreateCategoryUseCase extends UseCase<CategoryInput, CategoryOutput> {

    private final CategoryGateway gateway;

    public CreateCategoryUseCase(CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    public CategoryOutput execute(CategoryInput input) {
        final Category category = Category.newCategory(
                input.name(),
                input.description(),
                input.active()
        );

        category.validate(new ThrowsValidationHandler());

        return CategoryOutput.of(this.gateway.create(category));
    }
}
