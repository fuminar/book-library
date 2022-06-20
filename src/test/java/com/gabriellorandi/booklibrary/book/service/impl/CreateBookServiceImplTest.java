package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.repository.AuthorRepository;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.factory.BookStrategyFactory;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class CreateBookServiceImplTest {

    @InjectMocks
    CreateBookServiceImpl suite;

    @Mock
    BookStrategyFactory bookStrategyFactory;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    BookStrategy bookStrategy;

    @Test
    @DisplayName("Create Book test")
    void createBook() {
        CreateBookRequest request = new CreateBookRequest(
                "Book name",
                List.of("Author 1", "Author 2"),
                "Publisher",
                2022,
                "Summary of the book.",
                null
        );

        // Mocks
        when(authorRepository.findAuthorByName(anyString())).thenReturn(Optional.empty());
        when(bookStrategyFactory.findStrategy(false)).thenReturn(bookStrategy);
        when(bookStrategy.getBookType()).thenReturn(BookType.BOOK);
        when(bookStrategy.save(any(), any())).thenReturn(UUID.randomUUID());

        // Execution
        var response = suite.create(request);

        // Response
        assertNotNull(response.getBookId(), "Id must not be null.");
        assertEquals(response.getBookName(), request.getBookName(), "Must be equal");
        assertEquals(response.getBookType(), BookType.BOOK, "Must be equal");

        // Author
        verify(authorRepository, times(2)).findAuthorByName(anyString());
    }

}