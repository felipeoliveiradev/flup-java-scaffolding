package com.task.manager.modules.category.application.retrieve.list;


import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.required.pagination.Pagination;
import com.task.manager.modules.required.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListUseCase extends ListUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultListUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }
    @Override
    public Pagination<ListOutput> execute(final SearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery).map(ListOutput::from);
    }

}
