package com.task.manager.modules.category.domain;


import com.task.manager.modules.required.helpers.AggregateRoot;
import com.task.manager.modules.validation.handlers.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {
    private String post;
    private String image;
    private String userId;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
                   final CategoryID anId,
                   final String postField,
                   final String imageField,
                   final String userIdField,
                   final boolean isActive,
                   final Instant createdDateField,
                   final Instant updatedDateField,
                   final Instant deletedDateField
    ) {
        super(anId);
        this.post = postField;
        this.image = imageField;
        this.userId = userIdField;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(createdDateField, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedDateField, "'updatedAt' should not be null");
        this.deletedAt = deletedDateField;
    }



    public static Category newCategory(
        final String postField,
        final String imageField,
        final String userIdField,
        final boolean isActive
        ){
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(
            id, 
            postField,
            imageField,
            userIdField,
            isActive, 
            now, 
            now, 
            deletedAt
        );
    }

    public static Category with(
            final Category aCategory
    ) {
        return with(
                aCategory.id,
                aCategory.post,
                aCategory.image,
                aCategory.userId,
                aCategory.active,
                aCategory.createdAt,
                aCategory.updatedAt,
                aCategory.deletedAt
        );
    }

    public static Category with(
            final CategoryID anId,
            final String post,
            final String image,
            final String userId,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Category(
                anId,
                post,
                image,
                userId,
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

    public void deactivate(){
        if(getDeletedAt() == null){
            this.deletedAt = Instant.now();
        }
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public void activate(){

        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
    }


    public Category update( 
        final String postField,
        final String imageField,
        final String userIdField,
        final boolean isActive){

        if(isActive){
            this.activate();
        } else {
            this.deactivate();
        }
            this.post = postField;
            this.image = imageField;
            this.userId = userIdField;
        this.updatedAt = Instant.now();
        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getPost(){
        return post;
    }
    public String getImage(){
        return image;
    }
    public String getUserId(){
        return userId;
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
