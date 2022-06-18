package com.gabriellorandi.booklibrary.book.domain;

import com.gabriellorandi.booklibrary.author.domain.Author;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Book {

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
            })
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_Year")
    private Integer publicationYear;

    @Column(name = "summary")
    private String summary;

}
