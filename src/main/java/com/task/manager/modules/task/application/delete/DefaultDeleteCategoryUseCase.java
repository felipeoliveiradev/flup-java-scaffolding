package com.task.manager.modules.category.application.delete;

import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.category.domain.CategoryID;
import com.task.manager.modules.validation.Error;
import com.task.manager.modules.validation.Exceptions.DomainException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    private CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public void execute(final String anIn) {
        final var aCategoryId = CategoryID.from(anIn);
        this.categoryGateway.findById(CategoryID.from(anIn)).map(DeleteCategoryOutput::from).orElseThrow(NotFound(aCategoryId));
        this.categoryGateway.deleteById(CategoryID.from(anIn));
    }
    private static Supplier<DomainException> NotFound(final CategoryID anId) {
        return () -> DomainException.with(new Error("Category with ID %s was not found".formatted(anId.getValue())));
    }
}
