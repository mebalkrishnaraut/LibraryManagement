package com.library.LibraryManagement.service.impl;

import com.library.LibraryManagement.dataaceess.BookRepository;
import com.library.LibraryManagement.dto.BookRequest;
import com.library.LibraryManagement.dto.BookResponse;
import com.library.LibraryManagement.exception.BookNotFoundException;
import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public void createBook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        int diff = request.getTotalCopies() - book.getTotalCopies();
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(book.getAvailableCopies() + diff);
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private BookResponse toResponse(Book book) {
        BookResponse res = new BookResponse();
        res.setId(book.getId());
        res.setTitle(book.getTitle());
        res.setAuthor(book.getAuthor());
        res.setIsbn(book.getIsbn());
        res.setTotalCopies(book.getTotalCopies());
        res.setAvailableCopies(book.getAvailableCopies());
        return res;
    }
}
