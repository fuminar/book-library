package com.gabriellorandi.booklibrary.book.factory.impl;

import com.gabriellorandi.booklibrary.book.enums.BookType;
import com.gabriellorandi.booklibrary.book.factory.BookStrategyFactory;
import com.gabriellorandi.booklibrary.book.strategy.BookStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class BookStrategyFactoryImpl implements BookStrategyFactory {

    private Map<BookType, BookStrategy> strategies;

    @Autowired
    public BookStrategyFactoryImpl(Set<BookStrategy> strategySet) {
        createStrategy(strategySet);
    }

    @Override
    public BookStrategy findStrategy(BookType bookType) {
        return strategies.get(bookType);
    }

    private void createStrategy(Set<BookStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy -> strategies.put(strategy.getBookType(), strategy));
    }

}
