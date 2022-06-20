package com.gabriellorandi.booklibrary.book.strategy.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.exception.BookAlreadyExistException;
import com.gabriellorandi.booklibrary.book.repository.EBookRepository;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class EBookStrategyImpl implements BookStrategy {

    private final EBookRepository eBookRepository;

    @Override
    public UUID save(CreateBookRequest request, Set<Author> authors) {
        validBook(request.getBookName(), authors); // Books cannot be duplicated

        var eBook = EBook.from(request, authors);

        eBookRepository.save(eBook);

        return eBook.getId();
    }

    @Override
    public BookType getBookType() {
        return BookType.E_BOOK;
    }

    private void validBook(String bookName, Set<Author> authors) {
        if (eBookRepository.existsByNameAndAuthors(bookName, authors)) {
            throw new BookAlreadyExistException(bookName);
        }
    }

}
