package com.library.LibraryManagement.controller;

import com.library.LibraryManagement.dto.BookResponse;
import com.library.LibraryManagement.mapper.LibraryMapper;
import com.library.LibraryManagement.service.BookService;
import com.library.api.BooksApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController implements BooksApi {
    @Autowired
    BookService bookService;


    @Override
    public ResponseEntity<Void> createBook(com.library.api.model.BookRequest bookRequest) {
        bookService.createBook(LibraryMapper.toDto(bookRequest));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<com.library.api.model.BookResponse>> listBooks() {
        List<BookResponse> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks.stream().map(LibraryMapper::fromDto).toList());
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, com.library.api.model.BookRequest bookRequest) {
        bookService.updateBook(id, LibraryMapper.toDto(bookRequest));
        return ResponseEntity.ok().build();
    }
}
