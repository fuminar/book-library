package com.gabriellorandi.booklibrary.common.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(String keyMessage) {
        super(keyMessage);
    }

    public NotFoundException(String keyMessage, Object... arguments) {
        super(keyMessage, arguments);
    }
}
