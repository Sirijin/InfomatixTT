package com.example.ELibraryManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@RequiredArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name length should be in between 2 and 100 characters")
    private String name;

    @Column(name = "city")
    @NotEmpty(message = "City should not be empty")
    @Size(min = 2, max = 100, message = "City length should be in between 2 and 100 characters")
    private String city;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public String
    toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
