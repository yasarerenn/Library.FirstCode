package com.turkcell.Library.service;

import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.entity.Status;
import com.turkcell.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final StatusService statusService;

    public BookService(BookRepository bookRepository, CategoryService categoryService, StatusService statusService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.statusService = statusService;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        // Validate category and status exist
        if (book.getCategory() != null) {
            categoryService.getCategoryById(book.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }
        if (book.getStatus() != null) {
            statusService.getStatusById(book.getStatus().getStatus_id())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
        }
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublishDate(bookDetails.getPublishDate());

        if (bookDetails.getCategory() != null) {
            Category category = categoryService.getCategoryById(bookDetails.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            book.setCategory(category);
        }

        if (bookDetails.getStatus() != null) {
            Status status = statusService.getStatusById(bookDetails.getStatus().getStatus_id())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            book.setStatus(status);
        }

        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByCategory(int categoryId) {
        return bookRepository.findByCategory_CategoryId(categoryId);
    }

    public List<Book> getBooksByStatus(int statusId) {
        return bookRepository.findByStatusId(statusId);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
}
