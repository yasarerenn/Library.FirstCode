package com.turkcell.Library.dto.category.request;

public class UpdateCategoryRequest {
    private String categoryName;

    public UpdateCategoryRequest() {}

    public UpdateCategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
