package com.gabriellorandi.booklibrary.book.factory;

import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;

public interface BookStrategyFactory {
    BookStrategy findStrategy(BookType bookType);
}
