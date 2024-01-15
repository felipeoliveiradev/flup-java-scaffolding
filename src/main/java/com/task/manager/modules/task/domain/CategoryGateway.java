package com.task.manager.modules.category.domain;

import com.task.manager.modules.required.pagination.Pagination;
import com.task.manager.modules.required.pagination.SearchQuery;

import java.util.Optional;

public interface CategoryGateway {
    Category create(Category aCategory);
    void deleteById(CategoryID anID);
    Optional<Category> findById(CategoryID anID);
    Category update(Category aCategory);
    Pagination<Category> findAll(SearchQuery aQuery);

}
