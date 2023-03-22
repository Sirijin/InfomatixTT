package com.example.ELibraryManager.service;

import com.example.ELibraryManager.dto.PublisherDTO;
import com.example.ELibraryManager.mapper.PublisherMapper;
import com.example.ELibraryManager.model.Publisher;
import com.example.ELibraryManager.repository.PublisherRepository;
import com.example.ELibraryManager.exception.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherDTO getPublisherById(Long id) {
        return publisherMapper.toDTO(publisherRepository.findById(id).orElseThrow(() -> new PublisherNotFoundException("Publisher with this id does not exist")));
    }

    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream().map(publisherMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public PublisherDTO savePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.fromDTO(publisherDTO);
        return publisherMapper.toDTO(publisherRepository.save(publisher));
    }

    @Transactional
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    @Transactional
    public PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            Publisher publisher = publisherMapper.fromDTO(id, publisherDTO);
            return publisherMapper.toDTO(publisherRepository.save(publisher));
        } else {
            throw new PublisherNotFoundException("Publisher with this id does not exist");
        }
    }

}
