package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.author.repository.AuthorRepository;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookResponse;
import com.gabriellorandi.booklibrary.book.factory.BookStrategyFactory;
import com.gabriellorandi.booklibrary.book.service.CreateBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateBookServiceImpl implements CreateBookService {

    private final AuthorRepository authorRepository;
    private final BookStrategyFactory bookStrategyFactory;


    @Override
    public CreateBookResponse create(CreateBookRequest request) {
        var authors = getAuthors(request.getAuthorNames());

        var bookStrategy = bookStrategyFactory.findStrategy(request.isEBook());

        var bookId = bookStrategy.save(request, authors);

        return new CreateBookResponse(bookId, request.getBookName(), bookStrategy.getBookType());
    }

    private Set<Author> getAuthors(List<String> names) {
        return names.stream()
                .map(name -> authorRepository.findAuthorByName(name).orElse(new Author(null, name)))
                .collect(Collectors.toSet());
    }

}


