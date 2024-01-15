package com.task.manager.modules.category.application.delete;

import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryID;

public record DeleteCategoryOutput(
        CategoryID id
) {
    public static DeleteCategoryOutput from(final Category aCategory){
        return new DeleteCategoryOutput(
                aCategory.getId()
        );
    }
}
