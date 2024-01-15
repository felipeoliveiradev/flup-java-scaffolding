package com.task.manager.modules.category.infra.frameworks.spring;

import com.task.manager.modules.category.infra.CategoryDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDataEntity, String> {
    Page<CategoryDataEntity> findAll(Specification<CategoryDataEntity> whereClause, Pageable page);
}
