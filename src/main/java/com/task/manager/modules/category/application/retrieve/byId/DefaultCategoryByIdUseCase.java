package com.task.manager.modules.category.application.retrieve.byId;


import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.category.domain.CategoryID;
import com.task.manager.modules.validation.Exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultCategoryByIdUseCase extends CategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIn) {
        final var aCategoryId = CategoryID.from(anIn);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(NotFound(aCategoryId));
    }

    private static Supplier<NotFoundException> NotFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
