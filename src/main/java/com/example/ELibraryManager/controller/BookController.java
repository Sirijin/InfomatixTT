package com.example.ELibraryManager.controller;

import com.example.ELibraryManager.dto.BookDTO;
import com.example.ELibraryManager.service.BookService;
import com.example.ELibraryManager.exception.AuthorNotFoundException;
import com.example.ELibraryManager.exception.BookNotFoundException;
import com.example.ELibraryManager.exception.PublisherNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<?> getAllBooks() {
        try {
            List<BookDTO> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving all books.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        try {
            BookDTO bookDTO = bookService.getBookById(id);
            return ResponseEntity.ok(bookDTO);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving the book.");
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAllBooksByAuthorId(@PathVariable("id") Long id) {
        try {
            List<BookDTO> books = bookService.getAllBooksByAuthorId(id);
            return ResponseEntity.ok(books);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving books by author.");
        }
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<?> getAllBooksByPublisherId(@PathVariable("id") Long id) {
        try {
            List<BookDTO> books = bookService.getAllBooksByPublisherId(id);
            return ResponseEntity.ok(books);
        } catch (PublisherNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving books by publisher.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDTO bookDTO) {
        try {
            BookDTO savedBook = bookService.saveBook(bookDTO);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding a book.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.ok().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting a book.");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody @Valid BookDTO bookDTO) {
        try {
            BookDTO updatedBook = bookService.updateBookById(id, bookDTO);
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating a book.");
        }
    }
}
