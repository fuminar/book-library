package com.gabriellorandi.booklibrary.author.repository;

import com.gabriellorandi.booklibrary.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findAuthorByName(String name);
}
