package com.task.manager.modules.category.infra;

import com.task.manager.modules.category.application.retrieve.byId.CategoryOutput;
import com.task.manager.modules.category.application.retrieve.list.ListCategoriesOutput;
import com.task.manager.modules.category.infra.http.response.CategoryResponse;
import com.task.manager.modules.category.infra.http.response.ListCategoriesResponse;

public interface CategoryAPIPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ListCategoriesResponse present(final ListCategoriesOutput output) {
        return new ListCategoriesResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.updatedAt(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
