package com.gabriellorandi.booklibrary.book.strategy.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.exception.BookAlreadyExistException;
import com.gabriellorandi.booklibrary.book.exception.BookNotFoundException;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class BookStrategyImpl implements BookStrategy {

    private final BookRepository bookRepository;

    @Override
    public BookResponse save(BookRequest request, Set<Author> authors) {
        validBook(request.getBookName(), authors); // Books cannot be duplicated

        var book = Book.from(request, authors);

        bookRepository.save(book);

        return BookResponse.from(book);
    }

    @Override
    public BookResponse update(UUID id, BookRequest request, Set<Author> authors) {
        return bookRepository.findById(id)
                .map(book -> {
                    book = Book.from(request, authors);
                    book.setId(id);

                    return bookRepository.save(book);
                })
                .map(BookResponse::from)
                .orElseThrow(() -> {
                    log.info("Book not found with id: {}", id);
                    throw new BookNotFoundException(id);
                });
    }

    @Override
    public BookType getBookType() {
        return BookType.BOOK;
    }

    private void validBook(String bookName, Set<Author> authors) {
        authors = authors.stream()
                .filter(author -> author.getId() != null)
                .collect(Collectors.toSet());

        if (bookRepository.existsByNameAndAuthorsIn(bookName, authors)) {
            log.error("Error creating book. Book with name {} already exists.", bookName);
            throw new BookAlreadyExistException(bookName);
        }
    }

}
