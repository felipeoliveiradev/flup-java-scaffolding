package com.task.manager.modules.category.infra.frameworks.spring;


import com.task.manager.modules.category.infra.http.request.CreateCategoryRequest;
import com.task.manager.modules.category.infra.http.request.UpdateCategoryRequest;
import com.task.manager.modules.category.infra.http.response.CategoryResponse;
import com.task.manager.modules.category.infra.http.response.ListCategoriesResponse;
import com.task.manager.modules.required.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping( value = "categories")
@Tag(name = "Cagegories")
public interface CategoryAPI {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found a category"),
            @ApiResponse(responseCode = "422", description = "Invalid params"),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error")
    })
    ResponseEntity<?> createCategory(@RequestBody @Valid CreateCategoryRequest input);

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Listed successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found a category"),
            @ApiResponse(responseCode = "422", description = "Invalid params"),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error")
    })
    Pagination<ListCategoriesResponse> listCategories(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a category by it`s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found a category"),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error")
    })
    CategoryResponse getById(@PathVariable(name =  "id") String id);


    @PutMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a category by it`s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found a category"),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error")
    })
    ResponseEntity<?> updateById(@PathVariable(name =  "id") String id, @RequestBody UpdateCategoryRequest input);



    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a category by it`s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error")
    })
    void deleteById(@PathVariable(name =  "id") String id);


}
