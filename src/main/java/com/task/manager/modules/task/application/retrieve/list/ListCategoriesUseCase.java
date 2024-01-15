package com.task.manager.modules.category.application.retrieve.list;

import com.task.manager.modules.required.helpers.UseCase;
import com.task.manager.modules.required.pagination.Pagination;
import com.task.manager.modules.required.pagination.SearchQuery;

public abstract class ListCategoriesUseCase extends UseCase<SearchQuery, Pagination<ListCategoriesOutput>> {

}
