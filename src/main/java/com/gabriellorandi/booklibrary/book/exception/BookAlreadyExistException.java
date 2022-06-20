package com.gabriellorandi.booklibrary.book.exception;

import com.gabriellorandi.booklibrary.common.exception.BusinessException;
import lombok.Getter;

public class BookAlreadyExistException extends BusinessException {
    @Getter
    private final String bookName;

    public BookAlreadyExistException(String bookName) {
        super("book.exception.exist", bookName);
        this.bookName = bookName;
    }
}
