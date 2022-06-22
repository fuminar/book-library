package com.gabriellorandi.booklibrary.book.domain;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.bookinventory.domain.BookInventory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EBook extends Book {

    @Column(name = "file_format")
    private String fileFormat;

    public EBook(UUID id, String name, Set<Author> authors, String publisher, Integer publicationYear, String summary, BookInventory bookInventory, String fileFormat) {
        super(id, name, authors, publisher, publicationYear, summary, bookInventory);
        this.fileFormat = fileFormat;
    }

    public static EBook from(BookRequest request, Set<Author> authors) {
        return new EBook(null,
                request.getBookName(),
                authors,
                request.getPublisher(),
                request.getPublicationYear(),
                request.getSummary(),
                new BookInventory(null, 1L, null),
                request.getFileFormat());
    }

}
