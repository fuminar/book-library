package com.gabriellorandi.booklibrary.book.application.dto;

import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.bookinventory.domain.BookInventory;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookResponse {

    private UUID id;
    private String name;
    private List<AuthorResponse> authors;
    private String publisher;
    private Integer publicationYear;
    private String summary;
    private Long inventory;
    private String fileFormat;
    private BookType bookType;

    public static BookResponse from(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .authors(Optional.ofNullable(book.getAuthors()).stream()
                        .flatMap(Collection::stream)
                        .map(author -> new AuthorResponse(author.getId(), author.getName()))
                        .collect(Collectors.toList()))
                .publicationYear(book.getPublicationYear())
                .summary(book.getSummary())
                .inventory(Optional.ofNullable(book.getBookInventory()).map(BookInventory::getQuantity).orElse(null))
                .bookType(BookType.BOOK)
                .build();
    }

    public static BookResponse from(EBook eBook) {

        return BookResponse.builder()
                .id(eBook.getId())
                .name(eBook.getName())
                .authors(Optional.ofNullable(eBook.getAuthors()).stream()
                        .flatMap(Collection::stream)
                        .map(author -> new AuthorResponse(author.getId(), author.getName()))
                        .collect(Collectors.toList()))
                .publicationYear(eBook.getPublicationYear())
                .summary(eBook.getSummary())
                .inventory(Optional.ofNullable(eBook.getBookInventory()).map(BookInventory::getQuantity).orElse(null))
                .fileFormat(eBook.getFileFormat())
                .bookType(BookType.E_BOOK)
                .build();
    }

}
