package com.gabriellorandi.booklibrary.book.strategy;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.enums.BookType;

import java.util.Set;
import java.util.UUID;

public interface BookStrategy {
    UUID save(CreateBookRequest request, Set<Author> authors);

    BookType getBookType();
}
