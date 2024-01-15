package com.task.manager.modules.category.application.update;

import com.task.manager.modules.required.helpers.UseCase;
import com.task.manager.modules.validation.handlers.Notification;
import io.vavr.control.Either;

public abstract  class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {

}
