package com.turkcell.Library.dto.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UpdateBookRequest {
    @Size(min = 10, max = 50)
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Positive
    @NotBlank
    private int categoryId;
    @Positive
    @NotBlank
    private int statusId;
    @PastOrPresent
    private Date publishDate;

    public UpdateBookRequest() {}

    public UpdateBookRequest(String title, String author, int categoryId, int statusId, Date publishDate) {
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
