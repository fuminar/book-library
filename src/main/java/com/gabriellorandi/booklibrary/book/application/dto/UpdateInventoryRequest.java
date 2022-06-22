package com.gabriellorandi.booklibrary.book.application.dto;

import com.gabriellorandi.booklibrary.bookinventory.enums.InventoryOperationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateInventoryRequest {

    @NotNull(message = "{book.inventory.operation.invalid}")
    private InventoryOperationType operation;

    @Positive(message = "{book.inventory.amount.positive}")
    @NotNull(message = "{book.inventory.amount.null}")
    private Long amount;

}
