package com.example.ELibraryManager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherDTO {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String city;
    private List<BookDTO> books;
}
