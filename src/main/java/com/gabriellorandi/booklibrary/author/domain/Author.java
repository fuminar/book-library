package com.gabriellorandi.booklibrary.author.domain;

import com.gabriellorandi.booklibrary.book.domain.Book;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Author {

    @Id
    @Setter
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "authors")
    private Set<Book> books;

}
