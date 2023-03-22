package com.example.ELibraryManager.repository;

import com.example.ELibraryManager.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByBooksId(Long id);
}
