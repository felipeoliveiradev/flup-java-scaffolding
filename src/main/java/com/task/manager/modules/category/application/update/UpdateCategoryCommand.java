package com.task.manager.modules.category.application.update;

public record UpdateCategoryCommand(
        String id,
        String name,
        String description,
        boolean isActive
) {
    public static UpdateCategoryCommand with(
            final String aID,
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new UpdateCategoryCommand(aID, aName, aDescription, isActive);
    }
}
