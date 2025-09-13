package com.turkcell.Library.repository;

import com.turkcell.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByCategory_CategoryId(int categoryId);

    @Query("SELECT b FROM Book b WHERE b.status.status_id = :statusId")
    List<Book> findByStatusId(@Param("statusId") int statusId);

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);

    @Query(value = "select * from book b join category c on b.category = c.category_id " +
            "where LOWER(c.category_name) LIKE LOWER(:categoryName)", nativeQuery = true)
    List<Book> searchByCategory(String categoryName);
}
