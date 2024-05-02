package com.library.service.author;

import com.library.model.Author;
import java.util.List;

public interface AuthorService {

  List<Author> getAllAuthors();

  Author getAuthorById(Long id);

  Author getAuthorByName(String name);

  Author addAuthor(Author author);

  void addAuthors(List<Author> authors);

  Author updateAuthor(Author author);

  void deleteAuthorById(Long id);
}
