package com.example.ELibraryManager.repository;

import com.example.ELibraryManager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findByAuthorId(@Param("authorId") Long authorId);

    @Query("SELECT b FROM Book b WHERE b.publisher.id = :publisherId")
    List<Book> findByPublisherId(@Param("publisherId") Long publisherId);
}
