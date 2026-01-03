package com.library.LibraryManagement;

import com.library.LibraryManagement.dataaceess.BookRepository;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.service.BookService;

import com.library.LibraryManagement.service.impl.BookServiceImpl;
import com.library.api.model.BookRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookServiceImpl bookService;

    @Test
    void shouldCreateBookSuccessfully() {
        // given
        com.library.LibraryManagement.dto.BookRequest request = new com.library.LibraryManagement.dto.BookRequest();
        request.setTitle("Java 11");
        request.setAuthor("Balkrishan Raut");
        request.setTotalCopies(5);

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Java Fundamental");
        savedBook.setTotalCopies(5);
        savedBook.setAvailableCopies(5);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        // when
        bookService.createBook(request);
        Book result = bookRepository.findById(1L).orElse(null);

        // then
        assertNotNull(result);
        assertEquals(5, result.getAvailableCopies());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void shouldReturnAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book(), new Book()));

        List<Book> books = bookService.getAllBooks().stream().map(e -> new Book()).toList();

        assertEquals(2, books.size());
        verify(bookRepository).findAll();
    }

}
