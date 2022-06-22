package com.gabriellorandi.booklibrary.author.service.impl;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.author.repository.AuthorRepository;
import com.gabriellorandi.booklibrary.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Set<Author> findByNames(List<String> authorNames) {
        return authorNames.stream()
                .map(name -> authorRepository.findAuthorByName(name).orElse(new Author(null, name)))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Author> findById(UUID authorId) {
        return authorRepository.findById(authorId);
    }

}
