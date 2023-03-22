package com.example.ELibraryManager.controller;

import com.example.ELibraryManager.dto.PublisherDTO;
import com.example.ELibraryManager.service.PublisherService;
import com.example.ELibraryManager.exception.PublisherNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<?> getAllPublishers() {
        try {
            List<PublisherDTO> publishers = publisherService.getAllPublishers();
            return ResponseEntity.ok(publishers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting publishers");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisher(@PathVariable("id") Long id) {
        try {
            PublisherDTO publisher = publisherService.getPublisherById(id);
            return ResponseEntity.ok(publisher);
        } catch (PublisherNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting publisher");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPublisher(@RequestBody @Valid PublisherDTO publisherDTO) {
        try {
            PublisherDTO savedPublisher = publisherService.savePublisher(publisherDTO);
            return ResponseEntity.ok(savedPublisher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding publisher");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable("id") Long id) {
        try {
            publisherService.deletePublisher(id);
            return ResponseEntity.ok().build();
        } catch (PublisherNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting publisher");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable("id") Long id, @RequestBody @Valid PublisherDTO publisherDTO) {
        try {
            PublisherDTO updatedPublisher = publisherService.updatePublisher(id, publisherDTO);
            return ResponseEntity.ok(updatedPublisher);
        } catch (PublisherNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher with this id does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating publisher");
        }
    }
}
