package com.turkcell.Library.dto.category.request;

public class CreateCategoryRequest {
    private String categoryName;

    public CreateCategoryRequest() {}

    public CreateCategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
