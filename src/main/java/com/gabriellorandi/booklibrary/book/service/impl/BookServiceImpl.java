package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.author.service.AuthorService;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.application.dto.InventoryResponse;
import com.gabriellorandi.booklibrary.book.application.dto.UpdateInventoryRequest;
import com.gabriellorandi.booklibrary.book.exception.BookNotFoundException;
import com.gabriellorandi.booklibrary.book.exception.BookPositiveInventoryException;
import com.gabriellorandi.booklibrary.book.factory.BookStrategyFactory;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.service.BookService;
import com.gabriellorandi.booklibrary.common.application.response.ApiCollectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookStrategyFactory bookStrategyFactory;

    private final AuthorService authorService;


    @Override
    public ApiCollectionResponse<BookResponse> findAll(Pageable pageable) {
        Page<BookResponse> books = repository.findAll(pageable)
                .map(BookResponse::from);

        return ApiCollectionResponse.from(books);
    }

    @Override
    public ApiCollectionResponse<BookResponse> findAllByAuthors(Pageable pageable, String[] authorsId) {
        Set<Author> authors = Arrays.stream(authorsId)
                .map(UUID::fromString)
                .map(authorId -> authorService.findById(authorId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Page<BookResponse> books = repository.findAllByAuthorsIn(authors, pageable)
                .map(BookResponse::from);

        return ApiCollectionResponse.from(books);
    }

    @Override
    public BookResponse findById(UUID id) {
        return repository.findById(id)
                .map(BookResponse::from)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public BookResponse create(BookRequest request) {
        var bookStrategy = bookStrategyFactory.findStrategy(request.getBookType());
        var authors = authorService.findByNames(request.getAuthorNames());

        return bookStrategy.save(request, authors);
    }

    @Override
    public BookResponse update(UUID id, BookRequest request) {
        var bookStrategy = bookStrategyFactory.findStrategy(request.getBookType());
        var authors = authorService.findByNames(request.getAuthorNames());

        return bookStrategy.update(id, request, authors);
    }

    @Override
    public InventoryResponse updateInventory(UUID id, UpdateInventoryRequest request) {
        return repository.findById(id).map(book -> {
            book.getBookInventory().updateQuantity(request.getOperation(), request.getAmount());
            if (!book.getBookInventory().isPositive()) {
                throw new BookPositiveInventoryException(book.getName());
            }

            repository.save(book);
            return InventoryResponse.from(book);
        }).orElseThrow(() -> {
            log.info("Book not found with id: {}", id);
            throw new BookNotFoundException(id);
        });
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).ifPresentOrElse(book -> {
            if (book.getBookInventory().isNotEmpty()) {
                throw new BookPositiveInventoryException(book.getName());
            }

            repository.deleteById(id);
        }, () -> {
            log.info("Book not found with id: {}", id);
            throw new BookNotFoundException(id);
        });
    }

}


