package com.gabriellorandi.booklibrary.book.application.dto;

import com.gabriellorandi.booklibrary.book.enums.BookType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateBookResponse {

    private UUID bookId;

    private String bookName;

    private BookType bookType;

}
