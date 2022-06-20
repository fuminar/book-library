package com.gabriellorandi.booklibrary.bookinventory.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Column(name = "quantity")
    private Long quantity;

}
