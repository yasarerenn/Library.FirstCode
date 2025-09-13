package com.turkcell.Library.service;

import com.turkcell.Library.dto.book.request.CreateBookRequest;
import com.turkcell.Library.dto.book.request.UpdateBookRequest;
import com.turkcell.Library.dto.book.response.CreatedBookResponse;
import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.entity.Status;
import com.turkcell.Library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
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

    public CreatedBookResponse createBook(@Valid CreateBookRequest createBookRequest) {
        // Validate category and status exist
        categoryService.getCategoryById(createBookRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

        statusService.getStatusById(createBookRequest.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));

        Book book = convertToBook(createBookRequest);

        Book newCreatedBook = bookRepository.save(book);
        return convertToBookResponse(newCreatedBook);
    }

    public Book updateBook(int id, @Valid UpdateBookRequest bookDetails) {
        Book requestBook = convertToBook(bookDetails);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(requestBook.getTitle());
        book.setAuthor(requestBook.getAuthor());
        book.setPublishDate(requestBook.getPublishDate());

        Category category = categoryService.getCategoryById(bookDetails.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);

        Status status = statusService.getStatusById(bookDetails.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        book.setStatus(status);

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

    public List<Book> getBooksByCategoryName(String categoryName) {
        return bookRepository.searchByCategory(categoryName);
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
