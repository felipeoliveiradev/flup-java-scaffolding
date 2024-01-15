package com.task.manager.modules.category.infra.frameworks.spring.Databases;


import com.task.manager.modules.category.domain.Category;
import com.task.manager.modules.category.domain.CategoryGateway;
import com.task.manager.modules.category.domain.CategoryID;
import com.task.manager.modules.category.infra.CategoryDataEntity;
import com.task.manager.modules.category.infra.frameworks.spring.CategoryRepository;

import com.task.manager.modules.required.helpers.GatewayDefault;
import com.task.manager.modules.required.pagination.Pagination;
import com.task.manager.modules.required.pagination.SearchQuery;

import static com.task.manager.modules.spring.utils.SpecificationUtils.like;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.domain.Specification;
import java.util.Optional;

@Component
public class CategoryMySQLGateway extends GatewayDefault<CategoryDataEntity> implements CategoryGateway {
    private final CategoryRepository repository;



    public CategoryMySQLGateway(final CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anID) {
        final String idValue = anID.getValue();
        if(this.repository.existsById(idValue)){
            this.repository.deleteById(idValue);
        }
    }

    @Override
    public Optional<Category> findById(CategoryID anID) {
        return this.repository.findById(anID.getValue()).map(CategoryDataEntity::toAggregate);
    }

    @Override
    public Category update(final Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(final SearchQuery aQuery) {

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);


        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page(aQuery));

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CategoryDataEntity::toAggregate).toList()
        );
    }

    public Category save(final Category aCategory) {
        return this.repository.save(CategoryDataEntity.from(aCategory)).toAggregate();
    }
    private Specification<CategoryDataEntity> assembleSpecification(final String str) {
        final Specification<CategoryDataEntity> nameLike = like("name", str);
        final Specification<CategoryDataEntity> descriptionLike = like("description", str);
        return nameLike.or(descriptionLike);
    }
}
