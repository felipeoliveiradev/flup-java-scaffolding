package com.task.manager.modules.category.infra.frameworks.spring;

import com.task.manager.modules.category.application.create.CreateCategoryUseCase;
import com.task.manager.modules.category.application.create.DefaultCreateCategoryUseCase;
import com.task.manager.modules.category.application.delete.DefaultDeleteCategoryUseCase;
import com.task.manager.modules.category.application.delete.DeleteCategoryUseCase;
import com.task.manager.modules.category.application.retrieve.byId.CategoryByIdUseCase;
import com.task.manager.modules.category.application.retrieve.byId.DefaultCategoryByIdUseCase;
import com.task.manager.modules.category.application.retrieve.list.DefaultListCategoriesUseCase;
import com.task.manager.modules.category.application.retrieve.list.ListCategoriesUseCase;
import com.task.manager.modules.category.application.update.DefaultUpdateCategoryUseCase;
import com.task.manager.modules.category.application.update.UpdateCategoryUseCase;
import com.task.manager.modules.category.domain.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {
    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(){
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(){
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(){
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public CategoryByIdUseCase categoryByIdUseCase(){
        return new DefaultCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoryUseCase(){
        return new DefaultListCategoriesUseCase(categoryGateway);
    }
}
