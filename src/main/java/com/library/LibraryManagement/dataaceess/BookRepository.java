package com.library.LibraryManagement.dataaceess;

import com.library.LibraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
