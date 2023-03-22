package com.example.ELibraryManager.service;

import com.example.ELibraryManager.dto.BookDTO;
import com.example.ELibraryManager.mapper.BookMapper;
import com.example.ELibraryManager.model.Book;
import com.example.ELibraryManager.repository.BookRepository;
import com.example.ELibraryManager.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO getBookById(Long id) {
        return bookMapper.toDTO(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with this id does not exist")));

    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksByAuthorId(Long id) {
        return bookRepository.findByAuthorId(id).stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksByPublisherId(Long id) {
        return bookRepository.findByPublisherId(id).stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.fromDTO(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public BookDTO updateBookById(Long id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = bookMapper.fromDTO(id, bookDTO);
            return bookMapper.toDTO(bookRepository.save(book));
        } else {
            throw new BookNotFoundException("Book with this id does not exist");
        }
    }
}
