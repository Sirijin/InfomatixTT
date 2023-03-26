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
public class BookDTO {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private Integer yearOfPublishing;
    private List<Long> authorsIds;
    private Long publisherId;
}
