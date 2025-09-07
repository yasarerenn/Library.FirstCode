package com.turkcell.Library.controller;

import com.turkcell.Library.dto.category.request.CreateCategoryRequest;
import com.turkcell.Library.dto.category.request.UpdateCategoryRequest;
import com.turkcell.Library.dto.category.response.CreatedCategoryResponse;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedCategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CreatedCategoryResponse> response = categories.stream()
                .map(this::convertToCategoryResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedCategoryResponse> getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(convertToCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedCategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        try {
            Category category = convertToCategory(request);
            Category savedCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToCategoryResponse(savedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedCategoryResponse> updateCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        try {
            Category categoryDetails = convertToCategory(request);
            Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
            return ResponseEntity.ok(convertToCategoryResponse(updatedCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private CreatedCategoryResponse convertToCategoryResponse(Category category) {
        return new CreatedCategoryResponse(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }

    private Category convertToCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        return category;
    }

    private Category convertToCategory(UpdateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        return category;
    }
}
