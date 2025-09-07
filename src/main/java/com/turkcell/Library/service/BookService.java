package com.turkcell.Library.service;

import com.turkcell.Library.dto.book.BookForAddDto;
import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Category;
import com.turkcell.Library.entity.Status;
import com.turkcell.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //IoC'e bean olarak ekle.
public class BookService
{
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository)
        {
        this.bookRepository = bookRepository;
        }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book add(BookForAddDto bookForAddDto)
    {
        Book book = new Book();
        book.setTitle(bookForAddDto.getTitle());
        book.setAuthor(bookForAddDto.getAuthor());
        book.setPublishDate(bookForAddDto.getPublish_date());

        Category category = new Category();
        category.setCategoryId(bookForAddDto.getCategory());

        book.setCategory(category);

        Status status = new Status();
        status.setStatus_id(bookForAddDto.getStatus());
        book.setStatus(status);


        return this.bookRepository.save(book);

    }
}
