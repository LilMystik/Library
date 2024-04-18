package com.library.controller;

import com.library.exception.MyExceptionHandler;
import com.library.model.Author;
import com.library.service.author.AuthorService;
import java.util.List;
import lombok.AllArgsConstructor;
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

  @GetMapping(value = "list")
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @GetMapping(value = "find/{authorId}", produces = "application/json")
  public Author getAuthorById(@PathVariable Long authorId) {
    return authorService.getAuthorById(authorId);
  }

  @GetMapping(value = "findByName/{authorName}", produces = "application/json")
  public Author getAuthorByName(@PathVariable String authorName) {
    return authorService.getAuthorByName(authorName);
  }

  @PostMapping("save")
  public Author addAuthor(@RequestBody Author author) {
    return authorService.addAuthor(author);
  }

  @PostMapping("saveAuthors")
  public List<Author> addAuthors(@RequestBody List<Author> authors){
    return  authorService.addAuthors(authors);
  }

  @PutMapping("update")
  public Author updateAuthor(@RequestBody Author author) {
    return authorService.updateAuthor(author);
  }

  @DeleteMapping("delete/{authorId}")
  public void deleteAuthor(@PathVariable Long authorId) {
    authorService.deleteAuthorById(authorId);
  }
}