package com.gabriellorandi.booklibrary.author.domain;

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
public class Author {

    @Id
    @Setter
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

}
