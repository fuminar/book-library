package com.gabriellorandi.booklibrary.book.strategy;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.enums.BookType;

import java.util.Set;
import java.util.UUID;

public interface BookStrategy {
    BookResponse save(BookRequest request, Set<Author> authors);

    BookResponse update(UUID id, BookRequest request, Set<Author> authors);

    BookType getBookType();
}
