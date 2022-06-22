package com.gabriellorandi.booklibrary.book.repository;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.domain.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface EBookRepository extends JpaRepository<EBook, UUID> {
    boolean existsByNameAndAuthorsIn(String name, Set<Author> authors);
}

