package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.repository.AuthorRepository;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookResponse;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.repository.EBookRepository;
import com.gabriellorandi.booklibrary.book.service.CreateBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookServiceImpl implements CreateBookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final EBookRepository eBookRepository;

    @Override
    public CreateBookResponse create(CreateBookRequest createBookRequest) {
        return null;
    }

}
