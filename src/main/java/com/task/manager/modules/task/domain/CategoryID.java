package com.task.manager.modules.category.domain;


import com.task.manager.modules.required.helpers.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;


    public CategoryID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CategoryID unique(){
        return  CategoryID.from(UUID.randomUUID());
    }

    public static  CategoryID from (final String anID){
        return new CategoryID(anID);
    }

    public static CategoryID from(final UUID anID){
        return  new CategoryID(anID.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
