package com.library.LibraryManagement.exception;

public class BorrowTransactionNotFoundException extends RuntimeException {

    public BorrowTransactionNotFoundException(Long transactionId) {
        super("Transaction not found : " + transactionId);
    }
}
