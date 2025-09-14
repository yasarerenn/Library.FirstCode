package com.turkcell.Library.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    // DB’deki sütun "tittle" ama kodda "title" olarak kullanmak istiyoruz:
    @Column(name = "tittle", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    // FK sütunu book.category -> category.category_id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category") // mevcut şemanı koruyoruz
    private Category category;

    // FK sütunu book.status -> status.status (id)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "status") // mevcut şemanı koruyoruz
    private Status status;

    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date")
    private Date publishDate;

    // -- getters/setters --
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
}
