package com.gabriellorandi.booklibrary.book.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateBookResponse {

    private UUID bookId;

    private String bookName;

    private List<String> authorNames;

    private String publisher;

    private Integer publicationYear;

    private String summary;

    private String fileFormat;

}
