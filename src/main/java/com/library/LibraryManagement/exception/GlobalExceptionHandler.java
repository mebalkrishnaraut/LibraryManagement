package com.library.LibraryManagement.exception;

import com.library.LibraryManagement.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError("BOOK_NOT_FOUND", ex.getMessage()));
    }


    @ExceptionHandler(BookOutOfStockException.class)
    public ResponseEntity<ApiError> handleOutOfStock(BookOutOfStockException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError("BOOK_OUT_OF_STOCK", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(new ApiError("VALIDATION_ERROR", message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("INTERNAL_ERROR", "Unexpected error occurred"));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFound(MemberNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError("MEMBER_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(BorrowTransactionNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFound(BorrowTransactionNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError("BORROW_TRANSACTION_NOT_FOUND", ex.getMessage()));
    }

}
