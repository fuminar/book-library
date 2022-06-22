package com.gabriellorandi.booklibrary.book.service;

import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.application.dto.InventoryResponse;
import com.gabriellorandi.booklibrary.book.application.dto.UpdateInventoryRequest;
import com.gabriellorandi.booklibrary.common.application.response.ApiCollectionResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {
    ApiCollectionResponse<BookResponse> findAll(Pageable pageable);

    ApiCollectionResponse<BookResponse> findAllByAuthors(Pageable pageable, String[] authorsId);

    BookResponse findById(UUID id);

    BookResponse create(BookRequest bookRequest);

    BookResponse update(UUID id, BookRequest bookRequest);

    InventoryResponse updateInventory(UUID uuid, UpdateInventoryRequest request);

    void delete(UUID id);

}
