package com.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String title;
    private int listNumber;
    private int yearOfPublish;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="author_id")
    private Author author;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="book_genre",
    joinColumns = @JoinColumn(name="book_id"),
    inverseJoinColumns = @JoinColumn(name="genre_id"))
    private List<Genre> genres;
}
