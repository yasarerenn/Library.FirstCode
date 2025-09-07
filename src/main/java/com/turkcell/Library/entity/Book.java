package com.turkcell.Library.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "book", schema = "lb2")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "tittle")
    private String title;

    @Column(name = "author")
    private String author;

    @ManyToOne()
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "status", nullable = false)
    private Status status;

    @Column(name = "publish_date, nullable = false")
    private Date publishDate;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
