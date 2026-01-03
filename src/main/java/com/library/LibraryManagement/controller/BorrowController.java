package com.library.LibraryManagement.controller;

import com.library.LibraryManagement.dto.BorrowedBookResponse;
import com.library.LibraryManagement.mapper.LibraryMapper;
import com.library.LibraryManagement.service.BorrowingService;
import com.library.api.BorrowingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorrowController implements BorrowingApi {

    @Autowired
    BorrowingService borrowingService;


    @Override
    public ResponseEntity<Void> borrowBook(com.library.api.model.BorrowRequest borrowRequest) {
        borrowingService.borrowBook(LibraryMapper.toDto(borrowRequest));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<com.library.api.model.BorrowedBookResponse>> listBorrowedBooks(Long memberId) {
        //get list of BorrowedBookResponse
        List<BorrowedBookResponse> borrowedBooks = borrowingService.getBorrowedBooks(memberId);
        return ResponseEntity.ok(LibraryMapper.fromDto(borrowedBooks));

    }

    @Override
    public ResponseEntity<Void> returnBook(Long transactionId) {
        borrowingService.returnBook(transactionId);
        return ResponseEntity.ok().build();
    }


}
