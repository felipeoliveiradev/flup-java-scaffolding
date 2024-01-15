package com.task.manager.modules.category.application.create;

import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.validation.handlers.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateCategoryUseCase  extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notificationHandler = Notification.create();

        final var aCategory = Category.newCategory(aName,aDescription,isActive);
        aCategory.validate(notificationHandler);

        return notificationHandler.hasError() ? Left(notificationHandler) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory){
        return Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
