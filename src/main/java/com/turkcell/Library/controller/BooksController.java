package com.turkcell.Library.controller;

import com.turkcell.Library.dto.book.BookForAddDto;
import com.turkcell.Library.entity.Book;
import com.turkcell.Library.repository.BookRepository;
import com.turkcell.Library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAll() {
        return bookService.getAll();
    }

    //Ekleme endpointleri ekleme sonrası durum için eklenen entity'i geri döner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //Eğer işlem başarılı ise status dön.
    public Book add(@RequestBody BookForAddDto bookDto)
    {
        return bookService.add(bookDto);

    }
}
