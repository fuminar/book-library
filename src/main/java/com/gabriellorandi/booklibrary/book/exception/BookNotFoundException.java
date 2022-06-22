package com.gabriellorandi.booklibrary.book.exception;

import com.gabriellorandi.booklibrary.common.exception.NotFoundException;
import lombok.Getter;

import java.util.UUID;

public class BookNotFoundException extends NotFoundException {

    @Getter
    private final UUID id;

    public BookNotFoundException(UUID bookId) {
        super("book.exception.notFound", bookId);
        this.id = bookId;
    }
}
