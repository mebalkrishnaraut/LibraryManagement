package com.library.LibraryManagement.exception;

public class BookAlreadyReturnException extends RuntimeException {

    public BookAlreadyReturnException(Long transactionId) {
        super("Book already returned for transaction: " + transactionId);
    }
}
