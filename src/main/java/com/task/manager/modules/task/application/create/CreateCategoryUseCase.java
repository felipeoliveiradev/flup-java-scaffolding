package com.task.manager.modules.category.application.create;

import com.task.manager.modules.required.helpers.UseCase;
import com.task.manager.modules.validation.handlers.Notification;
import io.vavr.control.Either;

public abstract  class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
