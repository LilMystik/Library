package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="genres")
public class Genre {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy ="genres",cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JsonIgnore
    private List<Book> books;
}
