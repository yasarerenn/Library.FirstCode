package com.turkcell.Library.controller;

import com.turkcell.Library.dto.category.CategoryForAddDto;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    //Ekleme endpointleri ekleme sonrası durum için eklenen entity'i geri döner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //Eğer işlem başarılı ise status dön.
    public Category add(@RequestBody CategoryForAddDto categoryDto)
    {
        return categoryService.add(categoryDto);

    }
}
