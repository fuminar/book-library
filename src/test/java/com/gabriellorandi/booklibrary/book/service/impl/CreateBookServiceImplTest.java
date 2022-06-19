package com.gabriellorandi.booklibrary.book.service.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.author.repository.AuthorRepository;
import com.gabriellorandi.booklibrary.book.application.dto.CreateBookRequest;
import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import com.gabriellorandi.booklibrary.book.repository.BookRepository;
import com.gabriellorandi.booklibrary.book.repository.EBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class CreateBookServiceImplTest {

    @InjectMocks
    CreateBookServiceImpl suite;

    @Mock
    BookRepository bookRepository;

    @Mock
    EBookRepository eBookRepository;

    @Mock
    AuthorRepository authorRepository;

    @Captor
    ArgumentCaptor<Book> bookArgumentCaptor;

    @Captor
    ArgumentCaptor<EBook> eBookArgumentCaptor;

    @Captor
    ArgumentCaptor<Author> authorArgumentCaptor;


    @Test
    @DisplayName("Create Book test, author doesn't exist, book doesn't exist")
    void createBook() {
        CreateBookRequest request = new CreateBookRequest(
                "Book name",
                List.of("Author 1", "Author 2"),
                "Publisher",
                2022,
                "Sumary of the book.",
                null
        );

        // Mocks
        when(authorRepository.existsByName(anyString())).thenReturn(false);
        when(bookRepository.existsByNameAndAuthors(anyString(), any())).thenReturn(false);

        // Execution
        suite.create(request);

        // Book
        verify(bookRepository).existsByNameAndAuthors(anyString(), any());
        verify(bookRepository).save(bookArgumentCaptor.capture());

        var savedBook = bookArgumentCaptor.getValue();
        assertEquals(savedBook.getName(), request.getBookName(), "Must be equal");
        assertEquals(savedBook.getAuthors().size(), request.getAuthorNames().size(), "Must be same size");
        assertEquals(savedBook.getPublisher(), request.getPublisher(), "Must be equal");
        assertEquals(savedBook.getPublicationYear(), request.getPublicationYear(), "Must be equal");
        assertEquals(savedBook.getSummary(), request.getSummary(), "Must be equal");
        assertEquals(savedBook.getBookInventory().getQuantity(), 1L, "Must be equal");

        // eBook
        verify(eBookRepository, never()).existsByNameAndAuthors(anyString(), any());
        verify(eBookRepository, never()).save(any());

        // Author
        verify(authorRepository, times(2)).save(authorArgumentCaptor.capture());
        var savedAuthors = authorArgumentCaptor.getAllValues();
        savedAuthors.forEach(author -> assertTrue(author.getName().equals("Author 1") || author.getName().equals("Author 2")));

    }

}