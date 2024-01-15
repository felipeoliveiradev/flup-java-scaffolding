package com.task.manager.modules.category.application.retrieve.list;


import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.required.pagination.Pagination;
import com.task.manager.modules.required.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }
    @Override
    public Pagination<ListCategoriesOutput> execute(final SearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery).map(ListCategoriesOutput::from);
    }

}
