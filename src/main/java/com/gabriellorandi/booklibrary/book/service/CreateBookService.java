package com.gabriellorandi.booklibrary.book.service;

import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookResponse;

public interface CreateBookService {
    CreateBookResponse create(CreateBookRequest createBookRequest);
}
