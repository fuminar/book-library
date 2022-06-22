package com.gabriellorandi.booklibrary.book.application.dto;

import com.gabriellorandi.booklibrary.book.domain.Book;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InventoryResponse {
    private UUID bookId;
    private String bookName;
    private Long inventory;

    public static InventoryResponse from(Book book) {
        return InventoryResponse.builder()
                .bookId(book.getId())
                .bookName(book.getName())
                .inventory(book.getBookInventory().getQuantity())
                .build();
    }
}
