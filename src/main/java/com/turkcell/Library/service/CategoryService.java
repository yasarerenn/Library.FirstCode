package com.turkcell.Library.service;

import com.turkcell.Library.dto.category.CategoryForAddDto;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //IoC'e bean olarak ekle.
public class CategoryService
{
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category add(CategoryForAddDto categoryForAddDto)
    {
        Category category = new Category();
        category.setCategoryId(categoryForAddDto.getCategory_id());
        category.setCategoryName(categoryForAddDto.getCategoryName());

        return this.categoryRepository.save(category);

    }
}
