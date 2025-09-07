package com.turkcell.Library.repository;

import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{
}
