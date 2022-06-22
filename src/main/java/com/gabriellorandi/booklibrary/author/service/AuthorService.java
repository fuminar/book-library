package com.gabriellorandi.booklibrary.author.service;

import com.gabriellorandi.booklibrary.author.domain.Author;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AuthorService {
    Set<Author> findByNames(List<String> authorNames);

    Optional<Author> findById(UUID authorId);
}
