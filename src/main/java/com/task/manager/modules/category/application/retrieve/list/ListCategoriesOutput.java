package com.task.manager.modules.category.application.retrieve.list;


import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryID;

import java.time.Instant;

public record ListCategoriesOutput(
        CategoryID id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static ListCategoriesOutput from(final Category aCategory){
        return new ListCategoriesOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getUpdatedAt(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt()
        );
    }
}
