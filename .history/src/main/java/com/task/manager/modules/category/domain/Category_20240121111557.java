package com.task.manager.modules.category.domain;

import com.task.manager.modules.required.helpers.AggregateRoot;
import com.task.manager.modules.validation.handlers.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {

    private String post;
    private String image;
    private String userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String postField,
            final String imageField,
            final String userIdField,
            final Instant createdAtField,
            final Instant updatedAtField,
            final Instant deletedAtField

    ) {
        super(anId);
        this.name = anName;
        this.description = anDescription;
        this.lastName = anLastName;
        this.active = IsActive;
        this.createdAt = Objects.requireNonNull(anCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(anUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = anDeleteDate;
    }

    public static Category newCategory(final String aName, final String aDescription, final String lastName,
            final boolean isActive) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(id, aName, aDescription, lastName, isActive, now, now, deletedAt);
    }

    public static Category with(
            final Category aCategory) {
        return with(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.description,
                aCategory.lastName,
                aCategory.active,
                aCategory.createdAt,
                aCategory.updatedAt,
                aCategory.deletedAt);
    }

    public static Category with(
            final CategoryID anId,
            final String name,
            final String description,
            final String lastName,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        return new Category(
                anId,
                name,
                description,
                lastName,
                active,
                createdAt,
                updatedAt,
                deletedAt

        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public void deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public void activate() {

        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public Category update(final String aName, final String aDescription, final boolean isActive) {

        if (isActive) {
            this.activate();
        } else {
            this.deactivate();
        }

        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();

        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
