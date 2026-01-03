package com.library.LibraryManagement.service;





import com.library.LibraryManagement.dto.BorrowRequest;
import com.library.LibraryManagement.dto.BorrowedBookResponse;

import java.util.List;

public interface BorrowingService {

    void borrowBook(BorrowRequest request);
    void returnBook(Long transactionId);
    List<BorrowedBookResponse> getBorrowedBooks(Long memberId);
}
