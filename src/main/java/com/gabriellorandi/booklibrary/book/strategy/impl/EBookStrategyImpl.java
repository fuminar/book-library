package com.gabriellorandi.booklibrary.book.strategy.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.exception.BookAlreadyExistException;
import com.gabriellorandi.booklibrary.book.exception.BookNotFoundException;
import com.gabriellorandi.booklibrary.book.repository.EBookRepository;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class EBookStrategyImpl implements BookStrategy {

    private final EBookRepository eBookRepository;

    @Override
    public BookResponse save(BookRequest request, Set<Author> authors) {
        validBook(request.getBookName(), authors); // Books cannot be duplicated

        var eBook = EBook.from(request, authors);

        eBookRepository.save(eBook);

        return BookResponse.from(eBook);
    }

    @Override
    public BookResponse update(UUID id, BookRequest request, Set<Author> authors) {
        return eBookRepository.findById(id)
                .map(book -> {
                    book = EBook.from(request, authors);
                    book.setId(id);

                    return eBookRepository.save(book);
                })
                .map(BookResponse::from)
                .orElseThrow(() -> {
                    log.info("eBook not found with id: {}", id);
                    throw new BookNotFoundException(id);
                });
    }

    @Override
    public BookType getBookType() {
        return BookType.E_BOOK;
    }

    private void validBook(String bookName, Set<Author> authors) {
        authors = authors.stream()
                .filter(author -> author.getId() != null)
                .collect(Collectors.toSet());

        if (eBookRepository.existsByNameAndAuthorsIn(bookName, authors)) {
            log.error("Error creating eBook. EBook with name {} already exists.", bookName);
            throw new BookAlreadyExistException(bookName);
        }
    }

}
