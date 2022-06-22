package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.common.exception.BusinessException;

import java.util.UUID;

public class BookTypeNotValidException extends BusinessException {

    public BookTypeNotValidException(UUID id) {
        super("{book.type.notValid}", id);
    }
}
