package com.library.LibraryManagement.exception;

public class BookOutOfStockException extends RuntimeException{
    public BookOutOfStockException(String title) {
        super("No available copies for book : " + title);
    }
}
