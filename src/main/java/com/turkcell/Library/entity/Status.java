package com.turkcell.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Not: DB’de id sütunun adının "status" olduğunu görüyorum; onu koruyoruz.
    @Column(name = "status")
    private Integer status_id;

    @Column(name = "status_name", nullable = false, unique = true)
    private String status_name;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Book> books;

    // -- getters/setters --
    public Integer getStatus_id() { return status_id; }
    public void setStatus_id(Integer status_id) { this.status_id = status_id; }

    public String getStatus_name() { return status_name; }
    public void setStatus_name(String status_name) { this.status_name = status_name; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
