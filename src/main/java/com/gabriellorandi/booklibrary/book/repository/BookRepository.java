package com.gabriellorandi.booklibrary.book.repository;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByNameAndAuthorsIn(String name, Set<Author> authors);

    Page<Book> findAllByAuthorsIn(Set<Author> authors, Pageable pageable);

}
