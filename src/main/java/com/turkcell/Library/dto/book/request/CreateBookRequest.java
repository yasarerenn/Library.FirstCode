package com.turkcell.Library.dto.book.request;

import java.util.Date;

public class CreateBookRequest {
    private String title;
    private String author;
    private int categoryId;
    private int statusId;
    private Date publishDate;

    public CreateBookRequest() {}

    public CreateBookRequest(String title, String author, int categoryId, int statusId, Date publishDate) {
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