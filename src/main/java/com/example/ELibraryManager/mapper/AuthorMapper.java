package com.example.ELibraryManager.mapper;

import com.example.ELibraryManager.dto.AuthorDTO;
import com.example.ELibraryManager.dto.BookDTO;
import com.example.ELibraryManager.model.Author;
import com.example.ELibraryManager.model.Book;
import com.example.ELibraryManager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public AuthorDTO toDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        List<BookDTO> booksDTO = new ArrayList<>();

        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                BookDTO bookDTO = bookMapper.toDTO(book);
                booksDTO.add(bookDTO);
            }
            authorDTO.setBooks(booksDTO);
        }
        return authorDTO;
    }

    public Author fromDTO(AuthorDTO authorDTO) {
        Author author = new Author();
        return getAuthor(authorDTO, author);
    }

    public Author fromDTO(Long id, AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(id);
        return getAuthor(authorDTO, author);
    }

    private Author getAuthor(AuthorDTO authorDTO, Author author) {
        author.setName(authorDTO.getName());

        if (authorDTO.getId() != null) {
            author.setId(authorDTO.getId());
        }
        List<Book> books = new ArrayList<>();
        if (authorDTO.getBooks() != null) {
            for (BookDTO bookDTO : authorDTO.getBooks()) {
                Book book = bookMapper.fromDTO(bookDTO);
                books.add(book);
            }
        } else {
            books = bookRepository.findByAuthorId(author.getId());
        }
        author.setBooks(books);

        return author;
    }
}
