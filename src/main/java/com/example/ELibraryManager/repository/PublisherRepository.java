package com.example.ELibraryManager.repository;

import com.example.ELibraryManager.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByBooksId(Long id);
}
