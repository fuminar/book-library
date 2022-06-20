package com.gabriellorandi.booklibrary.book.factory.impl;

import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import com.gabriellorandi.booklibrary.book.strategy.impl.BookStrategyImpl;
import com.gabriellorandi.booklibrary.book.strategy.impl.EBookStrategyImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class BookStrategyFactoryImplTest {

    @Mock
    EBookStrategyImpl eBookStrategy;

    @Mock
    BookStrategyImpl bookStrategy;

    BookStrategyFactoryImpl suite;

    @BeforeEach
    void setUp() {
        when(eBookStrategy.getBookType()).thenReturn(BookType.E_BOOK);
        when(bookStrategy.getBookType()).thenReturn(BookType.BOOK);

        Set<BookStrategy> strategySet = new HashSet<>();
        strategySet.add(eBookStrategy);
        strategySet.add(bookStrategy);
        suite = new BookStrategyFactoryImpl(strategySet);
    }

    @Test
    @DisplayName("Find Strategy test, eBook")
    void testFindStrategy() {
        BookStrategy strategy = suite.findStrategy(true);
        Assertions.assertEquals(strategy.getBookType(), BookType.E_BOOK);
    }

    @Test
    @DisplayName("Find Strategy test, Book")
    void testFindStrategy2() {
        BookStrategy strategy = suite.findStrategy(false);
        Assertions.assertEquals(strategy.getBookType(), BookType.BOOK);
    }

}
