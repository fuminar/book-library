package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.author.service.AuthorService;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.application.dto.UpdateInventoryRequest;
import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.book.exception.BookNotFoundException;
import com.gabriellorandi.booklibrary.book.exception.BookPositiveInventoryException;
import com.gabriellorandi.booklibrary.book.factory.BookStrategyFactory;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import com.gabriellorandi.booklibrary.bookinventory.domain.BookInventory;
import com.gabriellorandi.booklibrary.bookinventory.enums.InventoryOperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl suite;

    @Mock
    BookRepository repository;

    @Mock
    BookStrategyFactory bookStrategyFactory;

    @Mock
    AuthorService authorService;

    @Mock
    BookStrategy bookStrategy;

    @Mock
    BookResponse bookResponse;

    @Mock
    Set<Author> authors;

    @Mock
    BookRequest request;

    @Test
    @DisplayName("Create Book test")
    void createBook() {
        // Mocks
        when(authorService.findByNames(any())).thenReturn(authors);
        when(bookStrategyFactory.findStrategy(any())).thenReturn(bookStrategy);
        when(bookStrategy.save(any(), any())).thenReturn(bookResponse);

        // Execution
        suite.create(request);

        // Author
        verify(bookStrategyFactory).findStrategy(any());
        verify(authorService).findByNames(any());
    }

    @Test
    @DisplayName("Update Book test")
    void update() {
        // Mocks
        when(authorService.findByNames(any())).thenReturn(authors);
        when(bookStrategyFactory.findStrategy(any())).thenReturn(bookStrategy);
        when(bookStrategy.update(any(), any(), any())).thenReturn(bookResponse);

        // Execution
        suite.update(UUID.randomUUID(), request);

        // Author
        verify(bookStrategyFactory).findStrategy(any());
        verify(authorService).findByNames(any());
    }

    @Test
    @DisplayName("Add Book Inventory test")
    void updateInventory() {
        Book book = new Book(
                UUID.randomUUID(),
                "Book name",
                Set.of(new Author(UUID.randomUUID(), "Author 1")),
                "Publisher",
                2022,
                "Summary of the book.",
                new BookInventory(UUID.randomUUID(), 0L, null)
        );

        UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(
                InventoryOperationType.ADD,
                1L
        );

        //Mocks
        when(repository.findById(any())).thenReturn(Optional.of(book));

        // Execution
        suite.updateInventory(UUID.randomUUID(), updateInventoryRequest);

        // Author
        verify(repository).findById(any());
        verify(repository).save(any());
    }

    @Test
    @DisplayName("Add Book Inventory not found test")
    void updateInventory2() {
        UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(
                InventoryOperationType.ADD,
                1L
        );

        //Mocks
        when(repository.findById(any())).thenReturn(Optional.empty());

        // Execution
        assertThrows(BookNotFoundException.class, () -> suite.updateInventory(UUID.randomUUID(), updateInventoryRequest));

        // Author
        verify(repository).findById(any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Remove Book Inventory positive test")
    void updateInventory3() {
        Book book = new Book(
                UUID.randomUUID(),
                "Book name",
                Set.of(new Author(UUID.randomUUID(), "Author 1")),
                "Publisher",
                2022,
                "Summary of the book.",
                new BookInventory(UUID.randomUUID(), 1L, null)
        );

        UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(
                InventoryOperationType.REMOVE,
                1L
        );

        //Mocks
        when(repository.findById(any())).thenReturn(Optional.of(book));

        // Execution
        suite.updateInventory(UUID.randomUUID(), updateInventoryRequest);

        // Author
        verify(repository).findById(any());
        verify(repository).save(any());
    }

    @Test
    @DisplayName("Remove Book Inventory empty test")
    void updateInventory4() {
        Book book = new Book(
                UUID.randomUUID(),
                "Book name",
                Set.of(new Author(UUID.randomUUID(), "Author 1")),
                "Publisher",
                2022,
                "Summary of the book.",
                new BookInventory(UUID.randomUUID(), 0L, null)
        );

        UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest(
                InventoryOperationType.REMOVE,
                1L
        );

        //Mocks
        when(repository.findById(any())).thenReturn(Optional.of(book));

        // Execution
        assertThrows(BookPositiveInventoryException.class, () -> suite.updateInventory(UUID.randomUUID(), updateInventoryRequest));

        // Author
        verify(repository).findById(any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Delete Book with empty inventory test")
    void delete() {
        Book book = new Book(
                UUID.randomUUID(),
                "Book name",
                Set.of(new Author(UUID.randomUUID(), "Author 1")),
                "Publisher",
                2022,
                "Summary of the book.",
                new BookInventory(UUID.randomUUID(), 0L, null)
        );

        // Mocks
        when(repository.findById(any())).thenReturn(Optional.of(book));

        // Execution
        suite.delete(UUID.randomUUID());

        // Author
        verify(repository).findById(any());
        verify(repository).deleteById(any());
    }

    @Test
    @DisplayName("Delete Book with positive inventory test")
    void delete2() {
        Book book = new Book(
                UUID.randomUUID(),
                "Book name",
                Set.of(new Author(UUID.randomUUID(), "Author 1")),
                "Publisher",
                2022,
                "Summary of the book.",
                new BookInventory(UUID.randomUUID(), 1L, null)
        );

        // Mocks
        when(repository.findById(any())).thenReturn(Optional.of(book));

        // Execution
        assertThrows(BookPositiveInventoryException.class, () -> suite.delete(UUID.randomUUID()));

        // Author
        verify(repository).findById(any());
        verify(repository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Delete Book not found test")
    void delete3() {
        // Mocks
        when(repository.findById(any())).thenReturn(Optional.empty());

        // Execution
        assertThrows(BookNotFoundException.class, () -> suite.delete(UUID.randomUUID()));

        // Author
        verify(repository).findById(any());
        verify(repository, never()).deleteById(any());
    }

}