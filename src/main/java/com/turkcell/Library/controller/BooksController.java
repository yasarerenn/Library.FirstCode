package com.turkcell.Library.controller;

import com.turkcell.Library.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private List<Book> books = new ArrayList<Book>();

    @GetMapping()
    public List<Book> getBooks() {
        return books;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        Random random = new Random();
        book.setId(random.nextInt(100));

        books.add(book);
        return book;
    }
}
