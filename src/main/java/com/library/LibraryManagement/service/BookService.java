package com.library.LibraryManagement.service;

import com.library.LibraryManagement.dto.BookRequest;
import com.library.LibraryManagement.dto.BookResponse;

import java.util.List;

public interface BookService {
    void createBook(BookRequest request);
    void updateBook(Long id, BookRequest request);
    List<BookResponse> getAllBooks();
}
