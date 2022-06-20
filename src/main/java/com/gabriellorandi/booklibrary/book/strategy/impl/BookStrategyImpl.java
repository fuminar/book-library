package com.gabriellorandi.booklibrary.book.strategy.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.exception.BookAlreadyExistException;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BookStrategyImpl implements BookStrategy {

    private final BookRepository bookRepository;

    @Override
    public UUID save(CreateBookRequest request, Set<Author> authors) {
        validBook(request.getBookName(), authors); // Books cannot be duplicated

        var book = Book.from(request, authors);

        bookRepository.save(book);

        return book.getId();
    }

    @Override
    public BookType getBookType() {
        return BookType.BOOK;
    }

    private void validBook(String bookName, Set<Author> authors) {
        if (bookRepository.existsByNameAndAuthors(bookName, authors)) {
            throw new BookAlreadyExistException(bookName);
        }
    }

}
