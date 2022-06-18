package com.gabriellorandi.booklibrary.bookinventory.repository;

import com.gabriellorandi.booklibrary.bookinventory.domain.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookInventoryRepository extends JpaRepository<BookInventory, UUID> {
}
