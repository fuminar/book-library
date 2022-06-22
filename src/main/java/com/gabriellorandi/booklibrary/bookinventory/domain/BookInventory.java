package com.gabriellorandi.booklibrary.bookinventory.domain;

import com.gabriellorandi.booklibrary.book.domain.Book;
import com.gabriellorandi.booklibrary.bookinventory.enums.InventoryOperationType;
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

    @Column(name = "quantity")
    private Long quantity;

    @OneToOne(mappedBy = "bookInventory")
    private Book book;

    public boolean isPositive() {
        return quantity >= 0;
    }

    public boolean isNotEmpty() {
        return quantity != 0;
    }

    public void updateQuantity(InventoryOperationType operationType, Long amount) {
        switch (operationType) {
            case ADD:
                quantity = quantity + amount;
                break;
            case REMOVE:
                quantity = quantity - amount;
                break;
        }
    }

}
