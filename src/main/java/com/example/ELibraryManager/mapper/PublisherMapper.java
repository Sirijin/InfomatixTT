package com.example.ELibraryManager.mapper;

import com.example.ELibraryManager.dto.BookDTO;
import com.example.ELibraryManager.dto.PublisherDTO;
import com.example.ELibraryManager.model.Book;
import com.example.ELibraryManager.model.Publisher;
import com.example.ELibraryManager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherMapper {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public PublisherDTO toDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setCity(publisher.getCity());

        if (publisher.getBooks() != null) {
            List<BookDTO> booksDTO = new ArrayList<>();
            for (Book book : publisher.getBooks()) {
                BookDTO bookDTO = bookMapper.toDTO(book);
                booksDTO.add(bookDTO);
            }
            publisherDTO.setBooks(booksDTO);
        }
        return publisherDTO;
    }

    public Publisher fromDTO(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setCity(publisherDTO.getCity());

        List<Book> books = new ArrayList<>();
        if (publisherDTO.getBooks() != null) {
            for (BookDTO bookDTO : publisherDTO.getBooks()) {
                Book book = bookMapper.fromDTO(bookDTO);
                books.add(book);
            }
            publisher.setBooks(books);
        }
        return publisher;
    }

    public Publisher fromDTO(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(id);

        publisher.setName(publisherDTO.getName());
        publisher.setCity(publisherDTO.getCity());

        List<Book> books = new ArrayList<>();
        if (publisherDTO.getBooks() != null) {
            for (BookDTO bookDTO : publisherDTO.getBooks()) {
                Book book = bookMapper.fromDTO(bookDTO);
                books.add(book);
            }
        } else {
            books = bookRepository.findByPublisherId(id);
        }
        publisher.setBooks(books);

        return publisher;
    }

}
