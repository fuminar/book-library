package com.gabriellorandi.booklibrary.bookinventory.domain;

import com.gabriellorandi.booklibrary.book.domain.Book;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BookInventory {

    @Id
    @Setter
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "quantity")
    private String quantity;

}
