package com.turkcell.Library.controller;

import com.turkcell.Library.dto.book.request.CreateBookRequest;
import com.turkcell.Library.dto.book.request.UpdateBookRequest;
import com.turkcell.Library.dto.book.response.CreatedBookResponse;
import com.turkcell.Library.entity.*;
import com.turkcell.Library.service.BookService;
import com.turkcell.Library.service.CategoryService;
import com.turkcell.Library.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final StatusService statusService;

    public BookController(BookService bookService, CategoryService categoryService, StatusService statusService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedBookResponse>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<CreatedBookResponse> response = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedBookResponse> getBookById(@PathVariable int id) {
        return bookService.getBookById(id)
                .map(book -> ResponseEntity.ok(convertToBookResponse(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedBookResponse> createBook(@RequestBody CreateBookRequest request) {
        try {
            CreatedBookResponse savedBook = bookService.createBook(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedBookResponse> updateBook(@PathVariable int id, @RequestBody UpdateBookRequest request) {
        try {
            Book updatedBook = bookService.updateBook(id, request);
            return ResponseEntity.ok(convertToBookResponse(updatedBook));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CreatedBookResponse>> getBooksByCategory(@PathVariable int categoryId) {
        List<Book> books = bookService.getBooksByCategory(categoryId);
        List<CreatedBookResponse> response = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<List<CreatedBookResponse>> getBooksByCategoryName(@PathVariable String categoryName) {
        List<Book> books = bookService.getBooksByCategoryName(categoryName);
        List<CreatedBookResponse> response = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<CreatedBookResponse>> getBooksByStatus(@PathVariable int statusId) {
        List<Book> books = bookService.getBooksByStatus(statusId);
        List<CreatedBookResponse> response = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreatedBookResponse>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<Book> books;
        if (title != null && !title.isEmpty()) {
            books = bookService.searchBooksByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            books = bookService.searchBooksByAuthor(author);
        } else {
            books = bookService.getAllBooks();
        }

        List<CreatedBookResponse> response = books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private CreatedBookResponse convertToBookResponse(Book book) {
        return new CreatedBookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getCategoryId(),
                book.getCategory().getCategoryName(),
                book.getStatus().getStatus_id(),
                book.getStatus().getStatus_name(),
                book.getPublishDate()
        );
    }

    private Book convertToBook(CreateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublishDate(request.getPublishDate());

        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        book.setCategory(category);

        Status status = new Status();
        status.setStatus_id(request.getStatusId());
        book.setStatus(status);

        return book;
    }

    private Book convertToBook(UpdateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublishDate(request.getPublishDate());

        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        book.setCategory(category);

        Status status = new Status();
        status.setStatus_id(request.getStatusId());
        book.setStatus(status);

        return book;
    }
}
