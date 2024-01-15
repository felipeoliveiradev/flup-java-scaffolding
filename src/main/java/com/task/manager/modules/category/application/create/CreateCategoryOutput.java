package com.task.manager.modules.category.application.create;


import com.task.manager.modules.category.domain.Category;

public record CreateCategoryOutput(
        String id
) {
    public static CreateCategoryOutput from(final String anId){
        return new CreateCategoryOutput(anId);
    }

    public static CreateCategoryOutput from(final Category aCategory){
        return new CreateCategoryOutput(aCategory.getId().getValue());
    }
}
