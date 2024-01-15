package com.task.manager.modules.category.application.update;

import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.category.domain.CategoryID;
import com.task.manager.modules.validation.Exceptions.DomainException;
import com.task.manager.modules.validation.Exceptions.NotFoundException;
import com.task.manager.modules.validation.handlers.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final CategoryID anId = CategoryID.from(aCommand.id());
        final String aName = aCommand.name();
        final String aDescription = aCommand.description();
        final boolean isActive = aCommand.isActive();

        final Category aCategory = this.categoryGateway.findById(anId)
                .orElseThrow(NotFound(anId));

        final Notification notificationHandler = Notification.create();
        aCategory.update(aName, aDescription, isActive).validate(notificationHandler);

        return notificationHandler.hasError() ? Left(notificationHandler) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory){
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private static Supplier<DomainException> NotFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, CategoryID.from(anId.getValue()));
    }
}
