package com.example.ELibraryManager.controller;

import com.example.ELibraryManager.dto.PublisherDTO;
import com.example.ELibraryManager.exception.PublisherNotFoundException;
import com.example.ELibraryManager.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @Operation(summary = "Get all publishers", description = "Get all publishers", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully received list of all publishers", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublisherDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllPublishers() {
        try {
            List<PublisherDTO> publishers = publisherService.getAllPublishers();
            return ResponseEntity.ok(publishers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting publishers");
        }
    }

    @Operation(summary = "Get publisher by id", description = "Get publisher by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully received publisher", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublisherDTO.class))),
            @ApiResponse(responseCode = "404", description = "Publisher not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable("id") Long id) {
        try {
            PublisherDTO publisher = publisherService.getPublisherById(id);
            return ResponseEntity.ok(publisher);
        } catch (PublisherNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting publisher");
        }
    }

    @Operation(summary = "Add publisher", description = "Add publisher", responses = {
            @ApiResponse(responseCode = "200", description = "Publisher added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublisherDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/add")
    public ResponseEntity<?> addPublisher(@RequestBody @Valid PublisherDTO publisherDTO) {
        try {
            PublisherDTO savedPublisher = publisherService.savePublisher(publisherDTO);
            return ResponseEntity.ok(savedPublisher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding publisher");
        }
    }

    @Operation(summary = "Delete publisher by id", description = "Delete publisher by id", responses = {
            @ApiResponse(responseCode = "200", description = "Publisher deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublisherDTO.class))),
            @ApiResponse(responseCode = "404", description = "Publisher not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
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

    @Operation(summary = "Update publisher by id", description = "Update publisher by id", responses = {
            @ApiResponse(responseCode = "200", description = "Publisher updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublisherDTO.class))),
            @ApiResponse(responseCode = "404", description = "Publisher not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)))
    })
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
