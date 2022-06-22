package com.gabriellorandi.booklibrary.book.strategy.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import com.gabriellorandi.booklibrary.book.repository.EBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class EBookStrategyImplTest {

    @InjectMocks
    EBookStrategyImpl suite;

    @Mock
    EBookRepository eBookRepository;

    @Mock
    EBook eBook;

    @Mock
    BookRequest request;

    @Mock
    Set<Author> authors;

    @Test
    @DisplayName("Save eBook")
    void saveEBook() {
        // Mocks
        when(eBookRepository.save(any())).thenReturn(eBook);

        // Execution
        suite.save(request, authors);

        // Assert
        verify(eBookRepository).save(any());
    }

}

