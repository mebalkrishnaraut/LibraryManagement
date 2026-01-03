package com.library.LibraryManagement.service.impl;

import com.library.LibraryManagement.dataaceess.BookRepository;
import com.library.LibraryManagement.dataaceess.BorrowRepository;
import com.library.LibraryManagement.dataaceess.MemberRepository;
import com.library.LibraryManagement.dto.BorrowedBookResponse;
import com.library.LibraryManagement.exception.*;
import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.model.BorrowTransaction;
import com.library.LibraryManagement.model.Member;
import com.library.LibraryManagement.service.BorrowingService;

import com.library.api.model.BorrowRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowingServiceImpl implements BorrowingService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BorrowRepository borrowRepository;


    public void borrowBook(BorrowRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BookNotFoundException(request.getBookId()));
        if (book.getAvailableCopies() <= 0) {
            throw new BookOutOfStockException(book.getTitle());
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowTransaction transaction = new BorrowTransaction();
        transaction.setBook(book);
        transaction.setBorrowedAt(LocalDateTime.now());
        transaction.setMember(member);
        borrowRepository.save(transaction);
    }

    @Override
    public void borrowBook(com.library.LibraryManagement.dto.BorrowRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BookNotFoundException(request.getBookId()));
        if (book.getAvailableCopies() <= 0) {
            throw new BookOutOfStockException(book.getTitle());
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowTransaction transaction = new BorrowTransaction();
        transaction.setBook(book);
        transaction.setMember(member);
        transaction.setBorrowedAt(LocalDateTime.now());
        borrowRepository.save(transaction);
    }

    @Override
    public void returnBook(Long transactionId) {
        BorrowTransaction transaction = borrowRepository.findById(transactionId)
                .orElseThrow(() -> new BorrowTransactionNotFoundException(transactionId));

        if (transaction.getReturnedAt() != null) {
            throw new BookAlreadyReturnException(transactionId);
        }

        transaction.setReturnedAt(LocalDateTime.now());
        borrowRepository.save(transaction);

        Book book = transaction.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

    @Override
    public List<BorrowedBookResponse> getBorrowedBooks(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        return borrowRepository.findByMemberAndReturnedAtIsNull(member)
                .stream()
                .map(t -> {
                    BorrowedBookResponse res = new BorrowedBookResponse();
                    res.setBookId(t.getBook().getId());
                    res.setBookTitle(t.getBook().getTitle());
                    res.setBorrowedAt(t.getBorrowedAt());
                    return res;
                }).collect(Collectors.toList());
    }
}
