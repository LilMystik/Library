package com.library.controller;

import com.library.exception.MyExceptionHandler;
import com.library.model.Author;
import com.library.service.CounterService;
import com.library.service.author.AuthorService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@MyExceptionHandler
@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class    AuthorController {
  private AuthorService authorService;
  private static  final  String msg = "Counter : {}";
  private CounterService counterService;
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

  private synchronized void logCounter() {
    LOGGER.info(msg, counterService.incrementAndGet());
  }
  @GetMapping(value = "list")
  public List<Author> getAllAuthors() {
    logCounter();
    return authorService.getAllAuthors();
  }

  @GetMapping(value = "find/{authorId}", produces = "application/json")
  public Author getAuthorById(@PathVariable Long authorId) {
    logCounter();
    return authorService.getAuthorById(authorId);
  }

  @GetMapping(value = "findByName/{authorName}", produces = "application/json")
  public Author getAuthorByName(@PathVariable String authorName) {
    logCounter();
    return authorService.getAuthorByName(authorName);
  }

  @PostMapping("save")
  public Author addAuthor(@RequestBody Author author) {
    logCounter();
    return authorService.addAuthor(author);
  }

  @PostMapping("saveAuthors")
  public List<Author> addAuthors(@RequestBody List<Author> authors) {
    logCounter();
    authorService.addAuthors(authors);
    return authors;
  }

  @PutMapping("update")
  public Author updateAuthor(@RequestBody Author author) {
    logCounter();
    return authorService.updateAuthor(author);
  }

  @DeleteMapping("delete/{authorId}")
  public void deleteAuthor(@PathVariable Long authorId) {
    logCounter();
    authorService.deleteAuthorById(authorId);
  }
}