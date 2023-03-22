package com.example.ELibraryManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title length should be in between 2 and 100 characters")
    private String title;

    @Column(name = "year_of_publishing")
    @Min(value = 0, message = "Book should be published later than 0")
    @Max(value = 2200, message = "Book should be published earlier than 2200")
    private Integer yearOfPublishing;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
