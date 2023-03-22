package com.example.ELibraryManager.service;

import com.example.ELibraryManager.dto.AuthorDTO;
import com.example.ELibraryManager.exception.AuthorNotFoundException;
import com.example.ELibraryManager.mapper.AuthorMapper;
import com.example.ELibraryManager.model.Author;
import com.example.ELibraryManager.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO getAuthorById(Long id) {
        return authorMapper.toDTO(authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with this id does not exist")));
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.fromDTO(authorDTO);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = authorMapper.fromDTO(id, authorDTO);
            return authorMapper.toDTO(authorRepository.save(author));
        } else {
            throw new AuthorNotFoundException("Author with this id does not exist");
        }
    }
}
