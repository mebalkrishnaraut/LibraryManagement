package com.library.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiError {
    private String code;
    private String message;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
