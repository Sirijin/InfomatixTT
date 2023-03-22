package com.example.ELibraryManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@RequiredArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name length should be in between 2 and 100 characters")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
