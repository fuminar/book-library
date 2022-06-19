package com.gabriellorandi.booklibrary.book.domain;

import com.gabriellorandi.booklibrary.author.domain.Author;
import com.gabriellorandi.booklibrary.bookinventory.domain.BookInventory;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Author> authors = new HashSet<>();

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "summary")
    private String summary;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST
            })
    @JoinColumn(name = "book_inventory", referencedColumnName = "id")
    private BookInventory bookInventory;

}
