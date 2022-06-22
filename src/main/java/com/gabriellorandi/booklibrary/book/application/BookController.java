package com.gabriellorandi.booklibrary.book.application;

import com.gabriellorandi.booklibrary.book.application.dto.BookRequest;
import com.gabriellorandi.booklibrary.book.application.dto.BookResponse;
import com.gabriellorandi.booklibrary.book.application.dto.InventoryResponse;
import com.gabriellorandi.booklibrary.book.application.dto.UpdateInventoryRequest;
import com.gabriellorandi.booklibrary.book.service.BookService;
import com.gabriellorandi.booklibrary.common.application.annotation.ApiPageable;
import com.gabriellorandi.booklibrary.common.application.response.ApiCollectionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/books")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Book Operations")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @ApiPageable
    @ApiOperation("Retrieve all Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation")
    })
    public ResponseEntity<ApiCollectionResponse<BookResponse>> findAll(@ApiIgnore Pageable pageable) {
        log.info("Receiving request to list all books.");

        var response = bookService.findAll(pageable);

        log.info("Retrieving all books.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/authors")
    @ApiPageable
    @ApiOperation("Retrieve all Books by Authors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation")
    })
    public ResponseEntity<ApiCollectionResponse<BookResponse>> findAllByAuthors(String[] authorsId, @ApiIgnore Pageable pageable) {
        log.info("Receiving request to list all books by Authors.");

        var response = bookService.findAllByAuthors(pageable, authorsId);

        log.info("Retrieving all books.");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/id/{id}")
    @ApiPageable
    @ApiOperation("Retrieve Book by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        log.info("Receiving request to get books by its Id.");

        var response = bookService.findById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation("Create new book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 500, message = "Book already exists")
    })
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request) {
        log.info("Receiving request to create new book with name: {}.", request.getBookName());

        var bookResponse = bookService.create(request);

        log.info("Book created. Id: {}.", bookResponse.getId());

        return ResponseEntity.ok(bookResponse);
    }

    @PutMapping("/{id}")
    @ApiOperation("Modify book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public ResponseEntity<BookResponse> update(@PathVariable UUID id, @RequestBody @Valid BookRequest request) {
        log.info("Receiving request to modify book with id: {}.", id);

        var bookResponse = bookService.update(id, request);

        log.info("Book modified. Id: {}.", id);

        return ResponseEntity.ok(bookResponse);
    }

    @PutMapping("{id}/update-inventory")
    @ApiOperation("Modify book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable UUID id, @RequestBody @Valid UpdateInventoryRequest request) {
        log.info("Receiving request to update inventory of book with id: {}.", id);

        var bookResponse = bookService.updateInventory(id, request);

        log.info("Inventory updated. BookId: {}.", id);

        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("Delete book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        log.info("Receiving request to delete book with id: {}.", id);

        bookService.delete(id);

        log.info("Book deleted. Id: {}.", id);

        return ResponseEntity.ok().build();
    }

}
