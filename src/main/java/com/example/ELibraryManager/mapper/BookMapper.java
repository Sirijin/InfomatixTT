package com.example.ELibraryManager.mapper;

import com.example.ELibraryManager.dto.BookDTO;
import com.example.ELibraryManager.model.Author;
import com.example.ELibraryManager.model.Book;
import com.example.ELibraryManager.repository.AuthorRepository;
import com.example.ELibraryManager.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setYearOfPublishing(book.getYearOfPublishing());
        bookDTO.setAuthorsIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()));
        bookDTO.setPublisherId(book.getPublisher().getId());
        return bookDTO;
    }

    public Book fromDTO(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setYearOfPublishing(bookDTO.getYearOfPublishing());

        List<Author> authors = new ArrayList<>();
        if (bookDTO.getAuthorsIds() != null) {
            for (Long authorId : bookDTO.getAuthorsIds()) {
                authorRepository.findById(authorId).ifPresent(authors::add);
            }
        } else {
            authors = authorRepository.findByBooksId(book.getId());
        }

        book.setAuthors(authors);

        publisherRepository.findById(bookDTO.getPublisherId()).ifPresent(book::setPublisher);

        return book;
    }

    public Book fromDTO(Long id, BookDTO bookDTO) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookDTO.getTitle());

        book.setYearOfPublishing(bookDTO.getYearOfPublishing());

        List<Author> authors = new ArrayList<>();
        if (bookDTO.getPublisherId() != null) {
            for (Long authorId : bookDTO.getAuthorsIds()) {
                if (authorRepository.existsById(authorId)) {
                    Author author = authorRepository.findById(authorId).orElse(null);
                    if (author != null) {
                        authors.add(author);
                    }
                }
            }
        } else {
            authors = authorRepository.findByBooksId(id);
        }
        book.setAuthors(authors);

        if (bookDTO.getPublisherId() != null) {
            publisherRepository.findById(bookDTO.getPublisherId()).ifPresent(book::setPublisher);
        } else {
            book.setPublisher(publisherRepository.findByBooksId(id));
        }

        return book;
    }

}
