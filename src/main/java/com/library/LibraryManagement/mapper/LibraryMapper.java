package com.library.LibraryManagement.mapper;

import com.library.LibraryManagement.dto.BorrowedBookResponse;

import java.time.OffsetDateTime;
import java.util.List;

public class LibraryMapper {

    public static com.library.LibraryManagement.dto.BookRequest toDto(
            com.library.api.model.BookRequest api) {

        var dto = new com.library.LibraryManagement.dto.BookRequest();
        dto.setTitle(api.getTitle());
        dto.setAuthor(api.getAuthor());
        dto.setIsbn(api.getIsbn());
        dto.setTotalCopies(api.getTotalCopies());
        return dto;
    }

    public static com.library.api.model.BookResponse fromDto(com.library.LibraryManagement.dto.BookResponse api) {
        var dto = new com.library.api.model.BookResponse();
        dto.setId(api.getId());
        dto.setTitle(api.getTitle());
        dto.setAuthor(api.getAuthor());
        dto.setIsbn(api.getIsbn());
        dto.setTotalCopies(api.getTotalCopies());
        return dto;
    }

    public static com.library.LibraryManagement.dto.BorrowRequest toDto(com.library.api.model.BorrowRequest api) {
        var dto = new com.library.LibraryManagement.dto.BorrowRequest();
        dto.setBookId(api.getBookId());
        dto.setMemberId(api.getMemberId());
        return dto;
    }

    public static List<com.library.api.model.BorrowedBookResponse> fromDto(List<com.library.LibraryManagement.dto.BorrowedBookResponse> api) {
        return api.stream().map(LibraryMapper::fromDto).toList();
    }

    private static com.library.api.model.BorrowedBookResponse fromDto(BorrowedBookResponse borrowedBookResponse) {
        var dto = new com.library.api.model.BorrowedBookResponse();
        dto.setBookId(borrowedBookResponse.getBookId());
        dto.setBookTitle(borrowedBookResponse.getBookTitle());
        dto.setBorrowedAt(OffsetDateTime.from(borrowedBookResponse.getBorrowedAt()));
        return dto;
    }

    public static com.library.LibraryManagement.dto.MemberRequest toDto(com.library.api.model.MemberRequest api) {
        var dto = new com.library.LibraryManagement.dto.MemberRequest();
        dto.setName(api.getName());
        dto.setEmail(api.getEmail());
        dto.setPhone(api.getPhone());
        return dto;
    }
}
