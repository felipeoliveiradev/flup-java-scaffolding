package com.task.manager.modules.category.application.update;


import com.task.manager.modules.category.domain.Category;

public record UpdateCategoryOutput(
        String id
) {

    public static UpdateCategoryOutput from(final String anId){
        return new UpdateCategoryOutput(anId);
    }
    public static UpdateCategoryOutput from(final Category aCategory){
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }
}
