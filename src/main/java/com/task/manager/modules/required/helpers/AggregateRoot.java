package com.task.manager.modules.required.helpers;

public abstract class AggregateRoot <ID extends Identifier> extends Entity<ID>{

    public AggregateRoot(final ID id) {
        super(id);
    }
}
