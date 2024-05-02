package com.library.service.author.impl;

import com.library.aspect.Logged;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.service.author.AuthorService;
import com.library.service.cache.Cache;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final Cache authorCache;

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository, Cache cacheAuthor) {
    this.authorRepository = authorRepository;
    this.authorCache = cacheAuthor;
  }

  @Override
  @Logged
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  @Logged
  public Author getAuthorById(Long id) {
    if (authorRepository.findAuthorById(id) == null) {
      throw new NotFoundException("Author not found");
    }
    return authorRepository.findAuthorById(id);
  }

  @Override
  @Logged
  public Author getAuthorByName(String name) {
    Author author = (Author) authorCache.get(name);
    if (author != null) {
      log.info("(cache)Author find by name: {}", name);
      return author;
    }
    author = authorRepository.findAuthorByName(name);
    log.info("(rep)Author find by name:{}", name);
    authorCache.put(name, author);
    return author;
  }

  @Override
  @Logged
  public Author addAuthor(Author author) {
    authorCache.put(author.getName(), author);
    log.info("Saving author:{}", author);
    return authorRepository.save(author);
  }

  @Override
  @Logged
  public void addAuthors(List<Author> authors) {
    List<Author> savedAuthors = authorRepository.saveAll(authors);
    for (Author author:savedAuthors) {
      authorCache.put(author.getName(), author);
    }
    savedAuthors.forEach(author -> log.info("Book added {}", author));

  }

  @Override
  @Logged
  public Author updateAuthor(Author author) {
    Author existingAuthor = authorRepository.findAuthorByName(author.getName());
    if (existingAuthor != null) {
      existingAuthor.setName(author.getName());
      existingAuthor.setId(author.getId());
      existingAuthor.setBooks(author.getBooks());
      authorCache.put(existingAuthor.getName(), existingAuthor);
    }
    assert existingAuthor != null;
    log.info("Updating author");
    return authorRepository.save(author);
  }

  @Override
  @Logged
  public void deleteAuthorById(Long id) {
    Author author = getAuthorById(id);
    authorCache.remove(author.getName());
    authorRepository.deleteById(id);
    log.info("Removing author");
  }

}