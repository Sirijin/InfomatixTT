package com.example.ELibraryManager.controller;

import com.example.ELibraryManager.dto.AuthorDTO;
import com.example.ELibraryManager.service.AuthorService;
import com.example.ELibraryManager.exception.AuthorNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        try {
            List<AuthorDTO> authors = authorService.getAllAuthors();
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting authors");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable("id") Long id) {
        try {
            AuthorDTO author = authorService.getAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting author");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        try {
            AuthorDTO savedAuthor = authorService.saveAuthor(authorDTO);
            return ResponseEntity.ok(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding author");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting author");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") Long id, @RequestBody @Valid AuthorDTO authorDTO) {
        try {
            AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
            return ResponseEntity.ok(updatedAuthor);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating author");
        }
    }
}
