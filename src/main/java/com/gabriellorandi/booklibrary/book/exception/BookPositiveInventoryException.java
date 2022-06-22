package com.gabriellorandi.booklibrary.book.exception;

import com.gabriellorandi.booklibrary.common.exception.BusinessException;
import lombok.Getter;

public class BookPositiveInventoryException extends BusinessException {

    @Getter
    private final String bookName;

    public BookPositiveInventoryException(String bookName) {
        super("book.inventory.positive", bookName);
        this.bookName = bookName;
    }
}
