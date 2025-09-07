package com.turkcell.Library.dto.category.response;

public class CreatedCategoryResponse {
    private int categoryId;
    private String categoryName;

    public CreatedCategoryResponse() {}

    public CreatedCategoryResponse(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
