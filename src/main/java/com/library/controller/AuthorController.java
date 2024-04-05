package com.library.controller;

import com.library.model.Author;
import com.library.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @GetMapping(value = "list")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = "find/{authorId}", produces = "application/json")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long authorId) {
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PostMapping("save")
        public ResponseEntity<Author> addAuthor(@RequestBody Author author)
        {
            return new ResponseEntity<>(authorService.addAuthor(author),HttpStatus.OK);
        }
    @PutMapping("update")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author)
    {
        return new ResponseEntity<>(authorService.updateAuthor(author),HttpStatus.OK);
    }
    @DeleteMapping("delete/{authorId}")
    public HttpStatus deleteAuthor(@PathVariable Long authorId)
    {
        authorService.deleteAuthorById(authorId);
        return HttpStatus.OK;
    }
}