package com.turkcell.Library.dto.book.response;

import java.util.Date;

public class CreatedBookResponse {
    private Integer id;
    private String title;
    private String author;
    private int categoryId;
    private String categoryName;
    private int statusId;
    private String statusName;
    private Date publishDate;

    public CreatedBookResponse() {}

    public CreatedBookResponse(Integer id, String title, String author, int categoryId, String categoryName,
                        int statusId, String statusName, Date publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.statusId = statusId;
        this.statusName = statusName;
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}